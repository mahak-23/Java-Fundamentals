# Tries

## About

A trie is a tree-like structure used to store strings efficiently. Each node represents a character, making prefix-based operations fast and intuitive.

## How to create a trie in Java

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEndOfWord;
}
```

## Common operations

### Insert

```java
void insert(String word) {
    TrieNode current = root;
    for (char ch : word.toCharArray()) {
        int idx = ch - 'a';
        if (current.children[idx] == null) {
            current.children[idx] = new TrieNode();
        }
        current = current.children[idx];
    }
    current.isEndOfWord = true;
}
```

### Search

```java
boolean search(String word) { ... }
```

### Prefix check

```java
boolean startsWith(String prefix) { ... }
```

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Insert | O(L) | O(L) |
| Search | O(L) | O(1) auxiliary |
| Prefix check | O(L) | O(1) auxiliary |

Where $L$ is the length of the word or prefix.

## Engineer checklist

- Know when to choose a trie over a hash map or array for string problems
- Understand how prefix operations work in trie nodes
- Be able to explain why trie operations are proportional to the word length
