/*
Problem Description

Given a directed graph having A nodes. A matrix B of size M x 2 is given which represents
the M edges such that there is an edge directed from node B[i][0] to node B[i][1].

Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.

NOTE:

The cycle must contain atleast two nodes.
There are no self-loops in the graph.
There are no multiple edges between two nodes.
The graph may or may not be connected.
Nodes are numbered from 1 to A.
Your solution will run on multiple test cases. If you are using global variables make
sure to clear them.


Problem Constraints

2 <= A <= 105

1 <= M <= min(200000,A*(A-1))

1 <= B[i][0], B[i][1] <= A

Input Format

The first argument given is an integer A representing the number of nodes in the graph.

The second argument given a matrix B of size M x 2 which represents the M edges such
that there is a edge directed from node B[i][0] to node B[i][1].

Output Format

Return 1 if cycle is present else return 0.

Example Input

Input 1:

 A = 5
 B = [  [1, 2]
        [4, 1]
        [2, 4]
        [3, 4]
        [5, 2]
        [1, 3] ]
Input 2:

 A = 5
 B = [  [1, 2]
        [2, 3]
        [3, 4]
        [4, 5] ]


Example Output

Output 1:

 1
Output 2:

 0


Example Explanation

Explanation 1:

 The given graph contain cycle 1 -> 3 -> 4 -> 1 or the cycle 1 -> 2 -> 4 -> 1
Explanation 2:

 The given graph doesn't contain any cycle.
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int A = 5;
        int[][] B = {
                {1,2},
                {2,3},
                {3,4},
                {4,5}
        };
        System.out.println(solve(A,B));
    }
    //DFS - Depth FIRST SEARCH
    public static int solve(int A, int[][] B) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < B.length; i++) {
            int u = B[i][0]; // Source node
            int v = B[i][1]; // Destination node
            graph.get(u).add(v);
        }

        boolean[] visited = new boolean[A + 1];      // Yeh batayega ki node visit ho chuki hai ya nahi
        boolean[] pathVisited = new boolean[A + 1];  // Yeh recursion stack track karega

        for (int i = 1; i <= A; i++) {
            if (!visited[i]) { // Agar node abhi tak visit nahi hui
                if (dfs(i, graph, visited, pathVisited)) {
                    return 1; // Cycle detected!
                }
            }
        }
        return 0; // Agar cycle nahi mili toh return 0
    }
    public static boolean dfs(int node, ArrayList<ArrayList<Integer>> graph, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true;
        pathVisited[node] = true; // Yeh track karega ki DFS recursion stack me kaun kaun hai

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (dfs(neighbor, graph, visited, pathVisited)) {
                    return true; // Cycle detected
                }
            }
            else if (pathVisited[neighbor]) {
                return true; // Cycle detected
            }
        }

        pathVisited[node] = false; // Ye step important hai!
        return false; // Cycle nahi mili
    }
}
// TC : O(A + M)
// SC : O(A + M)