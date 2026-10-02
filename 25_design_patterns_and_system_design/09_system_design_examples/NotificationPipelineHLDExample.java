import java.util.ArrayDeque;
import java.util.Queue;

class NotificationRequest {
    private final String userId;
    private final String channel;
    private final String message;

    public NotificationRequest(String userId, String channel, String message) {
        this.userId = userId;
        this.channel = channel;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }
}

class NotificationPipelineQueue {
    private final Queue<NotificationRequest> queue = new ArrayDeque<>();

    public void enqueue(NotificationRequest request) {
        queue.offer(request);
        System.out.println("Queued notification for " + request.getUserId() + " via " + request.getChannel());
    }

    public NotificationRequest poll() {
        return queue.poll();
    }
}

class NotificationWorker {
    private final NotificationPipelineQueue queue;

    public NotificationWorker(NotificationPipelineQueue queue) {
        this.queue = queue;
    }

    public void processNext() {
        NotificationRequest request = queue.poll();
        if (request == null) {
            System.out.println("No notifications to process.");
            return;
        }

        System.out.println("Sending via " + request.getChannel() + ": " + request.getMessage() + " to " + request.getUserId());
    }
}

public class NotificationPipelineHLDExample {
    public static void main(String[] args) {
        NotificationPipelineQueue queue = new NotificationPipelineQueue();
        NotificationWorker worker = new NotificationWorker(queue);

        queue.enqueue(new NotificationRequest("U-10", "EMAIL", "Your order has shipped."));
        queue.enqueue(new NotificationRequest("U-11", "SMS", "Your OTP is 123456."));

        worker.processNext();
        worker.processNext();

        // HLD explanation:
        // - The API accepts requests quickly and pushes them into a queue.
        // - Workers process notifications asynchronously, so user request latency stays low.
        // - This pattern is important when downstream providers are slow or unreliable.
    }
}
