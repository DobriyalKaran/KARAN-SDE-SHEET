/*
Problem Description

Given an integer A, representing number of vertices in a graph.

Also you are given a matrix of integers B of size N x 3 where N represents number of Edges
in a Graph and Triplet (B[i][0], B[i][1], B[i][2]) implies there is an undirected edge
between B[i][0] and B[i][1] and weight of that edge is B[i][2].

Find and return the weight of minimum weighted cycle and if there is no cycle return -1 instead.

NOTE: Graph may contain self loops but does not have duplicate edges.

Problem Constraints

1 <= A <= 1000

1 <= B[i][0], B[i][1] <= A

1 <= B[i][2] <= 100000


Input Format

The first argument given is the integer A.

The second argument given is the integer matrix B.



Output Format

Return the weight of minimum weighted cycle and if there is no cycle return -1 instead.



Example Input

Input 1:

 A = 4
 B = [  [1 ,2 ,2]
        [2 ,3 ,3]
        [3 ,4 ,1]
        [4 ,1 ,4]
        [1 ,3 ,15]  ]
Input 2:

 A = 3
 B = [  [1 ,2 ,2]
        [2 ,3 ,3]  ]


Example Output

Output 1:

 10
Output 2:

 -1


Example Explanation

Explanation 1:

 Given graph forms 3 cycles
 1. 1 ---> 2 ---> 3 ---> 4 ---> 1 weight = 10
 2. 1 ---> 2 ---> 3 ---> 1 weight = 20
 3. 1 ---> 3---> 4 ---> 1 weight = 20
 so answer would be 10.
Explanation 2:

 Given graph forms 0 cycles so return -1.
 */

import java.util.*;
 class Pair {
    int node, weight;
    Pair(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}
public class Main {
    public static void main(String[] args) {
        int A = 4;
        int[][] B = {
                {1, 2, 2},
                {2, 3, 3},
                {3, 4, 1},
                {4, 1, 4},
                {1, 3, 15}
        };
        System.out.println(solve(A,B));
    }
    public static int solve(int A, int[][] B) {
        // Step 1: Build Graph (Adjacency List)
        List<List<Pair>> graph = new ArrayList<>();
        for (int i = 0; i <= A; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : B) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new Pair(v, w));
            graph.get(v).add(new Pair(u, w));
        }

        int minCycleWeight = Integer.MAX_VALUE;

        // Step 2: Iterate over each edge (u, v, w)
        for (int[] edge : B) {
            int u = edge[0], v = edge[1], weight = edge[2];

            // Step 3: Remove the edge temporarily
            graph.get(u).removeIf(p -> p.node == v && p.weight == weight);
            graph.get(v).removeIf(p -> p.node == u && p.weight == weight);

            // Step 4: Find shortest path from u to v using Dijkstra
            int shortestPath = dijkstra(A, graph, u, v);

            // Step 5: If path exists, update minCycleWeight
            if (shortestPath != Integer.MAX_VALUE) {
                minCycleWeight = Math.min(minCycleWeight, shortestPath + weight);
            }

            // Step 6: Restore the edge
            graph.get(u).add(new Pair(v, weight));
            graph.get(v).add(new Pair(u, weight));
        }

        return (minCycleWeight == Integer.MAX_VALUE) ? -1 : minCycleWeight;
    }

    // Dijkstra's Algorithm to find shortest path from 'src' to 'target'
    private static int dijkstra(int A, List<List<Pair>> graph, int src, int target) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.weight));
        int[] dist = new int[A + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node, d = curr.weight;
            if (node == target) return d; // Early exit if we reach the target

            for (Pair neighbor : graph.get(node)) {
                int nextNode = neighbor.node, edgeWeight = neighbor.weight;
                if (dist[nextNode] > d + edgeWeight) {
                    dist[nextNode] = d + edgeWeight;
                    pq.add(new Pair(nextNode, dist[nextNode]));
                }
            }
        }

        return Integer.MAX_VALUE; // No path found
    }
}
// TC: O(N∗AlogA)
// SC : O(A+E)