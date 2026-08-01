package heaps;

import java.util.PriorityQueue;

public class HeapExample {
    public static void main(String[] args) {
        System.out.println("=== Min Heap ===");
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(10);
        minHeap.offer(5);
        minHeap.offer(20);
        System.out.println("Peek: " + minHeap.peek());
        System.out.println("Poll: " + minHeap.poll());
        System.out.println("After poll: " + minHeap);

        System.out.println("\n=== Max Heap ===");
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.offer(10);
        maxHeap.offer(5);
        maxHeap.offer(20);
        System.out.println("Peek: " + maxHeap.peek());
        System.out.println("Poll: " + maxHeap.poll());
        System.out.println("After poll: " + maxHeap);
    }
}
