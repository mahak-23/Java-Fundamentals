import java.util.*;

public class NumberAnalyser {
    public static void main(String args[]){
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = sc.nextInt();

        String Even = (num % 2 == 0) ? "Yes" : "No";
        String positive = (num > 0) ? "Yes" : "No";
        String DivisibleBy5 = (num % 5 == 0) ? "Yes" : "No";
        String DivisibleBy10 = (num % 10 == 0) ? "Yes" : "No";

        System.out.println("Even: " + Even);
        System.out.println("Positive: " + positive);
        System.out.println("Divisible by 5: " + DivisibleBy5);
        System.out.println("Divisible by 10: " + DivisibleBy10);
        sc.close();
    }
}