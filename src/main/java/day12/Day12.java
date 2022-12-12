package day12;

import common.AdventOfCodeBase;
import common.Part;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 extends AdventOfCodeBase {

    public Day12(String inputFilename, Part part) {
        super(inputFilename, part);
    }


    @Override
    public String run() {
        String[][] grid = parseGrid();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int currentHeight = 1;
        Map<String,Integer> heightMap = new HashMap<>();
        heightMap.put("S",1);
        heightMap.put("E",26);
        for( Character c : alphabet.toCharArray()) {
            heightMap.put(String.valueOf(c),currentHeight);
            currentHeight++;
        }

        // No of vertices
        int v = Arrays.stream(grid).map(Array::getLength).reduce(0,Integer::sum);

        // Adjacency list for storing which vertices are connected
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // Creating graph given in the above diagram.
        // add_edge function takes adjacency list, source
        // and destination vertex as argument and forms
        // an edge between them.
        int starting = 0;
        int ending = 0;

        for( int y = 0; y < grid.length; y++) {
            for( int x = 0; x < grid[0].length; x++) {
                int verticePosition = getVerticePosition(y,x,grid);
                String value = grid[y][x];
                Integer valueHeight = heightMap.get(value);
                if( "S".equals(value)) {
                    System.out.println("Starting verticePosition = " + verticePosition);
                    starting = verticePosition;
                } else if( "E".equals(value)) {
                    System.out.println("Ending verticePosition = " + verticePosition);
                    ending = verticePosition;
                    continue;
                }
                System.out.println("value = " + value);
                System.out.println("valueHeight = " + valueHeight);

                //Check Up
                if( y > 0 ) {
                    int localY = y-1;
                    int localX = x;
                    int localVerticePosition = getVerticePosition(localY, localX, grid);
                    String localValue = grid[localY][localX];
                    Integer localValueHeight = heightMap.get(localValue);
                    if( canTravel(valueHeight, localValueHeight)){
                        System.out.println("Add Up");
                        addEdge(adj, verticePosition, localVerticePosition);
                    }
                }
                //Check Down
                if( y < grid.length-2){
                    int localY = y+1;
                    int localX = x;
                    int localVerticePosition = getVerticePosition(localY, localX, grid);
                    String localValue = grid[localY][localX];
                    Integer localValueHeight = heightMap.get(localValue);
                    if( canTravel(valueHeight, localValueHeight)){
                        System.out.println("Add Down");
                        addEdge(adj, verticePosition, localVerticePosition);
                    }
                }
                //Check Left
                if( x > 0 ){
                    int localY = y;
                    int localX = x-1;
                    int localVerticePosition = getVerticePosition(localY, localX, grid);
                    String localValue = grid[localY][localX];
                    Integer localValueHeight = heightMap.get(localValue);
                    if( canTravel(valueHeight, localValueHeight)){
                        System.out.println("Add Left");
                        addEdge(adj, verticePosition, localVerticePosition);
                    }
                }
                //Check Right
                if( x < grid[0].length-2 ){
                    int localY = y;
                    int localX = x+1;
                    int localVerticePosition = getVerticePosition(localY, localX, grid);
                    String localValue = grid[localY][localX];
                    Integer localValueHeight = heightMap.get(localValue);
                    if( canTravel(valueHeight, localValueHeight)){
                        System.out.println("Add Right");
                        addEdge(adj, verticePosition, localVerticePosition);
                    }
                }
            }

        }



        return String.valueOf(printShortestDistance(adj, starting, ending, v));
    }

    private boolean canTravel(int valueHeight, int localValueHeight) {
        return localValueHeight <= (valueHeight+1);
    }

    private int getVerticePosition(int y, int x, String[][] grid) {
        return y*grid[0].length + x;
    }


    private String[][] parseGrid() {
        int width = lines.get(0).length();
        int height = lines.size();
        String[][] grid = new String[height][width];
        int y = 0;  int x = 0;

        for (String line : lines) {
            System.out.println("line = " + line);
            for( Character c : line.toCharArray()) {
                grid[y][x] = String.valueOf(c);
                x++;
            }
            y++;
            x=0;
        }
        return grid;
    }


    // function to form edge between two vertices
    // source and dest
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j)
    {
        System.out.println("addEdge: i:"+i+" j:"+j);
        adj.get(i).add(j);
//        adj.get(j).add(i);
    }

    // function to print the shortest distance and path
    // between source vertex and destination vertex
    private static int printShortestDistance(ArrayList<ArrayList<Integer>> adj, int s, int dest, int v)
    {
        // predecessor[i] array stores predecessor of
        // i and distance array stores distance of i
        // from s
        int pred[] = new int[v];
        int dist[] = new int[v];

        if (BFS(adj, s, dest, v, pred, dist) == false) {
            System.out.println("Given source and destination" +
                    "are not connected");
            return 0;
        }

        // LinkedList to store path
        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dest;
        path.add(crawl);
        while (pred[crawl] != -1) {
            path.add(pred[crawl]);
            crawl = pred[crawl];
        }

        // Print distance
        System.out.println("Shortest path length is: " + dist[dest]);

        // Print path
        System.out.println("Path is ::");
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i) + " ");
        }
        return dist[dest];
    }

    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                               int dest, int v, int pred[], int dist[])
    {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<>();

        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];

        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }

        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));

                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }

}

