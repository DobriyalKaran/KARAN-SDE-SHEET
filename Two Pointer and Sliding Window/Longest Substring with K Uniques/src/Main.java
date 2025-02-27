/*
Given a string s, you need to print the size of the longest
possible substring with exactly k unique characters. If
no possible substring exists, print -1.

Examples:

Input: s = "aabacbebebe", k = 3
Output: 7
Explanation: "cbebebe" is the longest substring with 3 distinct characters.
Input: s = "aaaa", k = 2
Output: -1
Explanation: There's no substring with 2 distinct characters.
Input: s = "aabaaab", k = 2
Output: 7
Explanation: "aabaaab" is the longest substring with 2 distinct characters.
Constraints:
1 ≤ s.size() ≤ 10^5
1 ≤ k ≤ 26
All characters are lowercase letters
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args)
    {
        String s = "aabacbebebe";
        int k = 3;
        System.out.println(longestkSubstr(s,k));
    }
    public static int longestkSubstr(String s, int k) {
        int left = 0;
        int right = 0;
        int len = -1;
        HashMap<Character,Integer> map = new HashMap<>();
        while(right < s.length())
        {
            if(map.containsKey(s.charAt(right)))
            {
                map.put(s.charAt(right), map.get(s.charAt(right))+1);
            }
            else map.put(s.charAt(right),1);
            if(map.size() > k)
            {
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                if(map.get(s.charAt(left)) == 0)
                {
                    map.remove(s.charAt(left));
                }
                left++;
            }
            if(map.size() == k)
            {
                len = Math.max(len, right - left + 1);
            }
            right++;
        }
        return len;
    }
}
// TC: O(N)
// SC : O(K)