/*
Problem Description

Misha likes finding all Subarrays of an Array. Now she gives you an array A
of N elements and told you to find the number of subarrays of A, that have unique elements.

Since the number of subarrays could be large, return value % 109 +7.
Input 1:

A = [1, 1, 3]

Output 1:
4

Explanation 1:
Subarrays of A that have unique elements only:
[1], [1], [1, 3], [3]
 */


import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] A = {2,1,2};
        System.out.println(solve(A));
    }
    public static int solve(int[] A) {
        long count = 0;
        int right = 0;
        int left = 0;
        int mod = 1000000007;
        HashSet<Integer> set = new HashSet<>();

        while (right < A.length) {
            // Remove elements from left until A[right] is unique
            while (set.contains(A[right])) {
                set.remove(A[left]);
                left++;
            }
            // Add A[right] to the set
            set.add(A[right]);

            // Count all unique subarrays ending at 'right'
            count += (right - left + 1);
            count %= mod; // Take modulo to prevent overflow

            // Move right pointer
            right++;
        }
        return (int) count;
    }
}

// TC : O(N)
// SC : O(N)