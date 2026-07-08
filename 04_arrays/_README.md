# arrays

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md)

Most DSA problems use arrays. Read each `.java` file — algorithm explanation, example, and run flow are in the file header comments.

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

→ [05_collections/_README.md](../05_collections/_README.md)
