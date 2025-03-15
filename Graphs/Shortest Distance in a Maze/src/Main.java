/*
Problem Description

Given a matrix of integers A of size N x M describing a maze. The maze consists of empty locations
and walls.

1 represents a wall in a matrix and 0 represents an empty location in a wall.

There is a ball trapped in a maze. The ball can go through empty spaces by rolling up, down, left
or right, but it won't stop rolling until hitting a wall (maze boundary is also considered as a wall).
When the ball stops, it could choose the next direction.

Given two array of integers of size B and C of size 2 denoting the starting and destination position
of the ball.

Find the shortest distance for the ball to stop at the destination. The distance is defined by the
number of empty spaces traveled by the ball from the starting position (excluded) to the destination
(included). If the ball cannot stop at the destination, return -1.

Problem Constraints

2 <= N, M <= 100

0 <= A[i] <= 1

0 <= B[i][0], C[i][0] < N

0 <= B[i][1], C[i][1] < M

Input Format

The first argument given is the integer matrix A.

The second argument given is an array of integer B.

The third argument if an array of integer C.

Output Format

Return a single integer, the minimum distance required to reach destination

Example Input

Input 1:

A = [ [0, 0],
      [0, 0] ]
B = [0, 0]
C = [0, 1]

Input 2:

A = [ [0, 1],
      [1, 0] ]
B = [0, 0]
C = [1, 1]

Example Output
Output 1:
 1
Output 2:
 -1
Example Explanation

Explanation 1:

Go directly from start to destination in distance 1.
Explanation 2:

 It is impossible to reach the destination from (0, 0) to (1, 1) as there are walls at (1, 0) and (0, 1)
 */

import  java.util.*;
class Pair {
    int row, col, level;
    Pair(int row, int col, int level) {
        this.row = row;
        this.col = col;
        this.level = level;
    }
}
public class Main {
    public static void main(String[] args)
    {
        int[][] A = {{0, 0},{0, 0}};
        int[] B = {0, 0};
        int[] C = {0, 1};
        System.out.println(solve(A,B,C));
    }
    public static int solve(int[][] A, int[] B, int[] C) {
        int n = A.length;
        int m = A[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();

        // Starting position push
        q.add(new Pair(B[0], B[1], 0));
        vis[B[0]][B[1]] = true;  // Mark start as visited

        int[] delRow = {0, -1, 0, 1};
        int[] delCol = {-1, 0, 1, 0};

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            int row = curr.row;
            int col = curr.col;
            int level = curr.level;

            // If we reach the destination, return level (shortest distance)
            if (row == C[0] && col == C[1]) return level;

            // Explore all 4 directions
            for (int i = 0; i < 4; i++) {
                int nrow = row;
                int ncol = col;
                int steps = 0;

                // Keep moving in the same direction until wall/boundary
                while (nrow + delRow[i] >= 0 && nrow + delRow[i] < n &&
                        ncol + delCol[i] >= 0 && ncol + delCol[i] < m &&
                        A[nrow + delRow[i]][ncol + delCol[i]] == 0) {
                    nrow += delRow[i];
                    ncol += delCol[i];
                    steps++;
                }

                // If not visited, add to queue
                if (!vis[nrow][ncol]) {
                    vis[nrow][ncol] = true;
                    q.add(new Pair(nrow, ncol, level + steps));
                }
            }
        }
        return -1; // If no path found
    }
}
// TC : O(N*M)
// SC : O(N*M)