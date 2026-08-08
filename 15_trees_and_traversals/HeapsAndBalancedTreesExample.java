import java.util.PriorityQueue;
import java.util.TreeSet;

public class HeapsAndBalancedTreesExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(10);
        minHeap.offer(4);
        minHeap.offer(7);

        System.out.println("Heap peek: " + minHeap.peek());
        System.out.println("Heap poll: " + minHeap.poll());

        TreeSet<Integer> balancedTree = new TreeSet<>();
        balancedTree.add(10);
        balancedTree.add(4);
        balancedTree.add(7);

        System.out.println("Balanced tree contains 7? " + balancedTree.contains(7));
        System.out.println("Sorted order: " + balancedTree);
    }
}
