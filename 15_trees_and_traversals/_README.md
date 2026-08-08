# trees_and_traversals

> **Prerequisites:** [08_linkedlist](../08_linkedlist/_README.md), [09_hashmaps_and_hashsets](../09_hashmaps_and_hashsets/_README.md)

Trees are hierarchical data structures used for organization, search, and traversal. This module is organized into dedicated folders for each topic.

## Subfolders

| Folder | Topic | Example |
|--------|-------|---------|
| [01_tree](01_tree/) | General trees | `TreeBasics.java` |
| [02_binary_tree](02_binary_tree/) | Binary trees | `BinaryTreeExample.java` |
| [03_binary_search_tree](03_binary_search_tree/) | Binary search trees | `BinarySearchTreeExample.java` |
| [04_avl_tree](04_avl_tree/) | AVL trees | `AVLTreeExample.java` |
| [05_tries](05_tries/) | Tries | `TrieExample.java` |
| [06_morris_traversal](06_morris_traversal/) | Morris traversal | `MorrisTraversalExample.java` |

## Core ideas

1. Trees model parent-child relationships.
2. Binary trees have at most two children per node.
3. BSTs support efficient search when keys are ordered.
4. AVL trees rebalance themselves to keep height low.
5. Morris traversal visits nodes without using a stack or recursion.

## Heaps and balanced trees

### Heaps

A heap is a complete binary tree that keeps either the minimum or the maximum element at the root. It is commonly used for priority queues, scheduling, and top-k problems.

- Min-heap: smallest value at the root
- Max-heap: largest value at the root
- Insert and remove operations take $O(\log n)$ time

### Balanced trees

Balanced trees keep the tree height small so operations stay efficient. AVL trees and Red-Black trees are classic examples.

- Search, insert, and delete are typically $O(\log n)$
- They prevent worst-case skewing that would make a normal binary tree behave like a linked list

## Time and space complexity

| Structure | Operation | Time | Space |
|-----------|-----------|------|-------|
| General tree | DFS/BFS | O(n) | O(h) |
| Binary tree | Traversal | O(n) | O(h) |
| BST | Search / insert / delete | O(log n) average | O(h) |
| AVL tree | Search / insert / delete | O(log n) | O(h) |
| Morris traversal | Inorder / preorder | O(n) | O(1) |

## Interview questions

1. What is the difference between a tree and a binary tree?
2. Why is an AVL tree balanced?
3. How does a BST differ from a hash table for lookup?
4. When would you choose a trie over a hash map?
