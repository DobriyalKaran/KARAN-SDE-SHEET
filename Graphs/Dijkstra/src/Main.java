/*
Problem Description

Given a weighted undirected graph having A nodes and M weighted edges, and a source node C.

You have to find an integer array D of size A such that:

D[i]: Shortest distance from the C node to node i.
If node i is not reachable from C then -1.
Note:

There are no self-loops in the graph.
There are no multiple edges between two pairs of vertices.
The graph may or may not be connected.
Nodes are numbered from 0 to A-1.
Your solution will run on multiple test cases. If you are using global variables, make sure
to clear them.



Problem Constraints

1 <= A <= 1e5

0 <= B[i][0],B[i][1] < A

0 <= B[i][2] <= 1e3

0 <= C < A



Input Format

The first argument is an integer A, representing the number of nodes in the graph.
The second argument is a matrix B of size M x 3, where each row represents an edge in the
graph. The three columns of each row denote the source node B[i][0], the destination node B[i][1], and the weight of the edge B[i][2].
The third argument is an integer C, representing the source node for which the shortest
distance to all other nodes needs to be found.


Output Format

Return the integer array D.

Example Input

Input 1:

A = 6
B = [   [0, 4, 9]
        [3, 4, 6]
        [1, 2, 1]
        [2, 5, 1]
        [2, 4, 5]
        [0, 3, 7]
        [0, 1, 1]
        [4, 5, 7]
        [0, 5, 1] ]
C = 4
Input 2:

A = 5
B = [   [0, 3, 4]
        [2, 3, 3]
        [0, 1, 9]
        [3, 4, 10]
        [1, 3, 8]  ]
C = 4


Example Output

Output 1:

D = [7, 6, 5, 6, 0, 6]
Output 2:

D = [14, 18, 13, 10, 0]


Example Explanation

Explanation 1:

 All Paths can be considered from the node C to get shortest path
Explanation 2:

 All Paths can be considered from the node C to get shortest path
 */

import java.util.*;
class Pair {
    int weight, node;
    Pair(int weight, int node) {
        this.weight = weight;
        this.node = node;
    }
}
public class Main {
    public static void main(String[] args) {
        int A = 6;
        int[][] B = {
                {0, 4, 9},
                {3, 4, 6},
                {1, 2, 1},
                {2, 5, 1},
                {2, 4, 5},
                {0, 3, 7},
                {0, 1, 1},
                {4, 5, 7},
                {0, 5, 1}
        };
        int C = 4;
        int[] ans = solve(A,B,C);
        for(int i : ans) System.out.println(i);
    }
    public static int[] solve(int A, int[][] B, int C) {
        // Step 1: Graph ka adjacency list banana
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : B) {
            int u = edge[0], v = edge[1], weight = edge[2];
            adj.get(u).add(new Pair(weight, v));
            adj.get(v).add(new Pair(weight, u)); // Undirected graph hai
        }

        // Step 2: Distance array initialize karna
        int[] dist = new int[A];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[C] = 0;

        // Step 3: Min Heap (Priority Queue) use karna
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        pq.add(new Pair(0, C)); // Start with source node

        while (!pq.isEmpty()) {
            Pair current = pq.poll();
            int currDist = current.weight;
            int node = current.node;

            // Agar current distance zyada hai to continue (Outdated Entry)
            if (currDist > dist[node]) continue;

            // Step 4: Neighbors process karo
            for (Pair neighbor : adj.get(node)) {
                int nextNode = neighbor.node;
                int edgeWeight = neighbor.weight;

                // Agar naye distance se improvement hota hai to update karo
                if (dist[node] + edgeWeight < dist[nextNode]) {
                    dist[nextNode] = dist[node] + edgeWeight;
                    pq.add(new Pair(dist[nextNode], nextNode));
                }
            }
        }

        // Step 5: Jo nodes reachable nahi hain, unko -1 set karna
        for (int i = 0; i < A; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}
// TC : O(M Log A)
// SC : O(A + M)