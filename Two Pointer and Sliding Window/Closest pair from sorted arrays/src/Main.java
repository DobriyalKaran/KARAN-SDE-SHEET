/*
Problem Description

Given two sorted arrays of distinct integers, A and B, and an integer C,
find and return the pair whose sum is closest to C and the pair has one element from each array.

More formally, find A[i] and B[j] such that abs((A[i] + B[j]) - C) has minimum value.

If there are multiple solutions find the one with minimum i and even if there are
multiple values of j for the same i then return the one with minimum j.

Return an array with two elements {A[i], B[j]}.
Problem Constraints
1 <= length of both the arrays <= 105
1 <= A[i], B[i] <= 10^9
1 <= C <= 10^9

Input Format
The first argument given is the integer array A.
The second argument given is the integer array B.
The third argument given is integer C.

Output Format
Return an array of size 2 denoting the pair which has sum closest to C.
Example Input
Input 1:
 A = [1, 2, 3, 4, 5]
 B = [2, 4, 6, 8]
 C = 9
Input 2:
 A = [5, 10, 20]
 B = [1, 2, 30]
 C = 13

Example Output
Output 1:
 [1, 8]
Output 2:
 [10, 2]

Example Explanation
Explanation 1:
There are three pairs: (1, 8), (3, 6), (5, 4), that gives the minimum value.
Since we have to return the value with minimum i and then with minimum j. We will return [1, 8].
Explanation 2:

 [10, 2] is the only pair such abs(10+2-13) is minimum.
 */

public class Main {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {2, 4, 6, 8};
        int C = 9;
        int[] ans = solve(A,B,C);
        for(int i : ans) System.out.println(i);
    }
    public static int[] solve(int[] A, int[] B, int C) {
        int i = 0, j = B.length - 1;
        int minDiff = Integer.MAX_VALUE;
        int bestA = 0, bestB = 0;

        while (i < A.length && j >= 0) {
            int sum = A[i] + B[j];
            int diff = Math.abs(sum - C);

            // Update best pair if a closer sum is found or if same diff with a lexicographically smaller pair
            if (diff < minDiff || (diff == minDiff && (A[i] < bestA || (A[i] == bestA && B[j] < bestB)))) {
                minDiff = diff;
                bestA = A[i];
                bestB = B[j];
            }

            // Move pointers
            if (sum > C) {
                j--; // Move left in B to decrease sum
            } else {
                i++; // Move right in A to increase sum
            }
        }
        return new int[]{bestA, bestB};
    }
}