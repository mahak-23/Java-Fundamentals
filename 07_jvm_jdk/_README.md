# jvm_jdk

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md)

The JDK provides the tools needed to compile and run Java programs, while the JVM executes the compiled bytecode. Understanding this flow helps explain why Java is portable and how memory is managed.

## How Java is created and run

### Write source code

```java
public class JavaVersion {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

### Compile with javac

```powershell
javac JavaVersion.java
```

### Run with java

```powershell
java JavaVersion
```

## Compilation flow

```
.java → javac → .class bytecode → java → JVM
```

## Common concepts

- JDK: development toolkit with compiler and tools
- JVM: runtime engine that executes bytecode
- JRE: runtime environment for running Java applications

## Files

| File | Content |
|------|---------|
| `JavaVersion.java` | System properties, Java version info |

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Compile | O(n) relative to source size | O(n) |
| Execute bytecode | Depends on program | Depends on program |
| Garbage collection | Implementation-dependent | Reclaimed heap space |

## Interview questions

1. Why is Java platform-independent?
2. Stack vs heap memory?
3. `javac` vs `java`?
