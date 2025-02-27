/*
You are visiting a farm that has a single row of fruit trees arranged from left to right.
The trees are represented by an integer array fruits where fruits[i] is the type of
fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules
that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit.
There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every
tree (including the start tree) while moving to the right. The picked fruits must
fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.



Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].


Constraints:

1 <= fruits.length <= 10^5
0 <= fruits[i] < fruits.length
 */


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        int[] A = {1,2,1};
        System.out.println(totalFruit(A));
    }
    public static int totalFruit(int[] arr) {
        int left = 0;
        int right = 0;
        int len = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        while (right < arr.length) {
            // Add the current fruit into the map
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);

            // If the window has more than 2 distinct fruits, shrink the window from the left
            if (map.size() > 2) {
                map.put(arr[left], map.get(arr[left]) - 1);
                if (map.get(arr[left]) == 0) {
                    map.remove(arr[left]);
                }
                left++;  // Shrink the window by moving the left pointer to the right
            }

            // Update the maximum length of the valid window
            len = Math.max(len, right - left + 1);

            // Expand the window by moving the right pointer
            right++;
        }
        return len;
    }
}
// TC : O(N)
// SC : O(N)