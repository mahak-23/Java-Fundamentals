package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphExample {
    static class Graph {
        private final int vertices;
        private final List<List<Integer>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new LinkedList<>());
            }
        }

        public void addEdge(int from, int to) {
            adjacencyList.get(from).add(to);
            adjacencyList.get(to).add(from);
        }

        public void bfs(int start) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            visited[start] = true;
            queue.add(start);

            while (!queue.isEmpty()) {
                int node = queue.poll();
                System.out.print(node + " ");
                for (int neighbor : adjacencyList.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }

        public void dfs(int start) {
            boolean[] visited = new boolean[vertices];
            dfsUtil(start, visited);
        }

        private void dfsUtil(int node, boolean[] visited) {
            visited[node] = true;
            System.out.print(node + " ");
            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    dfsUtil(neighbor, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);

        System.out.print("BFS: ");
        graph.bfs(0);
        System.out.println();

        System.out.print("DFS: ");
        graph.dfs(0);
        System.out.println();
    }
}
