package hashmapschain;

import java.util.Objects;

/**
 * Simple open-addressing HashMap using quadratic probing.
 *
 * Quadratic probing collision handling:
 * - The table is an array of entries.
 * - On collision, the algorithm probes using an increasing quadratic offset: i^2.
 * - This reduces clustering compared to linear probing but requires a good table size.
 *
 * Complexity:
 * - Average-case get/put/remove: O(1)
 * - Worst-case get/put/remove: O(n) if the table becomes too full or poorly sized
 * - Space: O(m), where m = table capacity
 *
 * Notes:
 * - This implementation keeps the table capacity at a prime number to improve probe coverage.
 * - Removal uses tombstones so that probe sequences remain valid.
 */
public class QuadraticProbingHashMap<K, V> {
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
    public QuadraticProbingHashMap(int initialCapacity, float loadFactor) {
        int capacity = nextPrime(initialCapacity);
        table = (Entry<K, V>[]) new Entry[capacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public QuadraticProbingHashMap() {
        this(17, 0.5f);
    }

    private int indexFor(Object key) {
        int h = Objects.hashCode(key);
        return (h & 0x7fffffff) % table.length;
    }

    public V get(K key) {
        int idx = indexFor(key);
        for (int i = 0; i < table.length; i++) {
            int j = (idx + i * i) % table.length;
            Entry<K, V> e = table[j];
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
            resize(nextPrime(table.length * 2));
        }

        int idx = indexFor(key);
        int firstDeleted = -1;
        for (int i = 0; i < table.length; i++) {
            int j = (idx + i * i) % table.length;
            Entry<K, V> e = table[j];
            if (e == null) {
                int insertIndex = (firstDeleted != -1) ? firstDeleted : j;
                table[insertIndex] = new Entry<>(key, value);
                size++;
                return;
            }
            if (e.deleted) {
                if (firstDeleted == -1) {
                    firstDeleted = j;
                }
            } else if (Objects.equals(e.key, key)) {
                e.value = value;
                return;
            }
        }

        resize(nextPrime(table.length * 2));
        put(key, value);
    }

    public V remove(K key) {
        int idx = indexFor(key);
        for (int i = 0; i < table.length; i++) {
            int j = (idx + i * i) % table.length;
            Entry<K, V> e = table[j];
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
        Entry<K, V>[] old = table;
        table = (Entry<K, V>[]) new Entry[newCapacity];
        size = 0;
        for (Entry<K, V> e : old) {
            if (e != null && !e.deleted) {
                put(e.key, e.value);
            }
        }
    }

    private int nextPrime(int value) {
        while (!isPrime(value)) {
            value++;
        }
        return value;
    }

    private boolean isPrime(int value) {
        if (value <= 1) return false;
        if (value <= 3) return true;
        if (value % 2 == 0 || value % 3 == 0) return false;
        for (int i = 5; i * i <= value; i += 6) {
            if (value % i == 0 || value % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }
}
