package tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class TreeBasics {
    public static void main(String[] args) {
        TreeNode<String> root = new TreeNode<>("Electronics");
        TreeNode<String> phones = new TreeNode<>("Phones");
        TreeNode<String> laptops = new TreeNode<>("Laptops");
        TreeNode<String> televisions = new TreeNode<>("Televisions");

        root.addChild(phones);
        root.addChild(laptops);
        root.addChild(televisions);

        phones.addChild(new TreeNode<>("iPhone"));
        phones.addChild(new TreeNode<>("Android"));
        laptops.addChild(new TreeNode<>("MacBook"));
        laptops.addChild(new TreeNode<>("ThinkPad"));
        televisions.addChild(new TreeNode<>("OLED"));
        televisions.addChild(new TreeNode<>("QLED"));

        System.out.println("=== Depth-First Traversal ===");
        printDepthFirst(root);

        System.out.println("\n=== Breadth-First Traversal ===");
        printBreadthFirst(root);
    }

    private static <T> void printDepthFirst(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        for (TreeNode<T> child : root.children) {
            printDepthFirst(child);
        }
    }

    private static <T> void printBreadthFirst(TreeNode<T> root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.remove();
            System.out.print(current.value + " ");
            queue.addAll(current.children);
        }
    }

    static class TreeNode<T> {
        T value;
        List<TreeNode<T>> children = new ArrayList<>();

        TreeNode(T value) {
            this.value = value;
        }

        void addChild(TreeNode<T> child) {
            children.add(child);
        }
    }
}
