/*
Problem Description

Given a matrix of integers A of size N x N, where A[i][j] represents the weight of directed
edge from i to j (i ---> j).

If i == j, A[i][j] = 0, and if there is no directed edge from vertex i to vertex j, A[i][j] = -1.

Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j.

If there is no possible path from vertex i to vertex j , B[i][j] = -1

Note: Rows are numbered from top to bottom and columns are numbered from left to right.



Problem Constraints

1 <= N <= 200
-1 <= A[i][j] <= 1000000


Input Format

The first and only argument given is the integer matrix A.


Output Format

Return a matrix B of size N x N where B[i][j] = shortest path from vertex i to vertex j
If there is no possible path from vertex i to vertex j, B[i][j] = -1.


Example Input

A = [ [0 , 50 , 39]
          [-1 , 0 , 1]
          [-1 , 10 , 0] ]


Example Output

[ [0 , 49 , 39 ]
   [-1 , 0 , -1 ]
   [-1 , 10 , 0 ] ]


Example Explanation

Shortest Path from 1 to 2 would be 1 ---> 3 ---> 2 and not directly from 1 to 2,
All remaining distances remains same.
 */


public class Main {
    public static void main(String[] args) {
        int[][] A = {{0 , 50 , 39},
        {-1 , 0 , 1},
        {-1 , 10 , 0}};
        int[][] ans = solve(A);
        for(int i=0; i<A.length; i++){
            for(int j=0; j<A[0].length; j++){
                System.out.println(ans[i][j]);
            }
        }
    }
    public static int[][] solve(int[][] A) {
        int n = A.length;

        // Step 1: Initialize the distance matrix
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(A[i][j] == -1) A[i][j] = Integer.MAX_VALUE;  // Replace -1 with INF
                if(i == j) A[i][j] = 0; // Distance to self is always 0
            }
        }

        // Step 2: Floyd Warshall Algorithm
        for(int via = 0; via < n; via++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    if(A[i][via] != Integer.MAX_VALUE && A[via][j] != Integer.MAX_VALUE) {
                        A[i][j] = Math.min(A[i][j], A[i][via] + A[via][j]);
                    }
                }
            }
        }

        // Step 3: Convert INF back to -1
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(A[i][j] == Integer.MAX_VALUE) A[i][j] = -1;
            }
        }

        return A;
    }
}
// TC : O(N^3)
// SC : O(1)