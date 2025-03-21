/*
Problem Description

You are the trainer of a gym and there are A people who come to your gym.

Some of them are friends because they walk together, and some of them are friends because they
talk together.
But people become possessive about each other, so a person cannot walk with one friend and talk
with another. Although he can walk with two or more people or talk with two or more people.

You being the trainer, decided to suggest each one of the 2 possible diets. But friends, being
friends will always have the same diet as all the other friends are having.

Find and return the number of ways you can suggest each of them a diet.

As the number of ways can be huge, return the answer modulo 109+7.

NOTE: If there is any person who walks with one person and talks with the another person,
then you may return 0.

Problem Constraints

1 <= A, N, M <= 105

1 <= B[i][0], B[i][1], C[i][0], C[i][1] <= A

Input Format

The first argument contains an integer A, representing the number of people.
The second argument contains a 2-D integer array B of size N x 2, where B[i][0] and B[i][1]
are friends because they walk together.
The third argument contains a 2-D integer array C of size M x 2, where C[i][0] and C[i][1]
are friends because they talk together.

Output Format

Return an integer representing the number of ways to suggest one of the two diets to the people.

Example Input

Input 1:

 A = 4
 B = [
       [1, 2]
     ]
 C = [
       [3, 4]
     ]
Input 2:

 A = 3
 B = [
       [1, 2]
     ]
 C = [
       [1, 3]
     ]


Example Output

Output 1:

 4
Output 2:

 0


Example Explanation

Explanation 1:

 There are four ways to suggest the diet:
 Diet-1 to (1, 2) and Diet-2 to (3, 4).
 Diet-1 to (1, 2) and Diet-1 to (3, 4).
 Diet-2 to (1, 2) and Diet-1 to (3, 4).
 Diet-2 to (1, 2) and Diet-2 to (3, 4).

Explanation 2:

 Person 1 walks with person 2 and talks with person 3. So, we will return 0.
 */

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int A = 4;
        int[][] B = {{1,2}};
        int[][] C = {{3,4}};
        System.out.println(solve(A,B,C));
    }
    public static int solve(int A, int[][] B, int[][] C) {
        int[] par = new int[A+1];
        HashSet<Integer> set = new HashSet<>();
        for(int[] arr: B) {
            set.add(arr[0]);
            set.add(arr[1]);
        }
        for(int[] arr: C) {
            if(set.contains(arr[0]) || set.contains(arr[1])) return 0;
        }
        for(int i=1; i<=A; i++) par[i] = i;
        for(int[] pair: B) {
            int a = getRoot(pair[0], par);
            int b = getRoot(pair[1], par);
            if(a!=b) par[a] = b;
        }
        for(int[] pair: C) {
            int a = getRoot(pair[0], par);
            int b = getRoot(pair[1], par);
            if(a!=b) par[a] = b;
        }
        int cnt = 0;
        for(int i=1; i<=A; i++) {
            if(i==par[i]) cnt++;
        }
        int mod = 1000000007;
        int ans = 1;
        while(cnt>0) {
            ans = (ans*2)%mod;
            cnt--;
        }
        return ans;
    }

    private static int getRoot(int x, int[] par) {
        if(x==par[x]) return x;
        int ans = getRoot(par[x], par);
        par[x] = ans;
        return ans;
    }
}
// TC : O(A + B + C + log A)
// SC : O(A + C)