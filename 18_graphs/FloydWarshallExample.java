package graphs;

/*
 * =============================================================================
 * Floyd-Warshall — All-Pairs Shortest Paths
 * =============================================================================
 *
 * PROBLEM
 *   Find the shortest distance between EVERY pair of vertices in a weighted graph.
 *
 * BEGINNER IDEA
 *   Think of a distance table dist[i][j] = shortest known path from i to j.
 *   Try every vertex k as a possible "middle stop":
 *     "Is it shorter to go from i to j through k?"
 *     dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
 *
 * WHEN TO USE
 *   - Need all-pairs shortest paths (not just one source like Dijkstra)
 *   - Graph is small/medium (V up to ~400 in contests)
 *   - Can handle negative weights (but NOT negative cycles)
 *
 * EXAMPLE (4 cities: 0, 1, 2, 3)
 *
 *   Direct edges:
 *     0 → 1 (3),  0 → 3 (7)
 *     1 → 2 (1)
 *     2 → 0 (1),  2 → 3 (2)
 *
 *   Initial distance matrix (INF = no direct edge):
 *         0    1    2    3
 *     0 [  0    3   INF   7 ]
 *     1 [ INF   0    1   INF]
 *     2 [  1   INF   0    2 ]
 *     3 [ INF  INF  INF   0 ]
 *
 *   After Floyd-Warshall:
 *     dist[0][2] becomes 4  (0 → 1 → 2, cost 3+1)
 *     dist[0][3] becomes 6  (0 → 1 → 2 → 3, cost 3+1+2) — better than direct 7
 *
 * RUN FLOW
 *   main()
 *     → build initial distance matrix from edges
 *     → floydWarshall(dist) updates all pairs using every k as intermediate
 *     → print final matrix and sample paths
 *
 * COMPLEXITY
 *   Time:  O(V³)
 *   Space: O(V²)
 *
 * COMPARE WITH
 *   Dijkstra  — single source, non-negative weights, faster for sparse graphs
 *   BFS       — unweighted graphs only
 *   Bellman-Ford — single source, handles negative weights, O(V×E)
 * =============================================================================
 */

public class FloydWarshallExample {
    private static final int INF = 1_000_000_000;

    public static void floydWarshall(int[][] dist) {
        int n = dist.length;

        // k = allowed intermediate vertex (0, 1, 2, ... n-1)
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) {
                        continue;
                    }
                    int throughK = dist[i][k] + dist[k][j];
                    if (throughK < dist[i][j]) {
                        dist[i][j] = throughK;
                    }
                }
            }
        }
    }

    static int[][] buildSampleGraph() {
        int n = 4;
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = (i == j) ? 0 : INF;
            }
        }

        dist[0][1] = 3;
        dist[0][3] = 7;
        dist[1][2] = 1;
        dist[2][0] = 1;
        dist[2][3] = 2;

        return dist;
    }

    static void printMatrix(int[][] dist) {
        System.out.print("     ");
        for (int j = 0; j < dist.length; j++) {
            System.out.printf("%4d ", j);
        }
        System.out.println();

        for (int i = 0; i < dist.length; i++) {
            System.out.printf("%4d ", i);
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == INF) {
                    System.out.print(" INF");
                } else {
                    System.out.printf("%4d", dist[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] dist = buildSampleGraph();

        System.out.println("=== Initial distances ===");
        printMatrix(dist);

        floydWarshall(dist);

        System.out.println("\n=== After Floyd-Warshall (all-pairs shortest) ===");
        printMatrix(dist);

        System.out.println("\nSample answers:");
        System.out.println("Shortest 0 → 2: " + dist[0][2] + "  (via 0 → 1 → 2)");
        System.out.println("Shortest 0 → 3: " + dist[0][3] + "  (via 0 → 1 → 2 → 3)");
        System.out.println("Shortest 1 → 3: " + dist[1][3] + "  (via 1 → 2 → 3)");
    }
}
