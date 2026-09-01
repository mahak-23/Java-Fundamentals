package queue;

/*
Circular-array queue operations are O(1) time and O(n) space for the underlying array.
*/

public class QueueWithCircularArray<T> {
    private final Object[] data;
    private int head;
    private int tail;
    private int count;

    public QueueWithCircularArray(int capacity) {
        this.data = new Object[capacity];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
    }

    public boolean enqueue(T value) {
        if (isFull()) {
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        count++;
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T result = (T) data[head];
        data[head] = null; // Clear the reference for garbage collection
        head = (head + 1) % data.length;
        count--;
        return result;
    }

    public T front() {
        if (isEmpty()) {
            return null;
        }
        @SuppressWarnings("unchecked")
        T result = (T) data[head];
        return result;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == data.length;
    }

    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            int index = (head + i) % data.length;
            builder.append(data[index]);
            if (i < count - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}