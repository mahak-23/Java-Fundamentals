# linkedlist

> **Prerequisites:** [05_collections](../05_collections/_README.md)

Pointer-based structure — see `LinkedListProblems.java` header for each algorithm explained.

## Files

| File | Content |
|------|---------|
| `LinkedListBasics.java` | Node structure, basic operations |
| `LinkedListProblems.java` | Reverse, cycle, merge, middle |

## Algorithms in LinkedListProblems

| Method | Technique | Example |
|--------|-----------|---------|
| `reverseList` | Three pointers | 1→2→3→4 → 4→3→2→1 |
| `hasCycle` | Fast/slow pointers | Detect loop in O(1) space |
| `mergeSortedLists` | Recursive merge | 1→3→5 + 2→4→6 |
| `removeDuplicates` | Skip same-value nodes | 1→1→2→2→3 → 1→2→3 |
| `findMiddle` | Fast/slow | Middle of 1→2→3→4→5 is 3 |

## Interview questions

1. Array vs linked list — tradeoffs?
2. How does Floyd's cycle detection work?
3. Why dummy node simplifies head operations?

## Next module

→ [09_hashmaps_and_hashsets/_README.md](../09_hashmaps_and_hashsets/_README.md)
