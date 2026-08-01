import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeAndGraphAlgorithms {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static int diameter(TreeNode root) {
        return diameterHelper(root).diameter;
    }

    private static Result diameterHelper(TreeNode node) {
        if (node == null) {
            return new Result(0, 0);
        }

        Result left = diameterHelper(node.left);
        Result right = diameterHelper(node.right);
        int height = 1 + Math.max(left.height, right.height);
        int diameter = Math.max(left.height + right.height, Math.max(left.diameter, right.diameter));
        return new Result(height, diameter);
    }

    private static class Result {
        int height;
        int diameter;

        Result(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = checkHeight(node.left);
        if (left == -1) {
            return -1;
        }
        int right = checkHeight(node.right);
        if (right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return 1 + Math.max(left, right);
    }

    static class Graph {
        private final int vertices;
        private final List<List<Integer>> adjacency;

        Graph(int vertices) {
            this.vertices = vertices;
            this.adjacency = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacency.add(new ArrayList<>());
            }
        }

        void addEdge(int from, int to) {
            adjacency.get(from).add(to);
            adjacency.get(to).add(from);
        }

        List<Integer> bfs(int start) {
            boolean[] visited = new boolean[vertices];
            List<Integer> order = new ArrayList<>();
            Queue<Integer> queue = new ArrayDeque<>();
            visited[start] = true;
            queue.offer(start);

            while (!queue.isEmpty()) {
                int node = queue.poll();
                order.add(node);
                for (int neighbor : adjacency.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
            return order;
        }

        List<Integer> dfs(int start) {
            boolean[] visited = new boolean[vertices];
            List<Integer> order = new ArrayList<>();
            dfsUtil(start, visited, order);
            return order;
        }

        private void dfsUtil(int node, boolean[] visited, List<Integer> order) {
            visited[node] = true;
            order.add(node);
            for (int neighbor : adjacency.get(node)) {
                if (!visited[neighbor]) {
                    dfsUtil(neighbor, visited, order);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println("maxDepth => " + maxDepth(root));
        System.out.println("diameter => " + diameter(root));
        System.out.println("levelOrder => " + levelOrder(root));
        System.out.println("isBalanced => " + isBalanced(root));

        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        System.out.println("bfs => " + graph.bfs(0));
        System.out.println("dfs => " + graph.dfs(0));
    }
}
