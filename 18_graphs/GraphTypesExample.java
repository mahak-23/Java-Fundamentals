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
    }

    public static void main(String[] args) {
        DirectedWeightedGraph graph = new DirectedWeightedGraph(5);
        graph.addEdge(0, 1, 6);
        graph.addEdge(0, 2, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 9);
        graph.addEdge(2, 4, 14);
        graph.addEdge(3, 4, 2);

        int[] dist = graph.dijkstra(0);
        System.out.println("Shortest distances from source 0: " + Arrays.toString(dist));
    }
}
