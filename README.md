# Java Fundamentals Workspace

A comprehensive, hands-on Java learning repository for **beginner-to-intermediate developers**. It covers core Java syntax, object-oriented programming, collections, and common data structures with runnable examples and interview-focused notes.

Folders are **numbered in learning order** (`01_`, `02_`, …). Each folder contains a [`_README.md`](01_java_basics/_README.md) with theory, examples, and quick reference. Java **package names** match topics without numbers (`java_basics`, `arrays`, `stack`) because packages cannot start with a digit.

## 📚 What You Will Learn

| Topic | Coverage | Level |
|-------|----------|-------|
| **Basics** | Variables, loops, methods, control flow | Beginner |
| **Data Structures** | Arrays, strings, linked lists, stacks, queues, hash tables | Intermediate |
| **OOP** | Classes, inheritance, polymorphism, encapsulation, interfaces | Beginner–Intermediate |
| **Collections** | List, Set, Map implementations and operations | Intermediate |
| **Advanced** | Streams, lambdas, regex, JVM concepts | Intermediate |
| **Interview Prep** | Common algorithms, problem-solving patterns | Intermediate |

## 📁 Folder Guide and Difficulty Levels

| # | Folder | Contents | Difficulty | Key Files |
|---|--------|----------|------------|-----------|
| 01 | [java_basics](01_java_basics/) | Variables, loops, methods, operators | Beginner | HelloJava, DataType, Loops, Methods |
| 02 | [oops](02_oops/) | Classes, objects, inheritance, constructors | Beginner–Intermediate | ClassAndObject, ConstructorsExample, OOPConcepts |
| 03 | [strings](03_strings/) | String pool, immutability, StringBuilder, formatting | Beginner–Intermediate | StringsExample, StringBuilderExample, PalindromeExample |
| 04 | [arrays](04_arrays/) | Array operations, prefix sum, matrix problems | Intermediate | ArraysExample, PrefixSum, MaxSubarraySum, SpiralMatrix |
| 05 | [collections](05_collections/) | List, Set, Map interfaces and operations | Intermediate | CollectionsConcepts |
| 06 | [stack](06_stack/) | LIFO stack, bracket matching, postfix evaluation | Intermediate | StackOperations, StackInterviewProblems |
| 07 | [queue](07_queue/) | FIFO queue, BFS, sliding window, circular queue | Intermediate | QueueOperations, QueueInterviewProblems |
| 08 | [linkedlist](08_linkedlist/) | Singly linked lists, reversal, cycle detection | Intermediate | LinkedListBasics, LinkedListProblems |
| 09 | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/) | Hash tables, collision handling, hashing | Intermediate | Hashmaps, Hashsets, custom implementations |
| 10 | [exception_handling](10_exception_handling/) | Try-catch, custom exceptions | Beginner | ExceptionHandlingExample |
| 11 | [interfaces](11_interfaces/) | Interface contracts, functional interfaces | Beginner–Intermediate | InterfacesExample, FunctionalInterfaceExample |
| 12 | [streams_and_lambdas](12_streams_and_lambdas/) | Stream API, Collectors, lambda expressions | Intermediate | StreamsExample, AdvancedStreamsExample |
| 13 | [regex](13_regex/) | Pattern matching, text processing | Intermediate | RegexExample, MatcherExample |
| 14 | [jvm_jdk](14_jvm_jdk/) | Java compilation, runtime, bytecode | Intermediate | JavaVersion |

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

| Pattern / Concept | Folder | Key idea | Example problems |
|-------------------|--------|----------|------------------|
| Primitive vs reference, pass by value | [java_basics](01_java_basics/DataType.java) | `int` vs `Integer`; Java passes reference copies | Explain variable behavior |
| String pool, `==` vs `.equals()` | [strings](03_strings/StringsExample.java) | Literals in pool; use `.equals()` for content | String comparison questions |
| StringBuilder in loops | [strings](03_strings/StringBuilderExample.java) | Avoid O(n²) string concatenation | Build strings efficiently |
| Two-pointer | [strings](03_strings/PalindromeExample.java), [arrays](04_arrays/TrapRainWater.java) | Start/end pointers moving inward | Palindrome, Two Sum II, 3Sum |
| Prefix sum | [arrays](04_arrays/PrefixSum.java) | O(1) range sum after O(n) preprocessing | Subarray sum, range queries |
| Kadane / sliding window | [arrays](04_arrays/MaxSubarraySum.java) | Max subarray, contiguous window | Max subarray, longest substring |
| Binary search | [arrays](04_arrays/ArraysExample.java) | O(log n) on sorted data | Search rotated array |
| Matrix traversal | [arrays](04_arrays/SpiralMatrix.java) | Row/column iteration, spiral | Spiral matrix, rotate image |
| List vs Set vs Map | [collections](05_collections/CollectionsConcepts.java) | Order, uniqueness, key-value lookup | Choose right collection |
| Stack (LIFO) | [stack](06_stack/StackInterviewProblems.java) | Push/pop top; monotonic stack | Valid parentheses, next greater element |
| Queue (FIFO) | [queue](07_queue/QueueInterviewProblems.java) | Enqueue rear, dequeue front; BFS | Level-order traversal, shortest path |
| Fast/slow pointers | [linkedlist](08_linkedlist/LinkedListProblems.java) | Cycle detection, find middle | Linked list cycle, middle node |
| Reverse linked list | [linkedlist](08_linkedlist/LinkedListProblems.java) | Three pointers: prev, curr, next | Reverse list, palindrome list |
| HashMap frequency | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/Hashmaps.java) | O(1) avg lookup; count/group by key | Two sum, group anagrams |
| HashSet uniqueness | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/Hashsets.java) | O(1) avg contains check | Contains duplicate |
| Hash collision handling | [hashmaps_and_hashsets](09_hashmaps_and_hashsets/SeparateChainingHashMap.java) | Chaining vs probing; load factor | Explain HashMap internals |
| OOP four pillars | [oops](02_oops/OOPConcepts.java) | Encapsulation, inheritance, polymorphism | Override vs overload |
| Interface vs abstract class | [interfaces](11_interfaces/InterfacesExample.java) | Contract vs partial implementation | Design choice questions |
| Streams + Collectors | [streams_and_lambdas](12_streams_and_lambdas/AdvancedStreamsExample.java) | filter, map, collect, groupingBy | Data transformation pipelines |
| JVM, stack vs heap | [jvm_jdk](14_jvm_jdk/JavaVersion.java) | Bytecode, compilation flow, memory | How Java runs |

## Big-O Cheat Sheet

| Operation | Array | Linked List | HashMap | Stack/Queue |
|-----------|-------|-------------|---------|-------------|
| Access by index | O(1) | O(n) | — | — |
| Search | O(n) | O(n) | O(1) avg | O(n) |
| Insert at end | O(1)* | O(1) | O(1) avg | O(1) |
| Insert at middle | O(n) | O(n) | — | — |
| Delete | O(n) | O(n) | O(1) avg | O(1) |

*ArrayList amortized O(1) for append.
