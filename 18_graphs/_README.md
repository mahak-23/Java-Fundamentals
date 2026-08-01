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

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Add edge | O(1) | O(1) |
| BFS | O(V + E) | O(V) |
| DFS | O(V + E) | O(V) |

Where $V$ is the number of vertices and $E$ is the number of edges.

## Example files

| File | Focus |
|------|-------|
| `GraphExample.java` | Undirected graph traversal with BFS and DFS |
| `GraphTypesExample.java` | Directed weighted graph and Dijkstra shortest path |

## Engineer checklist

- Understand adjacency list vs adjacency matrix
- Know when BFS is appropriate versus DFS
- Be able to explain shortest-path algorithms for directed and weighted graphs
- Know the difference between directed and undirected graph representations
- Understand when to use Dijkstra vs BFS for shortest path
