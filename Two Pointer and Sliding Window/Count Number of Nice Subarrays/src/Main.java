/*
Given an array of integers nums and an integer k. A continuous subarray is
called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

Example 1:

Input: nums = [1,1,2,1,1], k = 3
Output: 2
Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
Example 2:

Input: nums = [2,4,6], k = 1
Output: 0
Explanation: There are no odd numbers in the array.
Example 3:

Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
Output: 16

Constraints:

1 <= nums.length <= 50000
1 <= nums[i] <= 10^5
1 <= k <= nums.length
 */

public class Main {
    public static void main(String[] args) {
        int[] nums = {1,1,2,1,1};
        int k = 3;
        for(int i=0; i<nums.length; i++)
        {
            if(nums[i] % 2 == 0) nums[i] = 0;
            else nums[i] = 1;
        }
        System.out.println(numberOfSubarrays(nums,k) - numberOfSubarrays(nums,k-1));
    }
    public static int numberOfSubarrays(int[] nums, int goal) {
        if(goal < 0) return 0;
        int left = 0;
        int right = 0;
        int count = 0;
        int curSum = 0;
        while(right < nums.length)
        {
            curSum+=(nums[right] % 2);
            while(curSum > goal){
                curSum = curSum - (nums[left] % 2);
                left++;
            }
            count+= right-left+1;
            right++;
        }
        return count;
    }
}
// TC : O(N)
// SC: O(1)