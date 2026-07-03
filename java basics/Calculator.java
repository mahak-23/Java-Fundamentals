import java.util.*;

public class Calculator {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first number: ");
        int num1 = sc.nextInt();
        System.out.print("Enter the second number: ");
        int num2 = sc.nextInt();

        System.out.println("Please select an operation:");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                int sum = num1 + num2;
                System.out.println("Sum: " + sum);
                break;
            case 2:
                int difference = num1 - num2;
                System.out.println("Difference: " + difference);
                break;
            case 3:
                int product = num1 * num2;
                System.out.println("Product: " + product);
                break;
            case 4:
                int quotient = num1 / num2;
                System.out.println("Quotient: " + quotient);
                break;
            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}