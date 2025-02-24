/*
Problem Description

Given two arrays of integers A and B, Sort A in such a way that the relative
order among the elements will be the same as those are in B.
For the elements not present in B, append them at last in sorted order.

Return the array A after sorting from the above method.

NOTE: Elements of B are unique.

Problem Constraints
1 <= length of the array A <= 100000
1 <= length of the array B <= 100000
-10^9 <= A[i] <= 10^9

A = [1, 2, 3, 4, 5, 4]
B = [5, 4, 2]
Output 1:
[5, 4, 4, 2, 1, 3]

Explanation 1:

Since 2, 4, 5, 4 of A are present in the array B.  So Maintaining the relative order of B.
Thus, [5, 4, 4, 2] and appending the remaining element (1, 3) in sorted order.

The Final array is [5, 4, 4, 2, 1, 3]
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 4};
        int[] B = {5,4,2};
        int[] ans = solve(A,B);
        for(int i : ans) System.out.println(i);
    }
    public static int[] solve(int[] A, int[] B) {
        List<Integer> ls = new ArrayList<>();
        HashMap<Integer,Integer> mapA = new HashMap<>();
        HashMap<Integer,Integer> mapB = new HashMap<>();
        List<Integer> remNumber = new ArrayList<>();
        for(int i=0; i<A.length; i++)
        {
            if(mapA.containsKey(A[i]))
            {
                mapA.put(A[i], mapA.get(A[i])+1);
            }
            else mapA.put(A[i],1);
        }

        for(int i=0; i<B.length; i++)
        {
            if(mapB.containsKey(B[i]))
            {
                mapB.put(B[i],mapB.get(B[i])+1);
            }
            else mapB.put(B[i],1);
        }

        for(int i=0; i<B.length; i++)
        {
            if(mapA.containsKey(B[i]))
            {
                int freq = Math.max(mapA.get(B[i]),mapB.get(B[i]));
                while(freq > 0)
                {
                    ls.add(B[i]);
                    freq--;
                }
            }
        }

        for(int i=0; i<A.length; i++){
            if(!mapB.containsKey(A[i])){
                remNumber.add(A[i]);
            }
        }
        Collections.sort(remNumber);
        for(int i=0; i<remNumber.size(); i++) ls.add(remNumber.get(i));
        int[] ans = new int[ls.size()];
        for(int i=0; i<ans.length; i++) ans[i] = ls.get(i);
        return ans;
    }
}