public class Operators{
    public static void main(String args[]){
        int a = 10;
        int b = 20;

        // Arithmetic Operators
        System.out.println("Arithmetic Operators:");
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));

        // Relational Operators
        System.out.println("\nRelational Operators:");
        System.out.println("a == b: " + (a == b));
        System.out.println("a != b: " + (a != b));
        System.out.println("a > b: " + (a > b));
        System.out.println("a < b: " + (a < b));
        System.out.println("a >= b: " + (a >= b));
        System.out.println("a <= b: " + (a <= b));

        // Assignment Operators
        int c = a; // simple assignment
        System.out.println("\nAssignment Operators:");
        System.out.println("c = a -> " + c);
        c += b; // equivalent to c = c + b
        System.out.println("c += b -> " + c);
        c -= b; // equivalent to c = c - b
        System.out.println("c -= b -> " + c);
        c *= 2; // equivalent to c = c * 2
        System.out.println("c *= 2 -> " + c);
        c /= 2; // equivalent to c = c / 2
        System.out.println("c /= 2 -> " + c);
        c %= 3; // equivalent to c = c % 3
        System.out.println("c %= 3 -> " + c);

        // Bitwise Operators
        int p = 6; // binary 0110
        int q = 3; // binary 0011
        System.out.println("\nBitwise Operators:");
        System.out.println("p & q: " + (p & q)); // bitwise AND, 0110 & 0011 = 0010 -> 2
        System.out.println("p | q: " + (p | q)); // bitwise OR, 0110 | 0011 = 0111 -> 7
        System.out.println("p ^ q: " + (p ^ q)); // bitwise XOR, 0110 ^ 0011 = 0101 -> 5
        System.out.println("~p: " + (~p)); // bitwise NOT, flips all bits of p

        // Logical Operators
        boolean x = true;
        boolean y = false;
        System.out.println("\nLogical Operators:");
        System.out.println("x && y: " + (x && y)); // true only if both x and y are true
        System.out.println("x || y: " + (x || y)); // true if either x or y is true
        System.out.println("!x: " + (!x)); // logical NOT, flips the boolean value



    }
}