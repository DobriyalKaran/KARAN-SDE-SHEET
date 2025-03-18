/*
Problem Description

Given a matrix C of integers, of dimension A x B.

For any given K, you are not allowed to travel between cells that have an absolute difference
greater than K.

Return the minimum value of K such that it is possible to travel between any pair of cells in
the grid through a path of adjacent cells.

NOTE:

Adjacent cells are those cells that share a side with the current cell.


Problem Constraints

1 <= A, B <= 102

1 <= C[i][j] <= 109



Input Format

The first argument given is A.

The second argument give is B.

The third argument given is the integer matrix C.



Output Format

Return a single integer, the minimum value of K.



Example Input

Input 1:

 A = 3
 B = 3
 C = [  [1, 5, 6]
        [10, 7, 2]
        [3, 6, 9]   ]


Example Output

Output 1:

 4


Example Explanation

Explanation 1:


 It is possible to travel between any pair of cells through a path of adjacent cells that
 do not have an absolute
 difference in value greater than 4. e.g. : A path from (0, 0) to (2, 2) may look like this:
 => (0, 0) -> (0, 1) -> (1, 1) -> (2, 1) -> (2, 2)
 */

import java.util.*;
public class Main {
    static int[] dx = {-1, 1, 0, 0}; // Up, Down, Left, Right
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) {
        int A = 3;
        int B = 3;
        int[][] C = {{1, 5, 6},
                {10, 7, 2},
                {3, 6, 9}};
        System.out.println(solve(A,B,C));
    }
    public static int solve(int A, int B, int[][] C) {
        // Step 1: Find Min & Max in Grid
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                minVal = Math.min(minVal, C[i][j]);
                maxVal = Math.max(maxVal, C[i][j]);
            }
        }

        // Step 2: Apply Binary Search on K
        int low = 0, high = maxVal - minVal;
        while (low < high) {
            int mid = (low + high) / 2;
            if (canTraverse(A, B, C, mid)) {
                high = mid; // Try smaller K
            } else {
                low = mid + 1; // Increase K
            }
        }

        return low; // Final minimum K
    }
    private static boolean canTraverse(int A, int B, int[][] C, int K) {
        // Step 3: BFS Traversal
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[A][B];

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];


            for (int d = 0; d < 4; d++) {
                int newX = x + dx[d], newY = y + dy[d];

                if (newX >= 0 && newX < A && newY >= 0 && newY < B && !visited[newX][newY]) {
                    int diff = Math.abs(C[x][y] - C[newX][newY]);
                    if (diff <= K) { // Only move if difference <= K
                        queue.offer(new int[]{newX, newY});
                        visited[newX][newY] = true;
                    }
                }
            }
        }
        for(int i = 0; i < A; i++) {
            for(int j = 0; j < B; j++) {
                if(!visited[i][j]) return false;
            }
        }
        return true;
    }
}
// TC : O(log(maxVal−minVal)×(A×B))
// SC : O(A×B)