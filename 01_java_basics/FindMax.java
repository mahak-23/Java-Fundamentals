package java_basics;

class FindMax {
    public static void main(String[] args) {
        int[] numbers = {3, 5, 7, 2, 8};
        int max = findMax(numbers);
        int secondMax = secondMax(numbers);
        System.out.println("The maximum number is: " + max);
        System.out.println("The second maximum number is: " + secondMax);
    }

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static int secondMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > max) {
                secondMax = max; // Update second max before updating max
                max = num; // Update max
            } else if (num > secondMax && num != max) {
                secondMax = num; // Update second max if it's greater than current second max and not equal to max
            }
        }
        return secondMax;
    }
}