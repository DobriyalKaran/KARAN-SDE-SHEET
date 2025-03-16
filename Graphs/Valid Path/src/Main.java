/*
Problem Description

There is a rectangle with left bottom as (0, 0) and right up as (x, y).

There are N circles such that their centers are inside the rectangle.

Radius of each circle is R. Now we need to find out if it is possible that we can move from
 (0, 0) to (x, y) without touching any circle.

Note : We can move from any cell to any of its 8 adjecent neighbours and we cannot move
outside the boundary of the rectangle at any point of time.



Problem Constraints

0 <= x , y, R <= 100

1 <= N <= 1000

Center of each circle would lie within the grid



Input Format

1st argument given is an Integer x , denoted by A in input.

2nd argument given is an Integer y, denoted by B in input.

3rd argument given is an Integer N, number of circles, denoted by C in input.

4th argument given is an Integer R, radius of each circle, denoted by D in input.

5th argument given is an Array A of size N, denoted by E in input, where A[i] = x
cordinate of ith circle

6th argument given is an Array B of size N, denoted by F in input, where B[i] = y
cordinate of ith circle



Output Format

Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).

Example Input

Input 1:

 x = 2
 y = 3
 N = 1
 R = 1
 A = [2]
 B = [3]
Input 2:

 x = 3
 y = 3
 N = 1
 R = 1
 A = [0]
 B = [3]


Example Output

Output 1:

 NO
Output 2:

 YES


Example Explanation

Explanation 1:

 There is NO valid path in this case
Explanation 2:

 There is many valid paths in this case.
 One of the path is (0, 0) -> (1, 0) -> (2, 0) -> (3, 0) -> (3, 1) -> (3, 2) -> (3, 3).
 */

import java.util.*;
class Pair{
    int row;
    int col;
    Pair(int row, int col){
        this.row = row;
        this.col = col;
    }
}
public class Main {
    public static void main(String[] args) {
        int x = 2;
        int y = 3;
        int N = 1;
        int R = 1;
        int[] A = {2};
        int[] B = {3};
        System.out.println(solve(x,y,N,R,A,B));
    }
    public static String solve(int x, int y, int n, int r, int[] a, int[] b) {
        boolean[][] vis = new boolean[x+1][y+1];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        vis[0][0]=true;
        int[] delRow = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] delCol = {-1, 0, 1, -1, 1, -1, 0, 1};
        if(isBlocked(0, 0, a, b, r) || isBlocked(x, y, a, b, r))
            return "NO";
        while(!q.isEmpty()){
            int row = q.peek().row;
            int col = q.peek().col;
            q.poll();
            if(row == x && col == y) return "YES";
            for(int i=0; i<8; i++)
            {
                int nrow = row + delRow[i];
                int ncol = col + delCol[i];
                if(nrow>=0 && nrow<=x && ncol >=0 && ncol<=y && !vis[nrow][ncol] &&
                        !isBlocked(nrow,ncol,a,b,r))
                {
                    vis[nrow][ncol]=true;
                    q.add(new Pair(nrow,ncol));
                }
            }
        }
        return "NO";
    }
    public static boolean isBlocked(int x, int y, int[] a, int[] b, int R) {
        for(int i = 0; i < a.length; i++) {
            if((x-a[i])*(x-a[i]) + (y-b[i])*(y-b[i]) <= R*R) {
                return true; // point is blocked
            }
        }
        return false; // point is safe
    }
}
// TC : O(x * y * N)
// SC : O(x * y + N)