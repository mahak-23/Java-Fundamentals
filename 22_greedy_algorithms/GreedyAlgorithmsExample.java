package greedy_algorithms;

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
