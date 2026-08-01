# Tree

## Table of Contents

1. What is a Tree?
2. Tree Terminology
3. Tree Representation in Java
4. Traversal Techniques
5. Time Complexity
6. Interview Patterns

---

### 1. What is a Tree?

A tree is a hierarchical data structure made of nodes connected by edges. It has one root node and each node may contain zero or more children. Trees are used to represent file systems, DOM structures, organization charts, and decision-making processes.

### 2. Tree Terminology

- Root: the top node of the tree
- Parent: a node that has children
- Child: a node directly connected below another node
- Leaf: a node with no children
- Depth: distance from the root
- Height: maximum depth of a node in the tree

### 3. Tree Representation in Java

A general tree can be represented using a node that stores a value and a list of children.

```java
class TreeNode<T> {
    T value;
    List<TreeNode<T>> children = new ArrayList<>();

    TreeNode(T value) {
        this.value = value;
    }

    void addChild(TreeNode<T> child) {
        children.add(child);
    }
}
```

### 4. Traversal Techniques

- DFS (depth-first search): visit a node, then recursively visit each child
- BFS (breadth-first search): visit nodes level by level using a queue

```java
void dfs(TreeNode<?> node) {
    if (node == null) return;
    System.out.print(node.value + " ");
    for (TreeNode<?> child : node.children) {
        dfs(child);
    }
}
```

### 5. Time Complexity

| Operation | Time |
|----------|------|
| Traversal | O(n) |
| Insert child | O(1) (for a simple list-based implementation) |
| Search | O(n) |

### 6. Interview Patterns

- Identify root-to-leaf paths
- Count nodes or leaves
- Implement DFS and BFS
- Convert a tree into another structure

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| DFS / BFS traversal | O(n) | O(h) |
| Insert child | O(1) | O(1) |
| Search | O(n) | O(h) |

## Run

```powershell
javac -d out 15_trees_and_traversals\01_tree\TreeBasics.java
java -cp out tree.TreeBasics
```
