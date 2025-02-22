/*

Given an array A of N integers, return the number of unique elements in the array.

Problem Constraints
1 <= N <= 105
1 <= A[i] <= 109

A = [3, 4, 3, 6, 6]
o/p = 3
 */

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int[] A = {3, 4, 3, 6, 6};
        int ans = solve(A);
        System.out.println(ans);
    }
    public static int solve(int[] A) {
        HashSet<Integer> st = new HashSet<>();
        for(int i=0; i<A.length; i++) st.add(A[i]);
        return st.size();
    }
}