/*
Problem Description

You are given an undirected unweighted graph consisting of A vertices and M edges given in a form of 2D Matrix B of size M x 2 where (B[i][0], B][i][1]) denotes two nodes connected by an edge.

You have to write a number on each vertex of the graph. Each number should be 1, 2 or 3. The graph becomes Poisonous if for each edge the sum of numbers on vertices connected by this edge is odd.

Calculate the number of possible ways to write numbers 1, 2 or 3 on vertices so the graph becomes poisonous. Since this number may be large, return it modulo 998244353.

NOTE:

Note that you have to write exactly one number on each vertex.
The graph does not have any self-loops or multiple edges.
Nodes are labelled from 1 to A.


Problem Constraints

1 <= A <= 3*105

0 <= M <= 3*105

1 <= B[i][0], B[i][1] <= A

B[i][0] != B[i][1]



Input Format

First argument is an integer A denoting the number of nodes.

Second argument is an 2D Matrix B of size M x 2 denoting the M edges.

Output Format

Return one integer denoting the number of possible ways to write numbers 1, 2 or 3 on the vertices of given graph so it becomes Poisonous . Since answer may be large, print it modulo 998244353.



Example Input

Input 1:

 A = 2
 B = [  [1, 2]
     ]
Input 2:

 A = 4
 B = [  [1, 2]
        [1, 3]
        [1, 4]
        [2, 3]
        [2, 4]
        [3, 4]
    ]


Example Output

Output 1:

 4
Output 2:

 0


Example Explanation

Explanation 1:

 There are 4 ways to make the graph poisonous. i.e, writing number on node 1 and 2 as,
    [1, 2] , [3, 2], [2, 1] or [2, 3] repsectively.
Explanation 2:

 There is no way to make the graph poisonous.
 */

import java.util.*;
public class Main {
    static final int MOD = 998244353;
    public static void main(String[] args) {
        int A = 2;
        int[][] B = {{1,2}};
        System.out.println(solve(A,B));
    }
    public static int solve(int A, int[][] B) {
        // Graph banane ka step
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : B) {
            int u = edge[0], v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // Bipartite Check aur Group Counting
        int[] color = new int[A + 1]; // 0 = unvisited, 1 = group 1, -1 = group 2
        Arrays.fill(color, 0);
        long result = 1; // Final Answer

        for (int i = 1; i <= A; i++) {
            if (color[i] == 0) { // Agar node unvisited hai toh BFS start karo
                int[] count = bipartiteBFS(graph, color, i);
                if (count == null) return 0; // Graph bipartite nahi hai
                long ways = (power(2, count[0]) + power(2, count[1])) % MOD;
                result = (result * ways) % MOD;
            }
        }

        return (int) result;
    }
    // BFS to check Bipartiteness and Count Nodes
    private static int[] bipartiteBFS(List<List<Integer>> graph, int[] color, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1; // Start with color 1

        int count0 = 1, count1 = 0; // Groups ka count

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int curColor = color[node];

            for (int neighbor : graph.get(node)) {
                if (color[neighbor] == 0) { // Agar unvisited hai toh opposite color do
                    color[neighbor] = -curColor;
                    if (color[neighbor] == 1) count0++;
                    else count1++;
                    queue.offer(neighbor);
                } else if (color[neighbor] == curColor) {
                    return null; // Not Bipartite
                }
            }
        }
        return new int[]{count0, count1};
    }
    // Fast Power Function for (2^n % MOD)
    private static long power(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}
// TC : O(A log A)
// SC : O(A + M)