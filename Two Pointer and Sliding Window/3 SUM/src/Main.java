/*
Problem Description

Given an array A of N integers, find three integers in A such that the sum is closest
to a given number B. Return the sum of those three integers.

Assume that there will only be one solution.



Problem Constraints

-108 <= B <= 108
1 <= N <= 104
-108 <= A[i] <= 108


Input Format

First argument is an integer array A of size N.

Second argument is an integer B denoting the sum you need to get close to.



Output Format

Return a single integer denoting the sum of three integers which is closest to B.



Example Input

Input 1:

A = [-1, 2, 1, -4]
B = 1
Input 2:


A = [1, 2, 3]
B = 6


Example Output

Output 1:

2
Output 2:

6


Example Explanation

Explanation 1:

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2)
Explanation 2:

 Take all elements to get exactly 6.
 */

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] A = {-1, 2, 1, -4};
        int B = 1;
        System.out.println(threeSumClosest(A, B));
    }
    public static int threeSumClosest(int[] A, int B) {
        // Step 1: Array ko sort kar lo (O(N log N))
        Arrays.sort(A);

        int n = A.length;
        int closestSum = A[0] + A[1] + A[2]; // Bas ek initial value le lo

        // Step 2: Har element ko fix karo (O(N²))
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;     // Dusra pointer
            int right = n - 1;    // Teesra pointer

            while (left < right) {
                int currentSum = A[i] + A[left] + A[right];

                // 1. Agar exact target mil gaya, to wahi return karo
                if (currentSum == B) {
                    return currentSum;
                }

                // 2. Sabse nazdeek wala track karo
                if (Math.abs(currentSum - B) < Math.abs(closestSum - B)) {
                    closestSum = currentSum;
                }

                // 3. Adjust pointers
                if (currentSum < B) {
                    left++; // Sum chhota hai, to left ++ (bada banane ke liye)
                } else {
                    right--; // Sum bada hai, to right -- (chhota banane ke liye)
                }
            }
        }

        return closestSum; // Jo sabse nazdeek sum hai, wahi return karo
    }
}
// TC : O(N^2)
// SC : O(1)