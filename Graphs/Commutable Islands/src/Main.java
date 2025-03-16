/*
Problem Description

There are A islands and there are M bridges connecting them. Each bridge has some cost attached
to it.

We need to find bridges with minimal cost such that all islands are connected.

It is guaranteed that input data will contain at least one possible scenario in which all
islands are connected with each other.



Problem Constraints

1 <= A, M <= 6*104

1 <= B[i][0], B[i][1] <= A

1 <= B[i][2] <= 103



Input Format

The first argument contains an integer, A, representing the number of islands.

The second argument contains an 2-d integer matrix, B, of size M x 3 where Island
B[i][0] and B[i][1] are connected using a bridge of cost B[i][2].

Output Format

Return an integer representing the minimal cost required.

Example Input

Input 1:

 A = 4
 B = [  [1, 2, 1]
        [2, 3, 4]
        [1, 4, 3]
        [4, 3, 2]
        [1, 3, 10]  ]
Input 2:

 A = 4
 B = [  [1, 2, 1]
        [2, 3, 2]
        [3, 4, 4]
        [1, 4, 3]   ]


Example Output

Output 1:

 6
Output 2:

 6


Example Explanation

Explanation 1:

We can choose bridges (1, 2, 1), (1, 4, 3) and (4, 3, 2), where the total
cost incurred will be (1 + 3 + 2) = 6.
Explanation 2:

We can choose bridges (1, 2, 1), (2, 3, 2) and (1, 4, 3), where the total cost
incurred will be (1 + 2 + 3) = 6.
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
        int A = 4;
        int[][] B = {{1, 2, 1},
        {2, 3, 4},
        {1, 4, 3},
        {4, 3, 2},
        {1, 3, 10}};
        System.out.println(solve(A,B));
    }
    public static int solve(int A, int[][] B) {
        // Step 1: Graph ko adjacency list ke form mein convert karo
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Graph ka adjacency list banaye
        for (int[] bridge : B) {
            int u = bridge[0];
            int v = bridge[1];
            int cost = bridge[2];
            adj.get(u).add(new Pair(cost, v));
            adj.get(v).add(new Pair(cost, u));
        }

        // Step 3: Minimum Spanning Tree ke liye Prim's Algorithm
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        boolean[] visited = new boolean[A + 1];
        pq.add(new Pair(0, 1)); // Starting Node = 1, weight = 0

        int minCost = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < A) {
            Pair current = pq.poll();
            int weight = current.weight;
            int node = current.node;

            // Agar node already visited hai to continue
            if (visited[node]) continue;

            // Step 4: Node ko MST mein add karo
            visited[node] = true;
            minCost += weight;
            edgesUsed++;

            // Step 5: Saare neighbors ko MinHeap mein daalo jo abhi tak MST mein nahi aaye hain
            for (Pair neighbor : adj.get(node)) {
                if (!visited[neighbor.node]) {
                    pq.add(new Pair(neighbor.weight, neighbor.node));
                }
            }
        }

        return minCost;
    }
}
// TC : O(MlogA) * O(M \log A) *  O(MlogA)
// SC :  O(A+M)O(A + M)O(A+M)