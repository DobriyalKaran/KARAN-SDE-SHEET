/*
Given start, end, and an array arr of n numbers. At each step, the start is multiplied by any
number in the array and then a mod operation with 100000 is done to get the new start.



Find the minimum steps in which the end can be achieved starting from the start. If it is not
possible to reach the end, then return -1.


Examples:
Input: arr = [2, 5, 7], start = 3, end = 30

Output: 2

Explanation:

Step 1: 3*2 = 6 % 100000 = 6

Step 2: 6*5 = 30 % 100000 = 30

Therefore, in minimum 2 multiplications, we reach the end number which is treated as a destination
node of a graph here.

Input: arr = [3, 4, 65], start = 7, end = 66175

Output: 4

Explanation:

Step 1: 7*3 = 21 % 100000 = 21

Step 2: 21*3 = 6 % 100000 = 63

Step 3: 63*65 = 4095 % 100000 = 4095

Step 4: 4095*65 = 266175 % 100000 = 66175

Therefore, in minimum 4 multiplications we reach the end number which is treated as a
destination node of a graph here.

Constraints:
  1 <= n <= 10^4
  1 <= arr[i] <= 10^4
  1 <= start, end < 10^5

 */
import java.util.*;
public class Main {
    public static void main(String[] args)
    {
        int start = 3, end = 30;
        int[] arr = {2, 5, 7};

        /* Creating an instance of
        Solution class */
        Solution sol = new Solution();

        /* Function call to determine minimum
        multiplications to reach end */
        int ans = sol.minimumMultiplications(arr, start, end);

        // Output
        System.out.println("The minimum multiplications to reach end is: " + ans);
    }
}

class Solution {

    /* Function to determine minimum
    multiplications to reach end */
    public int minimumMultiplications(int[] arr,
                                      int start, int end) {

        // Base case
        if (start == end) return 0;

        // Size of array
        int n = arr.length;
        int mod = 100000; // mod

        /* Array to store minimum
        steps (distance array) */
        int[] minSteps = new int[mod];
        Arrays.fill(minSteps, Integer.MAX_VALUE);

        /* Queue to implement
        Dijkstra's algorithm */
        Queue<int[]> q = new LinkedList<>();

        // Mark initial position as 0
        minSteps[start] = 0;

        // Add the initial node to queue
        q.add(new int[]{0, start});

        // Until the queue is empty
        while (!q.isEmpty()) {

            // Get the element
            int[] p = q.poll();

            int steps = p[0]; // steps
            int val = p[1]; // value

            // Check for adjacent neighbors
            for (int i = 0; i < n; i++) {

                // Value of neighboring node
                int num = (val * arr[i]) % mod;

                // If end is reached, return steps taken
                if (num == end) return steps + 1;

                /* Check if the current steps taken is
                less than earlier known steps */
                if (steps + 1 < minSteps[num]) {

                    // Update the known steps
                    minSteps[num] = steps + 1;

                    // Insert the pair in queue
                    q.add(new int[]{steps + 1, num});
                }
            }
        }

        /* Return -1 if reaching
        end is not possible */
        return -1;
    }
}
/*
Complexity Analysis:
Time Complexity: O(100000*N) (where N is the length of given array)
A simple BFS traversal is performed taking O(V+E) time, where V represents nodes (which can be 100000 in the worst case) and E represents the number of edges (transitions) (which can be 100000*N, since for every value, N edges are formed). This makes the overall time complexity as O(100000*N).

Space Complexity: O(100000*N)

Queue space will store all the transitions possible in worst-case resulting in O(100000*N) space.
The array to store minimum steps takes O(100000) space.
 */