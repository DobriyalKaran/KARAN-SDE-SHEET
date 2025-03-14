/*
Problem Description

Given a matrix of integers A of size N x M consisting of 0 and 1. A group of connected 1's
forms an island. From a cell (i, j) such that A[i][j] = 1 you can visit any cell that shares
a corner with (i, j) and value in that cell is 1.

More formally, from any cell (i, j) if A[i][j] = 1 you can visit:

(i-1, j) if (i-1, j) is inside the matrix and A[i-1][j] = 1.
(i, j-1) if (i, j-1) is inside the matrix and A[i][j-1] = 1.
(i+1, j) if (i+1, j) is inside the matrix and A[i+1][j] = 1.
(i, j+1) if (i, j+1) is inside the matrix and A[i][j+1] = 1.
(i-1, j-1) if (i-1, j-1) is inside the matrix and A[i-1][j-1] = 1.
(i+1, j+1) if (i+1, j+1) is inside the matrix and A[i+1][j+1] = 1.
(i-1, j+1) if (i-1, j+1) is inside the matrix and A[i-1][j+1] = 1.
(i+1, j-1) if (i+1, j-1) is inside the matrix and A[i+1][j-1] = 1.
Return the number of islands.

NOTE: Rows are numbered from top to bottom and columns are numbered from left to right.

Problem Constraints

1 <= N, M <= 100

0 <= A[i] <= 1

Input Format

The only argument given is the integer matrix A.

Output Format

Return the number of islands.


Example Input

Input 1:

 A = [
       [0, 1, 0]
       [0, 0, 1]
       [1, 0, 0]
     ]
Input 2:

 A = [
       [1, 1, 0, 0, 0]
       [0, 1, 0, 0, 0]
       [1, 0, 0, 1, 1]
       [0, 0, 0, 0, 0]
       [1, 0, 1, 0, 1]
     ]


Example Output
Output 1:
 2
Output 2:
 5
Example Explanation

Explanation 1:

 The 1's at position A[0][1] and A[1][2] forms one island.
 Other is formed by A[2][0].
Explanation 2:

 There 5 island in total.
 */


public class Main {
    public static void main(String[] args) {
        int[][]  A = {
        {0, 1, 0},
        {0, 0, 1},
        {1, 0, 0}
    };
        System.out.println(solve(A));
    }
    public static int solve(int[][] A) {
        int count = 0;
        int n = A.length;
        int m = A[0].length;
        boolean[][] vis = new boolean[n][m];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(A[i][j] == 1 && !vis[i][j])
                {
                    count++;
                    dfs(i,j,A,vis);
                }
            }
        }
        return count;
    }
    public static void dfs(int row, int col, int[][] A, boolean[][] vis)
    {
        int n = A.length;
        int m = A[0].length;
        vis[row][col] = true;
        int[] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
        int[] dy = {-1, 0, 1, -1, 0, 1, -1, 1};
        for(int i=0; i<8; i++)
        {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            if(nrow >=0 && nrow <n && ncol>=0 && ncol<m && A[nrow][ncol]==1 && !vis[nrow][ncol])
            {
                dfs(nrow,ncol,A,vis);
            }
        }
    }
}
// TC : O(N*M) (Visiting Every Cell)
// SC : O(N*M) Recursion Stack (DFS)