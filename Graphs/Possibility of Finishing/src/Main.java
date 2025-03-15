/*
Problem Description

There are a total of A courses you have to take, labeled from 1 to A.

Some courses may have prerequisites, for example to take course 2 you have to first take course 1,
which is expressed as a pair: [1,2].

So you are given two integer array B and C of same size where for each i (B[i], C[i]) denotes a pair.

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish
all courses?

Return 1 if it is possible to finish all the courses, or 0 if it is not possible to finish all the courses.

Problem Constraints

1 <= A <= 6*104

1 <= length(B) = length(C) <= 105

1 <= B[i], C[i] <= A

Input Format

The first argument of input contains an integer A, representing the number of courses.

The second argument of input contains an integer array, B.

The third argument of input contains an integer array, C.


Output Format

Return 1 if it is possible to finish all the courses, or 0 if it is not possible to finish all the courses.


Example Input

Input 1:

 A = 3
 B = [1, 2]
 C = [2, 3]
Input 2:

 A = 2
 B = [1, 2]
 C = [2, 1]


Example Output

Output 1:

 1
Output 2:

 0


Example Explanation

Explanation 1:

 It is possible to complete the courses in the following order:
    1 -> 2 -> 3
Explanation 2:

 It is not possible to complete all the courses.
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int A = 3;
        int[] B = {1,2};
        int[] C = {2,3};
        System.out.println(solve(A,B,C));
    }
    public static int solve(int A, int[] B, int[] C) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<=A; i++)
        {
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<B.length; i++){
            int u = B[i];
            int v = C[i];
            graph.get(u).add(v);
        }
        boolean[] vis = new boolean[A+1];
        boolean[] pathVisited = new boolean[A+1];
        for (int i = 1; i <= A; i++) {
            if (!vis[i]) {
                if (dfs(i, graph, vis, pathVisited) == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }
    public static  int dfs(int src, ArrayList<ArrayList<Integer>> adj, boolean[] vis, boolean[] pathvis) {
        vis[src] = true;
        pathvis[src] = true;

        for (int i : adj.get(src)) {
            if (pathvis[i]) return 0; // Cycle detected
            if (!vis[i]) {
                if (dfs(i, adj, vis, pathvis) == 0)  //
                    return 0; // Cycle detected
            }
        }

        pathvis[src] = false;  // Backtrack
        return 1;  // No cycle found
    }
}
// TC : O(A + B.length)
// SC : O(A + B.length)