package tries;

public class TrieExample {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;
    }

    static class Trie {
        private final TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new TrieNode();
                }
                current = current.children[index];
            }
            current.isEndOfWord = true;
        }

        public boolean search(String word) {
            TrieNode node = findNode(word);
            return node != null && node.isEndOfWord;
        }

        public boolean startsWith(String prefix) {
            return findNode(prefix) != null;
        }

        public boolean delete(String word) {
            return delete(root, word, 0);
        }

        private boolean delete(TrieNode current, String word, int depth) {
            if (current == null) {
                return false;
            }

            if (depth == word.length()) {
                if (!current.isEndOfWord) {
                    return false;
                }
                current.isEndOfWord = false;
                return isEmpty(current);
            }

            int index = word.charAt(depth) - 'a';
            if (delete(current.children[index], word, depth + 1)) {
                current.children[index] = null;
                return !current.isEndOfWord && isEmpty(current);
            }
            return false;
        }

        private boolean isEmpty(TrieNode node) {
            for (TrieNode child : node.children) {
                if (child != null) {
                    return false;
                }
            }
            return true;
        }

        private TrieNode findNode(String word) {
            TrieNode current = root;
            for (char ch : word.toCharArray()) {
                int index = ch - 'a';
                if (current.children[index] == null) {
                    return null;
                }
                current = current.children[index];
            }
            return current;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");

        System.out.println("search apple -> " + trie.search("apple"));
        System.out.println("search app -> " + trie.search("app"));
        System.out.println("prefix ap -> " + trie.startsWith("ap"));

        System.out.println("delete apple -> " + trie.delete("apple"));
        System.out.println("search apple after delete -> " + trie.search("apple"));
        System.out.println("search app after delete -> " + trie.search("app"));
        System.out.println("prefix ap after delete -> " + trie.startsWith("ap"));
    }
}
