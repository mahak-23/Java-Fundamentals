package heaps;

/*
 * =============================================================================
 * Heap (Priority Queue) — Always Get Min or Max Fast
 * =============================================================================
 *
 * IDEA
 *   A complete binary tree where parent is always smaller (min-heap) or
 *   larger (max-heap) than children. Root = min/max element.
 *
 * JAVA
 *   PriorityQueue<Integer> minHeap = new PriorityQueue<>();
 *   PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b - a);
 *
 * USE CASES
 *   Top-K elements, merge K sorted lists, Dijkstra, task scheduling
 *
 * COMPLEXITY:  offer/poll/peek → O(log n)
 * =============================================================================
 */

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
