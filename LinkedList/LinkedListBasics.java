/*
Linked-list traversal and search are O(n); insert/delete at the head are O(1).
*/

import java.util.LinkedList;

public class LinkedListBasics {
    public static void main(String[] args) {
        System.out.println("=== java.util.LinkedList demo ===");
        LinkedList<String> languages = new LinkedList<>();
        languages.add("Java");
        languages.add("Python");
        languages.addFirst("C");
        languages.addLast("SQL");

        System.out.println("List: " + languages);
        System.out.println("First element: " + languages.getFirst());
        System.out.println("Last element: " + languages.getLast());
        languages.remove(1);
        System.out.println("After remove at index 1: " + languages);

        System.out.println("\n=== Custom singly linked list demo ===");
        SinglyLinkedList<Integer> numbers = new SinglyLinkedList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.print();

        numbers.reverse();
        System.out.println("After reverse:");
        numbers.print();
    }

    static class SinglyLinkedList<T> {
        private Node<T> head;

        void add(T value) {
            Node<T> newNode = new Node<>(value);
            if (head == null) {
                head = newNode;
                return;
            }

            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        void reverse() {
            Node<T> previous = null;
            Node<T> current = head;
            while (current != null) {
                Node<T> nextNode = current.next;
                current.next = previous;
                previous = current;
                current = nextNode;
            }
            head = previous;
        }

        void print() {
            Node<T> current = head;
            while (current != null) {
                System.out.print(current.data + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
}
