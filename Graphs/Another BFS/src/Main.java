/*
Problem Description

Given a weighted undirected graph having A nodes, a source node C and destination node D.

Find the shortest distance from C to D and if it is impossible to reach node D from C then return -1.

You are expected to do it in Time Complexity of O(A + M).

Note:

There are no self-loops in the graph.

No multiple edges between two pair of vertices.

The graph may or may not be connected.

Nodes are Numbered from 0 to A-1.

Your solution will run on multiple testcases. If you are using global variables make sure to clear them.



Problem Constraints

1 <= A <= 105

0 <= B[i][0], B[i][1] < A

1 <= B[i][2] <= 2

0 <= C < A

0 <= D < A



Input Format

The first argument given is an integer A, representing the number of nodes.

The second argument given is the matrix B, where B[i][0] and B[i][1] are connected through
an edge of weight B[i][2].

The third argument given is an integer C, representing the source node.

The fourth argument is an integer D, representing the destination node.

Note: B[i][2] will be either 1 or 2.



Output Format

Return the shortest distance from C to D. If it is impossible to reach node D from C then return -1.



Example Input

Input 1:


A = 6
B = [   [2, 5, 1]
        [1, 3, 1]
        [0, 5, 2]
        [0, 2, 2]
        [1, 4, 1]
        [0, 1, 1] ]
C = 3
D = 2
Input 2:

A = 2
B = [   [0, 1, 1]
    ]
C = 0
D = 1


Example Output

Output 1:

 4
Output 2:

 1


Example Explanation

Explanation 1:

The path to be followed will be:
    3 -> 1 (Edge weight : 1)
    1 -> 0 (Edge weight : 1)
    0 -> 2 (Edge weight : 2)
Total length of path = 1 + 1 + 2 = 4.
Explanation 2:

 Path will be 0-> 1.

 */
import java.util.*;
class Pair {
    int node, dist;
    Pair(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}
public class Main {
    public static void main(String[] args) {
        int A = 6;
        int[][]B = {{2, 5, 1},
                {1, 3, 1},
                {0, 5, 2},
                {0, 2, 2},
                {1, 4, 1},
                {0, 1, 1}};
        int C = 3;
        int D = 2;
        System.out.println(solve(A,B,C,D));
    }
    public static int solve(int A, int[][] B, int C, int D) {
        ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
        for (int i = 0; i < A; i++) graph.add(new ArrayList<>());

        //  Graph ko adjacency list me store karna (u, v, w)
        for (int[] edge : B) {
            int u = edge[0], v = edge[1], w = edge[2];
            graph.get(u).add(new Pair(v, w));
            graph.get(v).add(new Pair(u, w)); // Since it's undirected
        }

        //  Deque use karenge instead of priority queue
        Deque<Pair> dq = new LinkedList<>();
        int[] dist = new int[A];
        Arrays.fill(dist, Integer.MAX_VALUE);

        //  Source node insert karo
        dq.addFirst(new Pair(C, 0));
        dist[C] = 0;

        //  BFS Traverse karo
        while (!dq.isEmpty()) {
            Pair curr = dq.pollFirst();
            int node = curr.node, currDist = curr.dist;

            for (Pair neighbor : graph.get(node)) {
                int nextNode = neighbor.node, weight = neighbor.dist;
                int newDist = currDist + weight;

                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    if (weight == 0) {
                        dq.addFirst(new Pair(nextNode, newDist)); //  Front me daalo (0 weight)
                    } else {
                        dq.addLast(new Pair(nextNode, newDist)); //  Back me daalo (1 weight)
                    }
                }
            }
        }
        return dist[D] == Integer.MAX_VALUE ? -1 : dist[D];
    }
}
// TC : O(A +M)
// SC : O(A + M)