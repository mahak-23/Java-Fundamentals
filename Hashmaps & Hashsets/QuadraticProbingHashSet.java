package hashmapschain;

/**
 * Simple HashSet built on top of `QuadraticProbingHashMap`.
 *
 * This wrapper stores unique elements as keys in the underlying map,
 * using a dummy value to represent presence.
 */
public class QuadraticProbingHashSet<E> {
    private final QuadraticProbingHashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public QuadraticProbingHashSet() {
        map = new QuadraticProbingHashMap<>();
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
