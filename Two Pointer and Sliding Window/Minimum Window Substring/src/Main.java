/*
Given two strings s and t of lengths m and n respectively, return the
minimum window substring of s such that every character in t (including duplicates)
is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 10^5
s and t consist of uppercase and lowercase English letters.
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
       String s = "ADOBECODEBANC";
       String t = "ABC";
        System.out.println(minWindow(s,t));
    }
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";

        // Map to store frequency of characters in t
        HashMap<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0, minLen = Integer.MAX_VALUE, startIdx = 0;
        int required = t.length(); // Total characters needed to match

        while (right < s.length()) {
            char c = s.charAt(right);

            // If c is in tMap, reduce its count
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) - 1);
                if (tMap.get(c) >= 0) { // Only count if it's still needed
                    required--;
                }
            }

            // When all characters are matched, try to shrink the window
            while (required == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIdx = left;
                }

                char leftChar = s.charAt(left);

                // If leftChar is in tMap, restore its count
                if (tMap.containsKey(leftChar)) {
                    tMap.put(leftChar, tMap.get(leftChar) + 1);
                    if (tMap.get(leftChar) > 0) { // If we need this character back
                        required++;
                    }
                }

                left++; // Shrink the window
            }

            right++; // Expand the window
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);

    }
}

// TC : O(N + M)
// SC : O(N)