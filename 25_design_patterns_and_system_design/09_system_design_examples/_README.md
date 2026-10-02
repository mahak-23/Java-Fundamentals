# System Design Examples

Worked HLD examples, diagrams, and Java representations of common architecture designs.

## Design notes

- [System design examples](SystemDesignExamples.md)
- [Architecture diagrams](SystemDesignDiagrams.md)

## Java examples

- [URL shortener](URLShortenerHLDExample.java)
- [Chat system](ChatSystemHLDExample.java)
- [E-commerce platform](EcommerceSystemHLDExample.java)
- [Notification pipeline](NotificationPipelineHLDExample.java)

## Dry runs

URL shortener:

```text
Created short code abc1 -> https://www.example.com/very/long/path/with/many/segments
Resolved abc1 to https://www.example.com/very/long/path/with/many/segments
Redirect target: https://www.example.com/very/long/path/with/many/segments
```

Chat system:

```text
Stored message from U-100 to U-200
Delivering message instantly to U-200: Hey, are you free for a quick call?
Stored message from U-100 to U-300
User offline; queued notification for U-300
Message delivery paths handled by service layer.
```

E-commerce order flow:

```text
API Gateway received: CREATE_ORDER for P-5001
OrderService: creating order ORD-1001 for user U-77
InventoryService: reserving 2 unit(s) for P-5001
PaymentService: charging 499.99 for order ORD-1001
NotificationQueue: queued -> Order confirmation sent for ORD-1001
OrderService: order ORD-1001 placed successfully
Gateway response: Request accepted
Final order id: ORD-1001
```

Notification pipeline:

```text
Queued notification for U-10 via EMAIL
Queued notification for U-11 via SMS
Sending via EMAIL: Your order has shipped. to U-10
Sending via SMS: Your OTP is 123456. to U-11
```
