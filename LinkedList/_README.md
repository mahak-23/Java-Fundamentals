# Linked List

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
A linked list is a linear data structure where each element is stored in a node, and each node points to the next node. Unlike arrays, linked lists do not use contiguous memory.

## Why use linked lists?
- They support dynamic size.
- Insertion and deletion are efficient when the position is known.
- They are useful for stacks, queues, adjacency lists, and hash table buckets.
- They are common in interview questions because they test pointer manipulation.

## Basic node structure in Java
```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}
```

## Basic operations with examples

### 1. Create and traverse
```java
LinkedList<String> list = new LinkedList<>();
list.add("A");
list.add("B");
list.add("C");

// Forward traversal
for (String item : list) {
    System.out.print(item + " "); // Output: A B C
}

// Using iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    System.out.print(it.next() + " "); // Output: A B C
}
```

### 2. Add operations
```java
LinkedList<Integer> list = new LinkedList<>();
list.add(10);        // [10]
list.add(20);        // [10, 20]
list.add(0, 5);      // [5, 10, 20] - add at index 0
list.addFirst(1);    // [1, 5, 10, 20]
list.addLast(30);    // [1, 5, 10, 20, 30]
System.out.println(list); // Output: [1, 5, 10, 20, 30]
```

### 3. Remove operations
```java
LinkedList<Integer> list = new LinkedList<>(Arrays.asList(1, 5, 10, 20, 30));
list.remove(0);      // [5, 10, 20, 30] - remove at index 0
list.remove((Integer) 10); // [5, 20, 30] - remove by value
list.removeFirst();  // [20, 30]
list.removeLast();   // [20]
System.out.println(list); // Output: [20]
```

### 4. Access operations
```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C"));
System.out.println(list.get(0));      // Output: A
System.out.println(list.getFirst());  // Output: A
System.out.println(list.getLast());   // Output: C
System.out.println(list.size());      // Output: 3
System.out.println(list.isEmpty());   // Output: false
```

### 5. Search and contains
```java
LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "B"));
System.out.println(list.contains("B")); // Output: true
System.out.println(list.indexOf("B")); // Output: 1
System.out.println(list.lastIndexOf("B")); // Output: 3
```

## Quick reference: linked list operations

### Core operations
- Insert: `add()`, `addFirst()`, `addLast()` - varies by position
- Delete: `remove()`, `removeFirst()`, `removeLast()`
- Access: `get()`, `getFirst()`, `getLast()`
- Search: `contains()`, `indexOf()`, `lastIndexOf()`
- Check: `isEmpty()`, `size()`
- Traverse: iterator or for-each loop

### Complexity summary
- Head insertion/deletion: O(1)
- Tail insertion/deletion: O(1) if keeping tail reference
- Search or access by position: O(n)
- Space: O(n) for storing nodes

### Common linked-list problems
- Reverse a linked list
- Detect cycle (fast/slow pointers)
- Find middle node (fast/slow pointers)
- Merge two sorted lists
- Remove duplicates
- Partition linked list

## Essential operations on a linked list

### 1. Insert at the beginning
```java
public Node insertAtBeginning(Node head, int value) {
    Node newNode = new Node(value);
    newNode.next = head;
    return newNode;
}
```
- Time complexity: O(1)
- Space complexity: O(1)

### 2. Insert at the end
```java
public Node insertAtEnd(Node head, int value) {
    Node newNode = new Node(value);
    if (head == null) {
        return newNode;
    }
    Node current = head;
    while (current.next != null) {
        current = current.next;
    }
    current.next = newNode;
    return head;
}
```
- Time complexity: O(n)
- Space complexity: O(1)

### 3. Insert at a given index
```java
public Node insertAtIndex(Node head, int index, int value) {
    if (index == 0) {
        return insertAtBeginning(head, value);
    }

    Node newNode = new Node(value);
    Node current = head;
    for (int i = 0; current != null && i < index - 1; i++) {
        current = current.next;
    }

    if (current == null) {
        return head;
    }

    newNode.next = current.next;
    current.next = newNode;
    return head;
}
```
- Time complexity: O(n)
- Space complexity: O(1)

### 4. Delete from the beginning
```java
public Node deleteFromBeginning(Node head) {
    if (head == null) {
        return null;
    }
    return head.next;
}
```
- Time complexity: O(1)
- Space complexity: O(1)

### 5. Delete from the end
```java
public Node deleteFromEnd(Node head) {
    if (head == null || head.next == null) {
        return null;
    }

    Node current = head;
    while (current.next.next != null) {
        current = current.next;
    }
    current.next = null;
    return head;
}
```
- Time complexity: O(n)
- Space complexity: O(1)

### 6. Delete by value
```java
public Node deleteByValue(Node head, int value) {
    if (head == null) {
        return null;
    }
    if (head.data == value) {
        return head.next;
    }

    Node current = head;
    while (current.next != null) {
        if (current.next.data == value) {
            current.next = current.next.next;
            break;
        }
        current = current.next;
    }
    return head;
}
```
- Time complexity: O(n)
- Space complexity: O(1)

### 7. Search for an element
```java
public int search(Node head, int target) {
    int index = 0;
    Node current = head;
    while (current != null) {
        if (current.data == target) {
            return index;
        }
        current = current.next;
        index++;
    }
    return -1;
}
```
- Time complexity: O(n)
- Space complexity: O(1)

### 8. Reverse a linked list
```java
public Node reverseList(Node head) {
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
```
- Time complexity: O(n)
- Space complexity: O(1)

## CRUD, size, and iteration notes
- CRUD is implemented with insert/delete operations and pointer updates.
- Use traversal to read values and iterate through the list.
- A linked list has no built-in `.length`; you must count nodes manually or keep a size field.
- Iteration is done with a temporary pointer that moves from node to node.

```java
Node current = head;
int count = 0;
while (current != null) {
    count++;
    current = current.next;
}
System.out.println("Size: " + count);
```

## Types of linked lists
- Singly linked list: each node points to the next node.
- Doubly linked list: each node points to both the previous and next nodes.
- Circular linked list: the last node points back to the head.

## Fast and slow pointers
This pattern is very common in linked-list interviews.
- One pointer moves one step at a time.
- The other moves two steps at a time.
- It is used to find the middle node, detect cycles, and solve many advanced problems.

## Interview practice problems
### 1. Find the middle node
```java
public Node findMiddle(Node head) {
    Node slow = head;
    Node fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }
    return slow;
}
```

### 2. Detect a cycle
```java
public boolean hasCycle(Node head) {
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
```

### 3. Merge two sorted linked lists
```java
public Node mergeSorted(Node a, Node b) {
    if (a == null) return b;
    if (b == null) return a;

    if (a.data < b.data) {
        a.next = mergeSorted(a.next, b);
        return a;
    } else {
        b.next = mergeSorted(a, b.next);
        return b;
    }
}
```

## Interview notes
- Mention the difference between arrays and linked lists clearly.
- Explain whether you are working with a singly linked list, doubly linked list, or circular linked list.
- Always think about edge cases: empty list, one node, head deletion, and duplicate values.
- Draw the pointers step by step when solving a problem.
