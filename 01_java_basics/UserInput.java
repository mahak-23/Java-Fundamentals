package java_basics;

import java.util.*;

public class UserInput{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        System.out.println("--------------User Information--------------");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        sc.nextLine(); 
        
        System.out.print("Enter your city: ");
        String city = sc.nextLine();

        System.out.print("Enter your favorite technology: ");
        String favoriteTech = sc.nextLine();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("Favorite Tech: " + favoriteTech);
        System.out.println("--------------End of User Information--------------");

        sc.close();
    }
}