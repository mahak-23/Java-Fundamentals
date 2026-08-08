import java.util.*;

public class MSTAndTopologicalSortExample {
    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 4),
                new Edge(0, 2, 3),
                new Edge(1, 2, 1),
                new Edge(1, 3, 2),
                new Edge(2, 3, 5)
        );

        System.out.println("MST total weight: " + kruskalMST(edges, 4));

        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(3));
        graph.put(2, Arrays.asList(3));
        graph.put(3, new ArrayList<>());

        System.out.println("Topological order: " + topologicalSort(graph));
    }

    public static int kruskalMST(List<Edge> edges, int vertices) {
        edges.sort(Comparator.comparingInt(e -> e.weight));
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int totalWeight = 0;
        int count = 0;

        for (Edge edge : edges) {
            if (find(parent, edge.from) != find(parent, edge.to)) {
                union(parent, edge.from, edge.to);
                totalWeight += edge.weight;
                count++;
                if (count == vertices - 1) {
                    break;
                }
            }
        }

        return totalWeight;
    }

    private static int find(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = find(parent, parent[node]);
        }
        return parent[node];
    }

    private static void union(int[] parent, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

    public static List<Integer> topologicalSort(Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int node : graph.keySet()) {
            indegree.put(node, 0);
        }

        for (List<Integer> neighbors : graph.values()) {
            for (int neighbor : neighbors) {
                indegree.put(neighbor, indegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return order;
    }

    static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
