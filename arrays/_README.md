# Arrays

> **Difficulty:** Intermediate | **Prerequisites:** [java basics](../java%20basics/_README.md) | **Time:** 2-3 hours | **Topics:** indexing, searching, sorting, matrix operations

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Arrays are fixed-size collections of elements of the same type. They are one of the simplest and fastest data structures in Java.

## Why arrays matter
- They provide direct index-based access.
- They are memory-efficient for fixed-size data.
- They are commonly used in sorting, searching, and matrix problems.

## Array basics
```java
int[] nums = {1, 2, 3, 4};
System.out.println(nums[0]);
System.out.println(nums.length);
```

## Common operations
### 1. Traversal
```java
int[] nums = {1, 2, 3, 4};
for (int i = 0; i < nums.length; i++) {
    System.out.println(nums[i]); // Output: 1 2 3 4
}
```

### 2. Search
```java
public int findIndex(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i; // Found at index i
        }
    }
    return -1; // Not found
}
int[] nums = {10, 20, 30};
System.out.println(findIndex(nums, 20)); // Output: 1
```

### 3. Binary search (requires sorted array)
```java
int[] sorted = {1, 3, 5, 7, 9};
int index = Arrays.binarySearch(sorted, 5);
System.out.println(index); // Output: 2
```

### 4. Sorting
```java
int[] nums = {5, 2, 8, 1};
Arrays.sort(nums);
System.out.println(Arrays.toString(nums)); // Output: [1, 2, 5, 8]
```

### 5. Copying arrays
```java
int[] original = {1, 2, 3, 4, 5};
int[] copy = Arrays.copyOf(original, 3);
System.out.println(Arrays.toString(copy)); // Output: [1, 2, 3]
```

### 6. Sum and average
```java
int[] nums = {10, 20, 30};
int sum = 0;
for (int value : nums) {
    sum += value;
}
System.out.println(sum); // Output: 60
System.out.println(sum / nums.length); // Output: 20 (average)
```

### 7. Reverse an array
```java
int[] nums = {1, 2, 3, 4};
int left = 0, right = nums.length - 1;
while (left < right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
    left++;
    right--;
}
System.out.println(Arrays.toString(nums)); // Output: [4, 3, 2, 1]
```

## CRUD, size, and iteration notes
- Arrays support create/read/update operations directly through indexing.
- Delete is not built-in; you typically rebuild or shift elements.
- Use `arr.length` to get the size of an array.
- Iterate with `for`, `for-each`, or while loops.

```java
int[] values = {10, 20, 30};
values[1] = 25; // update
System.out.println(values.length); // size
for (int value : values) {
    System.out.println(value);
}
```

## Multidimensional arrays
```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6}
};
System.out.println(matrix[1][2]);
```

## Jagged arrays
```java
int[][] jagged = {
    {1, 2},
    {3},
    {4, 5, 6}
};
```

## Complexity
- Access by index: O(1)
- Search by value: O(n)
- Insert/delete in middle: O(n) because elements shift

## Interview notes
- Arrays are fixed-size and use zero-based indexing.
- Use `.length` to find array size.
- `ArrayIndexOutOfBoundsException` happens when index is invalid.
- Arrays are homogeneous; every element must be the same type.

## Quick reference: array operations

### Basic operations
- Access: direct indexing `arr[i]` - O(1)
- Traversal: for loop or for-each
- Search: linear search, binary search (on sorted arrays)
- Modify: direct assignment `arr[i] = value`
- Length: `arr.length`

### Array utilities (java.util.Arrays)
- Sort: `Arrays.sort()`
- Search: `Arrays.binarySearch()`
- Copy: `Arrays.copyOf()`, `Arrays.copyOfRange()`
- Fill: `Arrays.fill()`
- Convert: `Arrays.asList()`, `Arrays.toString()`
- Equality: `Arrays.equals()`
