/*
You are given an array A of N integers and an integer B. Count the number of pairs (i,j)
such that A[i] + A[j] = B and i ≠ j.

Since the answer can be very large, return the remainder after dividing the count with 109+7.

Note - The pair (i,j) is same as the pair (j,i) and we need to count it only once.

Problem Constraints

1 <= N <= 10^5
1 <= A[i] <= 10^9
1 <= B <= 10^9

A = [3, 5, 1, 2]
B = 8
o/p = 1
The only pair is (1, 2) which gives sum 8

 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
       int[] A = {3,5,1,2};
       int B = 8;
        System.out.println(solve(A,B));
    }
    public static int solve(int[] A, int B) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for(int i=0; i<A.length; i++)
        {
            int target = B - A[i];
            if(map.containsKey(target))
            {
                count += map.get(target);
            }
            if(map.containsKey(A[i]))
            {
                map.put(A[i],map.get(A[i])+1);
            }
            else map.put(A[i],1);
        }
        int mod  = 1000000007;
        return count % mod;
    }
}

// TC : O(N)
// SC : O(N)