/*
Problem Description

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message denoted by string A containing digits, determine the total number of
ways to decode it modulo 109 + 7.



Problem Constraints

1 <= length(A) <= 105



Input Format

The first and the only argument is a string A.



Output Format

Return an integer, representing the number of ways to decode the string modulo 109 + 7.



Example Input

Input 1:

 A = "12"
Input 2:

 A = "8"


Example Output

Output 1:

 2
Output 2:

 1


Example Explanation

Explanation 1:

 Given encoded message "12", it could be decoded as "AB" (1, 2) or "L" (12).
 The number of ways decoding "12" is 2.
Explanation 2:

 Given encoded message "8", it could be decoded as only "H" (8).
 The number of ways decoding "8" is 1.
 */

import java.util.*;
public class Main {
    static int MOD = 1000000007;
    public static void main(String[] args) {
        String A = "12";
        int n = A.length();
        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);
        System.out.println(waysOfDecode(A,0,dp,n));
    }
    public static int waysOfDecode(String A, int index, int[] dp, int n)
    {
        // Base Case
        if(index == n) return 1;
        // if it is 0 no contribution
        if(A.charAt(index) == '0') return 0;
        // Memoization
        if(dp[index] != -1) return dp[index];

        // single digit
        int ways = waysOfDecode(A,index+1,dp,n) % MOD;

        // Double Digit
        if(index < n-1)
        {
            int num = Integer.parseInt(A.substring(index,index+2));
            if(num >= 10 && num <= 26)
            {
                ways = (ways + waysOfDecode(A,index+2,dp,n)) % MOD;
            }
        }
        return dp[index] = ways;
    }
}
// TC : O(N)
// SC : O(N)