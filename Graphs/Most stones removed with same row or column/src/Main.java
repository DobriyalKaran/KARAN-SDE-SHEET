/*
On a 2D plane, we place n stones at some integer coordinate points. Each
coordinate point may have at most one stone.

A stone can be removed if it shares either the same row or the same column as
another stone that has not been removed.

Given an array stones of length n where stones[i] = [xi, yi] represents the
location of the ith stone, return the largest possible number of stones that can be removed.



Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another
stone still on the plane.
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column
with another stone still on the plane.
Example 3:

Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.


Constraints:

1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
 */

import java.util.*;

class DisjointSet {
    /* To store the ranks, parents and 
    sizes of different set of vertices */
    int[] rank, parent, size;

    // Constructor
    DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Function to find ultimate parent
    int findUPar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUPar(parent[node]);
    }

    // Function to implement union by rank
    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        }
        else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        }
        else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    // Function to implement union by size
    void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        }
        else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}


// Solution class
class Solution {
    // Function to remove maximum stones
    public int maxRemove(int[][] stones, int n) {
        
        /* To store the maximum row 
        and column having a stone */
        int maxRow = 0;
        int maxCol = 0;

        // Iterate on all the nodes
        for (int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]);
            maxCol = Math.max(maxCol, stone[1]);
        }

        // Disjoint Set data structure
        DisjointSet ds =
                new DisjointSet(maxRow + maxCol + 1);

        // To store the nodes having a stone in Disjoint Set
        Map<Integer, Integer> stoneNodes = new HashMap<>();

        // Iterate on all stones
        for (int[] stone : stones) {
            // Row number
            int nodeRow = stone[0];

            // Converted column number
            int nodeCol = stone[1] + maxRow + 1;

            // United two nodes
            ds.unionBySize(nodeRow, nodeCol);

            // Add the nodes to the map
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        // To store the number of connected components
        int k = 0;

        // Iterate on the set
        for (int key : stoneNodes.keySet()) {
            /* Increment the count if 
            a new component is found */
            if (ds.findUPar(key) == key) {
                k++;
            }
        }

        // Return the answer
        return n - k;
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] stones = {
                {0, 0}, {0, 1}, {1, 0},
                {1, 2}, {2, 1}, {2, 2}
        };

        // Creating instance of Solution class
        Solution sol = new Solution();

        /* Function call to get the 
        size of the largest island */
        int ans = sol.maxRemove(stones, n);

        // Output
        System.out.println("The size of the largest island is: " + ans);
    }
}
/*
Complexity Analysis:
Time Complexity: O(N) The given stones array is traversed multiple times.
Traversing the hashset will also take O(N) time.

Space Complexity: O(Max Row number + Max Column number) The Disjoint set will
store the nodes using the parent and size/rank array which will take
(2*number of nodes) space. Since, the number of nodes = max row number
+ max column number, the overall space complexity is O(Max Row number + Max Column number).
 */