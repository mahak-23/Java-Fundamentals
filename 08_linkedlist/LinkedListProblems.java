package linkedlist;

/*
 * =============================================================================
 * Linked List Interview Problems
 * =============================================================================
 *
 * ALGORITHMS IN THIS FILE:
 *
 * 1. reverseList — three pointers (prev, curr, next)
 *    1→2→3→4  becomes  4→3→2→1
 *
 * 2. hasCycle — Floyd's fast/slow pointers
 *    slow moves 1 step, fast moves 2; if they meet → cycle
 *
 * 3. mergeSortedLists — recursive merge of two sorted lists
 *    1→3→5 + 2→4→6 → 1→2→3→4→5→6
 *
 * 4. removeDuplicates — skip nodes with same value as next
 *    1→1→2→2→3 → 1→2→3
 *
 * 5. findMiddle — fast/slow; when fast reaches end, slow is middle
 *    1→2→3→4→5 → middle = 3
 *
 * =============================================================================
 */

public class LinkedListProblems {
    public static void main(String[] args) {
        System.out.println("=== Reverse linked list ===");
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        printList(head);
        Node reversed = reverseList(head);
        printList(reversed);

        System.out.println("\n=== Detect cycle ===");
        Node cycleHead = new Node(10);
        cycleHead.next = new Node(20);
        cycleHead.next.next = new Node(30);
        cycleHead.next.next.next = cycleHead.next;
        System.out.println("Has cycle? " + hasCycle(cycleHead));

        System.out.println("\n=== Merge two sorted lists ===");
        Node a = buildList(1, 3, 5);
        Node b = buildList(2, 4, 6);
        Node merged = mergeSortedLists(a, b);
        printList(merged);

        System.out.println("\n=== Remove duplicates ===");
        Node duplicates = buildList(1, 1, 2, 2, 3, 3);
        Node unique = removeDuplicates(duplicates);
        printList(unique);

        System.out.println("\n=== Find middle node ===");
        Node middleHead = buildList(1, 2, 3, 4, 5);
        System.out.println("Middle value: " + findMiddle(middleHead).data);
    }

    static Node reverseList(Node head) {
        Node previous = null;
        Node current = head;
        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        return previous;
    }

    static boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    static Node mergeSortedLists(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.data < b.data) {
            a.next = mergeSortedLists(a.next, b);
            return a;
        } else {
            b.next = mergeSortedLists(a, b.next);
            return b;
        }
    }

    static Node removeDuplicates(Node head) {
        Node current = head;
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static Node buildList(int... values) {
        Node head = null;
        Node tail = null;
        for (int value : values) {
            Node newNode = new Node(value);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }
        return head;
    }

    static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }
}
