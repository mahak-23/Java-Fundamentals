# Hashmaps & Hashsets

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Hash-based collections use hashing to store and retrieve data quickly.

## HashMap operations
```java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1); // add key-value
map.put("B", 2);
System.out.println(map.get("A")); // Output: 1
System.out.println(map.getOrDefault("C", 0)); // Output: 0
map.putIfAbsent("A", 10); // A already exists, no change
map.replace("A", 99); // Output: 99
map.remove("B"); // remove by key
System.out.println(map.size()); // Output: 2
System.out.println(map.containsKey("A")); // Output: true
System.out.println(map.containsValue(99)); // Output: true
System.out.println(map.isEmpty()); // Output: false
map.clear(); // remove all
```

## HashSet operations
```java
Set<String> set = new HashSet<>();
set.add("A"); // add element
set.add("B");
set.add("A"); // duplicate ignored
System.out.println(set.contains("A")); // Output: true
set.remove("B"); // remove element
System.out.println(set.size()); // Output: 1
System.out.println(set.isEmpty()); // Output: false
set.clear(); // remove all
```

## Iteration and views
```java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 2);

// keySet view
Set<String> keys = map.keySet(); // [A, B]

// values view
Collection<Integer> values = map.values(); // [1, 2]

// entrySet view
Set<Map.Entry<String, Integer>> entries = map.entrySet();
for (Map.Entry<String, Integer> entry : entries) {
    System.out.println(entry.getKey() + " -> " + entry.getValue()); // Output: A -> 1, B -> 2
}
```

## How hashing works
- A hash function converts a key into an integer.
- That integer determines where data is stored in a bucket.
- Good hash functions reduce collisions.

## Collisions
When two keys produce the same hash value, a collision occurs. Common strategies are:
- Separate chaining
- Linear probing
- Quadratic probing
- Double hashing

## Complexity
- Average `put` / `get` / `remove`: O(1)
- Worst case: O(n)

## CRUD, size, and iteration notes
- Create: `put()` for maps and `add()` for sets.
- Read: `get()` for maps and `contains()` for sets.
- Update: `put()` with an existing key updates the value.
- Delete: `remove()` removes a key or element.
- Use `size()` to get the current number of entries.
- Iterate with `forEach`, `entrySet()`, or `keySet()`.

```java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 2);
map.put("A", 3); // update
System.out.println(map.size());
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

## Interview notes
- `HashMap` stores key-value pairs; `HashSet` stores only unique values.
- `equals()` and `hashCode()` should be implemented consistently for custom objects.
- Hash tables are fast because they use buckets instead of scanning everything.

## Quick reference: hash operations

### HashMap operations
- Add/Update: `put()`, `putIfAbsent()`, `putAll()`, `replace()`
- Get: `get()`, `getOrDefault()`, `keySet()`, `values()`, `entrySet()`
- Remove: `remove()`, `clear()`
- Search: `containsKey()`, `containsValue()`
- Check: `isEmpty()`, `size()`

### HashSet operations
- Add: `add()`, `addAll()`
- Remove: `remove()`, `removeIf()`, `clear()`
- Search: `contains()`
- Check: `isEmpty()`, `size()`

### Iteration methods
- `forEach()` with lambda
- Enhanced for-loop: `for (K k : map.keySet())`
- Iterator: `map.entrySet().iterator()`
- Stream: `map.values().stream()`
