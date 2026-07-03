public class Methods {
    public static void main(String args[]){
        int num1 = 10;
        int num2 = 20;

        // Calling the add method
        int sum = add(num1, num2);
        System.out.println("Sum: " + sum);

        // Calling the subtract method
        int difference = subtract(num1, num2);
        System.out.println("Difference: " + difference);

        // Calling the multiply method
        int product = multiply(num1, num2);
        System.out.println("Product: " + product);

        // Calling the divide method
        double quotient = divide(num1, num2);
        System.out.println("Quotient: " + quotient);
    }

    // Method to add two numbers
    public static int add(int a, int b){
        return a + b;
    }

    // Method to subtract two numbers
    public static int subtract(int a, int b){
        return a - b;
    }

    // Method to multiply two numbers
    public static int multiply(int a, int b){
        return a * b;
    }

    // Method to divide two numbers
    public static double divide(int a, int b){
        if(b != 0){
            return (double) a / b;
        } else {
            System.out.println("Cannot divide by zero");
            return 0;
        }
    }
}