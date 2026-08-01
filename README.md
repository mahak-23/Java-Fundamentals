# Java Fundamentals Workspace

A comprehensive, hands-on Java learning repository for **beginner-to-intermediate developers**. It covers core Java syntax, object-oriented programming, collections, graphs, greedy algorithms, dynamic programming, and interview-focused notes.

Folders are **numbered in learning order** (`01_`, `02_`, …). Each folder contains a [`_README.md`](01_java_basics/_README.md) with theory, examples, and quick reference. Java **package names** match topics without numbers (`java_basics`, `arrays`, `stack`) because packages cannot start with a digit.

## 📚 What You Will Learn

| Topic               | Coverage                                                                                | Level                 |
| ------------------- | --------------------------------------------------------------------------------------- | --------------------- |
| **Basics**          | Variables, loops, methods, control flow                                                 | Beginner              |
| **Data Structures** | Arrays, strings, linked lists, stacks, queues, hash tables, trees, heaps, tries, graphs | Intermediate          |
| **Algorithms**      | Binary search, BFS/DFS, dynamic programming, greedy strategies, graph shortest path     | Intermediate          |
| **OOP**             | Classes, inheritance, polymorphism, encapsulation, interfaces                           | Beginner–Intermediate |
| **Collections**     | List, Set, Map implementations and operations                                           | Intermediate          |
| **Advanced**        | Streams, lambdas, regex, JVM concepts, concurrency, JDBC, design patterns               | Intermediate          |
| **Interview Prep**  | Common algorithms, problem-solving patterns                                             | Intermediate          |

## 📁 Folder Guide and Difficulty Levels

| #   | Folder                                                                     | Contents                                                         | Difficulty            | Key Files                                                                                                                                     |
| --- | -------------------------------------------------------------------------- | ---------------------------------------------------------------- | --------------------- | --------------------------------------------------------------------------------------------------------------------------------------------- |
| 01  | [java_basics](01_java_basics/)                                             | Variables, loops, methods, operators                             | Beginner              | HelloJava, DataType, Loops, Methods                                                                                                           |
| 02  | [oops](02_oops/)                                                           | Classes, objects, inheritance, constructors                      | Beginner–Intermediate | ClassAndObject, ConstructorsExample, OOPConcepts                                                                                              |
| 03  | [strings](03_strings/)                                                     | String pool, immutability, StringBuilder, formatting             | Beginner–Intermediate | StringsExample, StringBuilderExample, PalindromeExample                                                                                       |
| 04  | [arrays](04_arrays/)                                                       | Array operations, prefix sum, matrix problems                    | Intermediate          | ArraysExample, PrefixSum, MaxSubarraySum, SpiralMatrix                                                                                        |
| 05  | [collections](05_collections/)                                             | List, Set, Map interfaces and operations                         | Intermediate          | CollectionsConcepts                                                                                                                           |
| 06  | [stack](06_stack/)                                                         | LIFO stack, bracket matching, postfix evaluation                 | Intermediate          | StackOperations, StackInterviewProblems                                                                                                       |
| 07  | [queue](07_queue/)                                                         | FIFO queue, BFS, sliding window, circular queue                  | Intermediate          | QueueOperations, QueueInterviewProblems                                                                                                       |
| 08  | [linkedlist](08_linkedlist/)                                               | Singly linked lists, reversal, cycle detection                   | Intermediate          | LinkedListBasics, LinkedListProblems                                                                                                          |
| 09  | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/)                         | Hash tables, collision handling, hashing                         | Intermediate          | Hashmaps, Hashsets, custom implementations                                                                                                    |
| 10  | [exception_handling](10_exception_handling/)                               | Try-catch, custom exceptions                                     | Beginner              | ExceptionHandlingExample                                                                                                                      |
| 11  | [interfaces](11_interfaces/)                                               | Interface contracts, functional interfaces                       | Beginner–Intermediate | InterfacesExample, FunctionalInterfaceExample                                                                                                 |
| 12  | [streams_and_lambdas](12_streams_and_lambdas/)                             | Stream API, Collectors, lambda expressions                       | Intermediate          | StreamsExample, AdvancedStreamsExample                                                                                                        |
| 13  | [regex](13_regex/)                                                         | Pattern matching, text processing                                | Intermediate          | RegexExample, MatcherExample                                                                                                                  |
| 14  | [jvm_jdk](14_jvm_jdk/)                                                     | Java compilation, runtime, bytecode                              | Intermediate          | JavaVersion                                                                                                                                   |
| 15  | [trees_and_traversals](15_trees_and_traversals/)                           | Trees, binary trees, BSTs, AVL trees, Morris traversal           | Intermediate          | TreeBasics, BinaryTreeExample, BinarySearchTreeExample, AVLTreeExample, MorrisTraversalExample                                                |
| 16  | [heaps](16_heaps/)                                                         | Min-heaps and max-heaps, priority queue operations               | Intermediate          | HeapExample                                                                                                                                   |
| 17  | [tries](17_tries/)                                                         | Trie insert/search/prefix matching                               | Intermediate          | TrieExample                                                                                                                                   |
| 18  | [graphs](18_graphs/)                                                       | Graph traversal and adjacency-list design                        | Intermediate          | GraphExample, GraphTypesExample                                                                                                               |
| 19  | [dynamic_programming](19_dynamic_programming/)                             | Fibonacci and knapsack patterns                                  | Intermediate          | DynamicProgrammingExample                                                                                                                     |
| 20  | [algorithms_and_patterns](20_algorithms_and_patterns/)                     | Binary search, Kadane, two pointers, sliding window, BFS/DFS, DP | Intermediate          | ArraysAndStringsAlgorithms, LinkedListAndStackQueueAlgorithms, HashingAndHeapAlgorithms, TreeAndGraphAlgorithms, DynamicProgrammingAlgorithms |
| 21  | [greedy_algorithms](21_greedy_algorithms/)                                 | Activity selection, fractional knapsack                          | Intermediate          | GreedyAlgorithmsExample                                                                                                                       |
| 22  | [concurrency_and_multithreading](22_concurrency_and_multithreading/)       | Thread safety, synchronization, executors                        | Intermediate          | ConcurrencyExample                                                                                                                            |
| 23  | [jdbc_and_database_basics](23_jdbc_and_database_basics/)                   | JDBC, PreparedStatement, queries                                 | Intermediate          | JDBCExample, JDBCPreparedStatementExample                                                                                                     |
| 24  | [design_patterns_and_system_design](24_design_patterns_and_system_design/) | Strategy pattern, factory mindset, scalability basics            | Intermediate          | DesignPatternsExample, FactoryPatternExample                                                                                                  |

## 📖 Complexity Notes

- Arrays: access O(1), search O(n), insert/delete O(n)
- Linked lists: insert/delete at head O(1), search O(n)
- Stack and queue: push/pop/enqueue/dequeue O(1)
- HashMap/HashSet: average lookup O(1), worst-case O(n)
- BST: search/insert/delete O(log n) average, O(n) worst
- Heap: `offer`/`poll`/`peek` O(log n)
- Trie: insert/search/prefix O(L)
- Graph traversal: BFS/DFS O(V + E)
- Unweighted shortest path: BFS O(V + E)
- Weighted shortest path: Dijkstra O((V + E) log V)
- Greedy algorithms: often O(n log n) when sorting is required, or O(n) for linear scans
- Dynamic programming: depends on the problem, often O(n × W) or O(n^2)

## 📖 Repository Organization

Each folder follows this structure:

```
folder/
├── _README.md              # Theory, examples, quick reference, interview notes
├── TopicExample.java       # Beginner-friendly runnable examples
├── InterviewProblems.java  # Intermediate problem-solving examples
└── Custom*.java            # Custom implementations (where applicable)
```

## 🎓 Interview Preparation Checklist

Before your interview, master these:

### Core Java Concepts

- [ ] Primitive vs reference types (`int`, `String`, arrays)
- [ ] Stack vs heap memory
- [ ] Pass by value vs pass by reference
- [ ] String immutability and StringBuilder
- [ ] Exception handling (try-catch-finally)

### Object-Oriented Programming

- [ ] Classes, objects, and constructors
- [ ] Inheritance and `super` keyword
- [ ] Polymorphism (method overriding)
- [ ] Encapsulation (private, public, getters/setters)
- [ ] Abstraction and interfaces
- [ ] When to use inheritance vs composition

### Data Structures and Collections

- [ ] Array operations and time complexity
- [ ] ArrayList vs LinkedList — when to use which
- [ ] HashSet vs TreeSet
- [ ] HashMap collisions and load factor
- [ ] `Collections.sort()` and custom comparators

### Algorithms

- [ ] Stack: bracket matching, postfix evaluation, DFS
- [ ] Queue: BFS, level-order traversal, sliding window
- [ ] Linked lists: reverse, cycle detection, merge
- [ ] Arrays: binary search, prefix sums, two-pointer technique
- [ ] Hash tables: two-sum, duplicate detection
- [ ] Graphs: BFS/DFS, connectivity, directed vs undirected, weighted shortest path
- [ ] Dynamic programming: memoization, tabulation, knapsack, Fibonacci optimization
- [ ] Greedy algorithms: activity selection, fractional knapsack, interval scheduling

### Problem-Solving Strategy

- [ ] Read the problem carefully; ask clarifying questions
- [ ] Discuss approach before coding
- [ ] Start with brute force, then optimize
- [ ] Write clean, readable code
- [ ] Test edge cases (empty, single element, null)
- [ ] State time and space complexity

### Advanced Topics (if applicable)

- [ ] Streams and lambdas
- [ ] Generics and type parameters
- [ ] Custom Comparable / Comparator
- [ ] Regular expressions

## Interview + DSA Problems

| Pattern / Concept                     | Folder                                                                               | Key idea                                           | Example problems                                   |
| ------------------------------------- | ------------------------------------------------------------------------------------ | -------------------------------------------------- | -------------------------------------------------- |
| Primitive vs reference, pass by value | [java_basics](01_java_basics/DataType.java)                                          | `int` vs `Integer`; Java passes reference copies   | Explain variable behavior                          |
| String pool, `==` vs `.equals()`      | [strings](03_strings/StringsExample.java)                                            | Literals in pool; use `.equals()` for content      | String comparison questions                        |
| StringBuilder in loops                | [strings](03_strings/StringBuilderExample.java)                                      | Avoid O(n²) string concatenation                   | Build strings efficiently                          |
| Two-pointer                           | [strings](03_strings/PalindromeExample.java), [arrays](04_arrays/TrapRainWater.java) | Start/end pointers moving inward                   | Palindrome, Two Sum II, 3Sum                       |
| Prefix sum                            | [arrays](04_arrays/PrefixSum.java)                                                   | O(1) range sum after O(n) preprocessing            | Subarray sum, range queries                        |
| Kadane / sliding window               | [arrays](04_arrays/MaxSubarraySum.java)                                              | Max subarray, contiguous window                    | Max subarray, longest substring                    |
| Binary search                         | [arrays](04_arrays/ArraysExample.java)                                               | O(log n) on sorted data                            | Search rotated array                               |
| Matrix traversal                      | [arrays](04_arrays/SpiralMatrix.java)                                                | Row/column iteration, spiral                       | Spiral matrix, rotate image                        |
| List vs Set vs Map                    | [collections](05_collections/CollectionsConcepts.java)                               | Order, uniqueness, key-value lookup                | Choose right collection                            |
| Stack (LIFO)                          | [stack](06_stack/StackInterviewProblems.java)                                        | Push/pop top; monotonic stack                      | Valid parentheses, next greater element            |
| Queue (FIFO)                          | [queue](07_queue/QueueInterviewProblems.java)                                        | Enqueue rear, dequeue front; BFS                   | Level-order traversal, shortest path               |
| Fast/slow pointers                    | [linkedlist](08_linkedlist/LinkedListProblems.java)                                  | Cycle detection, find middle                       | Linked list cycle, middle node                     |
| Reverse linked list                   | [linkedlist](08_linkedlist/LinkedListProblems.java)                                  | Three pointers: prev, curr, next                   | Reverse list, palindrome list                      |
| HashMap frequency                     | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/Hashmaps.java)                      | O(1) avg lookup; count/group by key                | Two sum, group anagrams                            |
| HashSet uniqueness                    | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/Hashsets.java)                      | O(1) avg contains check                            | Contains duplicate                                 |
| Hash collision handling               | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/SeparateChainingHashMap.java)       | Chaining vs probing; load factor                   | Explain HashMap internals                          |
| Trie insert/search/prefix             | [tries](17_tries/TrieExample.java)                                                   | Character-by-character prefix search               | Autocomplete, prefix matching                      |
| Heap / priority queue                 | [heaps](16_heaps/HeapExample.java)                                                   | Top-k selection, median maintenance                | K largest elements, priority scheduling            |
| Graph BFS/DFS                         | [graphs](18_graphs/GraphExample.java)                                                | Connectivity and traversal in adjacency lists      | Shortest path in unweighted graph, cycle detection |
| Directed weighted shortest path       | [graphs](18_graphs/GraphTypesExample.java)                                           | Dijkstra with non-negative weights                 | Minimum distance, route planning                   |
| Greedy strategy                       | [greedy_algorithms](21_greedy_algorithms/GreedyAlgorithmsExample.java)               | Local optimum choices for global optimum           | Activity selection, fractional knapsack            |
| Dynamic programming                   | [dynamic_programming](19_dynamic_programming/DynamicProgrammingExample.java)         | Memoization/tabulation for overlapping subproblems | Knapsack, Fibonacci, coin change                   |
| OOP four pillars                      | [oops](02_oops/OOPConcepts.java)                                                     | Encapsulation, inheritance, polymorphism           | Override vs overload                               |
| Interface vs abstract class           | [interfaces](11_interfaces/InterfacesExample.java)                                   | Contract vs partial implementation                 | Design choice questions                            |
| Streams + Collectors                  | [streams_and_lambdas](12_streams_and_lambdas/AdvancedStreamsExample.java)            | filter, map, collect, groupingBy                   | Data transformation pipelines                      |
| JVM, stack vs heap                    | [jvm_jdk](14_jvm_jdk/JavaVersion.java)                                               | Bytecode, compilation flow, memory                 | How Java runs                                      |

## Big-O Cheat Sheet

| Operation        | Array  | Linked List | HashMap  | Stack/Queue |
| ---------------- | ------ | ----------- | -------- | ----------- |
| Access by index  | O(1)   | O(n)        | —        | —           |
| Search           | O(n)   | O(n)        | O(1) avg | O(n)        |
| Insert at end    | O(1)\* | O(1)        | O(1) avg | O(1)        |
| Insert at middle | O(n)   | O(n)        | —        | —           |
| Delete           | O(n)   | O(n)        | O(1) avg | O(1)        |

\*ArrayList amortized O(1) for append.
