# streams_and_lambdas

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [05_collections](../05_collections/_README.md)

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

## Interview questions

1. What is a functional interface?
2. Intermediate vs terminal stream operations?
3. When use `groupingBy` vs `partitioningBy`?
