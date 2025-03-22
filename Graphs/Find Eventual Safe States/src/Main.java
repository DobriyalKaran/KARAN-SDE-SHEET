/*
There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is
represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes
adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every
possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted
in ascending order.



Example 1:

Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:

Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.


Constraints:

n == graph.length
1 <= n <= 104
0 <= graph[i].length <= n
0 <= graph[i][j] <= n - 1
graph[i] is sorted in a strictly increasing order.
The graph may contain self-loops.
The number of edges in the graph will be in the range [1, 4 * 104].
 */

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][] graph = {
                {1, 2},   // Node 0 → {1, 2}
                {2, 3},   // Node 1 → {2, 3}
                {5},      // Node 2 → {5}
                {0},      // Node 3 → {0}
                {5},      // Node 4 → {5}
                {},       // Node 5 → {}
                {}        // Node 6 → {}
        };
        List<Integer> ans = eventualSafeNodes(graph);
        for(int i : ans) System.out.println(i);
    }
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        // Make an Adj List.
        int n = graph.length;

        // Convert the graph to adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            for (int neighbor : graph[i]) {
                adj.get(i).add(neighbor);
            }
        }
        boolean[] vis = new boolean[n];
        boolean[] pathVis = new boolean[n];
        boolean[] checkNode = new boolean[n];
        List<Integer> safeNodes = new ArrayList<>();
        for(int i=0; i<n; i++)
        {
            if(!vis[i])
            {
                dfs(i,vis,pathVis,checkNode,adj);
            }
        }
        for(int i=0;i<checkNode.length; i++)
        {
            if(checkNode[i]) safeNodes.add(i);
        }
        return safeNodes;
    }
    public static boolean dfs(int node, boolean[] vis, boolean[] pathVis, boolean[] checkNode, List<List<Integer>> adj)
    {
        vis[node] = true;
        pathVis[node] = true;
        checkNode[node] = false;

        for(int nei : adj.get(node))
        {
            if(!vis[nei])
            {
                if(dfs(nei,vis,pathVis,checkNode,adj)) return true;
            }
            else if(pathVis[nei]) return true;
        }

        pathVis[node] = false;
        checkNode[node] = true;
        return false;
    }
}
// TC: O(V + E)
// SC: O(V + E)