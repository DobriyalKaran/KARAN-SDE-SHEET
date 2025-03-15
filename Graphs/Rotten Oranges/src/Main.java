/*
Problem Description

Given a matrix of integers A of size N x M consisting of 0, 1 or 2.

Each cell can have three values:

The value 0 representing an empty cell.

The value 1 representing a fresh orange.

The value 2 representing a rotten orange.

Every minute, any fresh orange that is adjacent (Left, Right, Top, or Bottom) to a rotten
orange becomes rotten. Return the minimum number of minutes that must elapse until no cell has
a fresh orange. If this is impossible, return -1 instead.

Note: Your solution will run on multiple test cases. If you are using global variables, make
sure to clear them.

Problem Constraints

1 <= N, M <= 1000

0 <= A[i][j] <= 2

Input Format

The first argument given is the integer matrix A.

Output Format

Return the minimum number of minutes that must elapse until no cell has a fresh orange.

If this is impossible, return -1 instead.

Example Input

Input 1:

A = [   [2, 1, 1]
        [1, 1, 0]
        [0, 1, 1]   ]


Input 2:

A = [   [2, 1, 1]
        [0, 1, 1]
        [1, 0, 1]   ]


Example Output

Output 1:
 4
Output 2:

 -1
Example Explanation

Explanation 1:

Minute 0: [ [2, 1, 1]
            [1, 1, 0]
            [0, 1, 1] ]
Minute 1: [ [2, 2, 1]
            [2, 1, 0]
            [0, 1, 1] ]
Minute 2: [ [2, 2, 2]
            [2, 2, 0]
            [0, 1, 1] ]
Minute 3: [ [2, 2, 2]
            [2, 2, 0]
            [0, 2, 1] ]
Minute 4: [ [2, 2, 2]
            [2, 2, 0]
            [0, 2, 2] ]
At Minute 4, all the oranges are rotten.
Explanation 2:

The fresh orange at 2nd row and 0th column cannot be rotten, So return -1.
 */
import java.util.*;
class Pair{
    int row;
    int col;
    int time;
    Pair(int row, int col, int time)
    {
        this.row = row;
        this.col = col;
        this.time = time;
    }
}
public class Main {
    public static void main(String[] args) {
        int[][] A = {{2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}};
        System.out.println(solve(A));
    }
    public static  int solve(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        int count = 0;
        int countFresh = 0;
        // Catching all the rotten oranges
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 2)
                {
                    q.add(new Pair(i,j,0));
                    vis[i][j] = true;
                }
                if(grid[i][j] == 1) countFresh++;
            }
        }
        // apply bfs
        int tm =0;
        int[] delRow = {0,-1,0,1};
        int[] delCol = {-1,0,1,0};
        while(!q.isEmpty())
        {
            int row = q.peek().row;
            int col = q.peek().col;
            int time = q.peek().time;
            tm = Math.max(time,tm);
            q.poll();
            for(int i=0; i<4; i++){
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m
                        && !vis[nrow][ncol] && grid[nrow][ncol]==1)
                {
                    vis[nrow][ncol] = true;
                    q.add(new Pair(nrow,ncol,time+1));
                    count++;
                }
            }
        }
        if(count != countFresh) return -1;
        return tm;
    }
}
// TC : O(m * n)
// SC : O(m * n)