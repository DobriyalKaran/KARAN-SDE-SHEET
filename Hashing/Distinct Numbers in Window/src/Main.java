/*
You are given an array of N integers, A1, A2 ,..., AN and an integer B. Return the of
 count of distinct numbers in all windows of size B.

Formally, return an array of size N-B+1 where i'th element in this array contains
 number of distinct elements in sequence Ai, Ai+1 ,..., Ai+B-1.

NOTE: if B > N, return an empty array.

Problem Constraints
1 <= N <= 10^6
1 <= A[i] <= 10^9
A = [1, 2, 1, 3, 4, 3]
B = 3

O/P = [2, 3, 3, 2]
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] A = {1, 2, 1, 3, 4, 3};
        int B = 3;
        List<Integer> ls = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<B; i++)
        {
            if(map.containsKey(A[i]))
            {
                map.put(A[i],map.get(A[i])+1);
            }
            else map.put(A[i],1);
        }
        ls.add(map.size());
        int start = 1;
        int end = B;
        while(end < A.length)
        {
            int prevValue = A[start-1];
            map.put(prevValue,map.get(prevValue)-1);
            if(map.containsKey(A[end]))
            {
                map.put(A[end], map.get(A[end])+1);
            }
            else map.put(A[end],1);
            if(map.get(prevValue) == 0)
            {
                map.remove(prevValue);
            }
            ls.add(map.size());
            start++;
            end++;
        }

        int[] ans = new int[ls.size()];
        for (int i = 0; i < ls.size(); i++) {
            ans[i] = ls.get(i);
        }
        for(int i : ans) System.out.println(i);
    }
}

// TC : O(N)
// SC : O(N)