package morris;

public class MorrisTraversalExample {
    public static void main(String[] args) {
        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);
        root.left.left = new Node(1);
        root.left.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(7);

        System.out.println("Morris inorder traversal:");
        morrisInorder(root);

        System.out.println("\nMorris preorder traversal:");
        morrisPreorder(root);
    }

    static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    static void morrisInorder(Node root) {
        Node current = root;

        while (current != null) {
            if (current.left == null) {
                System.out.print(current.value + " ");
                current = current.right;
            } else {
                Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    predecessor.right = null;
                    System.out.print(current.value + " ");
                    current = current.right;
                }
            }
        }
        System.out.println();
    }

    static void morrisPreorder(Node root) {
        Node current = root;

        while (current != null) {
            if (current.left == null) {
                System.out.print(current.value + " ");
                current = current.right;
            } else {
                Node predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;
                    System.out.print(current.value + " ");
                    current = current.left;
                } else {
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        System.out.println();
    }
}
