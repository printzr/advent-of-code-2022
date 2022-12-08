package day8;

import utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Day8 {

    public int runPart1(String inputFilename) throws Exception {
        int visibleTrees = 0;
        List<String> lines = FileUtils.getInputLines(inputFilename);
        int[][] grid = parseGrid(lines);

        //Add edges
        visibleTrees = visibleTrees + (2*grid.length) + (2*grid[0].length) - 4;

        //Add interior
        visibleTrees = visibleTrees + countVisibileInterior(grid);

        return visibleTrees;
    }

    public int runPart2(String inputFilename) throws Exception {
        int maxViewingScore = 0;
        List<String> lines = FileUtils.getInputLines(inputFilename);
        int[][] grid = parseGrid(lines);

        for( int i = 1; i < grid[0].length-1; i++) {
            for( int j = 1; j<grid.length-1; j++) {

                int up = countVisibileTreesUp(grid, i, j);
                int down = countVisibileTreesDown(grid, i, j);
                int left = countVisibileTreesLeft(grid, i, j);
                int right = countVisibileTreesRight(grid, i, j);
                int viewingScore = up * down * left * right;

                if( viewingScore > maxViewingScore) {
                    maxViewingScore = viewingScore;
                }
            }
        }

        return maxViewingScore;
    }

    public int countVisibileTreesUp(int[][] grid, int x, int y) {
        int countTrees = 0;
        int height = grid[y][x];
        for( int i = y-1; i >= 0; i--) {
            int compareHeight = grid[i][x];
            if( compareHeight < height ) {
                countTrees++;
            } else if( compareHeight == height ) {
                countTrees++;
                break;
            } else {
                break;
            }
        }
        return countTrees == 0 ? 1 : countTrees;
    }

    public int countVisibileTreesLeft(int[][] grid, int x, int y) {
        int countTrees = 0;
        int height = grid[y][x];
        for( int i = x-1; i >= 0; i--) {
            int compareHeight = grid[y][i];
            if( compareHeight < height ) {
                countTrees++;
            } else if( compareHeight == height ) {
                countTrees++;
                break;
            } else {
                break;
            }
        }
        return countTrees == 0 ? 1 : countTrees;
    }

    public int countVisibileTreesDown(int[][] grid, int x, int y) {
        int countTrees = 0;
        int height = grid[y][x];
        for( int i = y+1; i < grid.length; i++) {
            int compareHeight = grid[i][x];
            if( compareHeight < height ) {
                countTrees++;
            } else if( compareHeight == height ) {
                countTrees++;
                break;
            } else {
                break;
            }
        }
        return countTrees == 0 ? 1 : countTrees;
    }

    public int countVisibileTreesRight(int[][] grid, int x, int y) {
        int countTrees = 0;
        int height = grid[y][x];
        for( int i = x+1; i < grid.length; i++) {
            int compareHeight = grid[y][i];
            if( compareHeight < height ) {
                countTrees++;
            } else if( compareHeight >= height ) {
                countTrees++;
                break;
            }
        }
        return countTrees == 0 ? 1 : countTrees;
    }

    private int countVisibileInterior(int[][] grid) {
        int visibleInterior = 0;

        for( int i = 1; i < grid.length-1; i++) {
            for( int j = 1; j<grid[0].length-1; j++) {
                if( isVisibile(grid, i, j)) {
                    visibleInterior++;
                }
            }
        }

        return visibleInterior;
    }

    private boolean isVisibile(int[][] grid, int x, int y) {
        return isVisibileUp(grid, x, y)
                || isVisibileDown(grid, x, y)
                || isVisibileLeft(grid, x, y)
                || isVisibileRight(grid,x,y);
    }

    private boolean isVisibileUp(int[][] grid, int x, int y) {
        int height = grid[x][y];
        for( int i = y-1; i >= 0; i--) {
            int compareHeight = grid[x][i];
            if( compareHeight >= height ) {
                return false;
            }
        }
        return true;
    }

    private boolean isVisibileDown(int[][] grid, int x, int y) {
        int height = grid[x][y];
        for( int i = y+1; i < grid.length; i++) {
            int compareHeight = grid[x][i];
            if( compareHeight >= height ) {
                return false;
            }
        }
        return true;
    }

    private boolean isVisibileLeft(int[][] grid, int x, int y) {
        int height = grid[x][y];
        for( int i = x-1; i >= 0; i--) {
            int compareHeight = grid[i][y];
            if( compareHeight >= height ) {
                return false;
            }
        }
        return true;
    }

    private boolean isVisibileRight(int[][] grid, int x, int y) {
        int height = grid[x][y];
        for( int i = x+1; i < grid.length; i++) {
            int compareHeight = grid[i][y];
            if( compareHeight >= height ) {
                return false;
            }
        }
        return true;
    }

    private int[][] parseGrid(List<String> lines) {
        int width = lines.get(0).length();
        int height = lines.size();
        int[][] grid = new int [width][height];
        int i = 0;  int j = 0;

        for (String line : lines) {
            for( Character c : line.toCharArray()) {
                grid[i][j] = Integer.parseInt(c.toString());
                j++;
            }
            i++;
            j=0;
        }
        return grid;
    }


}

