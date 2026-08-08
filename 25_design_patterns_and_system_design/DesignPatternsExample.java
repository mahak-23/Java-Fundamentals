interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

class PaymentProcessor {
    private final PaymentStrategy strategy;

    PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    void process(int amount) {
        strategy.pay(amount);
    }
}

public class DesignPatternsExample {
    public static void main(String[] args) {
        // What: demonstrates the Strategy design pattern for payment processing.
        // Why: it decouples payment logic from the processor and makes it easy to add new methods.
        // How: pass a concrete PaymentStrategy implementation to the processor.
        PaymentProcessor processor = new PaymentProcessor(new CreditCardPayment());
        processor.process(500);
    }
}
