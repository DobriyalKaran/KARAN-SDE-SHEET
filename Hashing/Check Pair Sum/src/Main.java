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
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<B.length; i++){
            if(map.containsKey(B[i]))
            {
                map.put(B[i], map.get(B[i])+1);
            }
            else map.put(B[i],1);
        }
        for(int i=0; i<B.length; i++){
            int target = A - B[i];
            if(target == B[i])
            {
                if(map.get(target)>1) return 1;
            }
            else {
                if(map.containsKey(target)) return 1;
            }
        }
        return 0;
    }
}

// TC : O(N)
// SC : O(N)