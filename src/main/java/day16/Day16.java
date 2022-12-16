package day16;

import common.AdventOfCodeBase;
import common.Part;
import org.apache.commons.lang3.StringUtils;

import java.util.*;


public class Day16 extends AdventOfCodeBase {

    public Day16(String inputFilename, Part part) {
        super(inputFilename, part);
    }

    private static HashMap<String,Integer> keys = new HashMap<>();
    private static HashMap<String, Valve> valves = new HashMap<>();
    private static List<Valve> path = new ArrayList<>();

    @Override
    public String run() {
        int answer = 0;

        // Create a graph given in the above diagram.
        // Here vertex numbers are 0, 1, 2, 3, 4, 5 with
        // following mappings:
        // 0=r, 1=s, 2=t, 3=x, 4=y, 5=z
        int graphSize = valves.entrySet().stream()
                .map(v->v.getValue().paths.length+1)
                .reduce(0,Integer::sum);
        Graph g = new Graph(graphSize);
        for( String line : lines ) {
            line = line.replace("Valve ","");
            line = line.replace(" has flow rate=",",");
            line = line.replace("; tunnels lead to valves ","~");
            line = line.replace("; tunnel leads to valve ","~");

            String[] parts = line.split("~");
            String[] valveParts = parts[0].split(",");
            String valveKey = valveParts[0];
            int valveWeight = Integer.valueOf(valveParts[1]);
            String[] paths = parts[1].split(",");
            for( int i = 0; i < paths.length; i++ ) {
                paths[i] = paths[i].trim();
            }

            valves.put(valveKey,Valve.of(valveKey, valveWeight, paths));
        }

        for( Map.Entry<String, Valve> entry : valves.entrySet() ) {
            String key = entry.getKey();
            Valve valve = entry.getValue();
            for( int i = 0; i < valve.paths.length; i++ ){
                Valve dest = valves.get(valve.paths[i]);
                g.addEdge(valve.numericKey, dest.numericKey, valve.rate);
//                g.addEdge(dest.numericKey, valve.numericKey, dest.rate);
            }
        }

//        Graph g = new Graph(6);
//        g.addEdge(0, 1, 5);
//        g.addEdge(0, 2, 3);
//        g.addEdge(1, 3, 6);
//        g.addEdge(1, 2, 2);
//        g.addEdge(2, 4, 4);
//        g.addEdge(2, 5, 2);
//        g.addEdge(2, 3, 7);
//        g.addEdge(3, 5, 1);
//        g.addEdge(3, 4, -1);
//        g.addEdge(4, 5, -2);

        int s = 1;
        System.out.print("Following are longest distances from source vertex "+ s + " \n" );
        g.longestPath(s);

        System.out.print("Path:");
        for( Valve v : path ) {
            System.out.print(v.key + " ");
        }

        return "" + answer;
    }

    private static class Valve {
        String key;
        int rate;
        Integer numericKey;
        String[] paths;


        public Valve(String key, int rate, String[] paths) {
            this.key = key;
            this.rate = rate;
            this.paths = paths;
            this.numericKey = getKeyValue(key);
        }

        public static Valve of(String key, int rate, String[] paths) {
            return new Valve(key, rate, paths);
        }
    }

    private static Integer getKeyValue(String key) {
        if( !keys.containsKey(key) ) {
            keys.put(key,keys.size()+1);
        }
        return keys.get(key);
    }

    private static Valve getValue(int numericKey) {
        return valves.entrySet().stream()
                .filter(v->v.getValue().numericKey == numericKey)
                .findFirst()
                .get().getValue();
    }

    // Graph is represented using adjacency list. Every
    // node of adjacency list contains vertex number of
    // the vertex to which edge connects. It also
    // contains weight of the edge
    static class AdjListNode {
        int v;
        int weight;

        AdjListNode(int _v, int _w)
        {
            v = _v;
            weight = _w;
        }
        int getV() { return v; }
        int getWeight() { return weight; }
    }

    // Class to represent a graph using adjacency list
    // representation
    static class Graph {
        int V; // No. of vertices'

        // Pointer to an array containing adjacency lists
        ArrayList<ArrayList<AdjListNode>> adj;

        Graph(int V) // Constructor
        {
            this.V = V;
            adj = new ArrayList<ArrayList<AdjListNode>>(V);

            for(int i = 0; i < V; i++){
                adj.add(new ArrayList<AdjListNode>());
            }
        }

        void addEdge(int u, int v, int weight)
        {
            AdjListNode node = new AdjListNode(v, weight);
            adj.get(u).add(node); // Add v to u's list
        }

        // A recursive function used by longestPath. See below
        // link for details
        // https:// www.geeksforgeeks.org/topological-sorting/
        void topologicalSortUtil(int v, boolean visited[],
                                 Stack<Integer> stack)
        {
            // Mark the current node as visited
            visited[v] = true;

            // Recur for all the vertices adjacent to this vertex
            for (int i = 0; i<adj.get(v).size(); i++) {
                AdjListNode node = adj.get(v).get(i);
                if (!visited[node.getV()])
                    topologicalSortUtil(node.getV(), visited, stack);
            }

            // Push current vertex to stack which stores topological
            // sort
            stack.push(v);
        }

        // The function to find longest distances from a given vertex.
        // It uses recursive topologicalSortUtil() to get topological
        // sorting.
        void longestPath(int s)
        {
            Stack<Integer> stack = new Stack<Integer>();
            int dist[] = new int[V];


            // Mark all the vertices as not visited
            boolean visited[] = new boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper function to store Topological
            // Sort starting from all vertices one by one
            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);

            // Initialize distances to all vertices as infinite and
            // distance to source as 0
            for (int i = 0; i < V; i++)
                dist[i] = Integer.MIN_VALUE;

            dist[s] = 0;

            // Process vertices in topological order
            while (stack.isEmpty() == false)
            {

                // Get the next vertex from topological order
                int u = stack.peek();
                stack.pop();

                // Update distances of all adjacent vertices ;
                if (dist[u] != Integer.MIN_VALUE)
                {
                    for (int i = 0; i<adj.get(u).size(); i++)
                    {
                        AdjListNode node = adj.get(u).get(i);
                        if (dist[node.getV()] < dist[u] + node.getWeight()) {
                            dist[node.getV()] = dist[u] + node.getWeight();
                            path.add(getValue(node.getV()));
                        }
                    }
                }
            }

            // Print the calculated longest distances
            for (int i = 0; i < V; i++)
                if(dist[i] == Integer.MIN_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(dist[i] + " ");
        }
    }
}
