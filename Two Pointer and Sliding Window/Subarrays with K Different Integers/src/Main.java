/*
Given an integer array nums and an integer k, return the number of good subarrays of nums.

A good array is an array where the number of different integers in that array is exactly k.

For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.



Example 1:

Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2],
[2,3], [1,2,1], [2,1,2], [1,2,1,2]
Example 2:

Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


Constraints:

1 <= nums.length <= 2 * 10^4
1 <= nums[i], k <= nums.length
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] A = {1,2,1,2,3};
        int k = 2;
        System.out.println(f(A,k) - f(A,k-1));
    }
    public static int f(int[] nums, int k) {
        if(k < 0) return 0;
        int left = 0;
        int right = 0;
        int count = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        while(right < nums.length)
        {
            map.put(nums[right], map.getOrDefault(nums[right],0)+1);
            while(map.size() > k)
            {
                map.put(nums[left],map.get(nums[left])-1);
                if(map.get(nums[left]) == 0) map.remove(nums[left]);
                left++;
            }
            count = count + (right - left) + 1;
            right++;
        }
        return count;
    }
}

// TC : O(2N)
// SC : O(N)