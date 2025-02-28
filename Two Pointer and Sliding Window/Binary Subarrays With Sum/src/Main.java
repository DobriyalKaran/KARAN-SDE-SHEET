/*
Given a binary array nums and an integer goal, return the number of non-empty
subarrays with a sum goal.

A subarray is a contiguous part of the array.

Example 1:

Input: nums = [1,0,1,0,1], goal = 2
Output: 4
Explanation: The 4 subarrays are bolded and underlined below:
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
[1,0,1,0,1]
Example 2:

Input: nums = [0,0,0,0,0], goal = 0
Output: 15

Constraints:
1 <= nums.length <= 3 * 10^4
nums[i] is either 0 or 1.
0 <= goal <= nums.length
 */



public class Main {
    public static void main(String[] args) {
        int[] A = {1,0,1,0,1};
        int goal = 2;
        System.out.println(numSubarraysWithSum(A,goal)-numSubarraysWithSum(A,goal-1));
    }
    public static int numSubarraysWithSum(int[] nums, int goal) {
        if(goal < 0) return 0;
        int left = 0;
        int right = 0;
        int count = 0;
        int curSum = 0;
        while(right < nums.length)
        {
            curSum+=nums[right];
            while(curSum > goal){
                curSum = curSum - nums[left];
                left++;
            }
            count+= right-left+1;
            right++;
        }
        return count;
    }
}
// TC : O(N)
// SC : O(1)