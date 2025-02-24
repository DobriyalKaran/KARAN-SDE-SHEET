/*
Problem Description

Given an array A of N integers.
Find the length of the longest subarray in the array which sums to zero.

If there is no subarray which sums to zero then return 0.

Problem Constraints

1 <= N <= 10^5
-109 <= A[i] <= 10^9

 A = [1, -2, 1, 2]
 o/p = 3
 Explanation 1:

 [1, -2, 1] is the largest subarray which sums up to 0.
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
       int[] A = {1, -2, 1, 2};
        System.out.println(solve(A));
    }
    public static int solve(int[] A) {
        int dist = 0;
        HashMap<Long, Integer> map = new HashMap<>();
        long pfSum = 0;
        for(int i=0; i<A.length; i++)
        {
            pfSum+=A[i];
            if(pfSum == 0) dist = Math.max(dist,i+1);

            if(map.containsKey(pfSum))
            {
                int idx = map.get(pfSum);
                dist = Math.max(dist, i - idx);
            }
            else map.put(pfSum,i);
        }
        return dist;
    }
}
// TC : O(N)
// SC : O(N)