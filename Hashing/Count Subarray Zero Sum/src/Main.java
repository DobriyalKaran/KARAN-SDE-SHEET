/*
Problem Description

Given an array A of N integers.

Find the count of the subarrays in the array which sums to zero. Since the answer can be very
large, return the remainder on dividing the result with 109+7

Problem Constraints

1 <= N <= 105
-109 <= A[i] <= 109

 A = [1, -1, -2, 2]
 o/p = 3
 The subarrays with zero-sum are [1, -1], [-2, 2] and [1, -1, -2, 2].

 */

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] A = {1, -1, -2, 2};
        int ans = solve(A);
        System.out.println(ans);
    }
    public static int solve(int[] A) {
        int count = 0;
        long pfSum = 0;
        HashMap<Long,Integer> map = new HashMap<>();
        map.put(0L,1);
        for(int i=0; i<A.length; i++)
        {
            pfSum += A[i];
            if(map.containsKey(pfSum))
            {
                count += map.get(pfSum);
            }
            map.put(pfSum, map.getOrDefault(pfSum,0)+1);
        }
        return count;
    }
}
// TC : O(N)
// SC : ON)