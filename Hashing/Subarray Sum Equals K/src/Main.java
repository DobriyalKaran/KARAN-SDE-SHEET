/*
Given an array of integers A and an integer B.
Find the total number of subarrays having sum equals to B.

Problem Constraints
 1 <= length of the array <= 50000
-1000 <= A[i] <= 1000

A = [1, 0, 1]
B = 1

o/p = 4
Explanation 1:
[1], [1, 0], [0, 1] and [1] are four subarrays having sum 1.
 */

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] A = {1, 0, 1};
        int B = 1;
        System.out.println(solve(A,B));
    }
    public static int solve(int[] A, int B) {
        HashMap<Integer, Integer> prevSum = new HashMap<Integer, Integer>();
        prevSum.put(0, 1);
        int currSum = 0, ans = 0;
        for(int i = 0 ; i < A.length ; i++){
            currSum += A[i];
            if(prevSum.containsKey(currSum - B)){
                ans += prevSum.get(currSum - B);
            }
            prevSum.put(currSum, prevSum.getOrDefault(currSum, 0) + 1);
        }
        return ans;
    }
}
// TC : O(N)
// SC : O(N)