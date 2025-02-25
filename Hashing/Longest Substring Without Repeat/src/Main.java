/*
Problem Description

Determine the "GOOD"ness of a given string A, where the "GOOD"ness is defined by the length
of the longest substring that contains no repeating characters. The greater the length of
this unique-character substring, the higher the "GOOD"ness of the string.

Your task is to return an integer representing the "GOOD"ness of string A.

Note: The solution should be achieved in O(N) time complexity, where N is the length of the string.

Input 1:
A = "abcabcbb"
Output 1:
3
Explanation 1:
Substring "abc" is the longest substring without repeating characters in string A.
 */


import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        String A = "dadbc";
        System.out.println(lengthOfLongestSubstring(A));
    }
    public static  int lengthOfLongestSubstring(String A) {
        int left = 0;
        int right = 0;
        int len = 0;
        if(A.length() == 1) return 1;
        HashSet<Character> set = new HashSet<>();
        while(right < A.length()){
            if(set.contains(A.charAt(right)))
            {
                len = Math.max(len,(right-left));
                set.remove(A.charAt(left));
                left++;
            }
            else{
                set.add(A.charAt(right));
                right++;
            }
        }
        len = Math.max(len, right-left);
        return len;
    }
}

// TC: O(N)
// SC : O(N)