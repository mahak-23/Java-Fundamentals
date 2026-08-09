# Graphs

## About

Graphs model relationships between objects using vertices and edges. They are used for social networks, maps, routing, dependency analysis, and search problems.

## How to create a graph in Java

```java
List<List<Integer>> adjacencyList = new ArrayList<>();
```

## Graph types

- **Undirected graph**: each edge connects two vertices in both directions.
- **Directed graph**: edges have direction, from one vertex to another.
- **Unweighted graph**: edges are equal and only connectivity matters.
- **Weighted graph**: each edge stores a weight, such as distance or cost.

### Representation choices

- Use an adjacency list for sparse graphs: O(V + E) space.
- Use an adjacency matrix for dense graphs: O(V^2) space.
- For weighted graphs, store pairs `(neighbor, weight)` in each adjacency list entry.

## MST and topological sort

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

## Engineer checklist

- Understand adjacency list vs adjacency matrix
- Know when BFS is appropriate versus DFS
- Be able to explain shortest-path algorithms for directed and weighted graphs
- Know the difference between directed and undirected graph representations
- Understand when to use Dijkstra vs BFS for shortest path
