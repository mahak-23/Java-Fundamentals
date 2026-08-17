//==============================================================================
// ADVANCED GRAPH ALGORITHMS REFERENCE & GUIDE
//==============================================================================
//
// 1. KOSARAJU'S ALGORITHM (Strongly Connected Components - SCCs)
//    - Purpose: Finds groups of nodes in a directed graph where every node can reach
//               every other node in the same group.
//    - Strategy: 2-Pass DFS.
//      * Pass 1: Run DFS on the original graph to order nodes by finish time (Stack).
//      * Pass 2: Reverse all edges (Transpose) and run DFS in the stack's order.
//    - Example Graph: [0] ──> [1] ──> [2] ──> [0] ──> [3]
//    - Trace:
//      * Pass 1 Stack Finish Order (Top to Bottom): 0, 1, 2, 3
//      * Transpose Edges: [1]──>[0], [2]──>[1], [0]──>[2], [3]──>[0]
//      * Pass 2 Component Pops: Pop 0 -> Pops out loop {0, 2, 1}. Pop 3 -> isolated {3}.
//
// 2. TARJAN'S ALGORITHM (Strongly Connected Components - SCCs)
//    - Purpose: Finds SCCs in a single pass instead of Kosaraju's two passes.
//    - Strategy: Uses low-link values and a custom tracking stack.
//      * discovery[u]: The structural timeline step when a node is first visited.
//      * low[u]: The lowest discovery time reachable from u, including back-edges.
//    - Trace: As DFS descends, nodes push onto the tracking stack. If low[u] == discovery[u]
//             upon returning, all nodes above u on the stack form a complete SCC.
//
// 3. BRIDGE FINDING ALGORITHM
//    - Purpose: Identifies critical edges whose deletion breaks a connected graph into pieces.
//    - Strategy: Uses Discovery and Low structural numbers on an undirected setup.
//    - Rule: An edge (u, v) is a structural bridge if and only if: low[v] > discovery[u].
//    - Meaning: Node v has absolutely no back-edge way to crawl back to u or higher up.
//
// 4. ARTICULATION POINTS (Cut Vertices)
//    - Purpose: Finds single point-of-failure vertices whose removal splits the graph.
//    - Strategy: Modified DFS low-link tracking rules.
//    - Conditions:
//      * Root Node: Root is an articulation point if it has 2 or more independent children.
//      * Non-Root Node: Node u is an articulation point if any child v has low[v] >= discovery[u].
//
// 5. BIPARTITE GRAPH CHECK
//    - Purpose: Verifies if a graph's vertices can be divided into 2 independent sets
//               such that no two adjacent vertices share the same set.
//    - Strategy: Breadth-First Search (BFS) 2-Coloring technique.
//    - Rule: If you encounter an adjacent neighbor already painted with your current color,
//            the graph contains an odd-length cycle and is NOT bipartite.
//
//==============================================================================

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class AdvancedGraphs {

  static final int INF = Integer.MAX_VALUE;
  private static int timer = 0; // Global discovery clock for Tarjan, Bridges, and APs

  // Structural class representing a graph edge
  static class Edge {

    int src, dest, weight;

    Edge(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.weight = weight;
    }
  }

  //--------------------------------------------------------------------------
  // MAIN EXECUTION HUB
  //--------------------------------------------------------------------------
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Setup initial 4-node structural example graph from your layout
    int numNodes = 4;
    Edge[] edges = new Edge[5];
    edges[0] = new Edge(0, 1, 3);
    edges[1] = new Edge(0, 3, 7);
    edges[2] = new Edge(1, 2, 1);
    edges[3] = new Edge(2, 0, 1);
    edges[4] = new Edge(2, 3, 2);

    // Convert edge definitions into a standard Adjacency List
    List<List<Integer>> adj = new ArrayList<>();
    for (int i = 0; i < numNodes; i++) {
      adj.add(new ArrayList<>());
    }
    for (Edge edge : edges) {
      adj.get(edge.src).add(edge.dest);
    }

    System.out.println("=== KOSARAJU'S ALGORITHM ===");
    runKosaraju(numNodes, adj);

    System.out.println("\n=== TARJAN'S ALGORITHM ===");
    runTarjan(numNodes, adj);

    System.out.println("\n=== BRIDGE FINDING ALGORITHM ===");
    runBridgeFinder(numNodes, adj);

    System.out.println("\n=== ARTICULATION POINTS ===");
    runArticulationPoints(numNodes, adj);

    System.out.println("\n=== BIPARTITE GRAPH CHECK ===");
    boolean bipartiteResult = runBipartiteCheck(numNodes, adj);
    System.out.println("Is the sample graph Bipartite? " + bipartiteResult);

    scanner.close();
  }

  //--------------------------------------------------------------------------
  // 1. KOSARAJU'S ALGORITHM IMPLEMENTATION
  //--------------------------------------------------------------------------
  public static void runKosaraju(int n, List<List<Integer>> adj) {
    boolean[] visited = new boolean[n];
    Stack<Integer> stack = new Stack<>();

    // Pass 1: Push nodes to stack based on finishing times
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        kosarajuPass1DFS(i, visited, stack, adj);
      }
    }

    // Pass 2: Invert/Transpose the graph directions
    List<List<Integer>> transposedAdj = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      transposedAdj.add(new ArrayList<>());
    }
    for (int u = 0; u < n; u++) {
      for (int v : adj.get(u)) {
        transposedAdj.get(v).add(u);
      }
    }

    // Pass 3: Collect components matching stack sequence pop ordering
    Arrays.fill(visited, false);
    while (!stack.isEmpty()) {
      int v = stack.pop();
      if (!visited[v]) {
        System.out.print("SCC Group: ");
        kosarajuPass2DFS(v, visited, transposedAdj);
        System.out.println();
      }
    }
  }

  private static void kosarajuPass1DFS(
    int v,
    boolean[] visited,
    Stack<Integer> stack,
    List<List<Integer>> adj
  ) {
    visited[v] = true;
    for (int neighbor : adj.get(v)) {
      if (!visited[neighbor]) {
        kosarajuPass1DFS(neighbor, visited, stack, adj);
      }
    }
    stack.push(v);
  }

  private static void kosarajuPass2DFS(
    int v,
    boolean[] visited,
    List<List<Integer>> transposedAdj
  ) {
    visited[v] = true;
    System.out.print(v + " ");
    for (int neighbor : transposedAdj.get(v)) {
      if (!visited[neighbor]) {
        kosarajuPass2DFS(neighbor, visited, transposedAdj);
      }
    }
  }

  //--------------------------------------------------------------------------
  // 2. TARJAN'S ALGORITHM IMPLEMENTATION
  //--------------------------------------------------------------------------
  public static void runTarjan(int n, List<List<Integer>> adj) {
    int[] disc = new int[n];
    int[] low = new int[n];
    boolean[] inStack = new boolean[n];
    Stack<Integer> st = new Stack<>();

    Arrays.fill(disc, -1);
    Arrays.fill(low, -1);
    timer = 0;

    for (int i = 0; i < n; i++) {
      if (disc[i] == -1) {
        tarjanDFS(i, disc, low, st, inStack, adj);
      }
    }
  }

  private static void tarjanDFS(
    int u,
    int[] disc,
    int[] low,
    Stack<Integer> st,
    boolean[] inStack,
    List<List<Integer>> adj
  ) {
    disc[u] = low[u] = ++timer;
    st.push(u);
    inStack[u] = true;

    for (int v : adj.get(u)) {
      if (disc[v] == -1) {
        tarjanDFS(v, disc, low, st, inStack, adj);
        low[u] = Math.min(low[u], low[v]);
      } else if (inStack[v]) {
        low[u] = Math.min(low[u], disc[v]);
      }
    }

    if (low[u] == disc[u]) {
      System.out.print("SCC Group: ");
      while (true) {
        int v = st.pop();
        inStack[v] = false;
        System.out.print(v + " ");
        if (u == v) break;
      }
      System.out.println();
    }
  }

  //--------------------------------------------------------------------------
  // 3. BRIDGE FINDING ALGORITHM IMPLEMENTATION
  //--------------------------------------------------------------------------
  public static void runBridgeFinder(int n, List<List<Integer>> adj) {
    int[] disc = new int[n];
    int[] low = new int[n];
    boolean[] visited = new boolean[n];
    timer = 0;

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        bridgeDFS(i, -1, disc, low, visited, adj);
      }
    }
  }

  private static void bridgeDFS(
    int u,
    int parent,
    int[] disc,
    int[] low,
    boolean[] visited,
    List<List<Integer>> adj
  ) {
    visited[u] = true;
    disc[u] = low[u] = ++timer;

    for (int v : adj.get(u)) {
      if (v == parent) continue;
      if (visited[v]) {
        low[u] = Math.min(low[u], disc[v]);
      } else {
        bridgeDFS(v, u, disc, low, visited, adj);
        low[u] = Math.min(low[u], low[v]);

        if (low[v] > disc[u]) {
          System.out.println("Bridge Edge Found: " + u + " --- " + v);
        }
      }
    }
  }

  //--------------------------------------------------------------------------
  // 4. ARTICULATION POINTS IMPLEMENTATION
  //--------------------------------------------------------------------------
  public static void runArticulationPoints(int n, List<List<Integer>> adj) {
    int[] disc = new int[n];
    int[] low = new int[n];
    boolean[] visited = new boolean[n];
    boolean[] isAP = new boolean[n];
    timer = 0;

    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        articulationDFS(i, -1, disc, low, visited, isAP, adj);
      }
    }

    System.out.print("Identified Critical Articulation Points: ");
    boolean empty = true;
    for (int i = 0; i < n; i++) {
      if (isAP[i]) {
        System.out.print(i + " ");
        empty = false;
      }
    }
    if (empty) System.out.print("None");
    System.out.println();
  }

  private static void articulationDFS(
    int u,
    int parent,
    int[] disc,
    int[] low,
    boolean[] visited,
    boolean[] isAP,
    List<List<Integer>> adj
  ) {
    visited[u] = true;
    disc[u] = low[u] = ++timer;
    int childCount = 0;

    for (int v : adj.get(u)) {
      if (v == parent) continue;
      if (visited[v]) {
        low[u] = Math.min(low[u], disc[v]);
      } else {
        childCount++;
        articulationDFS(v, u, disc, low, visited, isAP, adj);
        low[u] = Math.min(low[u], low[v]);

        if (parent != -1 && low[v] >= disc[u]) {
          isAP[u] = true;
        }
      }
    }

    if (parent == -1 && childCount > 1) {
      isAP[u] = true;
    }
  }

  //--------------------------------------------------------------------------
  // 5. BIPARTITE GRAPH CHECK IMPLEMENTATION
  //--------------------------------------------------------------------------
  public static boolean runBipartiteCheck(int n, List<List<Integer>> adj) {
    int[] colors = new int[n];
    Arrays.fill(colors, -1); // Initialize all vertices as uncolored (-1)

    for (int i = 0; i < n; i++) {
      if (colors[i] == -1) {
        if (!bipartiteBFS(i, colors, adj)) {
          return false;
        }
      }
    }
    return true;
  }

  private static boolean bipartiteBFS(
    int src,
    int[] colors,
    List<List<Integer>> adj
  ) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(src);
    colors[src] = 0; // Paint starting color code 0

    while (!queue.isEmpty()) {
      int u = queue.poll();
      for (int v : adj.get(u)) {
        if (colors[v] == colors[u]) {
          return false; // Direct coloring conflict discovered
        }
        if (colors[v] == -1) {
          colors[v] = 1 - colors[u]; // Invert color (Swaps between 0 and 1)
          queue.add(v);
        }
      }
    }
    return true;
  }
}
