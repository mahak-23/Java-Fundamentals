import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

class ChatMessage {
    private final String senderId;
    private final String receiverId;
    private final String text;

    public ChatMessage(String senderId, String receiverId, String text) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public String getText() {
        return text;
    }
}

class ChatService {
    private final MessageStore messageStore = new MessageStore();
    private final PresenceService presenceService = new PresenceService();
    private final NotificationService notificationService = new NotificationService();

    public void sendMessage(String senderId, String receiverId, String text) {
        ChatMessage message = new ChatMessage(senderId, receiverId, text);
        messageStore.save(message);

        if (presenceService.isUserOnline(receiverId)) {
            System.out.println("Delivering message instantly to " + receiverId + ": " + text);
        } else {
            notificationService.enqueueNotification(receiverId, "New message from " + senderId);
            System.out.println("User offline; queued notification for " + receiverId);
        }
    }
}

class MessageStore {
    private final List<ChatMessage> messages = new ArrayList<>();

    public void save(ChatMessage message) {
        messages.add(message);
        System.out.println("Stored message from " + message.getSenderId() + " to " + message.getReceiverId());
    }
}

class PresenceService {
    public boolean isUserOnline(String userId) {
        return "U-200".equals(userId);
    }
}

class NotificationService {
    private final Queue<String> pendingNotifications = new ArrayDeque<>();

    public void enqueueNotification(String userId, String message) {
        pendingNotifications.offer(userId + " -> " + message);
    }

    public String poll() {
        return pendingNotifications.poll();
    }
}

public class ChatSystemHLDExample {
    public static void main(String[] args) {
        ChatService chatService = new ChatService();

        chatService.sendMessage("U-100", "U-200", "Hey, are you free for a quick call?");
        chatService.sendMessage("U-100", "U-300", "Can you review the PR by lunch?");

        System.out.println("Message delivery paths handled by service layer.");

        // HLD explanation:
        // - ChatService coordinates domain flow and interacts with presence and notification systems.
        // - MessageStore persists chats, while PresenceService decides whether to deliver instantly.
        // - NotificationService handles offline user cases asynchronously.
        // - This mirrors a real HLD approach: separate responsibilities, external delivery concerns, and async behavior.
    }
}
