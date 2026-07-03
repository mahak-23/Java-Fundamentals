package hashmapschain;

/**
 * Simple HashSet built on top of `DoubleHashingHashMap`.
 *
 * This wrapper stores unique elements using the double hashing map implementation.
 * Each element is stored as a map key with a dummy value.
 */
public class DoubleHashingHashSet<E> {
    private final DoubleHashingHashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public DoubleHashingHashSet() {
        map = new DoubleHashingHashMap<>();
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
