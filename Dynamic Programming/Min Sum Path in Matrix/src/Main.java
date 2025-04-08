/*
Given a M x N grid A of integers, find a path from top left to bottom right which minimizes
the sum of all numbers along its path.

Return the minimum sum of the path.

NOTE: You can only move either down or right at any point in time.



Problem Constraints

1 <= M, N <= 2000

-1000 <= A[i][j] <= 1000



Input Format

First and only argument is a 2-D grid A.



Output Format

Return an integer denoting the minimum sum of the path.



Example Input

Input 1:

 A = [
       [1, 3, 2]
       [4, 3, 1]
       [5, 6, 1]
     ]
Input 2:

 A = [
       [1, -3, 2]
       [2, 5, 10]
       [5, -5, 1]
     ]


Example Output

Output 1:

 8
Output 2:

 -1


Example Explanation

Explanation 1:

 The path will be: 1 -> 3 -> 2 -> 1 -> 1.
Input 2:

 The path will be: 1 -> -3 -> 5 -> -5 -> 1.
 */


public class Main {
    public static void main(String[] args) {
        int[][] A = {
                {1, 3, 2},
        {4, 3, 1},
        {5, 6, 1}};
        System.out.println(minPathSum(A));
    }
    public static int minPathSum(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[n][m];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                dp[i][j] = -1;
            }
        }
        return minimalPathSum(n-1,m-1,n,m,dp,A);
    }
    public static int  minimalPathSum(int row, int col, int n, int m, int[][] dp, int[][] A)
    {
        if(row == 0 && col == 0) return A[row][col];
        if(row < 0 || col < 0)  return Integer.MAX_VALUE;
        if(dp[row][col] != -1) return dp[row][col];

        int up = minimalPathSum(row-1,col,n,m,dp,A);
        int left = minimalPathSum(row,col-1,n,m,dp,A);
        return dp[row][col] = A[row][col] + Math.min(up,left);
    }
}
// TC : O(M*N)
// SC : O(M*N)