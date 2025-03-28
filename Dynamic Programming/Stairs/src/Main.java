/*
Problem Description

You are climbing a staircase and it takes A steps to reach the top.


Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Return the number of distinct ways modulo 1000000007



Problem Constraints

1 <= A <= 105



Input Format

The first and the only argument contains an integer A, the number of steps.



Output Format

Return an integer, representing the number of ways to reach the top.



Example Input

Input 1:

 A = 2
Input 2:

 A = 3


Example Output

Output 1:

 2
Output 2:

 3


Example Explanation

Explanation 1:

 Distinct ways to reach top: [1, 1], [2].
Explanation 2:

 Distinct ways to reach top: [1 1 1], [1 2], [2 1].
 */
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int mod = 1000000007;
        int A = 10;
        int[] dp = new int[A+1];
        Arrays.fill(dp,-1);
        int ans = fib(A,dp,mod);
        System.out.println(ans % mod);
    }
    public static int fib(int N, int[] dp, int mod)
    {
        if(N <= 1) return dp[N] = 1;
        if(dp[N] != -1) return dp[N];
        return dp[N] = fib(N-1,dp,mod) % mod + fib(N-2,dp,mod) % mod;
    }
}
// TC : O(N)
// SC : O(N)