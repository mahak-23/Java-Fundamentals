package trie;

import java.util.HashMap;
import java.util.Map;

public class TrieExample {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("car");
        trie.insert("dog");
        trie.insert("carpet");

        System.out.println("Search 'cat' -> " + trie.search("cat"));
        System.out.println("Search 'car' -> " + trie.search("car"));
        System.out.println("Search 'ca' -> " + trie.search("ca"));
        System.out.println("Starts with 'ca' -> " + trie.startsWith("ca"));
        System.out.println("Starts with 'do' -> " + trie.startsWith("do"));
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
    }

    static class Trie {
        private final TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                current = current.children.computeIfAbsent(ch, c -> new TrieNode());
            }
            current.isWord = true;
        }

        boolean search(String word) {
            TrieNode node = findNode(word);
            return node != null && node.isWord;
        }

        boolean startsWith(String prefix) {
            return findNode(prefix) != null;
        }

        private TrieNode findNode(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                TrieNode next = current.children.get(ch);
                if (next == null) {
                    return null;
                }
                current = next;
            }
            return current;
        }
    }
}
