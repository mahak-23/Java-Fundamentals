package exception_handling;

// Custom exception class
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method that throws a custom exception
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance. Current balance: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawn: " + amount + ", Remaining balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class CustomExceptionExample {
    public static void main(String[] args) {
        System.out.println("=== Custom Exceptions ===");

        // Example 1: Custom InsufficientBalanceException
        System.out.println("Example 1: Bank Account Withdrawal");
        BankAccount account = new BankAccount(1000);

        try {
            account.withdraw(500);
            account.withdraw(300);
            account.withdraw(500); // This will throw exception
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught custom exception: " + e.getMessage());
        } finally {
            System.out.println("Final balance: " + account.getBalance() + "\n");
        }

        // Example 2: Custom InvalidAgeException
        System.out.println("Example 2: Age Validation");
        try {
            validateAge(25);
            validateAge(-5); // This will throw exception
        } catch (InvalidAgeException e) {
            System.out.println("Caught exception: " + e.getMessage() + "\n");
        }

        // Example 3: Re-throwing exceptions
        System.out.println("Example 3: Re-throwing exceptions");
        try {
            try {
                account.withdraw(2000);
            } catch (InsufficientBalanceException e) {
                System.out.println("Inner catch: " + e.getMessage());
                throw e; // Re-throw the exception
            }
        } catch (InsufficientBalanceException e) {
            System.out.println("Outer catch: Re-caught exception");
        }
    }

    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Age must be between 0 and 120");
        }
        System.out.println("Age " + age + " is valid");
    }
}
