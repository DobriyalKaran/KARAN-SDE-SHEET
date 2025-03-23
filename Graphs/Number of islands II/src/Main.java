/*
Given n, m denoting the row and column of the 2D matrix, and an array A of size k denoting the
number of operations. Matrix elements are 0 if there is water or 1 if there is land.
Originally, the 2D matrix is all 0 which means there is no land in the matrix.
The array has k operator(s) and each operator has two integers A[i][0], A[i][1]
means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island.
Return how many islands are there in the matrix after each operation.



The answer array will be of size k.


Examples:
Input: n = 4, m = 5, k = 4, A = [[1,1],[0,1],[3,3],[3,4]]

Output: [1, 1, 2, 2]

Explanation: The following illustration is the representation of the operation:



Input: n = 4, m = 5, k = 12, A = [[0,0],[0,0],[1,1],[1,0],[0,1],[0,3],[1,3],[0,4],
[3,2], [2,2],[1,2], [0,2]]

Output: [1, 1, 2, 1, 1, 2, 2, 2, 3, 3, 1, 1]

Explanation: If we follow the process like in example 1, we will get the above result.
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
    int[] delRow = {-1, 0, 1, 0};
    int[] delCol = {0, 1, 0, -1};

    /* Helper Function to check if a
    cell is within boundaries */
    boolean isValid(int i, int j, int n, int m) {

        // Return false if cell is invalid
        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= m) return false;

        // Return true if cell is valid
        return true;
    }

    /* Function to return the number
    of islands after each operation */
    public List<Integer> numOfIslands(int n, int m, int[][] A) {

        // Disjoint set Data structure
        DisjointSet ds = new DisjointSet(n * m);

        // Visited array
        int[][] vis = new int[n][m];
        for (int[] row : vis) Arrays.fill(row, 0);

        // To store the count of islands
        int cnt = 0;

        // To store the result
        List<Integer> ans = new ArrayList<>();

        // Perform each operation
        for (int[] it : A) {
            int row = it[0]; // Row
            int col = it[1]; // Column

            /* If already a land cell, no new
            islands will be formed */
            if (vis[row][col] == 1) {
                ans.add(cnt);
                continue;
            }

            // Make the cell as land
            vis[row][col] = 1;

            /* Increment the count considering
            the land cell was alone */
            cnt++;

            // Check all the neighbors
            for (int ind = 0; ind < 4; ind++) {
                // Get the coordinates of cell
                int newRow = row + delRow[ind];
                int newCol = col + delCol[ind];

                // If the cell is a valid land cell
                if (isValid(newRow, newCol, n, m) &&
                        vis[newRow][newCol] == 1) {

                    // Get the node and adjacent node number
                    int nodeNo = row * m + col;
                    int adjNodeNo = newRow * m + newCol;

                    // If not already connected, perform the union
                    if (ds.findUPar(nodeNo) !=
                            ds.findUPar(adjNodeNo)) {
                        // Decrement count
                        cnt--;

                        // Perform the union
                        ds.unionBySize(nodeNo, adjNodeNo);
                    }
                }
            }

            /* Store the number of islands after
            current operation in the result list */
            ans.add(cnt);
        }

        // Return the result
        return ans;
    }

    public static void main(String[] args) {
        int n = 4, m = 5, k = 4;
        int[][] A = {
                {1, 1},
                {0, 1},
                {3, 3},
                {3, 4}
        };

        // Creating instance of Solution class
        Solution sol = new Solution();

        /* Function call to return the number of islands after each operation */
        List<Integer> ans = sol.numOfIslands(n, m, A);

        // Output
        System.out.print("The number of islands after each operations are: ");
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}

/*
Complexity Analysis:
Time Complexity: O(K*4ɑ)
Each operation involves converting the sea cell to land cell and merging the nodes
(if possible) taking an overall O(4ɑ) time. Since, there are a total of K operations,
the overall time complexoty is O(K*4ɑ).

Space Complexity: O(K) + O(N*M)
The result list takes O(K) space. The visited array takes O(N*M) space and the Disjoint
set uses parent and rank/size array storing all N*M nodes taking O(N*M) space.
 */