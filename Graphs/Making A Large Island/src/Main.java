/*
You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.

Return the size of the largest island in grid after applying this operation.

An island is a 4-directionally connected group of 1s.



Example 1:

Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.


Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 500
grid[i][j] is either 0 or 1.
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
    // DelRow and delCol for neighbors
    private int[] delRow = {-1, 0, 1, 0};
    private int[] delCol = {0, 1, 0, -1};

    /* Helper function to check
    if a pixel is within boundaries */
    private boolean isValid(int i, int j, int n) {
        // Return false if pixel is invalid
        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= n) return false;

        // Return true if pixel is valid
        return true;
    }

    /* Function to add initial islands to
    the disjoint set data structure */
    private void addInitialIslands(int[][] grid,
                                   DisjointSet ds, int n) {
        // Traverse all the cells in the grid
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                // If the cell is not land, skip
                if (grid[row][col] == 0) continue;

                // Traverse on all the neighbors
                for (int ind = 0; ind < 4; ind++) {
                    // Get the coordinates of neighbor
                    int newRow = row + delRow[ind];
                    int newCol = col + delCol[ind];

                    // If the cell is valid and a land cell
                    if (isValid(newRow, newCol, n) &&
                            grid[newRow][newCol] == 1) {
                        // Get the number for node
                        int nodeNo = row * n + col;
                        // Get the number for neighbor
                        int adjNodeNo = newRow * n + newCol;

                        /* Take union of both nodes as they
                        are part of the same island */
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }
        }
    }

    // Function to get the size of the largest island
    public int largestIsland(int[][] grid) {
        // Dimensions of grid
        int n = grid.length;

        // Disjoint set data structure
        DisjointSet ds = new DisjointSet(n * n);

        /* Function call to add initial
        islands in the disjoint set */
        addInitialIslands(grid, ds, n);

        // To store the answer
        int ans = 0;

        // Traverse on the grid
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                // If the cell is a land cell, skip
                if (grid[row][col] == 1) continue;

                /* Set to store the ultimate
                parents of neighboring islands */
                Set<Integer> components = new HashSet<>();

                // Traverse on all its neighbors
                for (int ind = 0; ind < 4; ind++) {
                    // Coordinates of neighboring cell
                    int newRow = row + delRow[ind];
                    int newCol = col + delCol[ind];

                    if (isValid(newRow, newCol, n) &&
                            grid[newRow][newCol] == 1) {

                        /* Perform union and store
                        ultimate parent in the set */
                        int nodeNumber = newRow * n + newCol;
                        components.add(ds.findUPar(nodeNumber));
                    }
                }

                // To store the size of current largest island
                int sizeTotal = 0;

                // Iterate on all the neighboring ultimate parents
                for (int parent : components) {
                    // Update the size
                    sizeTotal += ds.size[ds.findUPar(parent)];
                }

                // Store the maximum size of island
                ans = Math.max(ans, sizeTotal + 1);
            }
        }

        // Edge case
        for (int cellNo = 0; cellNo < n * n; cellNo++) {
            // Keep the answer updated
            ans = Math.max(ans, ds.size[ds.findUPar(cellNo)]);
        }

        // Return the answer
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0},
                {0, 1}
        };

        // Creating instance of Solution class
        Solution sol = new Solution();

        /* Function call to get the
        size of the largest island */
        int ans = sol.largestIsland(grid);

        // Output
        System.out.println("The size of the largest island is: " + ans);
    }
}
/*
Complexity Analysis:
Time Complexity: O(N2) Using nested loops, and within the loops, all the operations take constant time.

Space Complexity: O(N2) The Disjoint set storing N2 nodes (cells) will take up 2*N2 space
due to parent and size arrays.
 */