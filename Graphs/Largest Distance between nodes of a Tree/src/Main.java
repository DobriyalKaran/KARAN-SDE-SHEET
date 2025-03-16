/*
Problem Description

Find largest distance Given an arbitrary unweighted rooted tree which consists of
N (2 <= N <= 40000) nodes.

The goal of the problem is to find largest distance between two nodes in a tree.
Distance between two nodes is a number of edges on a path between the nodes (there will
be a unique path between any pair of nodes since it is a tree).

The nodes will be numbered 0 through N - 1.

The tree is given as an array A, there is an edge between nodes A[i] and
i (0 <= i < N). Exactly one of the i's will have A[i] equal to -1, it will be root node.



Problem Constraints

2 <= |A| <= 40000



Input Format

First and only argument is vector A



Output Format

Return the length of the longest path



Example Input

Input 1:


A = [-1, 0]
Input 2:


A = [-1, 0, 0]


Example Output

Output 1:

 1
Output 2:

 2


Example Explanation

Explanation 1:

 Path is 0 -> 1.
Explanation 2:

 Path is 1 -> 0 -> 2.
 */


import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[] A = {-1, 0, 0};
        System.out.println(solve(A));
    }
    public static int solve(int[] A) {
        int N = A.length;
        List<List<Integer>> adj = new ArrayList<>();

        // Step 1: Graph Construct Karo (Adjacency List)
        int root = -1;
        for (int i = 0; i < N; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < N; i++) {
            if (A[i] == -1) root = i;  // Root node ko identify karna hai
            else {
                adj.get(i).add(A[i]);
                adj.get(A[i]).add(i);
            }
        }

        // Step 2: Pehla BFS (Root se farthest node find karo)
        int firstFarthest = bfs(root, adj, N);

        // Step 3: Dusra BFS (Diameter nikalna)
        int diameter = bfs(firstFarthest, adj, N);

        return diameter;
    }
    private static int bfs(int start, List<List<Integer>> adj, int N) {
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[N];
        Arrays.fill(dist, -1);

        q.add(start);
        dist[start] = 0;
        int farthestNode = start, maxDist = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int neighbor : adj.get(node)) {
                if (dist[neighbor] == -1) {  // Agar pehle visit nahi hua
                    dist[neighbor] = dist[node] + 1;
                    q.add(neighbor);
                    if (dist[neighbor] > maxDist) {
                        maxDist = dist[neighbor];
                        farthestNode = neighbor;
                    }
                }
            }
        }
        return maxDist;  // BFS ka final longest distance
    }
}
// TC : O(N)
// SC : O(N)