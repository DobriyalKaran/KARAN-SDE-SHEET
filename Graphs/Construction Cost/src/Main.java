/*
Problem Description

Flipkart has ‘A’ local distribution centers located across a large metropolitan city.
Each distribution center needs to be interconnected through roads to facilitate efficient
movement of goods. The cost of constructing a road between any two distribution centers is
represented by the weight of the edge connecting them.

Given a graph with ‘A’ nodes representing the distribution centers and C weighted edges
representing the possible roads between them, your task is to find the minimum total cost
of constructing roads such that every distribution center can be reached from the first
distribution center.

Cost Calculation:
The cost of constructing the roads is the sum of the weights of the edges selected for
the construction.

NOTE: Return the answer modulo 10^9+7 as the answer can be large.



Problem Constraints

1 <= A <= 100000
0 <= C <= 100000
1 <= B[i][0], B[i][1] <= N
1 <= B[i][2] <= 109



Input Format

First argument is an integer A.
Second argument is a 2-D integer array B of size C×3 denoting edges. B[i][0]and B[i][1]are the distribution centers connected by the ith edge with construction cost B[i][2].



Output Format

Return an integer denoting the minimum construction cost.



Example Input

Input 1:

A = 3
B = [   [1, 2, 14]
        [2, 3, 7]
        [3, 1, 2]   ]
Input 2:

A = 3
B = [   [1, 2, 20]
        [2, 3, 17]  ]


Example Output

Output 1:

9
Output 2:

37


Example Explanation

Explanation 1:
To connect the distribution centers, we can select only two roads: from center 2 to center 3
(cost 7) and
from center 3 to center 1 (cost 2). This allows us to reach the first distribution center from
both center 2 and center 3,
resulting in a total construction cost of 7+2=9
Explanation 2:
In this case, we must construct both roads (from center 1 to center 2 and from center 2 to center 3)
to ensure that all distribution centers are reachable from the first center. The total cost
will be 20+17=37
 */

import java.util.*;
public class Main {
    static int MOD = 1000000007;
    public static void main(String[] args) {
        int A = 3;
        int[][] B = {
                {1, 2, 14},
                {2, 3, 7},
                {3, 1, 2}
        };
        System.out.println(solve(A,B));
    }
    public static int solve(int A, int[][] B) {
        Arrays.sort(B, (a, b) -> Integer.compare(a[2], b[2]));

        // Step 2: Initialize DSU (Disjoint Set Union)
        int[] parent = new int[A + 1];
        int[] rank = new int[A + 1];
        for (int i = 1; i <= A; i++) parent[i] = i; // Initially, each node is its own parent

        int minCost = 0, edgesUsed = 0;

        // Step 3: Process edges in sorted order
        for (int[] edge : B) {
            int u = edge[0], v = edge[1], cost = edge[2];

            if (union(u, v, parent, rank)) { // If union is successful (no cycle)
                minCost = (minCost + cost) % MOD;
                edgesUsed++;

                // MST is complete when we have exactly (A - 1) edges
                if (edgesUsed == A - 1) break;
            }
        }

        return minCost;
    }
    // Find function with path compression
    private static int find(int node, int[] parent) {
        if (parent[node] != node)
            parent[node] = find(parent[node], parent); // Path compression
        return parent[node];
    }
    // Union function with rank-based merging
    private static boolean union(int u, int v, int[] parent, int[] rank) {
        int rootU = find(u, parent);
        int rootV = find(v, parent);

        if (rootU == rootV) return false; // Already in the same set, don't form a cycle

        // Union by rank
        if (rank[rootU] > rank[rootV]) {
            parent[rootV] = rootU;
        } else if (rank[rootU] < rank[rootV]) {
            parent[rootU] = rootV;
        } else {
            parent[rootV] = rootU;
            rank[rootU]++;
        }
        return true;
    }
}
/*
Time Complexity (TC) & Space Complexity (SC)
1️⃣ Using Kruskal’s Algorithm (Best for Edge List Representation)
Time Complexity:

Sorting edges: O(C log C) (where C is the number of edges)
Union-Find operations (with path compression and rank): O(C * α(A)),
where α(A) is the inverse Ackermann function, which is almost constant (≈ O(1)).
Total: O(C log C)
Space Complexity:

Edge list storage: O(C)
Disjoint Set (Union-Find) storage: O(A)
Total: O(A + C)
2️⃣ Using Prim’s Algorithm (Best for Adjacency List Representation)
Time Complexity:

Priority Queue operations: O(C log A)
Total: O(C log A)
Space Complexity:

Adjacency list: O(A + C)
Min-Heap (Priority Queue): O(A)
Total: O(A + C)
Which Algorithm is Better?
Use Kruskal’s when edges are given as a list (like in this problem) → O(C log C).
Use Prim’s when the graph is dense (C ≈ A²) → O(C log A).
 */