package concurrency_and_multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrencyExample {
    private static int counter = 0;

    // What: increments a shared counter safely across multiple threads.
    // Why: shared mutable state can lead to race conditions without synchronization.
    // How: use a synchronized method so only one thread updates the counter at a time.
    public static synchronized void increment() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> increment());
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Final counter value: " + counter);
    }
}
