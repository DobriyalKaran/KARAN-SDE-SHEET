/*
Given an one-dimensional unsorted array A containing N integers.

You are also given an integer B, find if there exists a pair of elements
 in the array whose difference is B.

Return 1 if any such pair exists else return 0.

Problem Constraints
1 <= N <= 10^5
-103 <= A[i] <= 10^3
-105 <= B <= 10^5

A = [5, 10, 3, 2, 50, 80]
 B = 78
 o/p =  1;
 Pair (80, 2) gives a difference of 78.
 */

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] A = {-100, 20, 80};
        int B = 9;
        System.out.println(solve(A,B));
    }
    public static int solve(int[] A, int B) {
        HashSet<Integer> st = new HashSet<>();
        for(int i : A) st.add(i);

        for(int i=0; i<A.length; i++)
        {
            int value = A[i] + B;
            if(st.contains(value)) return 1;
        }
        return 0;
    }
}
// TC : O(N)
// SC : O(N)