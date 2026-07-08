package java_basics;

import java.util.*;

public class MiniAtmSimulator {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int balance = 10000;

        System.out.print("---Menu---\n1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit\n");
        System.out.println("Initial Balance : " + balance);
        
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the amount to deposit: ");
                int deposit = sc.nextInt();
                balance += deposit;
                System.out.println("New Balance: " + balance);
                break;
            case 2:
                System.out.print("Enter the amount to withdraw: ");
                int withdraw = sc.nextInt();
                if (withdraw <= balance) {
                    balance -= withdraw;
                    System.out.println("New Balance: " + balance);
                } else {
                    System.out.println("Insufficient funds.");
                }
                break;
            case 3:
                System.out.println("Check Balance : " + balance);
                break;
            case 4:
                System.out.println("Exit");
                break;
            default:
                System.out.println("Invalid choice");
        }

        sc.close();
    }
}