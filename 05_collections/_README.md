# collections

> **Prerequisites:** [01_java_basics](../01_java_basics/_README.md), [02_oops](../02_oops/_README.md)

Java Collection Framework — learn before custom stack/queue/linked list implementations.

## Files

| File | Content |
|------|---------|
| `CollectionsConcepts.java` | List, Set, Map, iteration, Collectors basics |

## Core interfaces

| Interface | Ordered | Duplicates | Use when |
|-----------|---------|------------|----------|
| `List` | Yes | Yes | Index access, sequence matters |
| `Set` | No* | No | Uniqueness matters |
| `Map` | No* | Keys unique | Key → value lookup |

## CRUD quick reference

| Action | List | Set | Map |
|--------|------|-----|-----|
| Create | `add()` | `add()` | `put(k,v)` |
| Read | `get(i)` | `contains()` | `get(k)` |
| Update | `set(i,v)` | — | `put(k,newV)` |
| Delete | `remove()` | `remove()` | `remove(k)` |

## Interview questions

1. ArrayList vs LinkedList?
2. HashSet vs TreeSet?
3. When use Map over List?

## Next module

→ [06_stack/_README.md](../06_stack/_README.md)
