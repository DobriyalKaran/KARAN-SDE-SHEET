/*
Given a 2-D binary matrix A of size N x M filled with 0's and 1's, find the largest
rectangle containing only ones and return its area.




Problem Constraints

1 <= N, M <= 100



Input Format

The first argument is a 2-D binary array A.



Output Format

Return an integer denoting the area of the largest rectangle containing only ones.



Example Input

Input 1:

 A = [
       [1, 1, 1]
       [0, 1, 1]
       [1, 0, 0]
     ]
Input 2:

 A = [
       [0, 1, 0]
       [1, 1, 1]
     ]


Example Output

Output 1:

 4
Output 2:

 3


Example Explanation

Explanation 1:


 As the max area rectangle is created by the 2x2 rectangle created by
 (0, 1), (0, 2), (1, 1) and (1, 2).
Explanation 2:

 As the max area rectangle is created by the 1x3 rectangle created by
 (1, 0), (1, 1) and (1, 2).
 */

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[][]  A = {
        {0, 1, 0},
        {1, 1, 1}};
        System.out.println(maximalRectangle(A));
    }
    public static int maximalRectangle(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int[] heights = new int[m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            // Step 1: Update heights array
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) heights[j] = 0;
                else heights[j] += 1;
            }

            // Step 2: Find max area in this histogram row
            int area = largestRectangleArea(heights);
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    // Helper for Largest Rectangle in Histogram
    private static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int n = heights.length;
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currHeight = (i == n ? 0 : heights[i]);
            while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
// TC : O(N × M)
// SC : O(M)