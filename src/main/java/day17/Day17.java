package day17;

import common.AdventOfCodeBase;
import common.Part;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;


public class Day17 extends AdventOfCodeBase {

    public Day17(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    private static final String X = "X";
    private static final String DASH = "-";
    private static final String DOT = ".";
    private static final String PIPE = "|";

    private static final int PRINT_SIZE = 20;

    public long iterations = 2022;

    public static boolean DEBUG = false;

    @Override
    public String run() {
        long answer = 0;
        int gridCopies = 0;
        String input = lines.get(0);
        char[] jetPattern = input.toCharArray();
        List<Rock> rockOrder = List.of(Rock.a(), Rock.b(), Rock.c(), Rock.d(), Rock.e());
        int height = 5000;
        int arrayChompValue = 4000;
        String[][] grid = new String[height][9];
        Arrays.fill(grid[height-1],DASH);

        //Add new spaces
        for( int y = 0; y<height-1; y++) {
            grid[y]=newRow();
        }

        int rockTypePosition = 0;
        int jetPatternPosition = 0;
        for( int i = 0; i<iterations; i++) {
//            System.out.println("i = " + i);
            Rock rock = rockOrder.get(rockTypePosition);
//            System.out.println(rock);
//            System.out.println(gridAndRockToString(grid, rock, PRINT_SIZE));
            //Determine floor
            int floorPosition = getFloor(grid);



            //Place rock
            rock.position = new Point(3,floorPosition-8);

            while( rock.canMoveDown(grid) ) {
//                System.out.println("jetPatternPosition = " + jetPatternPosition);
//                System.out.println(gridAndRockToString(grid, rock, PRINT_SIZE));

                //Fall
//                System.out.println("try down");
                if( rock.canMoveDown(grid) ) {
//                    System.out.println("down");
                    rock.moveDown();
//                    System.out.println(gridAndRockToString(grid, rock, PRINT_SIZE));
                }

                //Gas Jet
//                System.out.println("jetPatternPosition = " + jetPatternPosition);
                char gasJet = jetPattern[jetPatternPosition];
//                System.out.println("gasJet = " + gasJet);
                if( '<' == gasJet ) {
//                    System.out.println("try left");
                    if(rock.canMoveLeft(grid)) {
//                        System.out.println("left");
                        rock.moveLeft();
//                        System.out.println(gridAndRockToString(grid, rock, PRINT_SIZE));
                    }
                } else {
//                    System.out.println("try right");
                    if(rock.canMoveRight(grid)) {
//                        System.out.println("right");
                        rock.moveRight();
//                        System.out.println(gridAndRockToString(grid, rock, PRINT_SIZE));
                    }
                }
                //Set next jetPatternPosition
                jetPatternPosition = getNexJetPosition(jetPatternPosition, jetPattern);
            }

            //Fill rock
            rock.fillRockToGrid(grid);

            //Set next rock type
            rockTypePosition++;
            if( rockTypePosition > rockOrder.size() -1 ) {
                rockTypePosition = 0;
            }

            //If grid height > 50 chomp and store
            int currentRockHeight = (height-getFloor(grid)-1);
//            System.out.println("currentRockHeight = " + currentRockHeight);
            if( currentRockHeight > arrayChompValue ){
                gridCopies++;
                int floor = getFloor(grid);
                String[][] copyGrid = new String[height][9];
                for( int y = 0; y<1000L; y++) {
                    copyGrid[y]=newRow();
                }
                int gridPosition = 1000;
                for( int y = floor; y<(floor+4000); y++ ) {
                    copyGrid[gridPosition] = grid[y];
                    gridPosition++;
                }
                grid = copyGrid;
            }


        }
        System.out.println("gridCopies = " + gridCopies);
        System.out.println("getFloor(grid) = " + getFloor(grid));
        System.out.println("height = " + height);
        System.out.println("(height-getFloor(grid)-1) = " + (height-getFloor(grid)-1));
        
        Long copiesLong = Long.valueOf(gridCopies);
        Long copiesHeight = copiesLong*1000L;
        return "" + (copiesHeight+Long.valueOf((height-getFloor(grid)-1)));
    }

    private int getNexJetPosition( int currentJetPosition, char[] jetPattern) {
        int newJetPosition = currentJetPosition;

        newJetPosition++;
        if( newJetPosition > jetPattern.length-1 ) {
            newJetPosition = 0;
        }
        return newJetPosition;
    }

    private int getFloor(String[][] grid) {
        for( int y = 0; y < grid.length; y++) {
            Optional<String> rock = Arrays.stream(grid[y]).filter(s-> X.equals(s) || DASH.equals(s)).findFirst();
            if( rock.isPresent() ) {
//                System.out.println("y = " + y);
                return y;
            }
        }
        return 0;
    }

    public static class Rock {
        String[][] shape;
        Point position;

        public Rock() {
            this.shape = new String[4][4];
            this.position = new Point(0,0);
            for (String[] row: shape) {
                Arrays.fill(row, DOT);
            }
        }

        public static Rock a() {
            Rock rock = new Rock();
            Arrays.fill(rock.shape[3], X);
            return rock;
        }

        public static Rock b() {
            Rock rock = new Rock();
            rock.shape[1][1]=X;
            rock.shape[2][0]=X;
            rock.shape[2][1]=X;
            rock.shape[2][2]=X;
            rock.shape[3][1]=X;
            return rock;
        }

        public static Rock c() {
            Rock rock = new Rock();
            rock.shape[1][2]=X;
            rock.shape[2][2]=X;
            rock.shape[3][0]=X;
            rock.shape[3][1]=X;
            rock.shape[3][2]=X;
            return rock;
        }

        public static Rock d() {
            Rock rock = new Rock();
            rock.shape[0][0]=X;
            rock.shape[1][0]=X;
            rock.shape[2][0]=X;
            rock.shape[3][0]=X;
            return rock;
        }

        public static Rock e() {
            Rock rock = new Rock();
            rock.shape[3][0]=X;
            rock.shape[3][1]=X;
            rock.shape[2][0]=X;
            rock.shape[2][1]=X;
            return rock;
        }

        public boolean canMoveDown(String[][] grid) {
            for( int y = 3; y >= 0; y-- ) {
                for( int x = 0; x <=3; x++ ) {
                    String rockSpace = shape[y][x];
                    if( X.equals(rockSpace) ){
                        String downSpace = grid[position.y+y+1][position.x +x];
                        if( isBlocked(downSpace)) {
                            return false;
                        }
                    }


                }
            }
            return true;
        }

        public boolean canMoveRight(String[][] grid) {
            for( int y = 0; y<=3; y++ ) {
                for( int x = 3; x>=0; x-- ){
                    String rockSpace = shape[y][x];
                    if(X.equals(rockSpace) ) {
                        String right = grid[position.y+y][position.x +x+1];
                        if( isBlocked(right) ){
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public boolean canMoveLeft(String[][] grid) {
            for( int y = 0; y<=3; y++ ) {
                for( int x = 0; x<=3; x++ ){
                    String rockSpace = shape[y][x];
                    if(X.equals(rockSpace) ) {
                        String left = grid[position.y+y][position.x+x-1];
                        if( isBlocked(left) ){
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public void moveLeft() {
            this.position.moveLeft();
        }

        public void moveRight() {
            this.position.moveRight();
        }

        public void moveDown() {
            this.position.moveDown();
        }

        private boolean isBlocked(String square) {
            return PIPE.equals(square) || X.equals(square) || DASH.equals(square);
        }



        public void fillRockToGrid(String[][] grid) {
            for( int y = 0; y<grid.length; y++) {
                for( int x = 0; x<9; x++) {
                    String movingRockSpace = rockAtPosition(x,y,grid);
                    if(X.equals(movingRockSpace)) {
                        grid[y][x] = X;
                    }
                }
            }
        }

        @Override
        public String toString() {
            String result = "Rock{ x:"+position.x+" y:"+position.y+"\n";
            result += gridToString(shape);
            result += "}";
            return result;
        }

        public String rockAtPosition(int xIn, int yIn, String[][] grid) {
            if( position.y <= yIn && yIn <= (position.y+3)) {
                if( position.x <= xIn && xIn <= position.x+3) {
                    return shape[yIn- position.y][xIn - position.x];
                }
            }
            return "";
        }

    }

    public static String gridToString(String[][] grid) {
        String result = "";
        for(String[] row : grid) {
            result += Arrays.stream(row).collect(Collectors.joining());
            result += "\n";
        }
        return result;
    }

    public static String gridToString(String[][] grid, int lastRows) {
        String result = "";
        for( int y = (grid.length-lastRows); y<grid.length; y++) {
            result += Arrays.stream(grid[y]).collect(Collectors.joining());
            result += "\n";
        }
        return result;
    }

    public static String gridAndRockToString(String[][] grid, Rock rock, int lastRows) {
        if( !DEBUG){
            return "";
        }
        String result = "";
        for( int y = (grid.length-lastRows); y<grid.length; y++) {
            for( int x = 0; x<9; x++) {
                String movingRockSpace = rock.rockAtPosition(x,y,grid);
                if(StringUtils.isNotEmpty(movingRockSpace) && X.equals(movingRockSpace)) {
                    result+="@";
                } else {
                    result+=grid[y][x];
                }
            }
            result += "\n";
        }
        return result;
    }



    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void moveLeft() {
            this.x = x-1;
        }

        public void moveRight() {
            this.x = x +1;
        }

        public void moveDown() {
            this.y = y +1;
        }
    }

    private String[] newRow() {
        return new String[] { PIPE, DOT, DOT, DOT, DOT, DOT, DOT, DOT, PIPE };
    }

}
