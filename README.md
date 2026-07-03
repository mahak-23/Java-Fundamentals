# Java Fundamentals Workspace

A comprehensive, hands-on Java learning repository for **beginner-to-intermediate developers** with practical examples, data structures, and interview preparation materials.

## ⚡ Quick Start

### Prerequisites
- Java Development Kit (JDK) 8+ installed
- Text editor or VS Code
- Basic command-line knowledge

### First Program (2 minutes)
```bash
cd java\ basics
javac HelloJava.java
java java.basics.HelloJava
```
Expected output: `Hello, Java!`

---

## 📚 What You Will Learn

| Topic | Coverage | Level |
|-------|----------|-------|
| **Basics** | Variables, loops, methods, control flow | Beginner |
| **Data Structures** | Arrays, strings, linked lists, stacks, queues, hash tables | Intermediate |
| **OOP** | Classes, inheritance, polymorphism, encapsulation, interfaces | Beginner-Intermediate |
| **Collections** | List, Set, Map implementations and operations | Intermediate |
| **Advanced** | Streams, lambdas, regex, JVM concepts | Intermediate |
| **Interview Prep** | Common algorithms, problem-solving patterns | Intermediate |

## 📁 Folder Guide & Difficulty Levels

| Folder | Contents | Difficulty | Time | Key Files |
|--------|----------|-----------|------|-----------|
| **java basics/** | Variables, loops, methods, operators | Beginner | 1-2 hrs | HelloJava, DataType, Loops, Methods |
| **arrays/** | Array operations, searching, sorting, matrix problems | Intermediate | 2-3 hrs | ArraysExample, SpiralMatrix, MaxSubarraySum |
| **strings/** | String operations, immutability, StringBuilder | Beginner-Intermediate | 1-2 hrs | StringsExample, StringBuilderExample |
| **oops/** | Classes, objects, inheritance, constructors | Beginner-Intermediate | 2-3 hrs | ClassAndObject, ConstructorsExample |
| **collections/** | List, Set, Map interfaces and operations | Intermediate | 1-2 hrs | CollectionsConcepts |
| **stack/** | LIFO stack, bracket matching, postfix evaluation | Intermediate | 2-3 hrs | StackOperations, StackInterviewProblems |
| **queue/** | FIFO queue, BFS, sliding window, deque | Intermediate | 2-3 hrs | QueueOperations, QueueInterviewProblems |
| **LinkedList/** | Singly/doubly linked lists, cycle detection | Intermediate | 2-3 hrs | LinkedListBasics, LinkedListProblems |
| **Hashmaps & Hashsets/** | Hash tables, collision handling, hashing | Intermediate | 2-3 hrs | Hashmaps, Hashsets, custom implementations |
| **exception_handling/** | Try-catch, custom exceptions, handling | Beginner | 1 hr | ExceptionHandlingExample |
| **regex/** | Pattern matching, text processing | Intermediate | 1-2 hrs | RegexExample, MatcherExample |
| **interfaces/** | Interface contracts, functional interfaces | Beginner-Intermediate | 1 hr | InterfacesExample, FunctionalInterfaceExample |
| **streams_and_lambdas/** | Stream API, lambda expressions, functional programming | Intermediate | 2-3 hrs | LambdaExpressionsExample, StreamsExample |
| **jvm/jdk/** | Java compilation, runtime, bytecode | Intermediate | 1 hr | JavaVersion |

## 🎯 Recommended Learning Paths

### Path 1: Complete Beginner
**Goal:** Build solid Java fundamentals (6-8 hours)
1. **java basics/** (2 hrs) - Start here if you're new to Java
   - Read: HelloJava, DataType, Operators, Loops, Methods
   - Run each example and modify them

2. **oops/** (2 hrs) - Understand objects and classes
   - Read: ClassAndObject, ConstructorsExample, OOPConcepts
   
3. **strings/** (1 hr) - Work with text
   - Read: StringsExample, StringBuilderExample

4. **arrays/** (1 hr) - Arrays fundamentals
   - Read: ArraysExample, understand indexing

Then continue with **intermediate path**.

### Path 2: Intermediate Developer (Skip to here if you know basics)
**Goal:** Master data structures and problem-solving (8-10 hours)
1. **arrays/** (2 hrs) - Array algorithms and problems
2. **collections/** (1 hr) - List, Set, Map operations
3. **stack/** (2 hrs) - Stack concepts and interview problems
4. **queue/** (2 hrs) - Queue concepts and BFS algorithms
5. **LinkedList/** (2 hrs) - Linked list operations and problems
6. **Hashmaps & Hashsets/** (1 hr) - Hash table internals

### Path 3: Interview Preparation (2-3 weeks)
1. Review all **InterviewProblems files** (stack, queue, linked list)
2. Practice with dry runs on paper first
3. Implement solutions from scratch (no copy-paste)
4. Time yourself: aim for 15-20 minutes per problem

### Path 4: Advanced Topics (Optional)
- **streams_and_lambdas/** - Functional programming
- **regex/** - Text pattern processing
- **exception_handling/** - Error handling strategies
- **interfaces/** - Design patterns
- **jvm/jdk/** - How Java runs under the hood

## 💡 How to Use This Repository

### Step-by-Step Guide
1. **Pick a learning path** above (Path 1, 2, 3, or 4)
2. **Read the README** in each folder before running code
3. **Compile and run** examples from the project root:
   ```bash
   # Example: compile a specific file
   javac java\ basics\HelloJava.java
   
   # Run the program
   java java.basics.HelloJava
   
   # Compile all files in a folder
   javac arrays\*.java
   ```
4. **Study the code** - understand what each line does
5. **Modify examples** - change values, add features, experiment
6. **Read interview problems** - try to solve before looking at solutions

### Tips for Effective Learning
- ✅ **Run code first** - see the output before reading the code
- ✅ **Trace through examples** - mentally execute the code line by line
- ✅ **Type code yourself** - don't copy-paste; typing helps memorize patterns
- ✅ **Break it down** - if a file is complex, focus on one method at a time
- ✅ **Test edge cases** - what happens with empty inputs, negatives, nulls?
- ✅ **Use a debugger** - VS Code has built-in Java debugger
- ✅ **Practice dry runs** - trace through array operations on paper

### Best Practices
- Read `_README.md` files first for theory
- Look for **"Time complexity"** and **"Space complexity"** headers
- Check **"Quick reference"** sections for common operations
- Use **interview notes** for key concepts to remember

## 📝 Common Commands Reference

```bash
# Compile a single file
javac java\ basics\HelloJava.java

# Run a compiled program (note: full package path, no .class)
java java.basics.HelloJava

# Compile all .java files in a folder
javac arrays\*.java

# Compile with debugging info (if using debugger)
javac -g arrays\ArraysExample.java

# Clean up compiled .class files
del /s *.class

# Compile and run in one command (advanced)
javac arrays\ArraysExample.java && java arrays.ArraysExample
```

## ⚠️ Common Errors & Solutions

| Error | Cause | Solution |
|-------|-------|----------|
| `'javac' is not recognized` | JDK not installed | Install JDK 8+ and add to PATH |
| `cannot find symbol` | Misspelled variable/method | Check spelling and capitalization |
| `Main method not found` | No `public static void main(String[] args)` | Add proper main method to class |
| `Exception in thread "main"` | Runtime error in program | Read the error message, add `System.out.println()` for debugging |
| `class HelloJava is public, should be declared in a file named HelloJava.java` | Filename mismatch | Rename file to match public class name |

## ❓ FAQ

**Q: Do I need to download anything besides the code?**
A: Just Java JDK (free from java.com or oracle.com). Everything else is included.

**Q: Can I run these on Mac/Linux?**
A: Yes! Paths use forward slashes on Mac/Linux: `javac java/basics/HelloJava.java`

**Q: How long does each section take?**
A: See the **Folder Guide** table above for estimated time per topic.

**Q: Can I skip topics?**
A: Beginners should follow Path 1 sequentially. Experienced devs can jump to Path 2.

**Q: Where do I find more practice problems?**
A: Each folder's `InterviewProblems` or `Problems` file has additional challenges.

**Q: How do I know if my solution is correct?**
A: Run the provided examples and compare output. Check complexity: O(n) should be faster than O(n²).

**Q: Should I memorize Big-O notation?**
A: No, but understand: O(1) is fastest, O(n) is linear, O(n²) is slow, O(log n) is efficient.

---

## 🎓 Interview Preparation Checklist

**Before your interview, master these:**

### Core Java Concepts
- [ ] Primitive vs. reference types (int, String, arrays)
- [ ] Stack vs. heap memory
- [ ] Pass by value vs. pass by reference
- [ ] String immutability and StringBuilder
- [ ] Exception handling (try-catch-finally)

### Object-Oriented Programming
- [ ] Classes, objects, and constructors
- [ ] Inheritance and super keyword
- [ ] Polymorphism (method overriding)
- [ ] Encapsulation (private, public, getters/setters)
- [ ] Abstraction and interfaces
- [ ] When to use inheritance vs. composition

### Data Structures & Collections
- [ ] Array operations and time complexity
- [ ] ArrayList vs. LinkedList (when to use which?)
- [ ] HashSet vs. TreeSet
- [ ] HashMap/Hashtable collisions and load factor
- [ ] Collections.sort() and custom comparators

### Algorithms (Most Important!)
- [ ] Stack: bracket matching, postfix evaluation, DFS
- [ ] Queue: BFS, level-order traversal, sliding window
- [ ] Linked Lists: reverse, cycle detection, merge
- [ ] Arrays: binary search, prefix sums, two-pointer technique
- [ ] Hash tables: two-sum problem, duplicate detection

### Problem-Solving Strategy
- [ ] Read problem carefully and ask clarifying questions
- [ ] Discuss approach before coding (think for 10 seconds)
- [ ] Start with brute force, then optimize
- [ ] Write clean, readable code with comments
- [ ] Test with edge cases (empty, single element, null)
- [ ] Discuss time and space complexity

### Advanced Topics (If Applicable)
- [ ] Streams and lambdas
- [ ] Generics and type parameters
- [ ] Custom comparable/comparator
- [ ] Regular expressions

---

## 📖 Repository Organization

Each folder follows this structure for consistency:

```
folder/
├── _README.md              # Start here! Contains:
│                           # - Theory and concepts
│                           # - Operation examples with output
│                           # - Time/space complexity
│                           # - Quick reference section
├── TopicExample.java       # Beginner-friendly examples
├── InterviewProblems.java  # Intermediate-level problems
└── Custom*.java            # Custom implementations (if applicable)
```

### Reading a _README.md
1. **Start with the concept explanation** (first 10 lines)
2. **Look at the table of contents** (if provided)
3. **Find your operation/problem** (use Ctrl+F to search)
4. **Read example code** with output comments
5. **Check "Quick reference"** for all operations
6. **Review "Interview notes"** for key tips

---

## 🚀 Next Steps

- **Week 1**: Complete Beginner Path (java basics → oops → strings)
- **Week 2-3**: Intermediate Path (arrays → collections → stack → queue → linked list)
- **Week 4**: Interview Problems & Dry Runs
- **Week 5+**: Practice problems on LeetCode (Easy → Medium)

### Advanced Resources (After This Course)
- **LeetCode** - Problem-solving practice
- **HackerRank** - Guided tutorials
- **GeeksforGeeks** - Detailed explanations
- **System Design** - If preparing for senior roles

---

## 📄 Repository Status

✅ All files compile successfully
✅ All data structures documented with examples
✅ Interview problems included in each module
✅ Quick-reference guides provided
✅ Best practices and patterns explained

**Ready to learn? Start with your chosen learning path above!**
