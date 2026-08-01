# Tries

## Table of Contents

1. What is a Trie?
2. Why Tries Are Useful
3. Trie Representation in Java
4. Core Operations
5. Time Complexity
6. Interview Patterns

---

### 1. What is a Trie?

A trie, also called a prefix tree, stores strings by character. Each edge represents a character, and each node can lead to many child nodes for the next letter.

### 2. Why Tries Are Useful

Tries are excellent for:

- autocomplete systems
- dictionary lookup
- prefix matching
- spell checking

### 3. Trie Representation in Java

```java
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;
}
```

### 4. Core Operations

Insert:

```java
void insert(String word) {
    TrieNode current = root;
    for (char ch : word.toCharArray()) {
        current = current.children.computeIfAbsent(ch, c -> new TrieNode());
    }
    current.isWord = true;
}
```

Search and prefix check are also demonstrated in the example file.

### 5. Time Complexity

| Operation | Time |
|----------|------|
| Insert | O(L) |
| Search | O(L) |
| Prefix check | O(L) |

Where $L$ is the length of the word or prefix.

### 6. Interview Patterns

- Implement autocomplete
- Check whether a string is present in a dictionary
- Find all words with a given prefix
- Compare trie performance with hash maps

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Insert | O(L) | O(L) |
| Search | O(L) | O(1) auxiliary |
| Prefix check | O(L) | O(1) auxiliary |

> This topic is now covered in the dedicated trie module at [17_tries/_README.md](../../17_tries/_README.md).

## Run

```powershell
javac -d out 15_trees_and_traversals\05_tries\TrieExample.java
java -cp out trie.TrieExample
```
