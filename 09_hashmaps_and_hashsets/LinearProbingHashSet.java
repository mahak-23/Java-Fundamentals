package hashmaps_and_hashsets;

/**
 * Simple HashSet built on top of `LinearProbingHashMap`.
 *
 * Linear probing set notes:
 * - The set stores each unique element as a key in an underlying hash map.
 * - A dummy value is used to represent presence.
 * - This avoids duplicate hash logic and keeps the set implementation small.
 */
public class LinearProbingHashSet<E> {
    private final LinearProbingHashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public LinearProbingHashSet() {
        map = new LinearProbingHashMap<>();
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

    public int size() {
        return map.size();
    }
}
