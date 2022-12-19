package day18;

import common.AdventOfCodeBase;
import common.Part;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Day18 extends AdventOfCodeBase {

    public Day18(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    @Override
    public String run() {
        long answer = 0;

        //Prefill grid with 0
        int[][][] grid = new int[23][23][23];
        for( int[][] a : grid )
            for( int[] b : a )
                Arrays.fill(b,0);

        for( String line : lines ) {
            String[] parts = line.split(",");
            int x = Integer.valueOf(parts[0]);
            int y = Integer.valueOf(parts[1]);
            int z = Integer.valueOf(parts[2]);
            grid[z][y][x] = 1;
        }

        /*
            Y
            Y
            Y
              XXX
         */

        for( int z = 0; z < grid.length; z++) {
            for( int y = 0; y < grid[0].length; y++) {
                for( int x = 0; x < grid[0][0].length; x++) {
                    int value = grid[z][y][x];
                    if( value == 1 ) {
                        //y-1
                        if( !filled(x,y-1,z, grid)) {
                            answer++;
                        }
                        //y+1
                        if( !filled(x,y+1,z, grid)) {
                            answer++;
                        }
                        //x-1
                        if( !filled(x-1,y,z, grid)) {
                            answer++;
                        }
                        //x+1
                        if( !filled(x+1,y,z, grid)) {
                            answer++;
                        }
                        //z-1
                        if( !filled(x,y,z-1, grid)) {
                            answer++;
                        }
                        //z+1
                        if( !filled(x,y,z+1, grid)) {
                            answer++;
                        }
                    }
                }
            }
        }

        if( isPart2() ) {
            //Find air pockets
//            int airPockets = 0;
//            for( int z = 0; z < grid.length; z++) {
//                for (int y = 0; y < grid[0].length; y++) {
//                    for (int x = 0; x < grid[0][0].length; x++) {
//                        int value = grid[z][y][x];
//                        if (value == 0) {
//                            if (filled(x, y - 1, z, grid) //y-1
//                                    && filled(x, y + 1, z, grid)//y+1
//                                    && filled(x - 1, y, z, grid)  //x-1
//                                    && filled(x + 1, y, z, grid) //x+1
//                                    && filled(x, y, z - 1, grid) //z-1
//                                    && filled(x, y, z + 1, grid) //z+1
//                            ) {
//                                airPockets++;
//                            }
//                        }
//                    }
//                }
//            }
//            System.out.println("airPockets = " + airPockets);
//            answer = answer - (airPockets*6);

            //Expanding Blocks
            int[][][] exteriorGrid = new int[23][23][23];
            for( int[][] a : exteriorGrid )
                for( int[] b : a )
                    Arrays.fill(b,0);

            int minX = 99;
            int minY = 99;
            int minZ = 99;
            for( int z = 0; z < grid.length; z++) {
                for (int y = 0; y < grid[0].length; y++) {
                    for (int x = 0; x < grid[0][0].length; x++) {
                        int value = grid[z][y][x];
                        if( value == 1 ) {
                            if( minX > x ) {
                                minX = x;
                            }
                            if( minY > y ) {
                                minY = y;
                            }
                            if( minZ > z) {
                                minZ = z;
                            }
                        }
                    }
                }
            }
            //Expand from 0,0,0
            exteriorGrid[0][0][0] = 1;

        }

        return "" + answer;
    }

    private List<Block> getNeighbors(int x, int y, int z) {
        List<Block> blocks = new ArrayList<>();
        if( x < 23 ) {
            blocks.add(new Block(x+1,y,z));
        }
        if( x > 0 ){
            blocks.add(new Block(x-1, y,z));
        }
        if( y < 23 ) {
            blocks.add(new Block(x,y+1,z));
        }
        if( y > 0 ){
            blocks.add(new Block(x, y-1,z));
        }
        if( z < 23 ) {
            blocks.add(new Block(x,y,z+1));
        }
        if( z > 0 ){
            blocks.add(new Block(z, y,z-1));
        }
        return blocks;
    }

    private record Block(int x, int y, int z){}

    private boolean filled(int x, int y, int z, int[][][] grid) {
        try {
            return grid[z][y][x] == 1;
        } catch (Exception ex) {
            System.out.println("ex = " + ex);
            return false;
        }
    }

}
