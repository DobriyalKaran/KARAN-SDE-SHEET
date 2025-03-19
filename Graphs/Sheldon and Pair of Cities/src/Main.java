/*
Problem Description

Sheldon lives in a country with A cities (numbered from 1 to A) and B bidirectional roads.

Roads are denoted by integer array D, E and F of size M, where D[i] and E[i] denotes the cities
and F[i] denotes the distance between the cities.

Now he has many lectures to give in the city and is running short of time, so he asked you C queries,
for each query i, find the shortest distance between city G[i] and H[i].

If the two cities are not connected then the distance between them is assumed to be -1.

Problem Constraints

1 <= A <= 200

1 <= B <= 200000

1 <= C <= 100000

1 <= F[i] <= 1000000

1 <= D[i], E[i], G[i], H[i] <= A



Input Format

First argument is an integer A.
Seocnd argument is an integer B.
Third argument is an integer C.
Fourth argument is an integer array D.
Fifth argument is an integer array E.
Sixth argument is an integer array F.
Seventh argument is an integer array G.
Eight argument is an integer array H.



Output Format

Return an integer array of size C, for each query denoting the shortest distance between the
given two vertices.
If the two vertices are not connected then output -1.


Example Input

Input 1:

 A = 4
 B = 6
 C = 2
 D = [1, 2, 3, 2, 4, 3]
 E = [2, 3, 4, 4, 1, 1]
 F = [4, 1, 1, 1, 1, 1]
 G = [1, 1]
 H = [2, 3]
Input 2:

 A = 3
 B = 3
 C = 2
 D = [1, 2, 1]
 E = [2, 3, 3]
 F = [3, 1, 1]
 G = [2, 1]
 H = [3, 2]


Example Output

Output 1:

 [2, 1]
Output 2:

 [1, 2]


Example Explanation

Explanation 1:

 Distance between (1,2) will 2 if we take path 1->4->2.
 Distance between (1,3) will 1 if we take path 1->3.
Explanation 2:

 Distance between (2,3) will 1 if we take path 1->3.
 Distance between (1,2) will 2 if we take path 1->3->2.
 */

import java.util.*;
public class Main {
    static final int INF = 1000000000;
    public static void main(String[] args) {
        int A = 4;
        int B = 6;
        int C = 2;

        int[] D = {1, 2, 3, 2, 4, 3};
        int[] E = {2, 3, 4, 4, 1, 1};
        int[] F = {4, 1, 1, 1, 1, 1};
        int[] G = {1, 1};
        int[] H = {2, 3};
        int[] ans = solve(A,B,C,D,E,F,G,H);
        for(int i : ans) System.out.println(i);
    }
    public static int[] solve(int A, int B, int C, int[] D, int[] E, int[] F, int[] G, int[] H) {
        // Step 1: Initialize distance matrix
        int[][] dist = new int[A][A];
        for (int i = 0; i < A; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0; // Distance to itself is 0
        }

        // Step 2: Fill direct edges
        for (int i = 0; i < B; i++) {
            int u = D[i] - 1, v = E[i] - 1, w = F[i];
            dist[u][v] = Math.min(dist[u][v], w);
            dist[v][u] = Math.min(dist[v][u], w);
        }

        // Step 3: Apply Floyd-Warshall Algorithm
        for (int k = 0; k < A; k++) {
            for (int i = 0; i < A; i++) {
                for (int j = 0; j < A; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Step 4: Answer Queries
        int[] result = new int[C];
        for (int i = 0; i < C; i++) {
            int u = G[i] - 1, v = H[i] - 1;
            result[i] = (dist[u][v] == INF) ? -1 : dist[u][v];
        }

        return result;
    }
}
// TC : O(A³ + B + C)
// SC : O(A² + B + C)