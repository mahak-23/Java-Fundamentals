/*
Queue offer, poll, peek, and size are O(1); iterating over queued items is O(n).
*/

package queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueOperations {
    public static void main(String[] args) {
        System.out.println("=== Basic Queue example ===");
        Queue<String> queue = new LinkedList<>();
        queue.offer("Alice");
        queue.offer("Bob");
        queue.offer("Carol");

        System.out.println("Queue contents: " + queue);
        System.out.println("Front element (peek): " + queue.peek());
        System.out.println("Dequeue element (poll): " + queue.poll());
        System.out.println("After dequeue: " + queue);
        System.out.println("Size: " + queue.size());
        System.out.println("Is empty: " + queue.isEmpty());

        System.out.println("\n=== ArrayDeque as queue ===");
        Deque<Integer> dequeQueue = new ArrayDeque<>();
        dequeQueue.offer(10);
        dequeQueue.offer(20);
        dequeQueue.offer(30);
        System.out.println("Deque queue: " + dequeQueue);
        System.out.println("Deque peek: " + dequeQueue.peek());
        System.out.println("Deque poll: " + dequeQueue.poll());
        System.out.println("Deque after poll: " + dequeQueue);

        System.out.println("\n=== PriorityQueue example ===");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(50);
        priorityQueue.offer(10);
        priorityQueue.offer(30);
        System.out.println("PriorityQueue contents: " + priorityQueue);
        System.out.println("PriorityQueue poll: " + priorityQueue.poll());
        System.out.println("After poll: " + priorityQueue);

        System.out.println("\n=== Custom circular queue example ===");
        CircularQueue<String> circularQueue = new CircularQueue<>(4);
        circularQueue.enqueue("A");
        circularQueue.enqueue("B");
        circularQueue.enqueue("C");
        System.out.println("Circular queue size: " + circularQueue.size());
        System.out.println("Dequeue: " + circularQueue.dequeue());
        circularQueue.enqueue("D");
        System.out.println("Front: " + circularQueue.front());
        System.out.println("Circular queue after operations: " + circularQueue);
    }

    private static class CircularQueue<T> {
        private final Object[] data;
        private int head;
        private int tail;
        private int count;

        CircularQueue(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than zero");
            }
            this.data = new Object[capacity];
            this.head = 0;
            this.tail = 0;
            this.count = 0;
        }

        boolean enqueue(T value) {
            if (isFull()) {
                return false;
            }
            data[tail] = value;
            tail = (tail + 1) % data.length;
            count++;
            return true;
        }

        T dequeue() {
            if (isEmpty()) {
                return null;
            }
            @SuppressWarnings("unchecked")
            T result = (T) data[head];
            data[head] = null;
            head = (head + 1) % data.length;
            count--;
            return result;
        }

        T front() {
            if (isEmpty()) {
                return null;
            }
            @SuppressWarnings("unchecked")
            T result = (T) data[head];
            return result;
        }

        boolean isEmpty() {
            return count == 0;
        }

        boolean isFull() {
            return count == data.length;
        }

        int size() {
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
}
