/*
There are n children standing in a line. Each child is assigned a rating value given in the
integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:

Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
Example 2:

Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.


Constraints:

n == ratings.length
1 <= n <= 2 * 104
0 <= ratings[i] <= 2 * 104
 */

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] ratings = {1,0,2};
        System.out.println(candy(ratings));
    }
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Give each child 1 candy initially
        Arrays.fill(candies, 1);

        // Scan from left to right, if the current child's rating is greater than the previous one,
        // give the current child one more candy than the previous one.
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Scan from right to left, if the current child's rating is greater than the next one
        // and it doesn't already have more candies than the next one, give it one more candy.
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1]) {
                candies[i] = candies[i + 1] + 1;
            }
        }

        // Sum up the candies for all children
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }
}

// TC : O(N)
// SC : O(N)