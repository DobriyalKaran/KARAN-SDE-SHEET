/*
Problem Description

Given an Array of integers B, and a target sum A.
Check if there exists a pair (i,j) such that Bi + Bj = A and i!=j.

Problem Constraints

1 <= Length of array B <= 10^5
0 <= Bi <= 10^9
0 <= A <= 10^9

A = 8
B = [3, 5, 1, 2, 1, 2]
o/p=1

Explanation
It is possible to obtain sum 8 using 3 and 5.
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int A = 8;
        int[] B = {3, 5, 1, 2, 1, 2};
        System.out.println(solve(A,B));
    }
    public static  int solve(int A, int[] B) {
        HashSet<Integer> set = new HashSet<>();

        for (int num : B) {
            int target = A - num;
            if (set.contains(target)) return 1;  // Pair mil gaya
            set.add(num);  // Current number store kar lo
        }
        return 0;  // Pair nahi mila
    }
}

// TC : O(N)
// SC : O(N)
