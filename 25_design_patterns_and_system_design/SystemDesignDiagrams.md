# System Design Diagrams and Notes

This file contains diagram-style notes you can use while preparing for interviews.

## 1. URL Shortener component diagram

```mermaid
flowchart LR
    Client[Client] --> API[API Layer]
    API --> Service[URL Service]
    Service --> DB[(URL Store)]
    Service --> Cache[(Redis Cache)]
    Service --> Queue[Analytics Queue]
    Queue --> Analytics[Analytics Worker]
```

### Explanation
- The API layer receives create and redirect requests.
- The URL service generates short codes and resolves them.
- Redis handles hot redirects.
- A separate analytics worker avoids slowing down the redirect path.

## 2. URL Shortener sequence diagram

```mermaid
sequenceDiagram
    participant User
    participant API
    participant Service
    participant Cache
    participant DB

    User->>API: GET /abc123
    API->>Service: resolveShortCode(abc123)
    Service->>Cache: lookup code
    alt cache hit
        Cache-->>Service: original URL
    else cache miss
        Service->>DB: fetch mapping
        DB-->>Service: original URL
        Service->>Cache: store redirect
    end
    Service-->>API: redirect response
    API-->>User: 302 redirect
```

## 3. Chat system component diagram

```mermaid
flowchart LR
    ClientA[User A] --> Gateway[API / Gateway]
    ClientB[User B] --> Gateway
    Gateway --> Chat[Chat Service]
    Chat --> Store[(Message Store)]
    Chat --> Presence[Presence Service]
    Chat --> Notify[Notification Service]
    Notify --> Queue[Offline Messages Queue]
    Queue --> Worker[Delivery Worker]
```

### Explanation
- The chat service stores messages and checks presence.
- If the receiver is online, delivery is immediate.
- If offline, a notification is queued for later processing.

## 4. Chat system sequence diagram

```mermaid
sequenceDiagram
    participant UserA
    participant API
    participant ChatService
    participant Presence
    participant MessageStore
    participant Notification

    UserA->>API: sendMessage(text)
    API->>ChatService: sendMessage(sender, receiver, text)
    ChatService->>MessageStore: save message
    ChatService->>Presence: isUserOnline(receiver)
    alt user online
        Presence-->>ChatService: true
        ChatService-->>API: message delivered instantly
    else user offline
        Presence-->>ChatService: false
        ChatService->>Notification: enqueue notification
        Notification-->>ChatService: queued
        ChatService-->>API: accepted for later delivery
    end
```

## 5. Notification pipeline component diagram

```mermaid
flowchart LR
    App[App / Services] --> API[Notification API]
    API --> Queue[Message Queue]
    Queue --> Worker[Notification Worker]
    Worker --> Email[Email Provider]
    Worker --> SMS[SMS Provider]
    Worker --> Push[Push Service]
    Worker --> Retry[Retry / Dead Letter Queue]
```

### Explanation
- Requests are accepted quickly and placed in a queue.
- Workers handle provider calls asynchronously.
- Retry logic and dead-letter processing improve reliability.

## 6. Notification pipeline sequence diagram

```mermaid
sequenceDiagram
    participant UserAction
    participant API
    participant Queue
    participant Worker
    participant Provider

    UserAction->>API: send notification request
    API->>Queue: enqueue event
    Queue-->>API: accepted
    Queue->>Worker: dispatch job
    Worker->>Provider: send push/email/SMS
    alt success
        Provider-->>Worker: success ack
    else failure
        Provider-->>Worker: error
        Worker->>Queue: retry with backoff
    end
```

## 7. What to explain in interviews using diagrams

When you present a design diagram, explain:
- the entry points
- the major components and responsibilities
- the critical data flow
- the failure points and retry strategy
- the scaling plan and bottlenecks

## 8. Interview tip

A diagram is not enough by itself. You should also say:
- where the system is read-heavy or write-heavy
- which piece is the bottleneck
- where you would add caching or asynchronous workers
- which tradeoff is being chosen and why
