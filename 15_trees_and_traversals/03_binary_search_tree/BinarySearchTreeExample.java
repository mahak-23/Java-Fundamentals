package bst;

public class BinarySearchTreeExample {
    public static void main(String[] args) {
        BST bst = new BST();
        int[] values = {50, 30, 20, 40, 70, 60, 80};

        for (int value : values) {
            bst.insert(value);
        }

        System.out.println("Inorder traversal:");
        bst.inorder();

        System.out.println("\nSearch 40 -> " + bst.search(40));
        System.out.println("Search 99 -> " + bst.search(99));

        bst.delete(20);
        System.out.println("\nAfter deleting 20:");
        bst.inorder();
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class BST {
        Node root;

        void insert(int value) {
            root = insert(root, value);
        }

        private Node insert(Node node, int value) {
            if (node == null) {
                return new Node(value);
            }
            if (value < node.value) {
                node.left = insert(node.left, value);
            } else if (value > node.value) {
                node.right = insert(node.right, value);
            }
            return node;
        }

        boolean search(int value) {
            return search(root, value);
        }

        private boolean search(Node node, int value) {
            if (node == null) {
                return false;
            }
            if (value == node.value) {
                return true;
            }
            return value < node.value ? search(node.left, value) : search(node.right, value);
        }

        void delete(int value) {
            root = delete(root, value);
        }

        private Node delete(Node node, int value) {
            if (node == null) {
                return null;
            }
            if (value < node.value) {
                node.left = delete(node.left, value);
            } else if (value > node.value) {
                node.right = delete(node.right, value);
            } else {
                if (node.left == null) {
                    return node.right;
                }
                if (node.right == null) {
                    return node.left;
                }

                Node successor = minValueNode(node.right);
                node.value = successor.value;
                node.right = delete(node.right, successor.value);
            }
            return node;
        }

        private Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        void inorder() {
            inorder(root);
            System.out.println();
        }

        private void inorder(Node node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }
    }
}
