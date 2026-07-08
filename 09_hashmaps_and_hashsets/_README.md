# hashmaps_and_hashsets

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [05_collections](../05_collections/_README.md)

Most used DS in interviews — O(1) average lookup.

## Files

| File | Content |
|------|---------|
| `Hashmaps.java` | HashMap operations demo |
| `Hashsets.java` | HashSet operations demo |
| `SeparateChainingHashMap.java` | Custom map with chaining |
| `LinearProbingHashMap.java` | Open addressing — linear |
| `QuadraticProbingHashMap.java` | Open addressing — quadratic |
| `DoubleHashingHashMap.java` | Open addressing — double hash |
| `HashSetWithChaining.java` | Custom set with chaining |

## DSA patterns

| Pattern | Example problems |
|---------|------------------|
| Frequency count | Group anagrams, top K frequent |
| Two sum | Store `target - num` in map |
| Seen before | Contains duplicate (HashSet) |
| Group by key | `computeIfAbsent` |

## How hashing works

1. `hashCode()` → bucket index
2. `equals()` → resolve collisions
3. Load factor ~0.75 → rehash when full
4. Collisions: chaining (linked list in bucket) or probing

## Interview questions

1. HashMap vs HashSet?
2. Why override `hashCode` AND `equals`?
3. Chaining vs probing — tradeoffs?
4. Worst case O(n) — when?

## Next module

→ [10_exception_handling/_README.md](../10_exception_handling/_README.md)
