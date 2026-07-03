# Java Collections

> **Difficulty:** Intermediate | **Prerequisites:** [java basics](../java%20basics/_README.md), [oops](../oops/_README.md) | **Time:** 1-2 hours | **Topics:** List, Set, Map, iteration, streams

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
The Java Collection Framework provides reusable data structures for storing and manipulating groups of objects.

## Core interfaces
- `List` - ordered, allows duplicates
- `Set` - unordered, no duplicates
- `Map` - key-value pairs

## List operations
```java
List<String> names = new ArrayList<>();
names.add("A"); // add to end
names.add(0, "Z"); // add at index 0
names.set(0, "B"); // update
names.remove(0); // remove by index
names.remove("A"); // remove by value
System.out.println(names.get(0)); // access by index
System.out.println(names.size()); // Output: number of elements
System.out.println(names.isEmpty()); // Output: false
System.out.println(names.contains("B")); // Output: true
names.clear(); // remove all elements
```

## Set operations
```java
Set<String> uniqueNames = new HashSet<>();
uniqueNames.add("A");
uniqueNames.add("A"); // duplicate ignored
uniqueNames.add("B");
System.out.println(uniqueNames.size()); // Output: 2
System.out.println(uniqueNames.contains("A")); // Output: true
uniqueNames.remove("A");
System.out.println(uniqueNames.isEmpty()); // Output: false
```

## Map operations
```java
Map<String, Integer> ages = new HashMap<>();
ages.put("Alice", 25);
ages.put("Bob", 30);
System.out.println(ages.get("Alice")); // Output: 25
System.out.println(ages.getOrDefault("Charlie", 0)); // Output: 0
ages.remove("Bob");
System.out.println(ages.size()); // Output: 1
System.out.println(ages.containsKey("Alice")); // Output: true
System.out.println(ages.containsValue(25)); // Output: true
ages.clear(); // remove all
System.out.println(ages.isEmpty()); // Output: true
```

## CRUD operations
- Create: `add()` / `put()`
- Read: `get()`, `contains()`, iteration
- Update: `set()` / `put()` with new value
- Delete: `remove()`

## Common implementations and operations
- `ArrayList` - fast random access with `get(index)`, `add(index, value)`, `remove(index)`
- `LinkedList` - fast insertion/deletion in middle, use `addFirst()`, `addLast()`, `removeFirst()`, `removeLast()`
- `HashSet` - fast unique-value storage, use `add()`, `remove()`, `contains()`
- `TreeSet` - sorted unique values, use `first()`, `last()`, `floor()`, `ceiling()`
- `HashMap` - fast key-based lookup, use `put()`, `get()`, `remove()`, `containsKey()`, `values()`, `keySet()`, `entrySet()`

## Iteration examples
```java
for (String name : names) {
    System.out.println(name);
}

for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}
```

## CRUD, size, and iteration notes
- CRUD operations in collections are usually done with `add()`, `get()`, `set()`, and `remove()`.
- Use `size()` to get the number of elements in a collection.
- Use `iterator()` or enhanced for-loops to traverse collections.
- Arrays use `.length`, while collections use `.size()`.

```java
List<Integer> nums = new ArrayList<>();
nums.add(10);
nums.add(20);
nums.set(0, 15);
System.out.println(nums.size());
for (int n : nums) {
    System.out.println(n);
}
```

## Complexity overview
- `ArrayList.get()` : O(1)
- `LinkedList.get()` : O(n)
- `HashSet.add()` / `contains()` : average O(1)
- `HashMap.get()` / `put()` : average O(1)

## Interview notes
- Use `List` when order matters and duplicates are allowed.
- Use `Set` when uniqueness matters.
- Use `Map` when you need fast key-value lookup.
- Remember: arrays use `.length`, collections use `.size()`.

## Quick reference: operations by type

### List operations
- Add: `add()`, `add(index, value)`
- Remove: `remove()`, `remove(index)`, `removeIf()`
- Get: `get()`, `getFirst()`, `getLast()`
- Search: `contains()`, `indexOf()`, `lastIndexOf()`
- Modify: `set()`, `clear()`
- Check: `isEmpty()`, `size()`

### Set operations
- Add: `add()`
- Remove: `remove()`, `removeIf()`, `clear()`
- Search: `contains()`
- Check: `isEmpty()`, `size()`

### Map operations
- Add/Update: `put()`, `putIfAbsent()`, `putAll()`
- Get: `get()`, `getOrDefault()`, `keySet()`, `values()`, `entrySet()`
- Remove: `remove()`, `clear()`
- Search: `containsKey()`, `containsValue()`
- Check: `isEmpty()`, `size()`
