/*
Given an array of integers A, find and return whether the given array contains
a non-empty subarray with a sum equal to 0.

If the given array contains a sub-array with sum zero return 1, else return 0.

Problem Constraints
1 <= |A| <= 100000
-10^9 <= A[i] <= 10^9

 Input 1
A = [1, 2, 3, 4, 5]
o/p =  0

Input 2
A = [4, -1, 1]
o/p = 1

Explanation 1:
    No subarray has sum 0.
Explanation 2:
    The subarray [-1, 1] has sum 0.
*/

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
       int[] A = {4,-1,1};
       int ans = solve(A);
        System.out.println(ans);
    }
    public static int solve(int[] A) {
        HashSet<Long> set = new HashSet<>();
        long[] pf = new long[A.length];
        pf[0] = A[0];
        for(int i=1; i<A.length; i++)
        {
            pf[i] = A[i] + pf[i-1];
        }
        for(int i=0; i<pf.length; i++)
        {
            if(pf[i] == 0)
            {
                return 1;
            }
            if(set.contains(pf[i]))
            {
                return 1;
            }
            set.add(pf[i]);
        }
        return 0;
    }
}
// TC : O(N)
// SC : O(N)