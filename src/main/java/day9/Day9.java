package day9;

import common.AdventOfCodeBase;

import java.util.ArrayList;
import java.util.List;

public class Day9 extends AdventOfCodeBase {

    public Day9(String inputFilename) {
        super(inputFilename);
    }

    @Override
    public String part1() {
        Space[][] grid = generateGrid();
        Position head = Position.of(500,500);
        Position tail = Position.of(500,500);
        Position lastHeadPosition = Position.of(50,50);;
        grid[500][500].visitHead();
        grid[500][500].visitTail();

        for( String line : lines) {
            String direction = line.split(" ")[0];
            int moveCount = Integer.valueOf(line.split(" ")[1]);

            for( int i = 0; i < moveCount; i++) {
                lastHeadPosition = Position.of(head.y, head.x);
                head.move(direction);
                grid[head.y][head.x].visitHead();
                if(shouldTailMove(head, tail)) {
                    tail = lastHeadPosition;
                    grid[tail.y][tail.x].visitTail();
                }
            }

        }


        return String.valueOf(countTailSpaceVisits(grid));
    }



    @Override
    public String part2() {
        Space[][] grid = generateGrid();
        List<Position> snake = new ArrayList<>();
        for(int i = 0; i<10; i++) {
            snake.add(Position.of(500,500));
        }

        grid[500][500].visitTail();

        for( String line : lines) {
            String direction = line.split(" ")[0];
            int moveCount = Integer.valueOf(line.split(" ")[1]);

            for( int i = 0; i < moveCount; i++) {
                Position head = snake.get(0);
                head.move(direction);

                for( int j = 1; j < 10; j++ ) {
                    Position upperBody = snake.get(j-1);
                    Position lowerBody = snake.get(j);

                    //If not touching
                    //Absolute value of the distance between horizontal or vertical distance is more than 1
                    if( Math.max(Math.abs(lowerBody.x - upperBody.x), Math.abs(lowerBody.y - upperBody.y))>1) {
                        //If horizontal difference
                        if(Math.abs(lowerBody.x - upperBody.x)>0) {
                            //Move towards upper body
                            if( upperBody.x > lowerBody.x ) {
                                lowerBody.x++;
                            } else {
                                lowerBody.x--;
                            }
                        }

                        //If vertical difference
                        if( Math.abs(lowerBody.y - upperBody.y) > 0 ) {
                            //Move towards upper body
                            if( upperBody.y > lowerBody.y ) {
                                lowerBody.y++;
                            } else {
                                lowerBody.y--;
                            }
                        }
                    }

                    //If tail, track visit
                    if( j == 9 ) {
                        grid[lowerBody.y][lowerBody.x].visitTail();
                    }

                }
            }
        }

        return String.valueOf(countTailSpaceVisits(grid));
    }

    private int countTailSpaceVisits(Space[][] grid) {
        int tailVisits = 0;
        for(int y=0; y<1000; y++) {
            for( int x=0; x<1000; x++) {
                if( grid[y][x].tailVisit) {
                    tailVisits++;
                }
            }
        }
        return tailVisits;
    }
    private boolean shouldTailMove(Position head, Position tail) {
        return head.distanceFrom(tail) > 1;
    }

    private boolean shouldMove(Position position1, Position position2) {
        return position1.distanceFrom(position2) > 1;
    }

    public Space[][] generateGrid() {
        Space[][] grid = new Space[1000][1000];
        for( int y=0; y<1000; y++) {
            for( int x=0; x<1000; x++) {
                grid[y][x] = new Space();
            }
        }
        return grid;
    }

    private static class Position {
        public int y;
        public int x;

        public int lastY;
        public int lastX;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
            this.lastY = y;
            this.lastX = x;
        }

        public Position(int y, int x, int lastY, int lastX) {
            this.y = y;
            this.x = x;
            this.lastY = lastY;
            this.lastX = lastX;
        }

        public void moveToLastPositionOf(Position position) {
            this.lastX = this.x;
            this.lastY = this.y;
            this.x = position.lastX;
            this.y = position.lastY;
        }
        public void move(String direction) {
            this.lastX = this.x;
            this.lastY = this.y;

            if( "R".equals(direction)){
                x++;
            } else if ("U".equals(direction)) {
                y--;
            } else if ("L".equals(direction)) {
                x--;
            } else if ("D".equals(direction)) {
                y++;
            }
        }

        public int distanceFrom(Position position) {
            //sqrt((x2-x1)^2 + (y2-y1)^2)
            return Double.valueOf(Math.sqrt(Math.pow((x- position.x),2)+Math.pow((y- position.y),2))).intValue();
        }

        public static Position of(int y, int x) {
            return new Position(y,x);
        };

        @Override
        public String toString() {
            return "Position{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

    private class Space {
        public boolean headVisit = false;
        public int headVisitCount = 0;

        public boolean tailVisit = false;
        public int tailVisitCount = 0;

        public void visitTail() {
            tailVisit = true;
            tailVisitCount++;
        }

        public void visitHead() {
            headVisit = true;
            headVisitCount++;
        }
    }
}

