import java.util.Arrays;
import java.util.LinkedList;

class GraphColoring {
    private int V; // Number of vertices
    private LinkedList<Integer>[] adj; // Adjacency List

    // Constructor
    GraphColoring(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // Assigns colors (starting from 0) to all vertices and
    // prints the assignment of colors
    void greedyColoring() {
        int result[] = new int[V];

        // Assign the first color to first vertex
        result[0] = 0;

        // Initialize remaining V-1 vertices as not colored
        Arrays.fill(result, 1, V, -1);

        // A temporary array to store the available colors. True
        // value of available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        boolean available[] = new boolean[V];
        Arrays.fill(available, true);

        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < V; u++) {
            // Process all adjacent vertices and flag their colors
            // as unavailable
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i);
                if (result[v] != -1)
                    available[result[v]] = false;
            }

            // Find the first available color
            int cr;
            for (cr = 0; cr < V; cr++)
                if (available[cr])
                    break;

            result[u] = cr; // Assign the found color

            // Reset the values back to true for the next iteration
            Arrays.fill(available, true);
        }

        // print the result
        for (int u = 0; u < V; u++)
            System.out.println("Vertex " + u + " --->  Color " + result[u]);
    }

    public static void main(String[] args) {
        GraphColoring g1 = new GraphColoring(5);
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        System.out.println("Coloring of graph 1");
        g1.greedyColoring();
    }
}
