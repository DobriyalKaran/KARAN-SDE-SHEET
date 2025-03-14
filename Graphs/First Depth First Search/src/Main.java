import java.util.ArrayList;

/*
Problem Description

You are given N towns (1 to N). All towns are connected via unique directed path as mentioned
in the input.

Given 2 towns find whether you can reach the first town from the second without repeating any edge.

B C : query to find whether B is reachable from C.

Input contains an integer array A of size N and 2 integers B and C ( 1 <= B, C <= N ).

There exist a directed edge from A[i] to i+1 for every 1 <= i < N. Also, it's guaranteed that A[i] <= i for every 1 <= i < N.

NOTE: Array A is 0-indexed. A[0] = 1 which you can ignore as it doesn't represent any edge.

Problem Constraints
1 <= N <= 100000

Input Format
First argument is vector A

Second argument is integer B

Third argument is integer C
Output Format

Return 1 if reachable, 0 otherwise.
Example Input

Input 1:

 A = [1, 1, 2]
 B = 1
 C = 2
Input 2:

 A = [1, 1, 2]
 B = 2
 C = 1

Example Output
Output 1:
 0
Output 2:
 1

Example Explanation
Explanation 1:
 Tree is 1--> 2--> 3 and hence 1 is not reachable from 2.
Explanation 2:

 Tree is 1--> 2--> 3 and hence 2 is reachable from 1.
 */
public class Main {
    public static void main(String[] args) {
        int[] A = {1, 1, 2};
        int B = 1;
        int C = 2;
        System.out.println(solve(A,B,C));
    }
    public static int solve(int[] A, final int B, final int C) {
        int n = A.length;
        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {  // Initialize adjacency list
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {  // Build directed graph
            int s = A[i];
            int d = i + 1;
            graph[s].add(d);
        }

        return DFSpath(C, B, graph, visited) ? 1 : 0;
    }
    public static boolean DFSpath(int source, int target, ArrayList<Integer>[] graph, boolean[] visited) {
        if (source == target) return true;

        visited[source] = true;

        for (int neighbor : graph[source]) {
            if (!visited[neighbor] && DFSpath(neighbor, target, graph, visited)) {
                return true;
            }
        }
        return false;
    }
}

// TC : O(N)
// SC : O(N)