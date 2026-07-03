# Streams and Lambda Expressions

## Time and space complexity
- The example uses simple operations whose running time depends on the input size.
- Extra space is usually constant unless the code builds additional collections or strings.
Java streams and lambdas support a more functional style of programming for processing collections and data pipelines.

## Lambda expressions
```java
List<String> names = Arrays.asList("A", "B", "C");
names.forEach(name -> System.out.println(name));
```

## Functional interface example
```java
@FunctionalInterface
interface Operation {
    int apply(int a, int b);
}
```

## Stream example
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
int sum = numbers.stream()
    .filter(n -> n > 2)
    .map(n -> n * 2)
    .reduce(0, Integer::sum);
System.out.println(sum);
```

## Common stream operations
- `filter()` - keep matching elements
- `map()` - transform each element
- `sorted()` - sort elements
- `distinct()` - remove duplicates
- `reduce()` - combine into one value
- `collect()` - gather into a collection
- `forEach()` - perform an action

## Method references
```java
names.forEach(System.out::println);
```

## Interview notes
- Streams are useful for declarative data processing.
- Lambdas are concise, but keep code readable.
- A functional interface has exactly one abstract method.
