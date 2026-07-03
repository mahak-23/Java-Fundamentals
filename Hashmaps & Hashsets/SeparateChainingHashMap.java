package hashmapschain;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Simple HashMap using separate chaining with singly-linked nodes.
 *
 * Separate chaining collision handling:
 * - The table is an array of buckets.
 * - Each bucket contains a linked list of entries that hash to that bucket.
 * - If multiple keys collide, they are stored sequentially in the linked list.
 *
 * Complexity:
 * - Average-case get/put/remove: O(1)
 * - Worst-case get/put/remove: O(n) if all keys collide into one bucket
 * - Space: O(n + m), where n = number of entries, m = number of buckets
 *
 * Notes:
 * - The implementation resizes when load factor exceeds threshold.
 * - A good hash function and proper load factor keep bucket chains short.
 */
public class SeparateChainingHashMap<K, V> {
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Node<K, V>[] buckets;
    private int size;
    private final float loadFactor;

    @SuppressWarnings("unchecked")
    public SeparateChainingHashMap(int initialCapacity, float loadFactor) {
        this.buckets = (Node<K, V>[]) new Node[initialCapacity];
        this.loadFactor = loadFactor;
        this.size = 0;
    }

    public SeparateChainingHashMap(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public SeparateChainingHashMap() {
        this(16, 0.75f);
    }

    private int indexFor(Object key) {
        int h = Objects.hashCode(key);
        return (h & 0x7fffffff) % buckets.length;
    }

    public V get(K key) {
        int idx = indexFor(key);
        for (Node<K, V> n = buckets[idx]; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                return n.value;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        int idx = indexFor(key);
        for (Node<K, V> n = buckets[idx]; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                n.value = value; // replace
                return;
            }
        }
        // insert at head
        buckets[idx] = new Node<>(key, value, buckets[idx]);
        size++;
        if ((float) size / buckets.length > loadFactor) {
            resize(buckets.length * 2);
        }
    }

    public V remove(K key) {
        int idx = indexFor(key);
        Node<K, V> prev = null;
        for (Node<K, V> n = buckets[idx]; n != null; ) {
            if (Objects.equals(n.key, key)) {
                V val = n.value;
                if (prev == null) {
                    buckets[idx] = n.next;
                } else {
                    prev.next = n.next;
                }
                size--;
                return val;
            }
            prev = n;
            n = n.next;
        }
        return null;
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (Node<K, V> bucket : buckets) {
            for (Node<K, V> n = bucket; n != null; n = n.next) {
                action.accept(n.key, n.value);
            }
        }
    }

    public void clear() {
        buckets = (Node<K, V>[]) new Node[buckets.length];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Node<K, V>[] old = buckets;
        buckets = (Node<K, V>[]) new Node[newCapacity];
        size = 0;
        for (Node<K, V> n : old) {
            for (Node<K, V> cur = n; cur != null; cur = cur.next) {
                put(cur.key, cur.value);
            }
        }
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return buckets.length;
    }
}
