import java.math.BigDecimal;

public class StructuralPatternsExample {
    public static void main(String[] args) {
        PaymentProcessor processor = new LegacyBankAdapter(new LegacyBankApi());
        processor.pay(new BigDecimal("12.50"));
    }

    // The application understands this interface.
    interface PaymentProcessor {
        void pay(BigDecimal amount);
    }

    // Older provider API uses integer cents and a different method name.
    static final class LegacyBankApi {
        void chargeInCents(long amountInCents) {
            System.out.println("Legacy bank charged " + amountInCents + " cents");
        }
    }

    // Adapter translates the application's interface into the legacy API.
    static final class LegacyBankAdapter implements PaymentProcessor {
        private final LegacyBankApi legacyBank;

        LegacyBankAdapter(LegacyBankApi legacyBank) {
            this.legacyBank = legacyBank;
        }

        @Override
        public void pay(BigDecimal amount) {
            long cents = amount.movePointRight(2).longValueExact();
            legacyBank.chargeInCents(cents);
        }
    }
}
