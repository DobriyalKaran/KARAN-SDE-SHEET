/*
Problem Description

Shaggy has an array A consisting of N elements. We call a pair of distinct
 indices in that array a special if elements at those indices in the array are equal.


Shaggy wants you to find a special pair such that the distance between that pair
is minimum. Distance between two indices is defined as |i-j|. If there is no special
pair in the array, then return -1.

Problem Constraints

1 <= |A| <= 10^5

A = [7, 1, 3, 4, 1, 7]
o/p=3

Here we have 2 options:
1. A[1] and A[4] are both 1 so (1,4) is a special pair and |1-4|=3.
2. A[0] and A[5] are both 7 so (0,5) is a special pair and |0-5|=5.
Therefore the minimum possible distance is 3
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] A = {7, 1, 3, 4, 1, 7};
        System.out.println(solve(A));
    }
    public static int solve(int[] A) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int dist = A.length;
        for(int i=0; i<A.length; i++)
        {
            if(map.containsKey(A[i]))
            {
                int curIndex = i;
                int seen_idx = map.get(A[i]);
                dist = Math.min(dist, (curIndex - seen_idx));
            }
            else map.put(A[i],i);
        }
        if(dist == A.length) dist = -1;
        return dist;
    }
}
// TC : O(N)
// SC : O(N)