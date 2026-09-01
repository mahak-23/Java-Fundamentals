package hashmaps_and_hashsets;

/*
Hashmaps.java

This demo uses `SeparateChainingHashMap` to show how map operations work with separate chaining.

Algorithm notes:
- Separate chaining stores entries in buckets where each bucket is a linked list of entries that hash to the same index.
- On insert, lookup, or removal, the bucket list is scanned linearly to find the correct entry.
- Good hash distribution and a low load factor keep each bucket short.

Complexity summary (separate chaining, average case with good hash distribution):
- Time: get/put/remove -> O(1) average, O(n) worst-case when many collisions occur
- Space: O(n + m) where n = number of entries, m = number of buckets
- Load factor α = n / m measures average chain length.

Memory notes:
- Each entry stores a key reference, value reference, and next pointer.
- The bucket array has one reference per slot.
- Resizing doubles capacity and rehashes entries when load factor exceeds threshold.
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hashmaps {
    public static void main(String[] args) {
        SeparateChainingHashMap<String, Integer> map = new SeparateChainingHashMap<>();

        System.out.println("=== HashMap demo (Separate Chaining) ===");
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        System.out.println("Size: " + map.size());
        System.out.println("Capacity (buckets): " + map.capacity());
        System.out.println("Get 'two': " + map.get("two"));

        System.out.println("Update key 'two' to 20");
        map.put("two", 20);
        System.out.println("Get 'two' after update: " + map.get("two"));

        System.out.println("Remove key 'one'");
        map.remove("one");
        System.out.println("Contains 'one'? " + map.containsKey("one"));

        System.out.println("\nInsert multiple entries to trigger resize...");
        int start = map.size();
        for (int i = 0; i < 40; i++) {
            map.put("k" + i, i);
        }
        System.out.println("Added " + (map.size() - start) + " entries.");
        System.out.println("Size after inserts: " + map.size());
        System.out.println("Capacity after inserts: " + map.capacity());

        System.out.println("\nLookup few values to show O(1) average behavior:");
        System.out.println("k0 -> " + map.get("k0"));
        System.out.println("k20 -> " + map.get("k20"));
        System.out.println("k39 -> " + map.get("k39"));

        System.out.println("\nTraverse all map entries:");
        map.forEach((key, value) -> System.out.println(" - " + key + " => " + value));

        System.out.println("\nClear the map and verify size:");
        map.clear();
        System.out.println("Size after clear: " + map.size());
    }
}
