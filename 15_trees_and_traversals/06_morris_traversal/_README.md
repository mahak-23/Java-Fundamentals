# Morris Traversal

## Table of Contents

1. What is Morris Traversal?
2. Why It Is Important
3. Java Idea Behind the Algorithm
4. Inorder and Preorder Examples
5. Time Complexity
6. Interview Patterns

---

### 1. What is Morris Traversal?

Morris traversal is a clever way to visit a binary tree without using recursion or an explicit stack. It temporarily creates threads from a node to its inorder predecessor to avoid extra memory.

### 2. Why It Is Important

This approach is useful when you want:

- O(1) extra space traversal
- better memory efficiency in interviews and production code
- a deeper understanding of pointer manipulation in trees

### 3. Java Idea Behind the Algorithm

The method uses the right pointer of the left subtree predecessor to point back to the current node. After visiting the node, the temporary link is removed.

```java
Node predecessor = current.left;
while (predecessor.right != null && predecessor.right != current) {
    predecessor = predecessor.right;
}
```

### 4. Inorder and Preorder Examples

- Inorder: left → root → right
- Preorder: root → left → right

These are implemented in the example file for a sample binary tree.

### 5. Time Complexity

| Operation | Time |
|----------|------|
| Morris inorder traversal | O(n) |
| Morris preorder traversal | O(n) |
| Extra space | O(1) |

### 6. Interview Patterns

- Explain how Morris traversal avoids recursion
- Compare Morris traversal with stack-based traversal
- Explain why the temporary links are removed after use

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Morris inorder traversal | O(n) | O(1) |
| Morris preorder traversal | O(n) | O(1) |
| Temporary-thread creation | O(1) per node | O(1) |

## Run

```powershell
javac -d out 15_trees_and_traversals\06_morris_traversal\MorrisTraversalExample.java
java -cp out morris.MorrisTraversalExample
```
