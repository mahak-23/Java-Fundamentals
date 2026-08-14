package graphs;

/*
 * =============================================================================
 * Weighted Graph Shortest Path — Dijkstra and Bellman-Ford
 * =============================================================================
 *
 * DIJKSTRA
 *   Problem: shortest path from ONE source when all edge weights are >= 0
 *   Idea: always pick the unvisited node with smallest known distance (greedy)
 *   Uses: priority queue (min-heap)
 *   Time: O((V + E) log V)
 *
 * BELLMAN-FORD
 *   Problem: shortest path from ONE source; CAN handle negative edge weights
 *   Idea: relax all edges V-1 times (repeat "can we improve any distance?")
 *   Time: O(V × E) — slower but more general
 *
 * EXAMPLE (Dijkstra graph)
 *   0 --6--> 1 --5--> 3 --2--> 4
 *   |        |        ^
 *   7        8        |
 *   v        v        9
 *   2 ------> (2 to 3)
 *
 *   Shortest from 0: dist = [0, 6, 7, 11, 13]
 *
 * RUN FLOW
 *   main() → build graph → dijkstra(0) → bellmanFord(0) on second graph
 * =============================================================================
 */

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
                    int nextDist = distance + edge.weight;
                    if (nextDist < dist[edge.to]) {
                        dist[edge.to] = nextDist;
                        pq.offer(new int[]{edge.to, nextDist});
                    }
                }
            }
            return dist;
        }

        public int[] bellmanFord(int source) {
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
        System.out.println("Dijkstra from 0: " + Arrays.toString(dijkstraDist));

        DirectedWeightedGraph bellmanFordGraph = new DirectedWeightedGraph(4);
        bellmanFordGraph.addEdge(0, 1, 4);
        bellmanFordGraph.addEdge(0, 2, 2);
        bellmanFordGraph.addEdge(1, 2, -3);
        bellmanFordGraph.addEdge(1, 3, 2);
        bellmanFordGraph.addEdge(2, 3, 1);
        bellmanFordGraph.addEdge(3, 1, -1);

        int[] bellmanFordDist = bellmanFordGraph.bellmanFord(0);
        System.out.println("Bellman-Ford from 0: " + Arrays.toString(bellmanFordDist));
    }
}
