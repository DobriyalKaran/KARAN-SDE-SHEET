/*
You are given a string s and an integer k. You can choose any character of the string
and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after
performing the above operations.

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exist other ways to achieve this answer too.


Constraints:
1 <= s.length <= 10^5
s consists of only uppercase English letters.
0 <= k <= s.length

 */


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;
        System.out.println(characterReplacement(s,k));
    }
    public static int characterReplacement(String s, int k) {
        int left=0;
        int right=0;
        int maxFreq=0;
        int maxLen = 0;
        int[] arr = new int[26];
        while(right < s.length())
        {
            arr[s.charAt(right)-'A']++;
            maxFreq = Arrays.stream(arr).max().getAsInt();
            // Check if it is not inside k or exceeds
            int checkFlips = (right-left+1);
            if(checkFlips - maxFreq > k)
            {
                arr[s.charAt(left)-'A']--;
                maxFreq = 0;
                left++;
            }
            if(checkFlips - maxFreq <= k)
            {
                maxLen = Math.max(checkFlips,maxLen);
            }
            right++;
        }
        return maxLen;
    }
}

// TC : O(N)
// SC : O(26)