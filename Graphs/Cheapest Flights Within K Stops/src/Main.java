/*
There are n cities connected by some number of flights. You are given an
array flights where flights[i] = [fromi, toi, pricei] indicates that there
is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest
price from src to dst with at most k stops. If there is no such route, return -1.



Example 1:


Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]],
src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has
cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because
it uses 2 stops.
Example 2:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
Output: 200
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 2 is marked in red and has
cost 100 + 100 = 200.
Example 3:


Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
Output: 500
Explanation:
The graph is shown above.
The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.


Constraints:

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
There will not be any multiple flights between two cities.
0 <= src, dst, k < n
src != dst
 */


import java.util.*;
public class Main {
    public static void main(String[] args) {
        int n = 4,  src = 0, dst = 3, k = 1;
        int[][] flights = {
        {0,1,100},
        {1,2,100},
        {2,0,100},
        {1,3,600},
        {2,3,200}};
        System.out.println(findCheapestPrice(n,flights,src,dst,k));
    }
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // To store the graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Adding edges to the graph
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }

        // To store minimum distance
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        /* Queue storing the elements of
        the form {stops, {node, dist}} */
        Queue<int[]> q = new LinkedList<>();

        // Add the source to the queue
        q.offer(new int[]{0, src, 0});

        // Until the queue is empty
        while (!q.isEmpty()) {

            // Get the element from the queue
            int[] p = q.poll();

            int stops = p[0]; // stops
            int node = p[1]; // node
            int dist = p[2]; // distance

            /* If the number of stops taken exceed k,
            skip and move to the next element */
            if (stops > k) continue;

            // Traverse all the neighbors
            for (int[] neighbor : adj.get(node)) {

                int adjNode = neighbor[0]; // Adjacent node
                int edgeWt = neighbor[1]; // Edge weight

                /* If the tentative distance to
                reach adjacent node is smaller
                than the known distance and number
                of stops doesn't exceed k */
                if (dist + edgeWt < minDist[adjNode] &&
                        stops <= k) {

                    // Update the known distance
                    minDist[adjNode] = dist + edgeWt;

                    // Add the new element to the queue
                    q.offer(new int[]{stops + 1, adjNode, dist + edgeWt});
                }
            }
        }

        // If the destination is unreachable, return -1
        if (minDist[dst] == Integer.MAX_VALUE)
            return -1;

        // Otherwise, return the result
        return minDist[dst];
    }
}
/*
Complexity Analysis:
Time Complexity: O((N+M*K)) (where N is the number of cities, M is the number of flight (edges),
and K is the max. number of stops allowed)

Creating the graph takes O(M) time.
Each node can be inserted into the queue up to k+1 times (0 stops, 1 stop, ..., up to k stops)
making it take O(N*k).
For each node processed in the queue, we iterate over all its adjacent nodes (edges).
If there are E edges in total and each edge can be considered at most k+1 times, the total
number of edge considerations takes O(M*k) time.
Space Complexity: O(M+N*K)

Storing the graph takes O(M) space.
Array to store minimum distance takes O(N) space.
Queue will store N*K elements in the worst case taking O(N*K) space.
 */