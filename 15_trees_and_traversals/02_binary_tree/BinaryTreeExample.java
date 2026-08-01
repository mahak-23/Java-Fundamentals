package binarytree;

public class BinaryTreeExample {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.println("Inorder traversal:");
        tree.inorder(tree.root);

        System.out.println("\nPreorder traversal:");
        tree.preorder(tree.root);

        System.out.println("\nPostorder traversal:");
        tree.postorder(tree.root);
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static class BinaryTree {
        Node root;

        void inorder(Node node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            System.out.print(node.value + " ");
            inorder(node.right);
        }

        void preorder(Node node) {
            if (node == null) {
                return;
            }
            System.out.print(node.value + " ");
            preorder(node.left);
            preorder(node.right);
        }

        void postorder(Node node) {
            if (node == null) {
                return;
            }
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.value + " ");
        }
    }
}
