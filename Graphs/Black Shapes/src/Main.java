/*
Problem Description

Given character matrix A of dimensions N×M consisting of O's and X's, where O = white, X = black.

Return the number of black shapes. A black shape consists of one or more adjacent X's
(diagonals not included)

Problem Constraints

1 <= N, M <= 1000


A[i][j] = 'X' or 'O'

Input Format

The First and only argument is character matrix A.

Output Format

Return a single integer denoting number of black shapes.

Example Input

Input 1:

 A = [ [X, X, X], [X, X, X], [X, X, X] ]
Input 2:

 A = [ [X, O], [O, X] ]

Example Output
Output 1:
 1
Output 2:
 2

Example Explanation

Explanation 1:

 All X's belong to single shapes
Explanation 2:

 Both X's belong to different shapes
 */

public class Main {
    public static void main(String[] args) {
        String[][] A = {{"X", "X", "X"}, {"X", "X", "X"}, {"X","X","X"} };
        System.out.println(black(A));
    }
    public static int black(String[][] A) {
        int n = A.length;
        int m = A[0].length;
        int count = 0;
        boolean[][] vis = new boolean[n][m];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                String cell = A[i][j];
                if(cell.equals("X") && !vis[i][j])
                {
                    count++;
                    dfs(i,j,A,vis,n,m);
                }
            }
        }
        return count;
    }
    static void dfs(int row, int col, String[][] A, boolean[][] vis, int n, int m)
    {
        vis[row][col] = true;
        int[] delRow = {0,-1,0,1};
        int[] delCol = {-1,0,1,0};
        for(int i=0; i<4; i++)
        {
            int nrow = row + delRow[i];
            int ncol = col + delCol[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && !vis[nrow][ncol] && A[nrow][ncol]=="X")
            {
                dfs(nrow,ncol,A,vis,n,m);
            }
        }
    }
}
// TC : O(N∗M)
// SC : O(N∗M)