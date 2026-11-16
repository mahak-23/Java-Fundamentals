# Java Setup and Environment

> **Prerequisites:** None

This module fills the gap that most beginners hit before they even begin writing real Java code: understanding how Java is installed, how code is compiled, and how a simple program actually runs.

## Why this matters

Java is not just syntax. You need to understand the tools around the language:

- JDK: Java Development Kit
- JRE: Java Runtime Environment
- JVM: Java Virtual Machine
- `javac`: Java compiler
- `java`: Java launcher

Without this foundation, you can write code but still struggle to run it.

## Java ecosystem basics

### JDK, JRE, and JVM

- JVM runs Java bytecode.
- JRE includes the runtime needed to execute Java programs.
- JDK includes the JRE plus tools like the compiler.

### Typical setup flow

1. Install JDK
2. Set `JAVA_HOME`
3. Add JDK `bin` folder to `PATH`
4. Confirm with:

```bash
java -version
javac -version
```

## Your first Java program

```java
public class HelloJava {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

## Compile and run

```bash
javac HelloJava.java
java HelloJava
```

## What happens internally?

1. Source code is written in `.java`
2. `javac` compiles it into `.class`
3. JVM loads the class and executes `main()`
4. Output appears in the console

## Project structure basics

A beginner-friendly Java project usually looks like:

```text
my-project/
├─ src/
│  └─ main/
│     └─ java/
│        └─ com/
│           └─ example/
│              └─ App.java
└─ README.md
```

## IDE vs terminal usage

For beginners, the IDE is helpful for writing and reading code, but the terminal is important for learning:

- compile with `javac`
- run with `java`
- read command-line errors
- understand the build/test cycle

## Engineer checklist

- Know the difference between JDK, JRE, and JVM
- Be able to compile and run a Java file from the terminal
- Understand the role of `main()`
- Know the difference between source code and bytecode

## Files

| File | Concept |
|------|---------|
| `SetupAndEnvironment.java` | Basic project setup and program execution |

## Interview questions

1. What is the difference between JDK and JRE?
2. Why is `main()` special in Java?
3. What happens after `javac` creates a `.class` file?

## Next module

→ [01_java_basics/_README.md](../01_java_basics/_README.md)
