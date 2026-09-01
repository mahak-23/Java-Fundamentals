# collections

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [03_oops](../03_oops/_README.md)

The Java Collection Framework provides ready-made data structures for storing and manipulating groups of objects. It is the foundation for lists, sets, maps, and many interview problems.

## How to create common collections in Java

### List

```java
import java.util.ArrayList;
import java.util.List;

List<String> names = new ArrayList<>();
names.add("Java");
names.add("Python");
```

### Set

```java
import java.util.HashSet;
import java.util.Set;

Set<String> uniqueNames = new HashSet<>();
uniqueNames.add("Java");
uniqueNames.add("Java");
```

### Map

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> scores = new HashMap<>();
scores.put("Alice", 90);
scores.put("Bob", 85);
```

## Common operations

### List operations

```java
names.add("C");        // create
names.get(0);           // read
names.set(0, "C");     // update
names.remove(0);        // delete
```

### Set operations

```java
uniqueNames.add("Java");     // create
uniqueNames.contains("Java"); // read/search
uniqueNames.remove("Java");  // delete
```

### Map operations

```java
scores.put("Alice", 90);   // create/update
scores.get("Alice");      // read
scores.put("Alice", 95);   // update
scores.remove("Alice");   // delete
```

### Iteration patterns

```java
for (String name : names) {
    System.out.println(name);
}

for (Map.Entry<String, Integer> entry : scores.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

## Engineer checklist

- Know when to use `List`, `Set`, and `Map`
- Be comfortable with CRUD-style operations for each interface
- Understand the difference between ordered and unordered collections

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| `ArrayList.get()` | O(1) | O(1) |
| `ArrayList.add()` (append) | O(1) amortized | O(1) |
| `LinkedList.add()` / `remove()` | O(1) at ends | O(1) |
| `HashSet.add()` / `contains()` | O(1) average | O(1) |
| `HashMap.get()` / `put()` | O(1) average | O(1) |

## Files

| File | Content |
|------|---------|
| `CollectionsConcepts.java` | List, Set, Map, iteration, Collectors basics |

## Core interfaces

| Interface | Ordered | Duplicates | Use when |
|-----------|---------|------------|----------|
| `List` | Yes | Yes | Index access, sequence matters |
| `Set` | No* | No | Uniqueness matters |
| `Map` | No* | Keys unique | Key → value lookup |

## CRUD quick reference

| Action | List | Set | Map |
|--------|------|-----|-----|
| Create | `add()` | `add()` | `put(k,v)` |
| Read | `get(i)` | `contains()` | `get(k)` |
| Update | `set(i,v)` | — | `put(k,newV)` |
| Delete | `remove()` | `remove()` | `remove(k)` |

## Interview questions

1. ArrayList vs LinkedList?
2. HashSet vs TreeSet?
3. When use Map over List?

## Next module

→ [06_stack/_README.md](../06_stack/_README.md)
