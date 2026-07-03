package hashmapschain;

import java.util.Objects;

/**
 * Simple open-addressing HashMap using linear probing.
 *
 * Linear probing collision handling:
 * - The table is an array of entries.
 * - If a hash bucket is occupied, the algorithm probes the next slot sequentially
 *   until it finds an empty or deleted slot.
 * - This keeps all entries in the same table array, avoiding linked node overhead.
 *
 * Complexity:
 * - Average-case get/put/remove: O(1)
 * - Worst-case get/put/remove: O(n) if clustering occurs or table is nearly full
 * - Space: O(m), where m = table capacity
 *
 * Notes:
 * - This implementation uses tombstones to preserve probe sequences after removal.
 * - Resizing helps avoid primary clustering and keeps load factor low.
 */
public class LinearProbingHashMap<K, V> {
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
    private int size; // number of active entries (excludes deleted)
    private final float loadFactor;

    @SuppressWarnings("unchecked")
    public LinearProbingHashMap(int initialCapacity, float loadFactor) {
        table = (Entry<K, V>[]) new Entry[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public LinearProbingHashMap() {
        this(16, 0.5f); // lower default load factor to reduce clustering
    }

    private int indexFor(Object key) {
        int h = Objects.hashCode(key);
        return (h & 0x7fffffff) % table.length;
    }

    public V get(K key) {
        int idx = indexFor(key);
        for (int i = 0; i < table.length; i++) {
            int j = (idx + i) % table.length;
            Entry<K, V> e = table[j];
            if (e == null) return null; // stop at empty slot
            if (!e.deleted && Objects.equals(e.key, key)) return e.value;
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

        int idx = indexFor(key);
        int firstDeleted = -1;
        for (int i = 0; i < table.length; i++) {
            int j = (idx + i) % table.length;
            Entry<K, V> e = table[j];
            if (e == null) {
                int insertIdx = (firstDeleted != -1) ? firstDeleted : j;
                table[insertIdx] = new Entry<>(key, value);
                size++;
                return;
            }
            if (e.deleted) {
                if (firstDeleted == -1) firstDeleted = j;
            } else if (Objects.equals(e.key, key)) {
                e.value = value; // replace
                return;
            }
        }

        // table full (shouldn't happen due to resize), try resizing and insert
        resize(table.length * 2);
        put(key, value);
    }

    public V remove(K key) {
        int idx = indexFor(key);
        for (int i = 0; i < table.length; i++) {
            int j = (idx + i) % table.length;
            Entry<K, V> e = table[j];
            if (e == null) return null;
            if (!e.deleted && Objects.equals(e.key, key)) {
                V val = e.value;
                e.key = null;
                e.value = null;
                e.deleted = true; // tombstone
                size--;
                return val;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Entry<K, V>[] old = table;
        table = (Entry<K, V>[]) new Entry[newCapacity];
        size = 0;
        for (Entry<K, V> e : old) {
            if (e != null && !e.deleted) {
                put(e.key, e.value);
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
