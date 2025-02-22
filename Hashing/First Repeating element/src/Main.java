/*
Given an integer array A of size N, find the first repeating element in it.

We need to find the element that occurs more than once and whose index of the first
occurrence is the smallest.

If there is no repeating element, return -1.


Problem Constraints
1 <= N <= 10^5
1 <= A[i] <= 10^9

 A = [10, 5, 3, 4, 3, 5, 6] , o/p = 5;
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] A = {6, 10, 5, 4, 9, 120};
        System.out.println(solve(A));
    }
    public  static int solve(int[] A){
        HashMap<Integer,Integer> map = new HashMap<>();
        int index = A.length;
        for(int i=0; i<A.length; i++)
        {
            if(map.containsKey(A[i])){
                index = Math.min(index,map.get(A[i]));
            }
            else {
                map.put(A[i],i);
            }
        }
        if(index == A.length) return -1;
        return A[index];
    }
}
// TC : O(N)
// SC : O(N)