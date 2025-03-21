/*
Problem Description

Given an undirected graph having A nodes labelled from 1 to A with M edges given in a
form of matrix B of size M x 2 where (B[i][0], B[i][1]) represents two nodes B[i][0] and
B[i][1] connected by an edge.

Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.

NOTE:

The cycle must contain atleast three nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make
sure to clear them.


Problem Constraints

1 <= A, M <= 3*105

1 <= B[i][0], B[i][1] <= A

Input Format

The first argument given is an integer A representing the number of nodes in the graph.

The second argument given is an matrix B of size M x 2 which represents the M edges such
that there is a edge between node B[i][0] and node B[i][1].

Output Format

Return 1 if cycle is present else return 0.

Example Input

Input 1:

 A = 5
 B = [  [1. 2]
        [1, 3]
        [2, 3]
        [1, 4]
        [4, 5]
     ]
Input 2:

 A = 3
 B = [  [1. 2]
        [1, 3]
     ]


Example Output

Output 1:

 1
Output 2:

 0


Example Explanation

Explanation 1:

 There is a cycle in the graph i.e 1 -> 2 -> 3 -> 1 so we will return 1
Explanation 2:

 No cycle present in the graph so we will return 0.
 */

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int A = 3;
        int[][] B = {{1,2}, {1,3}};
        System.out.println(solve(A,B));
    }
    public static int solve(int A, int[][] B) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            adj.add(new ArrayList<>());
        }

        // Building adjacency list
        for (int[] edge : B) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[A + 1];

        for (int i = 1; i <= A; i++) {
            if (!visited[i]) {
                if (dfs(i, -1, visited, adj, 0)) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static boolean dfs(int node, int parent, boolean[] visited, List<List<Integer>> adj, int depth) {
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, node, visited, adj, depth + 1)) {
                    return true;
                }
            } else if (neighbor != parent && depth >= 2) { // Found a cycle with at least 3 nodes
                return true;
            }
        }
        return false;
    }
}
// TC : O(A + M)
// SC : O(A + M)