/*
Problem Description

Given a 2-D board A of size N x M containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Problem Constraints

1 <= N, M <= 1000

Input Format

First and only argument is a N x M character matrix A.

Output Format

Return nothing. Make changes to the input only as matrix is passed by reference.

Example Input

Input 1:

 A = [
       [X, X, X, X],
       [X, O, O, X],
       [X, X, O, X],
       [X, O, X, X]
     ]
Input 2:

 A = [
       [X, O, O],
       [X, O, X],
       [O, O, O]
     ]


Example Output

Output 1:

 After running your function, the board should be:
 A = [
       [X, X, X, X],
       [X, X, X, X],
       [X, X, X, X],
       [X, O, X, X]
     ]
Output 2:

 After running your function, the board should be:
 A = [
       [X, O, O],
       [X, O, X],
       [O, O, O]
     ]


Example Explanation

Explanation 1:

 O in (4,2) is not surrounded by X from below.
Explanation 2:

 No O's are surrounded.
 */

public class Main {
    public static void main(String[] args) {
       char[][] board = {
               {'X','X','X','X'},
               {'X','O','O','X'},
               {'X','X','O','X'},
               {'X','O','X','X'}};
       solve(board);
    }
    public static void solve(char[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        boolean[][] vis = new boolean[n][m];
        // Checking first and Last col
        for(int i=0; i<n; i++)
        {
            // first col
            if(arr[i][0] == 'O' && !vis[i][0])
            {
                dfs(i,0,arr,vis);
            }
            // last col
            if(arr[i][m-1] == 'O' && !vis[i][m-1])
            {
                dfs(i,m-1,arr,vis);
            }
        }
        // Checking the first and last row
        for(int i=0; i<m; i++)
        {
            if(arr[0][i] == 'O' && !vis[0][i])
            {
                dfs(0,i,arr,vis);
            }
            if(arr[n-1][i] == 'O' && !vis[n-1][i])
            {
                dfs(n-1,i,arr,vis);
            }
        }
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<m; j++)
            {
                if(vis[i][j] == false)
                {
                    arr[i][j] = 'X';
                }
            }
        }
    }
    public static void dfs(int row, int col, char[][] arr, boolean[][] vis)
    {
        int n = arr.length;
        int m = arr[0].length;
        vis[row][col] = true;
        int[] delRow = {0,-1,0,1};
        int[] delCol = {-1,0,1,0};
        for(int i=0; i<4; i++)
        {
            int nrow = row + delRow[i];
            int ncol = col + delCol[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m &&
                    arr[nrow][ncol]=='O' && !vis[nrow][ncol])
            {
                dfs(nrow,ncol,arr,vis);
            }
        }
    }
}
/*
🔹 Time Complexity Analysis
Step 1: DFS runs for all boundary ‘O’ → O(N * M)
Step 2: We traverse matrix once → O(N * M)
Step 3: DFS calls in worst case O(N * M)
✅ Total: O(N * M)
🔹 Space Complexity Analysis
Visited array → O(N * M)
✅ Total: O(N * M)
 */