# Graphs

## About

Graphs model relationships between vertices (nodes) and edges (connections).
They are used for social networks, maps, routing, dependency analysis, and search problems.

## How to create a graph in Java

```java
List<List<Integer>> adjacencyList = new ArrayList<>();
```

Read each `.java` file — the header comment explains the algorithm, example, and run flow.

## Files

| File                                | Algorithm                       | Beginner idea                                     | Time                   |
| ----------------------------------- | ------------------------------- | ------------------------------------------------- | ---------------------- |
| `GraphExample.java`                 | BFS, DFS                        | Queue = level-by-level; recursion = go deep first | O(V + E)               |
| `GraphTypesExample.java`            | Dijkstra, Bellman-Ford          | Shortest path from one source                     | O((V+E) log V), O(V×E) |
| `FloydWarshallExample.java`         | Floyd-Warshall                  | Shortest path between **all pairs**               | O(V³)                  |
| `MSTAndTopologicalSortExample.java` | Kruskal, Prim, topological sort | Minimum spanning tree; task ordering in DAG       | O(E log E), O(V+E)     |

## Graph types

- **Undirected graph**: each edge connects two vertices in both directions.
- **Directed graph**: edges have direction, from one vertex to another.
- **Unweighted graph**: edges are equal and only connectivity matters.
- **Weighted graph**: each edge stores a weight, such as distance or cost.

## Graph basics

- **Vertex (node)** — an entity (city, user, task)
- **Edge** — a connection between two vertices
- **Undirected** — edge works both ways
- **Directed** — edge has a direction (A → B)
- **Weighted** — edge has a cost (distance, time)

### Representation choices

- Use an adjacency list for sparse graphs: O(V + E) space.
- Use an adjacency matrix for dense graphs: O(V^2) space.
- For weighted graphs, store pairs `(neighbor, weight)` in each adjacency list entry.

| Method           | Best for                        | Space    |
| ---------------- | ------------------------------- | -------- |
| Adjacency list   | Sparse graphs (most interviews) | O(V + E) |
| Adjacency matrix | Dense graphs, Floyd-Warshall    | O(V²)    |

## Shortest path — which algorithm?

| Algorithm      | Use when                                 | Handles negative weights? |
| -------------- | ---------------------------------------- | ------------------------- |
| BFS            | Unweighted graph, one source             | No                        |
| Dijkstra       | Weighted, non-negative, one source       | No                        |
| Bellman-Ford   | Weighted, one source, may have negatives | Yes                       |
| Floyd-Warshall | All pairs shortest path                  | Yes (no negative cycles)  |

### Floyd-Warshall (beginner summary)

Build a distance matrix `dist[i][j]`. For each intermediate vertex `k`, ask:

> Is going from `i` to `j` through `k` shorter?

```
dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
```

Repeat for every `k`. After all iterations, `dist[i][j]` holds the shortest path from `i` to `j`.

## MST and topological sort

- **Kruskal** — sort edges, union-find to avoid cycles
- **Prim** — min-heap picks next cheapest edge from growing tree
- **Topological sort** — order tasks with dependencies (must be a DAG)

### Minimum spanning tree (MST)

An MST connects all vertices in a weighted undirected graph with the minimum possible total edge weight. It is useful for network design, cable layout, clustering, and any problem where you need a minimum-cost spanning structure.

- Kruskal's algorithm sorts all edges by weight and adds the smallest edge that does not form a cycle, using a union-find structure.
- Prim's algorithm grows the MST from a starting vertex by repeatedly adding the cheapest edge that connects the current tree to a new vertex.
- In Prim's algorithm, edge candidates are ordered by weight in a priority queue so the next smallest crossing edge is selected.
- In Kruskal's algorithm, union-find should use path compression and union by rank or size so edge merges stay efficient.
- Both algorithms produce the same minimum spanning tree weight for connected, undirected graphs with non-negative weights.
- MSTs are only defined for connected, undirected graphs.

#### Prim vs Kruskal

| Feature        | Prim's                                          | Kruskal's                                               |
| -------------- | ----------------------------------------------- | ------------------------------------------------------- |
| Graph input    | Better for adjacency list representation        | Better for edge list representation                     |
| Strategy       | Grows one tree from a starting vertex           | Merges components using smallest edges                  |
| Edge selection | Chooses minimum outgoing edge from current tree | Chooses smallest global edge that does not form a cycle |
| Data structure | Min-heap / priority queue, visited set          | Union-find / disjoint sets                              |
| Best use case  | Dense graphs or adjacency list form             | Sparse graphs or when edges are easy to sort            |

### Union-find optimizations for Kruskal

Kruskal's algorithm uses a disjoint-set data structure to keep track of components.

- `find()` locates the component root for a vertex.
- `union()` merges two components when an edge connects them.
- Use **path compression** in `find()` so nodes point directly to their root.
- Use **union by rank or size** in `union()` so the smaller tree attaches to the larger tree.
- These heuristics keep union-find operations nearly constant time, typically $O(\alpha(n))$.

#### Union by Rank vs Union by Size

- **Union by rank**: each root stores an approximate tree height (rank). When merging two roots, attach the shorter tree under the taller one. If ranks are equal, attach one under the other and increment the resulting root's rank. Rank keeps the tree height small.

- **Union by size**: each root stores the size (number of elements) in its tree. When merging, attach the smaller tree under the larger tree and update the size. This keeps the resulting tree balanced by node count.

Both strategies achieve near-constant amortized cost when combined with path compression. In practice, union-by-size is slightly simpler to reason about, while union-by-rank can be marginally faster in some implementations; both are good choices.

### Topological sort

A topological sort orders the vertices of a directed acyclic graph (DAG) such that every edge goes from earlier to later in the ordering. It is commonly used for task scheduling and dependency resolution.

- Use Kahn's algorithm with a queue of zero-indegree nodes.
- A cycle means no valid topological ordering exists.

## Common operations

### Add an edge

```java
adjacencyList.get(from).add(to);
```

### BFS traversal

```java
Queue<Integer> queue = new LinkedList<>();
```

### DFS traversal

```java
void dfs(int node) { ... }
```

## Shortest path algorithms

### Unweighted shortest path

Use BFS when all edges have equal cost.

- Time: O(V + E)
- Space: O(V)

### Weighted shortest path

Use Dijkstra for non-negative edge weights.

- Time: O((V + E) log V) with a priority queue
- Space: O(V + E)

### Negative-weight shortest path

Use Bellman-Ford when some edges can have negative weights.

- Time: O(V × E)
- Space: O(V)

### Dijkstra vs Bellman-Ford

| Algorithm    | Best for                                       | Handles negative weights? | Typical complexity |
| ------------ | ---------------------------------------------- | ------------------------- | ------------------ |
| BFS          | Unweighted graphs                              | No                        | O(V + E)           |
| Dijkstra     | Weighted graphs with non-negative weights      | No                        | O((V + E) log V)   |
| Bellman-Ford | Weighted graphs with possible negative weights | Yes                       | O(V × E)           |

Bellman-Ford is slower than Dijkstra, but it is more general because it can detect negative cycles and still compute shortest paths when the graph is valid.

## Time and space complexity

| Operation | Time     | Space |
| --------- | -------- | ----- |
| Add edge  | O(1)     | O(1)  |
| BFS       | O(V + E) | O(V)  |
| DFS       | O(V + E) | O(V)  |

Where $V$ is the number of vertices and $E$ is the number of edges.

## Example files

| File                                | Focus                                              |
| ----------------------------------- | -------------------------------------------------- |
| `GraphExample.java`                 | Undirected graph traversal with BFS and DFS        |
| `GraphTypesExample.java`            | Directed weighted graph and Dijkstra shortest path |
| `MSTAndTopologicalSortExample.java` | Kruskal's MST and topological sort examples        |

# Advanced Graph Algorithms Quick Reference Guide

## 📊 Summary Reference Table

| Problem                | Graph Type | Key Test Condition                                        | Time Complexity | Space Complexity |
| :--------------------- | :--------- | :-------------------------------------------------------- | :-------------- | :--------------- |
| **SCC (Tarjan)**       | Directed   | `low[u] == disc[u]` $\rightarrow$ Found SCC Root          | $O(V + E)$      | $O(V)$           |
| **SCC (Kosaraju)**     | Directed   | Finish order Stack + Transpose Graph DFS                  | $O(V + E)$      | $O(V + E)$       |
| **Bridge Finding**     | Undirected | `low[v] > disc[u]` $\rightarrow$ Critical Edge            | $O(V + E)$      | $O(V)$           |
| **Articulation Point** | Undirected | `low[v] >= disc[u]` (or Root with >1 child)               | $O(V + E)$      | $O(V)$           |
| **Bipartite Check**    | Undirected | Adjacent neighbor shares same color $\rightarrow$ `false` | $O(V + E)$      | $O(V)$           |

---

## 🔎 Full Advanced Graph Algorithms Comparison Matrix

| Algorithm               | Best For                                                                                                                              | Key Structural Test / Strategy                                                                                                                                | Handles Weights?                                                          | Typical Complexity |
| :---------------------- | :------------------------------------------------------------------------------------------------------------------------------------ | :------------------------------------------------------------------------------------------------------------------------------------------------------------ | :------------------------------------------------------------------------ | :----------------- |
| **Kosaraju's**          | Finding **Strongly Connected Components (SCCs)** in modular systems where clean code separation is favored over micro-optimizations.  | **2-Pass DFS**: Uses a finishing-time tracking stack on the original graph, reverses all edge directions, and executes a second focused DFS pass.             | **Yes** (Ignores weight values; evaluates structural reachability paths). | $O(V + E)$         |
| **Tarjan's**            | Finding **Strongly Connected Components (SCCs)** in performance-critical, low-latency, or tight memory-constrained software setups.   | **1-Pass DFS**: Evaluates dynamic node identity states via `discovery` tracking clocks and tracking `low-link` node values alongside a member stack.          | **Yes** (Ignores weight values; evaluates structural reachability paths). | $O(V + E)$         |
| **Bridge Finding**      | Finding **critical communication bottlenecks** or single-point-of-failure routing links across physical infrastructure networks.      | **DFS Edge Ancestry Check**: Looks for any child node branch where the backtracking traversal condition `low[v] > discovery[u]` holds true.                   | **Yes** (Ignores weights; evaluates fundamental structural connections).  | $O(V + E)$         |
| **Articulation Points** | Finding **critical system core nodes** whose structural failure or elimination partitions a single connected network map.             | **DFS Vertex Splitting Check**: Validates if any downstream branch fulfills `low[v] >= disc[u]`, or checks if a root has multiple independent child subtrees. | **Yes** (Ignores weights; evaluates fundamental structural connections).  | $O(V + E)$         |
| **Bipartite Check**     | Finding structural compatibilities, processing thread-safe scheduling layers, or verifying **two-way resource assignment matchings**. | **BFS / DFS 2-Coloring Loop**: Alternates coloring binary states between adjacent nodes; checks for odd-length cycle structural collisions.                   | **Yes** (Ignores weights; evaluates structural state graphs).             | $O(V + E)$         |

---

## 💡 Key Operational Differences: Kosaraju's vs. Tarjan's

To understand why you would pick one approach over the other for a production pipeline, evaluate these architectural trade-offs:

- **Pass Count and Memory I/O**: Kosaraju's algorithm requires a dedicated structural graph inversion pass to build the transpose graph. This allocations extra heap space and reads edges twice. Tarjan's algorithm executes completely within a single forward DFS pass.
- **Execution Constants**: Because Tarjan's operates in a single pass without rebuilding internal adjacency structures mid-execution, it features smaller constants. This makes it faster for real-time applications.
- **Code Modularity & Readability**: Kosaraju’s algorithm is much simpler to debug, read, and write because it separates responsibilities into two distinct, predictable standard DFS sub-routines. Tarjan's algorithm is slightly more complex because it combines topological tracking variables, state tables, and a specialized node isolation stack in a single recursive sweep.

# Graph Theory Quiz: Answer Key

## Q1 & Q2: Circular Dependencies & Bridges

### Circular Dependency Detection

- **Structure:** Strongly Connected Components (SCCs) or Directed Cycles.
- **Algorithm:** **Tarjan's Algorithm** (or Path-based strong component algorithm) finds SCCs in a single DFS pass.

### Bridge vs. Articulation Point Tests

- **Bridge Test:** `low[v] > disc[u]`
- **Articulation Point Test:** `low[v] >= disc[u]` (for non-root nodes).
- **Difference:** A bridge requires strict inequality because the child `v` cannot even reach back to `u` via an alternative path. An articulation point allows `v` to reach back to `u`, but no higher.

---

## Q3: Splitting Nodes into Conflict-Free Groups

- **Graph Property:** **Bipartite** (2-colorable).
- **How to Check:** Run a **BFS** or **DFS** graph coloring algorithm. If you encounter an adjacent node already colored with the same color as the current node, the graph is not bipartite.

---

## Q4: Undirected DFS Parent Revisit

- **Why Skip the Parent:** Undirected edges are bidirectional. DFS traverses from parent `u` to child `v`. Without a check, `v` sees the edge back to `u` as an available path.
- **The Bug:** It causes a **false positive cycle detection**. The algorithm misidentifies the traversal path itself as a loop.

## Engineer checklist

- Understand adjacency list vs adjacency matrix
- Know when BFS is appropriate versus DFS
- Be able to explain shortest-path algorithms for directed and weighted graphs
- Know the difference between directed and undirected graph representations
- Understand when to use Dijkstra vs BFS for shortest path

## Interview questions

1. BFS vs DFS — when use each?
2. Why can't Dijkstra handle negative weights?
3. When use Floyd-Warshall instead of running Dijkstra from every vertex?
4. What is a DAG and why does topological sort need one?
