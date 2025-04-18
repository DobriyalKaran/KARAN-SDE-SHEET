/*
Problem Description

Given an integer array A of size N. Find the contiguous subarray within the
given array (containing at least one number) which has the largest product.

Return an integer corresponding to the maximum product possible.

NOTE: Answer will fit in 32-bit integer value.



Problem Constraints

1 <= N <= 5 * 105

-100 <= A[i] <= 100



Input Format

First and only argument is an integer array A.



Output Format

Return an integer corresponding to the maximum product possible.



Example Input

Input 1:

 A = [4, 2, -5, 1]
Input 2:

 A = [-3, 0, -5, 0]


Example Output

Output 1:

 8
Output 2:

 0


Example Explanation

Explanation 1:

 We can choose the subarray [4, 2] such that the maximum product is 8.
Explanation 2:

 0 will be the maximum product possible.
 */

public class Main {
    public static void main(String[] args) {
        int[] A = {4, 2, -5, 1};
        System.out.println(maxProduct(A));
    }
    public static  int maxProduct(final int[] A) {
        int pre = 1;
        int suff = 1;
        int ans = Integer.MIN_VALUE;
        for(int i=0; i<A.length; i++)
        {
            if(pre == 0) pre = 1;
            if(suff == 0) suff = 1;
            pre = pre * A[i];
            suff = suff * A[A.length-i-1];
            ans = Math.max(ans,Math.max(pre,suff));
        }
        return ans;
    }
}
// TC : O(N)
// SC : O(1)