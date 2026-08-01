# Binary Search Tree

## Table of Contents

1. What is a BST?
2. BST Properties
3. BST Operations in Java
4. Balanced vs Unbalanced BST
5. Time Complexity
6. Common Interview Questions

---

### 1. What is a BST?

A binary search tree is a special binary tree where every left subtree contains smaller values and every right subtree contains larger values. This ordering property makes BSTs very useful for fast lookup.

### 2. BST Properties

- Left subtree values are smaller than the current node
- Right subtree values are larger than the current node
- Both subtrees must also be valid BSTs

### 3. BST Operations in Java

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

Insert:

```java
void insert(Node node, int value) {
    if (value < node.value) {
        if (node.left == null) node.left = new Node(value);
        else insert(node.left, value);
    } else {
        if (node.right == null) node.right = new Node(value);
        else insert(node.right, value);
    }
}
```

Search:

```java
boolean search(Node node, int value) {
    if (node == null) return false;
    if (value == node.value) return true;
    return value < node.value ? search(node.left, value) : search(node.right, value);
}
```

Delete and inorder traversal are also implemented in the example file.

### 4. Balanced vs Unbalanced BST

- Balanced BST: height is small, operations are efficient
- Unbalanced BST: tree becomes skewed, operations degrade to O(n)
- AVL and Red-Black trees solve this problem

### 5. Time Complexity

| Operation | Average | Worst Case |
|----------|---------|------------|
| Search | O(log n) | O(n) |
| Insert | O(log n) | O(n) |
| Delete | O(log n) | O(n) |
| Inorder traversal | O(n) | O(n) |

### 6. Common Interview Questions

- Check whether a tree is a valid BST
- Find the kth smallest value
- Find LCA in a BST
- Convert a sorted array into a balanced BST

## Time and space complexity

| Operation | Average Time | Worst Time | Space |
|-----------|--------------|------------|-------|
| Search | O(log n) | O(n) | O(h) |
| Insert | O(log n) | O(n) | O(h) |
| Delete | O(log n) | O(n) | O(h) |
| Inorder traversal | O(n) | O(n) | O(h) |

## Run

```powershell
javac -d out 15_trees_and_traversals\03_binary_search_tree\BinarySearchTreeExample.java
java -cp out bst.BinarySearchTreeExample
```
