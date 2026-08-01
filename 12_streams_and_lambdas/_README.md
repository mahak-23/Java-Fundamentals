# streams_and_lambdas

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [05_collections](../05_collections/_README.md)

Streams and lambdas let you write compact, expressive code for processing collections. They are especially useful for filtering, transforming, and summarizing data.

## How to create and use lambdas

```java
java.util.function.Function<Integer, Integer> square = x -> x * x;
System.out.println(square.apply(5));
```

## How to use streams

```java
import java.util.List;

List<Integer> numbers = List.of(1, 2, 3, 4, 5);
List<Integer> even = numbers.stream()
    .filter(n -> n % 2 == 0)
    .toList();
```

## Common operations

### Filter

```java
numbers.stream().filter(n -> n > 2);
```

### Map

```java
numbers.stream().map(n -> n * 2);
```

### Reduce

```java
int sum = numbers.stream().reduce(0, Integer::sum);
```

## Files

| File | Content |
|------|---------|
| `LambdaExpressionsExample.java` | Lambda syntax, functional interfaces |
| `StreamsExample.java` | filter, map, reduce, `Collectors.toList` |
| `AdvancedStreamsExample.java` | groupingBy, partitioningBy, joining |

## Collectors reference

| Collector | Returns | Use |
|-----------|---------|-----|
| `toList()` | List | Collect stream elements |
| `toSet()` | Set | Unique elements |
| `joining()` | String | Join with delimiter |
| `groupingBy()` | Map | Group by key |
| `partitioningBy()` | Map | Split true/false groups |
| `averagingDouble()` | Double | Average of field |

## Time and space complexity

| Operation | Time | Space |
|-----------|------|-------|
| Filter / map | O(n) | O(1) to O(n) |
| Reduce | O(n) | O(1) |
| Grouping / partitioning | O(n) | O(n) |

## Interview questions

1. What is a functional interface?
2. Intermediate vs terminal stream operations?
3. When use `groupingBy` vs `partitioningBy`?
