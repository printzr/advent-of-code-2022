package day14;

import common.AdventOfCodeBase;
import common.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Day14 extends AdventOfCodeBase {

    public Day14(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    private static final String ROCK = "#";
    private static final String SAND = "O";
    private static final Point SAND_INPUT_POINT = Point.of(0,500);

    @Override
    public String run() {
        int answer = 0;
        String[][] grid = parseInput();
        boolean sandResting = true;
        Point nextSandPoint = getNextSandRestingPoint(grid);
        while(nextSandPoint != null){
            answer++;
            grid[nextSandPoint.y][nextSandPoint.x] = SAND;
            nextSandPoint = getNextSandRestingPoint(grid);
            if(isPart2() && nextSandPoint.y==0 && nextSandPoint.x == 500) {
                nextSandPoint = null;
                answer++;
            }
        }
        return ""+answer;
    }
    private Point getNextSandRestingPoint(String[][] grid) {
        boolean canMove = true;
        int x = SAND_INPUT_POINT.x;
        int y = SAND_INPUT_POINT.y;
        while(canMove) {
            if( y == grid.length-1) {
                //Bottom reached
                return null;
            } else if( !isSpaceFull(grid[y+1][x])) { //Down
                y = y+1;
            } else if (!isSpaceFull(grid[y+1][x-1])) {//Down left
                y = y+1;
                x = x-1;
            } else if (!isSpaceFull(grid[y+1][x+1])) { //Down right
                y = y+1;
                x = x+1;
            } else {
                canMove = false;
            }
        }
        return Point.of(y,x);
    }

    private boolean isSpaceFull(String value) {
        return ROCK.equals(value) || SAND.equals(value);
    }

    private String[][] parseInput() {
        List<List<Point>> inputRows = new ArrayList<>();
        for( String line : lines ) {
            List<Point> points = new ArrayList<>();
            String[] parts = line.split(" -> ");
            for( int i = 0; i<parts.length; i++) {
                String[] pointParts = parts[i].split(",");
                Point point = new Point(Integer.valueOf(pointParts[1]),Integer.valueOf(pointParts[0]));
                points.add(point);
            }
            inputRows.add(points);
        }

        List<Point> flatPointList = inputRows.stream() .flatMap(l -> l.stream()) .collect(Collectors.toList());

        int maxX = flatPointList.stream().mapToInt(Point::getX).max().getAsInt();
        int maxY = flatPointList.stream().mapToInt(Point::getY).max().getAsInt();

        if( isPart2() ){
            maxY = maxY+2;
        }

        String[][] grid = new String[maxY+1][maxX+1000];
        for( List<Point> points : inputRows ) {
            for( int i = 0; i < points.size()-1; i++) {
                drawRock(grid, points.get(i), points.get(i+1));
            }
        }
        if( isPart2() ){
            drawRock(grid, Point.of(maxY, 0), Point.of(maxY,maxX+999));
        }
        return grid;
    }

    private void drawRock(String[][] grid, Point start, Point end) {
        if( start.x == end.x ) {
            //Draw vertical
            int s = Math.min(start.y, end.y);
            int e = Math.max(start.y, end.y);
            for( int i = s; i<=e; i++) {
                grid[i][start.x] = ROCK;
            }
        } else {
            //Draw horizontal
            int s = Math.min(start.x, end.x);
            int e = Math.max(start.x, end.x);
            for( int i = s; i<=e; i++) {
                grid[start.y][i] = ROCK;
            }
        }
    }

    public static class Point {
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        int x;
        int y;

        public Point(int y, int x) {
            this.x = x;
            this.y = y;
        }
        public static Point of(int y, int x) {
            return new Point(y,x);
        }

        public String toString() {
            return "Point(y="+y+",x="+x+")";
        }
    }

}

