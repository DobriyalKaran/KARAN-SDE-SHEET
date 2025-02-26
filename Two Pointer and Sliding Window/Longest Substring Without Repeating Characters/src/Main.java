/*
Given a string s, find the length of the longest substring without duplicate characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.

 */

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String  s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }
    public static int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[256]; // Array to store last seen indices of characters
        Arrays.fill(lastSeen, -1); // Initialize with -1 (character not seen)

        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            if (lastSeen[currentChar] != -1) {
                left = Math.max(left, lastSeen[currentChar] + 1); // Move left pointer to avoid duplicate
            }

            lastSeen[currentChar] = right; // Update last seen index
            maxLength = Math.max(maxLength, right - left + 1); // Update max length
        }

        return maxLength;
    }
}
// TC : O(N)
// SC : O(256)