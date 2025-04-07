/*
Find out the number of A digit positive numbers, whose digits on being added equals to a given
number B.

Note that a valid number starts from digits 1-9 except the number 0 itself. i.e. leading zeroes
are not allowed.

Since the answer can be large, output answer modulo 1000000007



Problem Constraints

1 <= A <= 1000

1 <= B <= 10000



Input Format

First argument is the integer A

Second argument is the integer B



Output Format

Return a single integer, the answer to the problem



Example Input

Input 1:

 A = 2
 B = 4
Input 2:

 A = 1
 B = 3


Example Output

Output 1:

 4
Output 2:

 1


Example Explanation

Explanation 1:

 Valid numbers are {22, 31, 13, 40}
 Hence output 4.
Explanation 2:

 Only valid number is 3
 */

public class Main {
    static int MOD = 1000000007;
    public static void main(String[] args) {
        int A = 2;
        int B = 4;
        System.out.println(solve(A,B));
    }
    public static int solve(int A, int B) {
        int[][] dp = new int[A + 1][B + 1];
        // Base case: 0 digits, sum 0 => 1 way (empty number)
        dp[0][0] = 1;

        // Fill the table
        for (int i = 1; i <= A; i++) {
            for (int s = 0; s <= B; s++) {
                for (int d = 0; d <= 9; d++) {
                    if (i == 1 && d == 0) continue; // No leading zero
                    if (s - d >= 0) {
                        dp[i][s] = (dp[i][s] + dp[i - 1][s - d]) % MOD;
                    }
                }
            }
        }

        return dp[A][B];
    }
}
// TC : O(A × B × 10)
// SC : O(A × B)