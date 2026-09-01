# Java Fundamentals Workspace

A hands-on Java learning repository for beginners and intermediate developers.

This workspace is organized by learning order and covers Java basics, object-oriented design, core data structures, algorithms, and system-level engineering concepts.

## Folder order

| #   | Folder                                                                        | Focus                                                      |
| --- | ----------------------------------------------------------------------------- | ---------------------------------------------------------- |
| 01  | [01_java_basics](01_java_basics/)                                             | Java syntax, control flow, methods, basics                 |
| 02  | [02_exception_handling](02_exception_handling/)                               | Exceptions, errors, defensive coding                       |
| 03  | [03_oops](03_oops/)                                                           | Classes, objects, inheritance, polymorphism, encapsulation |
| 04  | [04_interfaces](04_interfaces/)                                               | Interfaces, abstraction, contracts, functional interfaces  |
| 05  | [05_streams_and_lambdas](05_streams_and_lambdas/)                             | Streams, lambdas, functional style                         |
| 06  | [06_regex](06_regex/)                                                         | Regex and text processing                                  |
| 07  | [07_jvm_jdk](07_jvm_jdk/)                                                     | JVM, JDK, runtime model                                    |
| 08  | [08_collections](08_collections/)                                             | Lists, sets, maps, collections framework                   |
| 09  | [09_arrays](09_arrays/)                                                       | Arrays, prefix sums, matrices, patterns                    |
| 10  | [10_strings](10_strings/)                                                     | Strings, manipulation, format, immutability                |
| 11  | [11_stack](11_stack/)                                                         | Stacks, bracket matching, DFS support                      |
| 12  | [12_queue](12_queue/)                                                         | Queues, BFS, sliding window                                |
| 13  | [13_linkedlist](13_linkedlist/)                                               | Linked lists, reversal, cycle detection                    |
| 14  | [14_hashmaps_and_hashsets](14_hashmaps_and_hashsets/)                         | Hashing, collision handling, set/map internals             |
| 15  | [15_trees_and_traversals](15_trees_and_traversals/)                           | Trees, BSTs, AVL, Morris traversal                         |
| 16  | [16_heaps](16_heaps/)                                                         | Heap and priority queue patterns                           |
| 17  | [17_tries](17_tries/)                                                         | Trie and prefix matching                                   |
| 18  | [18_graphs](18_graphs/)                                                       | BFS, DFS, shortest paths, MST, topo sort                   |
| 19  | [19_greedy_algorithms](19_greedy_algorithms/)                                 | Greedy strategies and proof ideas                          |
| 20  | [20_dynamic_programming](20_dynamic_programming/)                             | DP state, recurrence, optimization                         |
| 21  | [21_bit_manipulation](21_bit_manipulation/)                                   | Bit-level operations and masks                             |
| 22  | [22_algorithms_and_patterns](22_algorithms_and_patterns/)                     | Core interview patterns and algorithm templates            |
| 23  | [23_concurrency_and_multithreading](23_concurrency_and_multithreading/)       | Threads, locks, executors                                  |
| 24  | [24_jdbc_and_database_basics](24_jdbc_and_database_basics/)                   | JDBC and database fundamentals                             |
| 25  | [25_design_patterns_and_system_design](25_design_patterns_and_system_design/) | Design patterns, LLD, HLD, system thinking                 |

## Core topics covered

- Java fundamentals and syntax
- OOP and interfaces
- Collections and data structures
- Trees, heaps, graphs, tries
- Greedy, DP, bit manipulation
- System design and design patterns

## Quick study order

1. Java basics
2. OOP + interfaces
3. Collections + arrays + strings
4. Stacks, queues, linked lists, hashing
5. Trees, heaps, tries, graphs
6. Greedy, DP, bit manipulation
7. Design patterns and system design

## Complexity notes

- Arrays: access O(1), search O(n)
- HashMap / HashSet: average lookup O(1)
- Stack / queue: push / pop / enqueue / dequeue O(1)
- Tree traversal: O(V + E) for graphs, O(n) for tree walk
- Heap operations: O(log n)
- Dijkstra: O((V + E) log V)
- DP: depends on the state; often O(n × W) or O(n²)

## Important principle

The goal is not only to memorize code, but to understand when to use a structure or algorithm and why it works.

Each folder contains a focused README and runnable Java examples to help practice the concept directly.

| Access by index | O(1) | O(n) | — | — |
| Search | O(n) | O(n) | O(1) avg | O(n) |
| Insert at end | O(1)\* | O(1) | O(1) avg | O(1) |
| Insert at middle | O(n) | O(n) | — | — |
| Delete | O(n) | O(n) | O(1) avg | O(1) |

\*ArrayList amortized O(1) for append.
