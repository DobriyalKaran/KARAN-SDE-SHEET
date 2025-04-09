/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may
move to adjacent numbers on the row below.

Adjacent numbers for jth column of ith row is jth and (j+1)th column of (i + 1)th row



Problem Constraints

|A| <= 1000

A[i] <= 1000


Input Format

First and only argument is the vector of vector A defining the given triangle



Output Format

Return the minimum sum



Example Input

Input 1:


A = [
         [2],
        [3, 4],
       [6, 5, 7],
      [4, 1, 8, 3]
    ]
Input 2:

 A = [ [1] ]


Example Output

Output 1:

 11
Output 2:

 1


Example Explanation

Explanation 1:

 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
Explanation 2:

 Only 2 can be collected.
 */

import java.util.*;
public class Main {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> triangle = new ArrayList<>();

        triangle.add(new ArrayList<>(Arrays.asList(2)));
        triangle.add(new ArrayList<>(Arrays.asList(3, 4)));
        triangle.add(new ArrayList<>(Arrays.asList(6, 5, 7)));
        triangle.add(new ArrayList<>(Arrays.asList(4, 1, 8, 3)));
        System.out.println(minimumTotal(triangle));
    }
    public static int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];

        // Fill dp with -1 (indicating unvisited)
        for (int[] row : dp) Arrays.fill(row, -1);

        return helper(0, 0, triangle, dp);
    }

    public static int helper(int i, int j, ArrayList<ArrayList<Integer>> triangle, int[][] dp) {
        int n = triangle.size();

        // Base case: last row
        if (i == n - 1) {
            return triangle.get(i).get(j);
        }

        if (dp[i][j] != -1) return dp[i][j];

        int down = helper(i + 1, j, triangle, dp);
        int diag = helper(i + 1, j + 1, triangle, dp);

        dp[i][j] = triangle.get(i).get(j) + Math.min(down, diag);
        return dp[i][j];
    }
}
// TC : O(N^2)
// SC : O(N^2)