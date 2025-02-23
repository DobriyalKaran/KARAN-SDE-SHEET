/*
Given an integer array A containing N distinct integers.
Find the number of unique pairs of integers in the array whose XOR is equal to B.

NOTE:
Pair (a, b) and (b, a) is considered to be the same and should be counted once.

Problem Constraints
1 <= N <= 10^5
1 <= A[i], B <= 10^7

A = [5, 4, 10, 15, 7, 6]
B = 5
o/p = 1

Explanation 1:
(10 ^ 15) = 5

 */

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] A = {5,4,10,15,7,6};
        int B = 5;
        System.out.println(solve(A,B));
    }
    public static int solve(int[] A, int B) {
        int res = 0;
        HashSet<Integer> hashset = new HashSet<>();
        for(int i=0; i<A.length; i++)
        {
            if(hashset.contains(B ^ A[i]))
            {
                res++;
            }
            else
            {
                hashset.add(A[i]);
            }
        }
        return res;
    }
}
// TC : O(N)
// SC : O(N)