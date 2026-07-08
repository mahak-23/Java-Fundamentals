package oops;

 //  Your design is good for an OOP practice project:

// ✅ Encapsulation (Book fields private)
// ✅ Abstraction (User abstract class)
// ✅ Inheritance (Student, Teacher)
// ✅ Polymorphism (getUserType())

// Book     -> Book data
// User     -> Common user abstraction
// Student  -> User specialization
// Teacher  -> User specialization
// Library  -> Library operations

import java.util.*;
 
class Book {
 
	private int id;
	private String title;
	private boolean issued;
 
	public Book(int id, String title) {
		this.id = id;
		this.title = title;
		this.issued = false;
	}
 
	public int getId() {
		return id;
	}
 
	public String getTitle() {
		return title;
	}
 
	public boolean isIssued() {
		return issued;
	}
 
	public void markIssued() {
        issued = true;
	}
 
	public void markReturned() {
		issued = false;
	}
 
	@Override
 
	public String toString() {
		return "Book{" +
		       "id=" + id +
		       ", title='" + title + '\'' +
		       ", issued=" + issued +
		       '}';
	}
 
}
 
abstract class User {
    
    protected int id;
    protected String name;
    
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public abstract String getUserType();
}
 
class Student extends User {
    
    public Student(int id, String name) {
        super(id,name);
    }
    
    @Override
    
    public String getUserType() {
        return "Student";
    }
}
 
class Teacher extends User {
    
    public Teacher(int id, String name) {
        super(id,name);
    }
    
    @Override
    
    public String getUserType() {
        return "Teacher";
    }
}
 
class Library {
    
    private final Map <Integer, Book> books;
    
    
    public Library() {
        books = new HashMap<>();
    }
    
    public void addBook(Book book) {
        books.put(book.getId(), book);
    }
    
    public void issueBook(int bookId) {
        Book book = books.get(bookId);
        
        if(book == null) {
            System.out.println("Book not found");
            return;
        }
        
        if(book.isIssued()) {
            System.out.println("Book is already issued");
            return;
        }

        book.markIssued();
        System.out.println("Book issued successfully");
        
    }

    public void returnBook(int bookId) {
        Book book = books.get(bookId);
        
        if(book == null) {
            System.out.println("Book not found");
            return;
        }
        
        if(!book.isIssued()) {
            System.out.println("Book is not issued");
            return;
        }

        book.markReturned();
        System.out.println("Book returned successfully");
        
    }
}
 
public class LibraryMain {
 
    public static void main(String[] args) {
 
        Library library = new Library();
 
        // Add books
        Book book1 = new Book(1, "Clean Code");
        Book book2 = new Book(2, "Design Patterns");
 
        library.addBook(book1);
        library.addBook(book2);
 
        // Create users
        User student = new Student(101, "Sachin");
        User teacher = new Teacher(201, "Rahul");
 
        System.out.println(student.getUserType());
        System.out.println(teacher.getUserType());
 
        // Print books
        System.out.println(book1);
        System.out.println(book2);
 
        // Issue books
        library.issueBook(1);
 
        // Try issuing again
        library.issueBook(1);
 
        // Non-existing book
        library.issueBook(100);

        // after issuing
        System.out.println(book1);
        System.out.println(book2);

        // Return book
        library.returnBook(1);

        // Try returning again
        library.returnBook(1);

        // after return
        System.out.println(book1);
        System.out.println(book2);
    }
}

