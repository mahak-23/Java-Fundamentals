package greedy_algorithms;

/*
 * =============================================================================
 * Greedy Algorithms — Local Best Choice
 * =============================================================================
 *
 * IDEA
 *   At each step, pick the option that looks best NOW. Works when local
 *   optimum leads to global optimum (must prove or recognize the pattern).
 *
 * ACTIVITY SELECTION
 *   Sort meetings by end time. Pick next meeting that starts after last ends.
 *   Example: [(1,3), (2,5), (4,6)] → pick (1,3) and (4,6) → 2 meetings
 *
 * FRACTIONAL KNAPSACK
 *   Sort items by value/weight ratio. Take highest ratio first (can take fractions).
 *   Example: items (value/weight): (60/10=6), (100/20=5), (120/30=4)
 *
 * COMPLEXITY:  Usually O(n log n) due to sorting
 * =============================================================================
 */

import java.util.Arrays;

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

        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;
        System.out.println("Fractional knapsack => " + fractionalKnapsack(values, weights, capacity));
    }
}
