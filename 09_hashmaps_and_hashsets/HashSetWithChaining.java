package hashmaps_and_hashsets;

import java.util.function.Consumer;

/**
 * Minimal HashSet implemented on top of `SeparateChainingHashMap`.
 *
 * Separate chaining set notes:
 * - Uses the underlying map to store each element as a key with a dummy value.
 * - This avoids duplicating hash table logic and provides expected O(1) set operations.
 * - Add/contains/remove are all backed by the underlying map's collision handling.
 */
public class HashSetWithChaining<E> {
    private final SeparateChainingHashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public HashSetWithChaining() {
        map = new SeparateChainingHashMap<>();
    }

    public boolean add(E e) {
        if (map.get(e) == null) {
            map.put(e, PRESENT);
            return true;
        }
        return false;
    }

    public boolean contains(E e) {
        return map.get(e) != null;
    }

    public boolean remove(E e) {
        return map.remove(e) != null;
    }

    public void forEach(Consumer<? super E> action) {
        map.forEach((key, value) -> action.accept(key));
    }

    public void clear() {
        map.clear();
    }

    public int size() {
        return map.size();
    }
}
