# Binary Tree

## Table of Contents

1. What is a Binary Tree?
2. Binary Tree Properties
3. Binary Tree Representation in Java
4. Traversal Types
5. Time Complexity
6. Interview Patterns

---

### 1. What is a Binary Tree?

A binary tree is a tree in which each node has at most two children: a left child and a right child. It is one of the most common tree structures in computer science.

### 2. Binary Tree Properties

- Each node can have 0, 1, or 2 children
- The left and right subtrees are independent
- A binary tree is recursive by nature

### 3. Binary Tree Representation in Java

```java
class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
    }
}
```

### 4. Traversal Types

- Inorder: left → root → right
- Preorder: root → left → right
- Postorder: left → right → root

```java
void inorder(Node node) {
    if (node == null) return;
    inorder(node.left);
    System.out.print(node.value + " ");
    inorder(node.right);
}
```

### 5. Time Complexity

| Operation | Time |
|----------|------|
| Traversal | O(n) |
| Insert | O(n) in a general binary tree |
| Search | O(n) in a general binary tree |

### 6. Interview Patterns

- Validate tree structure
- Print all root-to-leaf paths
- Find the height of a tree
- Convert a binary tree to its mirror image

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Inorder / preorder / postorder traversal | O(n) | O(h) |
| Insert | O(n) | O(h) |
| Search | O(n) | O(h) |

## Run

```powershell
javac -d out 15_trees_and_traversals\02_binary_tree\BinaryTreeExample.java
java -cp out binarytree.BinaryTreeExample
```
