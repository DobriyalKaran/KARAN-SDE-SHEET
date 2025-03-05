/*
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining
a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such
that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

Example 1:

Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
Output: 120
Explanation: The subset chosen is the first and fourth job.
Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
Example 2:



Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
Output: 150
Explanation: The subset chosen is the first, fourth and fifth job.
Profit obtained 150 = 20 + 70 + 60.
Example 3:



Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
Output: 6


Constraints:

1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
1 <= startTime[i] < endTime[i] <= 10^9
1 <= profit[i] <= 10^4

 */


import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        int[] startTime = {1,2,3,3}, endTime = {3,4,5,6}, profit = {50,10,40,70};
        System.out.println(jobScheduling(startTime,endTime,profit));
    }
    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];

        // Step 1: Store jobs and sort by start time
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // Sort by start time

        // Step 2: Min Heap (endTime, totalProfit)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int maxProfit = 0;

        // Step 3: Process each job
        for (int[] job : jobs) {
            int start = job[0], end = job[1], jobProfit = job[2];

            // Remove jobs that ended before this job starts
            while (!pq.isEmpty() && pq.peek()[0] <= start) {
                maxProfit = Math.max(maxProfit, pq.poll()[1]); // Update max profit
            }

            // Add current job with updated max profit
            pq.offer(new int[]{end, maxProfit + jobProfit});
        }

        // Step 4: Get max profit from heap
        while (!pq.isEmpty()) {
            maxProfit = Math.max(maxProfit, pq.poll()[1]);
        }

        return maxProfit;
    }
}

// TC : O(NlogN)
// SC : O(N)