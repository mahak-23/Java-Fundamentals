package greedy_algorithms;

/*
 * =============================================================================
 * Greedy Algorithms — make the best local choice
 * =============================================================================
 *
 * A greedy algorithm picks the option that looks best right now, with the hope
 * that this choice still leads to an optimal global result.
 *
 * IMPORTANT:
 *   Greedy works only when a correct ordering or choice rule can be proved.
 *   If not, dynamic programming or backtracking is often the safer approach.
 *
 * COMMON GREEDY PATTERNS:
 *   1) activity selection (sort by end time)
 *   2) interval merging (sort by start time)
 *   3) minimum platforms (arrival/departure scan)
 *   4) greedy coin change (largest denomination first)
 *   5) fractional knapsack (highest value/weight first)
 *
 * BIG IDEA:
 *   After sorting by the right key, scan once and commit to the local best
 *   choice while keeping the state valid.
 * =============================================================================
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class GreedyAlgorithmsExample {
    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;

        Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting other) {
            return Integer.compare(this.end, other.end);
        }
    }

    // Activity selection
    // Sort by finish time, then pick the next meeting that starts after the
    // last chosen meeting ends.
    // Greedy proof: choosing the earliest finishing meeting leaves the most room.
    public static int maxMeetings(Meeting[] meetings) {
        Arrays.sort(meetings);
        int count = 0;
        int lastEnd = -1;

        for (Meeting meeting : meetings) {
            if (meeting.start >= lastEnd) {
                count++;
                lastEnd = meeting.end;
            }
        }

        return count;
    }

    // Interval merging
    // Sort by start time and merge all overlapping intervals.
    // Greedy proof: once intervals overlap, their union is one continuous range.
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0].clone();

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } else {
                merged.add(current);
                current = next.clone();
            }
        }

        merged.add(current);
        return merged.toArray(new int[0][]);
    }

    // Minimum platforms required for trains
    // Sort arrivals and departures separately. Use two pointers to count how many
    // trains overlap at the same time.
    public static int minimumPlatforms(int[] arrivals, int[] departures) {
        Arrays.sort(arrivals);
        Arrays.sort(departures);

        int i = 0;
        int j = 0;
        int platforms = 0;
        int maxPlatforms = 0;

        while (i < arrivals.length) {
            if (arrivals[i] <= departures[j]) {
                platforms++;
                maxPlatforms = Math.max(maxPlatforms, platforms);
                i++;
            } else {
                platforms--;
                j++;
            }
        }

        return maxPlatforms;
    }

    // Greedy coin change
    // This works for canonical coin systems such as {1,2,5,10,...} but not all
    // coin sets. Example: with {1,3,4}, the greedy choice is not always optimal.
    public static int greedyCoinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
            }
        }

        return count;
    }

    // Fractional knapsack
    // Sort by value/weight ratio. Because fractional items are allowed, taking the
    // highest ratio items first is always optimal.
    public static double fractionalKnapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        double[][] items = new double[n][3];
        for (int i = 0; i < n; i++) {
            items[i][0] = values[i];
            items[i][1] = weights[i];
            items[i][2] = items[i][0] / items[i][1];
        }

        Arrays.sort(items, (a, b) -> Double.compare(b[2], a[2]));

        double totalValue = 0;
        int remaining = capacity;
        for (double[] item : items) {
            if (remaining == 0) {
                break;
            }

            int weight = (int) item[1];
            if (weight <= remaining) {
                totalValue += item[0];
                remaining -= weight;
            } else {
                totalValue += item[2] * remaining;
                remaining = 0;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Meeting[] meetings = {
            new Meeting(1, 4),
            new Meeting(2, 3),
            new Meeting(3, 5),
            new Meeting(4, 7),
            new Meeting(5, 9)
        };
        System.out.println("Max meetings => " + maxMeetings(meetings));

        int[][] intervals = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };
        System.out.println("Merged intervals => " + Arrays.deepToString(mergeIntervals(intervals)));

        int[] arrivals = {900, 940, 950, 1100, 1500, 1800};
        int[] departures = {910, 1200, 1120, 1130, 1900, 2000};
        System.out.println("Minimum platforms => " + minimumPlatforms(arrivals, departures));

        int[] coins = {1, 2, 5, 10};
        System.out.println("Greedy coin count for 27 => " + greedyCoinChange(coins, 27));

        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        System.out.println("Fractional knapsack => " + fractionalKnapsack(values, weights, capacity));
    }
}
