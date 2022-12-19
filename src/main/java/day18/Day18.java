package day18;

import common.AdventOfCodeBase;
import common.Part;
import org.apache.commons.lang3.StringUtils;

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

                        if( isPart2() ) {

                        }
                    }
                }
            }
        }

        return "" + answer;
    }

    private boolean filled(int x, int y, int z, int[][][] grid) {
        try {
            return grid[z][y][x] == 1;
        } catch (Exception ex) {
            System.out.println("ex = " + ex);
            return false;
        }
    }

}
