# AVL Tree

## Table of Contents

1. What is an AVL Tree?
2. Why AVL Trees Matter
3. Balance Factor
4. Rotations in Java
5. Time Complexity
6. Interview Patterns

---

### 1. What is an AVL Tree?

An AVL tree is a self-balancing binary search tree. It keeps the height of the left and right subtrees balanced so that search, insert, and delete operations remain efficient.

### 2. Why AVL Trees Matter

Without balancing, a BST can become skewed and behave like a linked list. AVL trees prevent this by rebalancing after insertions and deletions.

### 3. Balance Factor

The balance factor of a node is:

$$
\text{balance} = \text{height(left)} - \text{height(right)}
$$

AVL trees require the balance factor to stay in the range $[-1, 0, 1]$.

### 4. Rotations in Java

When a node becomes unbalanced, AVL trees rotate the subtree:

- Left rotation for RR imbalance
- Right rotation for LL imbalance
- Left-right rotation for LR imbalance
- Right-left rotation for RL imbalance

```java
private Node rotateRight(Node y) {
    Node x = y.left;
    Node t2 = x.right;

    x.right = y;
    y.left = t2;
    return x;
}
```

### 5. Time Complexity

| Operation | Time |
|----------|------|
| Search | O(log n) |
| Insert | O(log n) |
| Delete | O(log n) |

### 6. Interview Patterns

- Explain why AVL trees are balanced
- Identify rotation cases from insertion order
- Compare AVL tree with BST and Red-Black tree

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Search | O(log n) | O(h) |
| Insert | O(log n) | O(h) |
| Delete | O(log n) | O(h) |
| Rotation | O(1) | O(1) |

## Run

```powershell
javac -d out 15_trees_and_traversals\04_avl_tree\AVLTreeExample.java
java -cp out avl.AVLTreeExample
```
