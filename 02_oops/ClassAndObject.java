package oops;

public class ClassAndObject {
    static class Student {
        String name;
        int age;

        // Constructor to initialize the student object
        Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Method to display student information
        void displayInfo() {
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
        }
    }

    public static void main(String args[]){
        // Creating an object of the Student class
        Student student1 = new Student("Alice", 22);
        Student student2 = new Student("Bob", 24);

        // Displaying student information
        System.out.println("--------------Student 1 Information--------------");
        student1.displayInfo();

        System.out.println("\n--------------Student 2 Information--------------");
        student2.displayInfo();
    }


    class Book {
        String title;
        String author;
        double price;

        // Constructor to initialize the book object
        Book(String title, String author, double price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }

        // Method to display book information
        void displayInfo() {
            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("Price: Rs" + price);
        }
    }
}