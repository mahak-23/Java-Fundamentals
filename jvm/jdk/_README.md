# JVM / JDK

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
The JVM is the engine that runs Java bytecode, while the JDK provides the tools to develop, compile, and run Java applications.

## Key terms
- `JDK` - Java Development Kit
- `JRE` - Java Runtime Environment
- `JVM` - Java Virtual Machine

## Compilation flow
```text
.java source -> javac -> .class bytecode -> java -> JVM execution
```

## Why Java is platform-independent
The same compiled bytecode can run on any machine that has a compatible JVM.

## Example: inspect Java version
```java
System.out.println(System.getProperty("java.version"));
```

## Interview notes
- `javac` compiles source code.
- `java` runs compiled classes.
- Bytecode is the intermediate format executed by the JVM.
- The JVM may use JIT compilation to optimize hot code paths.
