package graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class GraphTypesExample {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class DirectedWeightedGraph {
        private final List<List<Edge>> adjacency;

        public DirectedWeightedGraph(int vertices) {
            adjacency = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacency.add(new ArrayList<>());
            }
        }

        public void addEdge(int from, int to, int weight) {
            adjacency.get(from).add(new Edge(to, weight));
        }

        public int[] dijkstra(int source) {
            // Dijkstra graph structure:
            // 0 -> 1 (6), 0 -> 2 (7), 1 -> 2 (8), 1 -> 3 (5), 2 -> 3 (9), 2 -> 4 (14), 3 -> 4 (2)
            int n = adjacency.size();
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
            pq.offer(new int[]{source, 0});

            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int node = current[0];
                int distance = current[1];
                if (distance > dist[node]) {
                    continue;
                }
                for (Edge edge : adjacency.get(node)) {
                    int next = edge.to;
                    int nextDist = distance + edge.weight;
                    if (nextDist < dist[next]) {
                        dist[next] = nextDist;
                        pq.offer(new int[]{next, nextDist});
                    }
                }
            }
            return dist;
        }

        public int[] bellmanFord(int source) {
            // Bellman-Ford graph structure (supports negative weights):
            // 0 -> 1 (4), 0 -> 2 (2), 1 -> 2 (-3), 1 -> 3 (2), 2 -> 3 (1), 3 -> 1 (-1)
            int n = adjacency.size();
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;

            for (int i = 0; i < n - 1; i++) {
                boolean updated = false;
                for (int u = 0; u < n; u++) {
                    if (dist[u] == Integer.MAX_VALUE) {
                        continue;
                    }
                    for (Edge edge : adjacency.get(u)) {
                        int nextDist = dist[u] + edge.weight;
                        if (nextDist < dist[edge.to]) {
                            dist[edge.to] = nextDist;
                            updated = true;
                        }
                    }
                }
                if (!updated) {
                    break;
                }
            }

            return dist;
        }
    }

    public static void main(String[] args) {
        DirectedWeightedGraph dijkstraGraph = new DirectedWeightedGraph(5);
        dijkstraGraph.addEdge(0, 1, 6);
        dijkstraGraph.addEdge(0, 2, 7);
        dijkstraGraph.addEdge(1, 2, 8);
        dijkstraGraph.addEdge(1, 3, 5);
        dijkstraGraph.addEdge(2, 3, 9);
        dijkstraGraph.addEdge(2, 4, 14);
        dijkstraGraph.addEdge(3, 4, 2);

        int[] dijkstraDist = dijkstraGraph.dijkstra(0);
        System.out.println("Shortest distances from source 0 (Dijkstra): " + Arrays.toString(dijkstraDist));

        DirectedWeightedGraph bellmanFordGraph = new DirectedWeightedGraph(4);
        bellmanFordGraph.addEdge(0, 1, 4);
        bellmanFordGraph.addEdge(0, 2, 2);
        bellmanFordGraph.addEdge(1, 2, -3);
        bellmanFordGraph.addEdge(1, 3, 2);
        bellmanFordGraph.addEdge(2, 3, 1);
        bellmanFordGraph.addEdge(3, 1, -1);

        int[] bellmanFordDist = bellmanFordGraph.bellmanFord(0);
        System.out.println("Shortest distances from source 0 (Bellman-Ford): " + Arrays.toString(bellmanFordDist));
    }
}
