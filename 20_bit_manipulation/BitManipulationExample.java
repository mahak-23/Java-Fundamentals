public class BitManipulationExample {
    public static void main(String[] args) {
        int value = 13; // 1101 in binary
        int position = 2;

        System.out.println("Original value: " + value);
        System.out.println("Binary form: " + decimalToBinary(value));
        System.out.println("Binary to decimal: " + binaryToDecimal("1101"));
        System.out.println("Is power of two? " + isPowerOfTwo(value));
        System.out.println("Set bit count: " + countSetBits(value));
        System.out.println("Bit at position " + position + " is set? " + isBitSet(value, position));
        System.out.println("After setting bit: " + setBit(value, position));
        System.out.println("After clearing bit: " + clearBit(value, position));
        System.out.println("After toggling bit: " + toggleBit(value, position));
        System.out.println("Divide by 2: " + divideByTwo(value));
        System.out.println("Is odd? " + isOdd(value));
        System.out.println("Set rightmost unset bit: " + setRightmostUnsetBit(10));
        System.out.println("Unset rightmost set bit: " + unsetRightmostSetBit(10));

        int[] swapped = swapUsingXor(3, 5);
        System.out.println("Swapped values: " + swapped[0] + ", " + swapped[1]);
        System.out.println("Division without operators: " + divideWithoutOperators(20, 3));
    }

    public static String decimalToBinary(int n) {
        return Integer.toBinaryString(n);
    }

    public static int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    public static boolean isBitSet(int n, int position) {
        return (n & (1 << position)) != 0;
    }

    public static int setBit(int n, int position) {
        return n | (1 << position);
    }

    public static int clearBit(int n, int position) {
        return n & ~(1 << position);
    }

    public static int toggleBit(int n, int position) {
        return n ^ (1 << position);
    }

    public static int divideByTwo(int n) {
        return n >> 1;
    }

    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }

    public static int setRightmostUnsetBit(int n) {
        return n | (n + 1);
    }

    public static int unsetRightmostSetBit(int n) {
        return n & (n - 1);
    }

    public static int[] swapUsingXor(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        return new int[]{a, b};
    }

    public static int divideWithoutOperators(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }

        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int quotient = 0;

        while (a >= b) {
            long temp = b;
            int multiple = 1;

            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            a -= temp;
            quotient += multiple;
        }

        return sign * quotient;
    }
}
