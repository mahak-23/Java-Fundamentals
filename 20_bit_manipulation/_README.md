# Bit Manipulation

Bit manipulation is the process of working directly with the individual bits that make up integers. It is useful for writing faster, more memory-efficient code in tasks such as flags, masks, low-level optimization, cryptography, and competitive programming.

## Why Bit Manipulation Matters

- It enables fast operations for checking, setting, clearing, and toggling bits.
- It helps compress data and represent sets or flags efficiently.
- It makes simple arithmetic tricks possible through shifts.
- It is widely used in algorithms, system programming, and interviews.

## Decimal to Binary Conversion

To convert a decimal number to binary:

1. Repeatedly divide the number by 2.
2. Record the remainder each time.
3. Read the remainders in reverse order.

Example: 13

- 13 ÷ 2 = 6 remainder 1
- 6 ÷ 2 = 3 remainder 0
- 3 ÷ 2 = 1 remainder 1
- 1 ÷ 2 = 0 remainder 1

Binary: 1101

## Binary to Decimal Conversion

To convert a binary number to decimal, multiply each bit by $2^n$ based on its position and sum the values.

Example:

- 1101 = $1\times2^3 + 1\times2^2 + 0\times2^1 + 1\times2^0$
- = $8 + 4 + 0 + 1 = 13$

## Java Example: Conversion Helpers

```java
public static String decimalToBinary(int n) {
    return Integer.toBinaryString(n);
}

public static int binaryToDecimal(String binary) {
    return Integer.parseInt(binary, 2);
}
```

## How Computers Store Numbers

Java stores integers using a fixed number of bits. For example, an `int` uses 32 bits. Each bit is either 0 or 1, and signed values are represented using two's complement.

## One's and Two's Complement

- One's complement flips every bit.
- Two's complement is obtained by taking the one's complement and adding 1.

Example for 5:

- Binary: `0101`
- One's complement: `1010`
- Two's complement: `1011` (which represents -5 in signed 4-bit form)

## Bitwise Operators

| Operator | Name | Description |
| --- | --- | --- |
| `&` | AND | 1 if both bits are 1 |
| `|` | OR | 1 if at least one bit is 1 |
| `^` | XOR | 1 if bits differ |
| `~` | NOT | Inverts every bit |
| `<<` | Left shift | Multiplies by $2^n$ |
| `>>` | Right shift | Divides by $2^n$ |

## Common Bit Manipulation Tricks

### Check if the i-th bit is set

```java
public static boolean isBitSet(int n, int position) {
    return (n & (1 << position)) != 0;
}
```

### Set a bit

```java
public static int setBit(int n, int position) {
    return n | (1 << position);
}
```

### Clear a bit

```java
public static int clearBit(int n, int position) {
    return n & ~(1 << position);
}
```

### Toggle a bit

```java
public static int toggleBit(int n, int position) {
    return n ^ (1 << position);
}
```

### Divide by 2 using bit shifting

```java
public static int divideByTwo(int n) {
    return n >> 1;
}
```

### Check whether a number is odd or even

```java
public static boolean isOdd(int n) {
    return (n & 1) == 1;
}
```

### Check whether a number is a power of two

```java
public static boolean isPowerOfTwo(int n) {
    return n > 0 && (n & (n - 1)) == 0;
}
```

### Count set bits

```java
public static int countSetBits(int n) {
    int count = 0;
    while (n > 0) {
        n &= (n - 1);
        count++;
    }
    return count;
}
```

### Set and unset the rightmost set bit

```java
public static int setRightmostUnsetBit(int n) {
    return n | (n + 1);
}

public static int unsetRightmostSetBit(int n) {
    return n & (n - 1);
}
```

### Swap two numbers using XOR

```java
public static int[] swapUsingXor(int a, int b) {
    a = a ^ b;
    b = a ^ b;
    a = a ^ b;
    return new int[]{a, b};
}
```

### Divide without using `*`, `/`, or `%`

```java
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
```

## Example File

- [BitManipulationExample.java](BitManipulationExample.java)

## Interview Problems

- Power of two
- Count set bits
- Find the missing number
- Find the single non-duplicate element
- Generate subsets using bitmasks
