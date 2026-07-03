public class DataType {
    public static void main(String args[]){
        int num = 10;
        long bigNum = 10000000000L;
        double decimalNum = 3.14;
        float smallDecimalNum = 2.5f;
        char letter = 'A';
        boolean isJavaFun = true;
        String text = "Hello, Java!";
        Object obj = new Object(); // Example of a reference type

        System.out.println("Integer: " + num);
        System.out.println("Long: " + bigNum);
        System.out.println("Double: " + decimalNum);
        System.out.println("Float: " + smallDecimalNum);
        System.out.println("Character: " + letter);
        System.out.println("Boolean: " + isJavaFun);
        System.out.println("String: " + text);
        System.out.println("Object: " + obj);
    }
}
// ========== DATA TYPES IN JAVA ==========
// Java has 2 categories: Primitives & Reference Types
// Primitives: int, long, double, float, char, boolean (store actual values)
// Reference Types: String, Object, Arrays (store memory addresses/pointers)
//
// ========== HOW JAVA STORES DATA IN MEMORY ==========
//
// STACK MEMORY (used for primitives & references):
//   - int num = 10              → stores value 10 directly (4 bytes)
//   - long bigNum = 10000000000L → stores value directly (8 bytes)
//   - double decimalNum = 3.14   → stores value directly (8 bytes)
//   - float smallDecimalNum = 2.5f → stores value directly (4 bytes)
//   - char letter = 'A'         → stores value directly (2 bytes)
//   - boolean isJavaFun = true  → stores value directly (1 byte)
//   - String text               → stores reference/pointer (not the actual string)
//   - Object obj                → stores reference/pointer (not the actual object)
//
// HEAP MEMORY (used for actual objects & data):
//   - "Hello, Java!"            → actual String content stored here
//   - new Object()              → actual Object instance stored here
//
// ========== KEY CONCEPT ==========
// PRIMITIVES store VALUE on Stack (fast, limited storage)
// OBJECTS store REFERENCE on Stack, actual data on Heap (slower, larger storage)
//
// Stack: Auto cleanup when method ends → Garbage
// Heap: Garbage Collection removes unused objects → Auto cleanup
//
// ========== REAL-WORLD USES ==========
// - int: Product IDs, user ages, counters
// - long: Timestamps, account balances, large numbers
// - double: Prices, measurements, calculations (precise)
// - float: Memory-limited scenarios, graphics coordinates
// - char: Single characters, symbols
// - boolean: Login status, conditions, flags
// - String: Names, messages, text data
// - Object: Base class for all Java objects 
