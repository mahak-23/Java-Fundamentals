package hashmapschain;

import java.util.Objects;

/**
 * DoubleHashingHashMap.java
 *
 * This is an educational HashMap implementation using double hashing.
 * It is designed to help beginners understand how open-addressing hash tables work.
 *
 * What is double hashing?
 * - Double hashing is a collision resolution strategy for hash tables.
 * - When two keys map to the same bucket index, we compute a second hash value
 *   and use it as the step size for probing.
 * - The probe sequence is: index = (hash1(key) + i * hash2(key)) mod capacity.
 *
 * Why use double hashing?
 * - It reduces clustering compared to linear probing.
 * - It spreads collisions more evenly across the table.
 * - It provides better performance when the table is moderately full.
 *
 * Key terms:
 * - hash1: the primary hash used to locate the initial bucket.
 * - hash2: the secondary hash used as the probe step; must be non-zero and typically
 *   chosen so it is relatively prime to the table capacity.
 * - tombstone: a marker used when removing an entry from an open-addressing table.
 *
 * Complexity summary:
 * - Average case: O(1) for get/put/remove when the table has low load.
 * - Worst case: O(n) if the table becomes too full or the probe sequence scans many slots.
 * - Space: O(m) for the table array, where m is the capacity.
 *
 * Memory notes:
 * - Each table slot stores one Entry object or null.
 * - Open addressing stores entries directly in the table array rather than separate nodes.
 * - Tombstones keep probe chains intact after removals.
 */
public class DoubleHashingHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        boolean deleted;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.deleted = false;
        }
    }

    private Entry<K, V>[] table;
    private int size;
    private final float loadFactor;

    @SuppressWarnings("unchecked")
    public DoubleHashingHashMap(int initialCapacity, float loadFactor) {
        this.table = (Entry<K, V>[]) new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public DoubleHashingHashMap() {
        this(17, 0.5f);
    }

    private int hash1(Object key) {
        int h = Objects.hashCode(key);
        return (h & 0x7fffffff) % table.length;
    }

    private int hash2(Object key) {
        int h = Objects.hashCode(key);
        int result = 1 + ((h & 0x7fffffff) % (table.length - 1));
        return result;
    }

    public V get(K key) {
        int initialIndex = hash1(key);
        int step = hash2(key);

        for (int i = 0; i < table.length; i++) {
            int idx = (initialIndex + i * step) % table.length;
            Entry<K, V> e = table[idx];
            if (e == null) {
                return null;
            }
            if (!e.deleted && Objects.equals(e.key, key)) {
                return e.value;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        if ((float) (size + 1) / table.length > loadFactor) {
            resize(table.length * 2);
        }

        int initialIndex = hash1(key);
        int step = hash2(key);
        int firstDeleted = -1;

        for (int i = 0; i < table.length; i++) {
            int idx = (initialIndex + i * step) % table.length;
            Entry<K, V> e = table[idx];
            if (e == null) {
                int insertIndex = (firstDeleted != -1) ? firstDeleted : idx;
                table[insertIndex] = new Entry<>(key, value);
                size++;
                return;
            }
            if (e.deleted) {
                if (firstDeleted == -1) {
                    firstDeleted = idx;
                }
            } else if (Objects.equals(e.key, key)) {
                e.value = value;
                return;
            }
        }

        resize(table.length * 2);
        put(key, value);
    }

    public V remove(K key) {
        int initialIndex = hash1(key);
        int step = hash2(key);

        for (int i = 0; i < table.length; i++) {
            int idx = (initialIndex + i * step) % table.length;
            Entry<K, V> e = table[idx];
            if (e == null) {
                return null;
            }
            if (!e.deleted && Objects.equals(e.key, key)) {
                V value = e.value;
                e.key = null;
                e.value = null;
                e.deleted = true;
                size--;
                return value;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[newCapacity];
        size = 0;
        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.deleted) {
                put(entry.key, entry.value);
            }
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }
}
