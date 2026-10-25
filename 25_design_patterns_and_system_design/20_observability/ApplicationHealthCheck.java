/**
 * Demonstrates readiness (traffic) vs liveness (process alive).
 * Dependency checks belong on readiness — not liveness — so a brief DB blip
 * stops traffic instead of restarting every pod.
 */
public class ApplicationHealthCheck {
    private boolean databaseAvailable = false;

    /** Liveness: is the JVM/process able to make progress? Fail → restart. */
    public boolean isAlive() {
        return !Thread.currentThread().isInterrupted();
    }

    /** Readiness: may this instance receive traffic? Fail → remove from load balancer. */
    public boolean isReady() {
        return isDatabaseAvailable();
    }

    public boolean isDatabaseAvailable() {
        return databaseAvailable;
    }

    public void setDatabaseAvailable(boolean databaseAvailable) {
        this.databaseAvailable = databaseAvailable;
    }

    public void start() {
        if (!isAlive()) {
            System.out.println("Liveness failed — orchestrator would restart this instance.");
            return;
        }
        if (isReady()) {
            System.out.println("Application is ready to serve traffic.");
        } else {
            System.out.println("Application is not ready due to database unavailability.");
        }
    }

    public static void main(String[] args) {
        ApplicationHealthCheck app = new ApplicationHealthCheck();
        System.out.println("=== DB down (readiness fails, liveness OK) ===");
        app.start();

        app.setDatabaseAvailable(true);
        System.out.println("=== DB up ===");
        app.start();
    }
}
