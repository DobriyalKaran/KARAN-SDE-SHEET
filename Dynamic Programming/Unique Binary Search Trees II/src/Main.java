/*
Given an integer A, how many structurally unique BST's (binary search trees)
exist that can store values 1...A?



Problem Constraints

1 <= A <=18



Input Format

First and only argument is the integer A



Output Format

Return a single integer, the answer to the problem



Example Input

Input 1:

 1
Input 2:

 2


Example Output

Output 1:

 1
Output 2:

 2


Example Explanation

Explanation 1:

 Only single node tree is possible.
Explanation 2:

 2 trees are possible, one rooted at 1 and other rooted at 2.
 */



public class Main {
    public static void main(String[] args)
    {
        System.out.println(numTrees(2));
    }
    public static  int numTrees(int A) {
        int[] dp = new int[A + 1];

        dp[0] = 1; // empty tree
        dp[1] = 1; // one node

        for (int n = 2; n <= A; n++) {
            for (int i = 1; i <= n; i++) {
                dp[n] += dp[i - 1] * dp[n - i];
            }
        }

        return dp[A];
    }
}
// TC : O(N^2)
// SC: O(1) Because A is upto 18.