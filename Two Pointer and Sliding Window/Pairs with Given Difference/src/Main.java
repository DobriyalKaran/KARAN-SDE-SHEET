/*
Problem Description

Given an one-dimensional integer array A of size N and an integer B.

Count all distinct pairs with difference equal to B.

Here a pair is defined as an integer pair (x, y), where x and y are both numbers
in the array and their absolute difference is B.

Problem Constraints
1 <= N <= 10^4
0 <= A[i], B <= 10^5

 */


import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] A = {1, 5, 3, 4, 2};
        int B = 3;
        System.out.println(solve(A,B));
    }
    public static int solve(int[] A, int B) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<String> seenPairs = new HashSet<>();
        int count = 0;

        for (int num : A) {
            if (set.contains(num + B) && !seenPairs.contains((num) + "_" + (num + B))) {
                count++;
                seenPairs.add((num) + "_" + (num + B));
            }
            if (set.contains(num - B) && !seenPairs.contains((num - B) + "_" + (num))) {
                count++;
                seenPairs.add((num - B) + "_" + (num));
            }
            set.add(num);
        }

        return count;
    }
}
// TC : O(N)
// SC : O(N)