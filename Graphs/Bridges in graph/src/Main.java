/*
Given an undirected connected Graph with V vertices (Numbered from 0 to V-1) and E edges.
An edge is represented [ai, bi] denoting that there is an edge from vertex ai to bi .
An edge is called a bridge if its removal makes some vertex unable to reach another vertex.

Return all bridges in the graph in any order.


Examples:




Input: V = 4, E = [ [0,1],[1,2],[2,0],[1,3] ]

Output: [ [1, 3] ]

Explanation: The edge [1, 3] is the critical edge because if we remove the edge the graph
will be divided into 2 components.


Input: V = 3, E = [[0,1],[1,2],[2,0]]

Result: []

Explanation: There no bridges in the graph.
 */
import java.util.*;

class Solution {
    private int timer = 1;

    /* Helper function to make DFS calls while
    checking for bridges in the graph */
    private void dfs(int node, int parent, int[] vis, List<Integer>[] adj,
                     int[] tin, int[] low, List<List<Integer>> bridges) {

        // Mark the node as visited
        vis[node] = 1;

        /* Time of insertion and the lowest time of
        insert for node will be the current time */
        tin[node] = low[node] = timer;

        // Increment the current time
        timer++;

        // Traverse all its neighbors
        for (int it : adj[node]) {

            // Skip the parent
            if (it == parent) continue;

            // If a neighbor is not visited
            if (vis[it] == 0) {

                // Make a recursive DFS call
                dfs(it, node, vis, adj, tin, low, bridges);

                /* Once the recursive DFS call returns, update
                the lowest time of insertion for the node */
                low[node] = Math.min(low[it], low[node]);

                /* If the lowest time of insertion of the
                node is found to be greater than the
                time of insertion of the neighbor */
                if (low[it] > tin[node]) {

                    // The edge represents a bridge
                    bridges.add(Arrays.asList(it, node));
                }
            }

            // Else if the neighbor is already visited
            else {
                // Update the lowest time of insertion of the node
                low[node] = Math.min(low[node], low[it]);
            }
        }
    }

    // Function to identify the bridges in a graph
    public List<List<Integer>> criticalConnections(int n,
                                                   List<List<Integer>> connections) {

        // Adjacency list
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Add all the edges to the adjacency list
        for (List<Integer> it : connections) {
            int u = it.get(0), v = it.get(1);
            adj[u].add(v);
            adj[v].add(u);
        }

        // Visited array
        int[] vis = new int[n];

        // To store the time of insertion (discovery time) of nodes
        int[] tin = new int[n];

        // To store the lowest time of insert of the nodes
        int[] low = new int[n];

        // To store the bridges of the graph
        List<List<Integer>> bridges = new ArrayList<>();

        // Start a DFS traversal from node 0 with its parent as -1
        dfs(0, -1, vis, adj, tin, low, bridges);

        // Return the computed result
        return bridges;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> E = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(1, 2),
                Arrays.asList(2, 0),
                Arrays.asList(1, 3)
        );

        // Creating an instance of Solution class
        Solution obj = new Solution();

        // Function call to identify the bridges in a graph
        List<List<Integer>> ans = obj.criticalConnections(V, E);

        System.out.println("The critical connections in the given graph are:");
        for (List<Integer> bridge : ans) {
            System.out.println(bridge.get(0) + " " + bridge.get(1));
        }
    }
}
/*
Complexity Analysis:
Time Complexity: O(V+E) (where E represents the number of edges in the graph)
A DFS traversal is performed which takes O(V+E) time.

Space Complexity: O(V) The algorithm uses two arrays to store the discovery
time and lowest time of insertion taking O(V) space. The list of bridges
returned will take O(E) space in worst-case when all the edges are bridges in the graph.
 */