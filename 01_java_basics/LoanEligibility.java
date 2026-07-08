package java_basics;

import java.util.*;

public class LoanEligibility {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your salary: ");
        double salary = sc.nextDouble();

        System.out.print("Enter your experience: ");
        double experience = sc.nextDouble();

        System.out.print("Enter your credit score: ");
        int creditScore = sc.nextInt();
        sc.nextLine();

        boolean eligible = salary > 50000 && experience > 2 && creditScore > 700;

        
        if (eligible) {
            System.out.println("You are eligible for the loan.");
        } else {
            System.out.println("you are not eligible for the loan.");
        }

        sc.close();
    }

}