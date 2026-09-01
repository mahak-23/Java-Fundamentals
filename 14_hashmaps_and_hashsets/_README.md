# hashmaps_and_hashsets

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [07_collections](../07_collections/_README.md)

Hash maps and hash sets are used for fast lookup, frequency counting, and deduplication. They work by hashing keys so that average lookup time stays near O(1).

## How to create them in Java

### HashMap

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> ages = new HashMap<>();
ages.put("Alice", 25);
ages.put("Bob", 30);
```

### HashSet

```java
import java.util.HashSet;
import java.util.Set;

Set<String> names = new HashSet<>();
names.add("Java");
names.add("Python");
```

## Common operations

### HashMap operations

```java
ages.put("Alice", 25);   // create
ages.get("Alice");      // read
ages.put("Alice", 26);   // update
ages.remove("Alice");   // delete
```

### HashSet operations

```java
names.add("Java");      // create
names.contains("Java"); // read/search
names.remove("Java");   // delete
```

### Iteration and CRUD-style workflow

```java
for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

## Engineer checklist

- Know how to create, read, update, and delete entries in maps and sets
- Understand how `hashCode()` and `equals()` affect lookup behavior
- Be able to explain collision handling and when to choose a map vs a set

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| `put()` / `get()` / `remove()` | O(1) average | O(1) |
| `contains()` in `HashSet` | O(1) average | O(1) |
| Iteration | O(n) | O(1) |
| Worst-case with collisions | O(n) | O(n) |

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

→ [02_exception_handling/_README.md](../02_exception_handling/_README.md)
