/*
A software engineer is tasked with using the shortest job first (SJF) policy to
 calculate the average waiting time for each process. The shortest job first also known
 as shortest job next (SJN) scheduling policy selects the waiting process with the least
 execution time to run next.

Given an array of n integers representing the burst times of processes,
determine the average waiting time for all processes and return the closest
whole number that is less than or equal to the result.

Examples:
Input : bt = [4, 1, 3, 7, 2]

Output : 4

Explanation : The total waiting time is 20.

So the average waiting time will be 20/5 => 4.

Input : bt = [1, 2, 3, 4]

Output : 2

Explanation : The total waiting time is 10.

So the average waiting time will be 10/4 => 2.
 */

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] bt = {4, 1, 3, 7, 2};
        System.out.println(solve(bt));
    }
    public static long solve(int[] bt) {
        Arrays.sort(bt); // Sort the burst times in ascending order (SJF rule)
        long t = 0, wt = 0; // Use long to avoid overflow

        for (int i = 0; i < bt.length; i++) {
            wt += t; // Accumulate waiting time
            t += bt[i]; // Update total execution time
        }

        return (long) Math.floor((double) wt / bt.length); // Floor the result
    }
}
// TC : O(N)
// SC : O(1)