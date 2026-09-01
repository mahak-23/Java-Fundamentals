# arrays

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md)

Arrays store multiple values in a fixed-size sequence. They are one of the simplest and fastest data structures when you know the size in advance.

## How to create an array in Java

```java
int[] numbers = {1, 2, 3, 4};
String[] names = new String[3];
```

## Common operations

### Create and initialize

```java
int[] numbers = {1, 2, 3, 4};
String[] names = new String[3];
```

### Access an element

```java
System.out.println(numbers[0]);
```

### Update an element

```java
numbers[1] = 10;
```

### Traverse the array

```java
for (int value : numbers) {
    System.out.println(value);
}
```

### CRUD-style array operations

```java
int[] arr = {1, 2, 3};

// Create/append
int[] updated = new int[arr.length + 1];
System.arraycopy(arr, 0, updated, 0, arr.length);
updated[arr.length] = 4;

// Read
System.out.println(updated[0]);

// Update
updated[1] = 99;

// Delete (simulate by shifting)
int[] removed = new int[updated.length - 1];
for (int i = 1, j = 0; i < updated.length; i++, j++) {
    removed[j] = updated[i];
}
```

### Insert in the middle (simulate)

```java
int[] inserted = new int[arr.length + 1];
for (int i = 0; i < 2; i++) inserted[i] = arr[i];
inserted[2] = 50;
for (int i = 2; i < arr.length; i++) inserted[i + 1] = arr[i];
```

## Engineer checklist

- Know how to create, access, update, and traverse arrays
- Understand that arrays are fixed-size and insertion/deletion in the middle is O(n)
- Be comfortable with 1D and 2D arrays and matrix traversal patterns

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Access by index | O(1) | O(1) |
| Search | O(n) | O(1) |
| Insert/delete in middle | O(n) | O(1) |
| Traverse | O(n) | O(1) |
| Matrix traversal | O(m × n) | O(1) |

## Algorithm files

| File | Algorithm | Idea | Time | Space |
|------|-----------|------|------|-------|
| `ArraysExample.java` | Array basics | Creation, traversal, indexing | O(n) | O(1) |
| `JaggedArraysExample.java` | Jagged arrays | Rows with different lengths | — | — |
| `PrefixSum.java` | Prefix sum | `sum(l,r) = prefix[r] - prefix[l-1]` | O(n) build, O(1) query | O(n) |
| `MaxSubarraySum.java` | Kadane's algorithm | Max contiguous subarray sum | O(n) | O(1) |
| `SpiralMatrix.java` | Spiral traversal | Four boundaries, shrink after each side | O(m×n) | O(1) |
| `TransposeMatrix.java` | Transpose | Swap `[i][j]` with `[j][i]` | O(m×n) | O(m×n) |
| `WaveMatrix.java` | Wave print | Alternate column down/up | O(m×n) | O(1) |
| `PrefixSumMatrix.java` | 2D prefix sum | Rectangle sum in O(1) after build | O(m×n) | O(m×n) |
| `TrapRainWater.java` | Two pointer | Trap water between bars | O(n) | O(1) |

## Key patterns

- **Prefix sum** — range queries, subarray sum problems
- **Kadane** — maximum subarray, stock problems
- **Two pointer** — sorted arrays, trapping water, palindrome
- **Matrix traversal** — spiral, wave, transpose

## Interview questions

1. Why is array access O(1) but insert in middle O(n)?
2. What is Kadane's algorithm and when do you reset `currentSum`?
3. How does prefix sum answer `sum(l, r)` in O(1)?
4. Two-pointer vs sliding window — what's the difference?

## Next module

→ [07_collections/_README.md](../07_collections/_README.md)
