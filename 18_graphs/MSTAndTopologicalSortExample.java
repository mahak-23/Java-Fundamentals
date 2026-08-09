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

        System.out.println("MST total weight (Kruskal - array): " + kruskalMSTArray(edges, 4));
        System.out.println("MST total weight (Kruskal - DSU rank): " + kruskalMST(edges, 4));
        System.out.println("MST total weight (Kruskal - DSU size): " + kruskalMSTBySize(edges, 4));
        System.out.println("MST total weight (Prim): " + primMST(edges, 4));

        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(3));
        graph.put(2, Arrays.asList(3));
        graph.put(3, new ArrayList<>());

        System.out.println("Topological order (BFS / Kahn): " + topologicalSortBFS(graph));
        System.out.println("Topological order (DFS): " + topologicalSortDFS(graph));
    }
    
    public static int primMST(List<Edge> edges, int vertices) {
        // Convert edge list to an adjacency list for Prim's algorithm.
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            adj.get(edge.from).add(new int[]{edge.to, edge.weight});
            adj.get(edge.to).add(new int[]{edge.from, edge.weight});
        }

        boolean[] visited = new boolean[vertices];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // (weight, node)
        pq.offer(new int[]{0, 0});

        int totalWeight = 0;
        int edgesUsed = 0;

        // Step 1: Use a min-heap to always add the smallest outgoing edge.
        while (!pq.isEmpty() && edgesUsed < vertices) {
            int[] entry = pq.poll();
            int weight = entry[0];
            int node = entry[1];
            if (visited[node]) {
                continue;
            }

            visited[node] = true;
            totalWeight += weight;
            edgesUsed++;

            for (int[] neighbor : adj.get(node)) {
                int next = neighbor[0];
                int nextWeight = neighbor[1];
                if (!visited[next]) {
                    pq.offer(new int[]{nextWeight, next});
                }
            }
        }

        return totalWeight;
    }
    
    public static int kruskalMST(List<Edge> edges, int vertices) {
        // Step 1: Sort edges by ascending weight.
        edges.sort(Comparator.comparingInt(e -> e.weight));

        // Step 2: Initialize Disjoint Set (DSU) to manage components efficiently.
        // DSU supports `find` with path compression and `union` by rank.
        DisjointSet dsu = new DisjointSet(vertices);

        int totalWeight = 0;
        int count = 0;

        // Step 3: Iterate over sorted edges and add the safest edge.
        for (Edge edge : edges) {
            int aRoot = dsu.find(edge.from);
            int bRoot = dsu.find(edge.to);
            if (aRoot != bRoot) {
                // Edge connects two different components, so include it in the MST.
                dsu.union(aRoot, bRoot);
                totalWeight += edge.weight;
                count++;
                if (count == vertices - 1) {
                    // MST is complete once it has exactly V-1 edges.
                    break;
                }
            }
        }

        return totalWeight;
    }

    // Original array-based Kruskal (no union-by-rank) kept as a separate function
    public static int kruskalMSTArray(List<Edge> edges, int vertices) {
        // Step 1: Sort edges by ascending weight.
        edges.sort(Comparator.comparingInt(e -> e.weight));

        // Step 2: Initialize parent links so each node is its own set.
        int[] parent = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int totalWeight = 0;
        int count = 0;

        // Step 3: Iterate over sorted edges and add the safest edge.
        for (Edge edge : edges) {
            if (findParent(parent, edge.from) != findParent(parent, edge.to)) {
                // Edge connects two different components, so include it in the MST.
                unionParent(parent, edge.from, edge.to);
                totalWeight += edge.weight;
                count++;
                if (count == vertices - 1) {
                    break;
                }
            }
        }

        return totalWeight;
    }

    // Simple find with path compression (array-based helper)
    private static int findParent(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent, parent[node]);
        }
        return parent[node];
    }

    // Simple union (attach root of a to root of b) — no rank optimization.
    private static void unionParent(int[] parent, int a, int b) {
        int rootA = findParent(parent, a);
        int rootB = findParent(parent, b);
        if (rootA != rootB) {
            parent[rootA] = rootB;
        }
    }

    // Disjoint Set (Union-Find) with path compression and union by rank.
    // - `find(x)` returns the representative (root) of x's component.
    // - `union(x, y)` merges two components; union by rank keeps tree shallow.
    static class DisjointSet {
        private final int[] parent;
        private final int[] rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        // Path compression: flattens the tree so future finds are faster.
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        // Union by rank: attach the smaller tree under the larger one.
        void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) {
                parent[rx] = ry;
            } else if (rank[ry] < rank[rx]) {
                parent[ry] = rx;
            } else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }

    // Disjoint Set variant that uses union by size instead of rank.
    static class DisjointSetBySize {
        private final int[] parent;
        private final int[] size;

        DisjointSetBySize(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx == ry) return;
            // Attach smaller tree under the larger one by size.
            if (size[rx] < size[ry]) {
                parent[rx] = ry;
                size[ry] += size[rx];
            } else {
                parent[ry] = rx;
                size[rx] += size[ry];
            }
        }
    }

    // Kruskal using union-by-size DSU
    public static int kruskalMSTBySize(List<Edge> edges, int vertices) {
        edges.sort(Comparator.comparingInt(e -> e.weight));
        DisjointSetBySize dsu = new DisjointSetBySize(vertices);

        int totalWeight = 0;
        int count = 0;
        for (Edge edge : edges) {
            int aRoot = dsu.find(edge.from);
            int bRoot = dsu.find(edge.to);
            if (aRoot != bRoot) {
                dsu.union(aRoot, bRoot);
                totalWeight += edge.weight;
                count++;
                if (count == vertices - 1) break;
            }
        }
        return totalWeight;
    }

    public static List<Integer> topologicalSortBFS(Map<Integer, List<Integer>> graph) {
        // Step 1: Compute indegree for every node.
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int node : graph.keySet()) {
            indegree.put(node, 0);
        }

        for (List<Integer> neighbors : graph.values()) {
            for (int neighbor : neighbors) {
                indegree.put(neighbor, indegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        // Step 2: Initialize queue with all nodes that have indegree 0.
        // BFS / Kahn's algorithm uses indegree counts and a queue of ready nodes.
        // Nodes with indegree 0 have no prerequisites and can appear next in the order.
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            // Step 3: Remove a ready node and append it to the ordering.
            int node = queue.poll();
            order.add(node);

            // Step 4: Decrease indegree for its outgoing neighbors.
            for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    // Step 5: If a neighbor becomes ready, enqueue it.
                    queue.offer(neighbor);
                }
            }
        }

        return order;
    }

    public static List<Integer> topologicalSortDFS(Map<Integer, List<Integer>> graph) {
        // Step 1: Track visited nodes and the current recursion path.
        // visited: nodes that are completely processed and added to the result stack.
        // onStack: nodes in the current DFS recursion chain, used to detect cycles.
        // stack: post-order container; we push a node after all its descendants are visited.
        Set<Integer> visited = new HashSet<>();
        Set<Integer> onStack = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfsTopo(node, graph, visited, onStack, stack);
            }
        }

        // Step 4: Reverse the post-order stack to get the topological order.

        List<Integer> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }
        return order;
    }

    private static void dfsTopo(int node,
        Map<Integer, List<Integer>> graph,
        Set<Integer> visited,
        Set<Integer> onStack,
        Deque<Integer> stack) {
        // Mark node as visited in this DFS branch and on the active recursion stack.
        visited.add(node);
        onStack.add(node);

        for (int neighbor : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsTopo(neighbor, graph, visited, onStack, stack);
            } else if (onStack.contains(neighbor)) {
                // If a neighbor is still on the recursion stack, there is a back edge
                // and the graph has a cycle, so no valid topological ordering exists.
                throw new IllegalArgumentException("Graph is not a DAG: cycle detected");
            }
        }

        // Remove the node from the recursion stack after processing all descendants.
        onStack.remove(node);
        // Push onto stack in post-order so the topological order is reversed by pop.
        stack.push(node);
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
