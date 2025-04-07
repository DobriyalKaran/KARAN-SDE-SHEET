/*
Given a 2 x N grid of integers, A, your task is to choose numbers from the grid such
that sum of these numbers is maximized.
However, you cannot choose two numbers that are adjacent horizontally, vertically,
or diagonally.

Return the maximum possible sum.

Note: You are allowed to choose more than 2 numbers from the grid.


Problem Constraints

1 <= N <= 20000
1 <= A[i] <= 2000


Input Format

The first and the only argument of input contains a 2d matrix, A.


Output Format

Return an integer, representing the maximum possible sum.


Example Input

Input 1:

 A = [
        [1]
        [2]
     ]
Input 2:

 A = [
        [1, 2, 3, 4]
        [2, 3, 4, 5]
     ]


Example Output

Output 1:

 2
Output 2:

 8


Example Explanation

Explanation 1:

 We will choose 2 (From 2nd row 1st column).
Explanation 2:

 We will choose 3 (From 2nd row 2nd column) and 5 (From 2nd row 4th column).
 */

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][] A ={{1},{2}};
        System.out.println(adjacent(A));
    }
    public static int adjacent(int[][] A) {
        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[n][m];
        for(int[] row : dp)
            Arrays.fill(row, -1);
        int ans = f(n-1,m-1,A,dp);
        return ans;
    }

    static int f(int n, int m, int[][] A, int[][] dp)
    {
        if(m<0) return 0;
        if(dp[n][m] != -1) return dp[n][m];
        int pick = f(n,m-2,A,dp) + Math.max(A[0][m], A[1][m]);
        int notPick = f(n,m-1,A,dp);
        return dp[n][m] = Math.max(pick,notPick);
    }
}
// TC : O(N)
// SC : O(N)