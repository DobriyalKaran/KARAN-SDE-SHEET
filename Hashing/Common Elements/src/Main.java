/*
Problem Description

Given two integer arrays, A and B of size N and M, respectively.
Your task is to find all the common elements in both the array.

NOTE:
Each element in the result should appear as many times as it appears in both arrays.
The result can be in any order.

Problem Constraints
1 <= N, M <= 10^5
1 <= A[i] <= 10^9


Input 1
 A = [1, 2, 2, 1]
 B = [2, 3, 1, 2]
 o/p = [1, 2, 2]

 Explanation 1:
 Elements (1, 2, 2) appears in both the array. Note 2 appears twice in both the array.

 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] A = {1,2,2,1};
        int[] B = {2,3,1,2};
        List<Integer> ans = findCommonElements(A,B);
        for(int i : ans) System.out.println(i);
    }
    public static List<Integer> findCommonElements(int[] A, int[] B) {
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        // Store frequency of elements in A
        for (int num : A) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Check common elements in B
        for (int num : B) {
            if (freqMap.containsKey(num) && freqMap.get(num) > 0) {
                result.add(num); // Add to result
                freqMap.put(num, freqMap.get(num) - 1); // Reduce count
            }
        }

        return result;
    }
}

// TC : O(N + M) (efficient for large inputs))
// SC : O(N)