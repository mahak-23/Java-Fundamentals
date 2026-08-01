package avl;

public class AVLTreeExample {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {30, 20, 10, 25, 40, 35, 50};

        for (int value : values) {
            tree.insert(value);
        }

        System.out.println("AVL tree inorder traversal:");
        tree.inorder();
        System.out.println("Root: " + tree.root.value);
    }

    static class Node {
        int value;
        int height;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
            this.height = 1;
        }
    }

    static class AVLTree {
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
            } else {
                return node;
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));
            int balance = balanceFactor(node);

            if (balance > 1 && value < node.left.value) {
                return rotateRight(node);
            }
            if (balance < -1 && value > node.right.value) {
                return rotateLeft(node);
            }
            if (balance > 1 && value > node.left.value) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
            if (balance < -1 && value < node.right.value) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node t2 = x.right;

            x.right = y;
            y.left = t2;

            y.height = 1 + Math.max(height(y.left), height(y.right));
            x.height = 1 + Math.max(height(x.left), height(x.right));
            return x;
        }

        private Node rotateLeft(Node x) {
            Node y = x.right;
            Node t2 = y.left;

            y.left = x;
            x.right = t2;

            x.height = 1 + Math.max(height(x.left), height(x.right));
            y.height = 1 + Math.max(height(y.left), height(y.right));
            return y;
        }

        private int height(Node node) {
            return node == null ? 0 : node.height;
        }

        private int balanceFactor(Node node) {
            return node == null ? 0 : height(node.left) - height(node.right);
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
