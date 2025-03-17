/*
Problem Description

Given a undirected weighted graph with A nodes labelled from 1 to A with M edges given
in a form of 2D-matrix B of size M * 3 where B[i][0] and B[i][1] denotes the two nodes
connected by an edge of weight B[i][2].

For each edge check whether it belongs to any of the possible minimum spanning tree or
not , return 1 if it belongs else return 0.

Return an one-dimensional binary array of size M denoting answer for each edge.

NOTE:

The graph may be disconnected in that case consider mst for each component.
No self-loops and no multiple edges present.
Answers in output array must be in order with the input array B output[i] must denote the
answer of edge B[i][0] to B[i][1].


Problem Constraints

1 <= A, M <= 3*105

1 <= B[i][0], B[i][1] <= A

1 <= B[i][1] <= 103

Input Format

The first argument given is an integer A representing the number of nodes in the graph.

The second argument given is an matrix B of size M x 3 which represents the M edges
such that there is a edge between node B[i][0] and node B[i][1] with weight B[i][2].

Output Format

Return an one-dimensional binary array of size M denoting answer for each edge.

Example Input

Input 1:

 A = 3
 B = [ [1, 2, 2]
       [1, 3, 2]
       [2, 3, 3]
     ]


Example Output

Output 1:

 [1, 1, 0]


Example Explanation

Explanation 1:

 Edge (1, 2) with weight 2 is included in the MST           1
                                                          /   \
                                                         2     3
 Edge (1, 3) with weight 2 is included in the same MST mentioned above.
 Edge (2,3) with weight 3 cannot be included in any of the mst possible.
 So we will return [1, 1, 0]
 */

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int A = 3;
        int[][] B = {
                {1, 2, 2},
                {1, 3, 2},
                {2, 3, 3}
        };
        int[] ans = solve(A,B);
        for(int i : ans) System.out.println(i);
    }
    static int[] parent, rank;
    // Union-Find ka find function (Path Compression)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // Union function with rank optimization
    static boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false;
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
    public static int[] solve(int A, int[][] B) {
        int M = B.length;
        int[] result = new int[M];

        // Step 1: Sort edges by weight
        Integer[] indexArr = new Integer[M];
        for (int i = 0; i < M; i++) indexArr[i] = i;

        Arrays.sort(indexArr, (i, j) -> B[i][2] - B[j][2]);

        // Step 2: Initialize Union-Find
        parent = new int[A + 1];
        rank = new int[A + 1];
        for (int i = 1; i <= A; i++) parent[i] = i;

        // Step 3: Process edges in groups of same weight
        int i = 0;
        while (i < M) {
            int weight = B[indexArr[i]][2];
            List<int[]> batchEdges = new ArrayList<>();
            List<Boolean> used = new ArrayList<>();

            // Collecting all edges with the same weight
            while (i < M && B[indexArr[i]][2] == weight) {
                batchEdges.add(new int[]{B[indexArr[i]][0], B[indexArr[i]][1], indexArr[i]});
                used.add(false);
                i++;
            }

            // Try adding these edges in MST
            for (int j = 0; j < batchEdges.size(); j++) {
                int u = batchEdges.get(j)[0], v = batchEdges.get(j)[1];
                if (find(u) != find(v)) {
                    used.set(j, true);  // Mark edge as used in MST
                }
            }

            // Actually union the edges to maintain MST structure
            for (int j = 0; j < batchEdges.size(); j++) {
                int u = batchEdges.get(j)[0], v = batchEdges.get(j)[1], idx = batchEdges.get(j)[2];
                if (used.get(j)) {
                    union(u, v);
                    result[idx] = 1;
                }
            }
        }
        return result;
    }
}
// TC : O(M log M) + O(M) ≈ O(M log M)
// SC : O(A + M)