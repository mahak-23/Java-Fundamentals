/*
List access is O(1) for ArrayList, while HashMap and HashSet operations are average O(1).
Iteration is O(n) over the number of stored elements.
*/

package collections;

/*
 * CollectionsConcepts.java
 * This file explains Java Collections and shows commonly used collection types.
 * It covers List, Set, and Map, with comments and runtime output.
 */

import java.util.*;
import java.util.Objects;

public class CollectionsConcepts {
    public static void main(String[] args) {
        // Java Collections: reusable data structures for grouping objects.
        // Common collection interfaces:
        // - List: ordered, allows duplicates
        // - Set: unordered, unique elements only
        // - Map: key-value pairs, keys are unique
        //
        // CRUD Operations for collections:
        // - Create: add() or put() inserts new items
        // - Read: get(), contains(), iteration retrieves items
        // - Update: replace existing values in a Map or modify objects inside a collection
        // - Delete: remove() deletes items from the collection
        //
        // Collection size methods:
        // - list.size(), set.size(), map.size()
        // There is no length() on Collection interfaces; use size().
        // To get all items, iterate with for-each or use methods like entrySet() for maps.

        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Apple"); // duplicate allowed in List

        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Apple"); // duplicate ignored in Set

        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 3);
        map.put("Banana", 5);
        map.put("Apple", 4); // duplicate key replaces previous value

        System.out.println("List contents: " + list);
        System.out.println("List size: " + list.size());
        System.out.println("First list element: " + list.get(0));
        System.out.println();

        System.out.println("Set contents: " + set);
        System.out.println("Set size: " + set.size());
        System.out.println("Set contains Banana: " + set.contains("Banana"));
        System.out.println();

        System.out.println("Map contents: " + map);
        System.out.println("Map size: " + map.size());
        System.out.println("Value for key 'Apple': " + map.get("Apple"));
        System.out.println();

        System.out.println("Iterate all List items:");
        for (String item : list) {
            System.out.println(" - " + item);
        }
        System.out.println();

        System.out.println("Iterate all Set items:");
        for (String item : set) {
            System.out.println(" - " + item);
        }
        System.out.println();

        System.out.println("Iterate all Map entries:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(" - " + entry.getKey() + " => " + entry.getValue());
        }

        // Built-in actions: sort, reverse, and math operations
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        System.out.println("\nSorted list: " + sortedList);

        Collections.reverse(sortedList);
        System.out.println("Reversed sorted list: " + sortedList);

        int sum = map.values().stream().mapToInt(Integer::intValue).sum();
        int max = map.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = map.values().stream().mapToInt(Integer::intValue).min().orElse(0);
        double average = map.values().stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("\nMath operations on Map values:");
        System.out.println(" Sum = " + sum);
        System.out.println(" Max = " + max);
        System.out.println(" Min = " + min);
        System.out.println(" Avg = " + average);
        System.out.println(" Sqrt of max = " + Math.sqrt(max));

        // Objects stored in collections
        List<Product> products = new ArrayList<>();
        products.add(new Product("Pen", 2.5));
        products.add(new Product("Notebook", 5.0));
        products.add(new Product("Eraser", 1.25));

        Set<Product> productSet = new HashSet<>();
        productSet.add(new Product("Pen", 2.5));
        productSet.add(new Product("Notebook", 5.0));
        productSet.add(new Product("Eraser", 1.25));

        Map<Product, Integer> productQuantities = new HashMap<>();
        productQuantities.put(new Product("Pen", 2.5), 10);
        productQuantities.put(new Product("Notebook", 5.0), 3);
        productQuantities.put(new Product("Eraser", 1.25), 8);

        System.out.println("\nProducts list:");
        for (Product product : products) {
            System.out.println(" - " + product);
        }
        products.forEach(product -> System.out.println(" - " + product));

        System.out.println("\nProducts set:");
        productSet.forEach(product -> System.out.println(" - " + product));

        System.out.println("\nProduct quantities map:");
        for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
            System.out.println(" - " + entry.getKey() + " => " + entry.getValue());
        }

        // same
        productQuantities.forEach((product, quantity) -> System.out.println(" - " + product + " => " + quantity));
    }

    static class Product {
        private final String name;
        private final double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return name + " (" + price + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Product)) return false;
            Product product = (Product) o;
            return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }
    }
}
