/*
Problem Description

Surprisingly, in an alien language, they also use English lowercase letters,
 but possibly in a different order. The order of the alphabet is some permutation
 of lowercase letters.


Given an array of words A of size N written in the alien language,
and the order of the alphabet denoted by string B of size 26, return
1 if and only if the given words are sorted lexicographically in this
alien language else, return 0.

Problem Constraints
1 <= N, length of each word <= 10^5
Sum of the length of all words <= 2 * 10^6

 A = ["hello", "scaler", "interviewbit"]
 B = "adhbcfegskjlponmirqtxwuvzy"
 o/p = 1
 Explanation 1:

 The order shown in string B is: h < s < i (adhbcfegskjlponmirqtxwuvzy) for the given words.
 So, Return 1.
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        String[] A = {"hello", "scaler", "interviewbit"};
        String B = "adhbcfegskjlponmirqtxwuvzy";
        System.out.println(solve(A,B));
    }

    public static int solve(String[] A, String B) {
        // Step 1: Create a mapping of character order
        HashMap<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < B.length(); i++) {
            orderMap.put(B.charAt(i), i);
        }

        // Step 2: Compare adjacent words
        for (int i = 0; i < A.length - 1; i++) {
            if (!isOrdered(A[i], A[i + 1], orderMap)) {
                return 0;
            }
        }
        return 1;
    }
    private static  boolean isOrdered(String word1, String word2, HashMap<Character, Integer> orderMap) {
        int len1 = word1.length(), len2 = word2.length();
        int minLen = Math.min(len1, len2);

        for (int i = 0; i < minLen; i++) {
            char c1 = word1.charAt(i), c2 = word2.charAt(i);
            if (c1 != c2) {
                return orderMap.get(c1) < orderMap.get(c2);
            }
        }
        // If words are identical up to minLen, the shorter word should come first
        return len1 <= len2;
    }
}

// TC : O(N * M)
// SC : O(1)