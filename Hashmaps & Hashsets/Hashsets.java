package hashmapschain;

/*
Hashsets.java

Demonstrates `HashSetWithChaining`, a set abstraction built on top of `SeparateChainingHashMap`.

Algorithm notes:
- A hash set stores only unique keys, not key/value pairs.
- The set implementation reuses a map and stores a dummy value for each key.
- This avoids duplicating the hash table logic and keeps set operations simple.

Complexity summary (separate chaining underlying map):
- Time: add/contains/remove -> O(1) average, O(n) worst-case
- Space: O(n + m)

Memory notes:
- The set stores keys inside an underlying map entry structure.
- Each add operation uses the map's hash table to detect duplicates efficiently.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hashsets {
    public static void main(String[] args) {
        HashSetWithChaining<String> set = new HashSetWithChaining<>();

        System.out.println("=== HashSet demo (Separate Chaining) ===");
        System.out.println("Add 'apple': " + set.add("apple"));
        System.out.println("Add 'banana': " + set.add("banana"));
        System.out.println("Add 'apple' again (should be false): " + set.add("apple"));

        System.out.println("Contains 'apple'? " + set.contains("apple"));
        System.out.println("Contains 'cherry'? " + set.contains("cherry"));

        System.out.println("Remove 'banana': " + set.remove("banana"));
        System.out.println("Contains 'banana' after remove? " + set.contains("banana"));

        System.out.println("\nTraverse set contents:");
        set.forEach(item -> System.out.println(" - " + item));

        List<String> sortedItems = new ArrayList<>();
        set.forEach(sortedItems::add);
        Collections.sort(sortedItems);
        System.out.println("\nSorted set items: " + sortedItems);
        Collections.reverse(sortedItems);
        System.out.println("Reversed sorted set items: " + sortedItems);

        System.out.println("\nMath examples:");
        System.out.println(" Size = " + set.size());
        System.out.println(" Size * 2 = " + (set.size() * 2));

        System.out.println("Clear set and verify size:");
        set.clear();
        System.out.println("Size after clear: " + set.size());
    }
}
