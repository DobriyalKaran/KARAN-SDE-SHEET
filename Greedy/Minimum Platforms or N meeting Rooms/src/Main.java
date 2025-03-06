/*
You are given timings of n meetings in the form of (start[i], end[i]) where start[i]
is the start time of meeting i and end[i] is the finish time of meeting i. Return the
maximum number of meetings that can be accommodated in a single meeting room, when only
one meeting can be held in the meeting room at a particular time.

Note: The start time of one chosen meeting can't be equal to the end time of the other
chosen meeting.

Examples :

Input: start[] = [1, 3, 0, 5, 8, 5], end[] =  [2, 4, 6, 7, 9, 9]
Output: 4
Explanation: Maximum four meetings can be held with given start and end timings.
The meetings are - (1, 2), (3, 4), (5,7) and (8,9)
Input: start[] = [10, 12, 20], end[] = [20, 25, 30]
Output: 1
Explanation: Only one meetings can be held with given start and end timings.
Input: start[] = [1, 2], end[] = [100, 99]
Output: 1
Constraints:
1 ≤ n ≤ 10^5
0 ≤ start[i] < end[i] ≤ 10^6
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end = {2, 4, 6, 7, 9, 9};
        System.out.println(maxMeetings(start,end));
    }
    // Comparator function to sort meetings based on end times
    static class MeetingComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            // Sort by end time in ascending order
            return Integer.compare(a[1], b[1]);
        }
    }
    public static int maxMeetings(int start[], int end[]) {
        int n = start.length;
        // List to store meetings
        List<int[]> meetings = new ArrayList<>();

        // Fill the meetings list with start and end times
        for (int i = 0; i < n; i++) {
            meetings.add(new int[]{start[i], end[i]});
        }

        // Sort the meetings based on the custom comparator
        Collections.sort(meetings, new MeetingComparator());

        // The end time of last selected meeting
        int limit = meetings.get(0)[1];
        // Initialize count
        int count = 1;

        /*Iterate through the meetings
        to select the maximum number
        of non-overlapping meetings*/
        for (int i = 1; i < n; i++) {
            /*If the current meeting starts
            after the last selected meeting ends*/
            if (meetings.get(i)[0] > limit) {
                /*Update the limit to the end
                time of the current meeting*/
                limit = meetings.get(i)[1];
                // Increment count
                count++;
            }
        }

        // Return count
        return count;
    }

}
// TC : O(N + NLOGN)
// SC : O(N)