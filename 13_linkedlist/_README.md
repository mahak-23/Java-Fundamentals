# linkedlist

> **Prerequisites:** [07_collections](../07_collections/_README.md)

A linked list is a linear data structure where each node points to the next node. It is useful when you need efficient insertion and deletion at the front or middle without shifting elements like in arrays.

## How to create a linked list in Java

A linked list is usually built from a simple node class:

```java
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}
```

You can create a list by connecting nodes manually:

```java
Node head = new Node(10);
Node second = new Node(20);
Node third = new Node(30);

head.next = second;
second.next = third;
```

## Common operations

### 1. Insert at the front

```java
Node newHead = new Node(5);
newHead.next = head;
head = newHead;
```

### 2. Insert at the end

```java
Node current = head;
while (current.next != null) {
    current = current.next;
}
current.next = new Node(40);
```

### 3. Traverse the list

```java
Node current = head;
while (current != null) {
    System.out.print(current.data + " ");
    current = current.next;
}
```

### 4. Delete a node

```java
Node prev = null;
Node current = head;
while (current != null && current.data != 20) {
    prev = current;
    current = current.next;
}
if (current != null) {
    prev.next = current.next;
}
```

### 5. Search and update

```java
Node current = head;
while (current != null) {
    if (current.data == 20) {
        current.data = 99; // update
        break;
    }
    current = current.next;
}
```

### 6. CRUD-style linked list workflow

```java
head = new Node(10);          // create
head.next = new Node(20);
Node current = head;
while (current != null) {
    System.out.println(current.data); // read
    current = current.next;
}
```

## Engineer checklist

- Know how to insert, delete, traverse, search, and update nodes
- Understand the difference between linked lists and arrays for insertion/removal
- Be able to explain singly linked vs doubly linked list behavior

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Insert at head | O(1) | O(1) |
| Insert at tail | O(n) | O(1) |
| Delete by value | O(n) | O(1) |
| Search | O(n) | O(1) |
| Traverse | O(n) | O(1) |

## Files

| File | Content |
|------|---------|
| `LinkedListBasics.java` | Node structure, basic operations |
| `LinkedListProblems.java` | Reverse, cycle, merge, middle |

## Algorithms in LinkedListProblems

| Method | Technique | Example |
|--------|-----------|---------|
| `reverseList` | Three pointers | 1→2→3→4 → 4→3→2→1 |
| `hasCycle` | Fast/slow pointers | Detect loop in O(1) space |
| `mergeSortedLists` | Recursive merge | 1→3→5 + 2→4→6 |
| `removeDuplicates` | Skip same-value nodes | 1→1→2→2→3 → 1→2→3 |
| `findMiddle` | Fast/slow | Middle of 1→2→3→4→5 is 3 |

## Interview questions

1. Array vs linked list — tradeoffs?
2. How does Floyd's cycle detection work?
3. Why dummy node simplifies head operations?

## Next module

→ [09_hashmaps_and_hashsets/_README.md](../09_hashmaps_and_hashsets/_README.md)
