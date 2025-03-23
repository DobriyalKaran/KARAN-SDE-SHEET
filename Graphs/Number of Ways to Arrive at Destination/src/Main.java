/*
You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional
roads between some intersections. The inputs are generated such that you can reach any intersection
from any other intersection and that there is at most one road between any two intersections.

You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that
there is a road between intersections ui and vi that takes timei minutes to travel. You want to know
in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.

Return the number of ways you can arrive at your destination in the shortest amount of time. Since
the answer may be large, return it modulo 109 + 7.


Example 1:


Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
Output: 4
Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
Example 2:

Input: n = 2, roads = [[1,0,10]]
Output: 1
Explanation: There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.


Constraints:

1 <= n <= 200
n - 1 <= roads.length <= n * (n - 1) / 2
roads[i].length == 3
0 <= ui, vi <= n - 1
1 <= timei <= 109
ui != vi
There is at most one road connecting any two intersections.
You can reach any intersection from any other intersection.
 */

import java.util.*;
class Solution {

    /* Function to get the number of ways to arrive
    at destinations in shortest possible time */
    public int countPaths(int n, List<List<Integer>> roads) {

        int mod = 1000000007; // Mod value

        // To store the graph
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Adding the edges to the graph
        for (List<Integer> it : roads) {
            adj[it.get(0)].add(new int[]{it.get(1), it.get(2)});
            adj[it.get(1)].add(new int[]{it.get(0), it.get(2)});
        }

        /* Array to store the minimum
        time to get to nodes */
        long[] minTime = new long[n];
        Arrays.fill(minTime, Long.MAX_VALUE);

        /* To store the number of
        ways to reach nodes */
        int[] ways = new int[n];

        // Priority queue to store: {time, node}
        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Initial configuration
        minTime[0] = 0;
        ways[0] = 1;
        pq.add(new long[]{0, 0});

        // Until the priority queue is empty
        while (!pq.isEmpty()) {

            // Get the element
            long[] p = pq.poll();
            long time = p[0]; // time
            int node = (int) p[1]; // node

            // Traverse its neighbors
            for (int[] it : adj[node]) {

                int adjNode = it[0]; // adjacent node
                int travelTime = it[1]; // travel time

                /* If the current time taken is less than
                earlier known time to reach adjacent node */
                if (minTime[adjNode] > time + travelTime) {

                    // Update the time to reach adjacent node
                    minTime[adjNode] = time + travelTime;

                    // Update the number of ways
                    ways[adjNode] = ways[node];

                    // Add the new element in priority queue
                    pq.add(new long[]{minTime[adjNode], adjNode});
                }

                /* Else if the current time taken is same as
                earlier known time to reach adjacent node */
                else if (minTime[adjNode] == time + travelTime) {

                    /* Update the number of ways
                    to reach adjacent nodes */
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        // Return the result
        return ways[n - 1] % mod;
    }

    public static void main(String[] args) {
        int n = 7, m = 20;
        List<List<Integer>> roads = Arrays.asList(
                Arrays.asList(0, 6, 7),
                Arrays.asList(0, 1, 2),
                Arrays.asList(1, 2, 3),
                Arrays.asList(1, 3, 3),
                Arrays.asList(6, 3, 3),
                Arrays.asList(3, 5, 1),
                Arrays.asList(6, 5, 1),
                Arrays.asList(2, 5, 1),
                Arrays.asList(0, 4, 5),
                Arrays.asList(4, 6, 2)
        );

        /* Creating an instance of
        Solution class */
        Solution sol = new Solution();

        /* Function call to get the number of ways to
        arrive at destinations in shortest possible time */
        int ans = sol.countPaths(n, roads);

        // Output
        System.out.println("The number of ways to arrive at destinations in shortest possible time is: " + ans);
    }
}
/*
### **Complexity Analysis:**

**Time Complexity: O(M*logN)**A simple Dijkstra's algorithm is used which takes O(E*logV) time (where V and E represents the number of nodes and edges in the graph respectively).

**Space Complexity: O(N)**

- *Dijkstra's Algorithm will take extra O(N) space due to priority queue and array to store minimum time to reach nodes.*
- *The array to store the number of ways take O(N) space.*
 */