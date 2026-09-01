# Java Fundamentals Workspace

A hands-on Java learning repository for beginners, intermediate learners, and interview-focused developers.

This workspace is organized by topic and covers Java fundamentals, object-oriented programming, core data structures, algorithms, system design, design patterns, and interview preparation.

## Learning roadmap

| #   | Folder                                                                        | Focus                                                      |
| --- | ----------------------------------------------------------------------------- | ---------------------------------------------------------- |
| 01  | [01_java_basics](01_java_basics/)                                             | Java syntax, variables, operators, control flow, methods   |
| 02  | [02_exception_handling](02_exception_handling/)                               | Exceptions, handling errors, defensive coding              |
| 03  | [03_oops](03_oops/)                                                           | Classes, objects, inheritance, polymorphism, encapsulation |
| 04  | [04_interfaces](04_interfaces/)                                               | Interfaces, abstraction, contracts, functional interfaces  |
| 05  | [05_streams_and_lambdas](05_streams_and_lambdas/)                             | Streams, lambdas, functional style                         |
| 06  | [06_regex](06_regex/)                                                         | Regex and text processing                                  |
| 07  | [07_jvm_jdk](07_jvm_jdk/)                                                     | JVM internals, JDK, runtime model                          |
| 08  | [08_collections](08_collections/)                                             | Lists, sets, maps, collection APIs                         |
| 09  | [09_arrays](09_arrays/)                                                       | Arrays, matrices, patterns, prefix sums                    |
| 10  | [10_strings](10_strings/)                                                     | Strings, manipulation, formatting, immutability            |
| 11  | [11_stack](11_stack/)                                                         | Stacks, bracket matching, DFS support                      |
| 12  | [12_queue](12_queue/)                                                         | Queues, BFS, sliding window                                |
| 13  | [13_linkedlist](13_linkedlist/)                                               | Linked lists, reversing, cycle detection                   |
| 14  | [14_hashmaps_and_hashsets](14_hashmaps_and_hashsets/)                         | Hashing, collisions, hash set/map internals                |
| 15  | [15_trees_and_traversals](15_trees_and_traversals/)                           | Trees, BSTs, balanced trees, traversal                     |
| 16  | [16_heaps](16_heaps/)                                                         | Heaps, priority queues, heap patterns                      |
| 17  | [17_tries](17_tries/)                                                         | Tries, prefix matching, dictionary problems                |
| 18  | [18_graphs](18_graphs/)                                                       | Graph traversal, MST, shortest paths, topological sort     |
| 19  | [19_greedy_algorithms](19_greedy_algorithms/)                                 | Greedy strategies and proof ideas                          |
| 20  | [20_dynamic_programming](20_dynamic_programming/)                             | DP states, recurrence, optimization                        |
| 21  | [21_bit_manipulation](21_bit_manipulation/)                                   | Bitwise operators, masks, XOR, toggling                    |
| 22  | [22_algorithms_and_patterns](22_algorithms_and_patterns/)                     | Interview patterns and algorithm templates                 |
| 23  | [23_concurrency_and_multithreading](23_concurrency_and_multithreading/)       | Threads, locks, executors, synchronization                 |
| 24  | [24_jdbc_and_database_basics](24_jdbc_and_database_basics/)                   | JDBC and database fundamentals                             |
| 25  | [25_design_patterns_and_system_design](25_design_patterns_and_system_design/) | Design patterns, LLD, HLD, system design prep              |

## Core topic areas

- Java basics and syntax
- OOP and interfaces
- Collections and data structures
- Trees, heaps, tries, graphs
- Greedy, DP, and bit manipulation
- Concurrency basics
- JDBC and database foundations
- Design patterns and system design

## Recommended study sequence

1. Learn Java basics and OOP
2. Master collections, arrays, strings, and hashing
3. Practice stacks, queues, linked lists, and trees
4. Study heaps, tries, graphs, and graph algorithms
5. Cover greedy, DP, and bit manipulation
6. Learn concurrency and database basics
7. Finish with design patterns, LLD, HLD, and interview prep

## Interview-focused system design section

The final topic folder includes:

- design patterns
- low-level design (LLD) concepts and examples
- high-level design (HLD) concepts and examples
- interview Q&A notes
- component and sequence diagram notes
- Java code examples for common architecture problems

## Complexity summary

- Array access: O(1)
- Array search: O(n)
- HashMap / HashSet average lookup: O(1)
- Stack / queue operations: O(1)
- Tree traversal: O(n)
- Graph traversal: O(V + E)
- Heap operations: O(log n)
- Dijkstra: O((V + E) log V)
- DP: depends on state, often O(n × W) or O(n²)

## Important principle

The goal is not only to memorize code, but to understand why a data structure or algorithm is used and when it is appropriate.

Each folder contains focused explanations and runnable Java examples to help you practice directly.

## Quick reference table

| Structure         | Access by index |   Search |  Insert at end | Insert at middle |   Delete |
| ----------------- | --------------: | -------: | -------------: | ---------------: | -------: |
| ArrayList         |            O(1) |     O(n) | O(1) amortized |             O(n) |     O(n) |
| LinkedList        |            O(n) |     O(n) |           O(1) |             O(1) |     O(1) |
| HashMap / HashSet |        O(1) avg | O(1) avg |       O(1) avg |         O(1) avg | O(1) avg |

\*For ArrayList, append is amortized O(1).

## Notes

- This repo is intended for practice and learning, not for production application code.
- Java files are source examples; they are not meant to be committed as compiled `.class` output.
- You can run each example individually with `javac` and `java` while keeping the repo clean.
