# Low-Level Design (LLD)

Low-level design focuses on the implementation details of a single module or feature.

It answers questions like:
- what classes exist?
- what are the responsibilities of each class?
- how do the classes interact?
- what are the APIs, repositories, services, and models?
- what exceptions, validations, and edge cases matter?

## Typical LLD questions

- Design a `ParkingLot` with slots, vehicles, and ticketing
- Design a `LibraryManagementSystem`
- Design a `Splitwise` app with groups and settlements
- Design an `ElevatorSystem`
- Design a `RideSharingSystem`
- Design a `HotelBookingSystem`
- Design a `PaymentService`
- Design a `NotificationService`

## What a strong LLD design includes

### 1. Entities

These are model objects representing business data.

Examples:
- `User`
- `Order`
- `Product`
- `ParkingSlot`
- `Booking`
- `Invoice`

### 2. Value Objects

Immutable or wrapped data structures used to represent domain concepts.

Examples:
- `Money`
- `Address`
- `DateRange`
- `Coordinates`

### 3. Services

Services encapsulate business logic and orchestrate the flow.

Examples:
- `ParkingLotService.allocateSlot(vehicle)`
- `BillingService.calculateCharges(order)`
- `BookingService.createBooking(user, hotelRoom, dates)`
- `PaymentService.processPayment(order, method)`

### 4. Repositories / DAOs

These handle persistence and data access.

Examples:
- `UserRepository.findById(id)`
- `OrderRepository.save(order)`
- `SlotRepository.getAvailableSlots(location)`

### 5. Controllers / Facades

These expose operations to the outside and coordinate multiple components.

Examples:
- `BookingController.createBooking(request)`
- `PaymentFacade.pay(order, paymentMethod)`

### 6. Validation and exceptions

A real LLD design must define:
- invalid input handling
- duplicate booking prevention
- negative stock or balance checks
- payment failure scenarios
- concurrency problems and locking concerns

## Example: Ride Sharing LLD

### Problem
Design a ride-sharing system where a rider requests a ride, the system finds a nearby driver, and the trip is charged after completion.

### Core entities
- `User`
- `Driver`
- `Ride`
- `Vehicle`
- `Location`
- `Payment`

### Core services
- `RideMatchingService.findNearestDriver(location)`
- `BookingService.createRideRequest(user, source, destination)`
- `PaymentService.chargeRide(ride)`
- `TripService.startTrip(ride)`
- `TripService.completeTrip(ride)`

### Important decisions
- Rider and driver can be represented as separate user roles.
- A ride progresses through states like `REQUESTED`, `ACCEPTED`, `IN_PROGRESS`, `COMPLETED`, `CANCELLED`.
- Driver matching may use nearest-distance sorting or a simple geo-index idea.
- Fare calculation should be separated from payment execution.

### Edge cases
- driver cancels after accepting
- rider cancels before pickup
- no available drivers nearby
- duplicate requests due to retry
- payment timeout or failed transaction

### Example API design
- `POST /rides/request`
- `POST /rides/{rideId}/accept`
- `POST /rides/{rideId}/start`
- `POST /rides/{rideId}/complete`
- `GET /rides/{rideId}`

### How to answer in an interview

1. Start with scope and assumptions.
   - one rider, one driver, trip booking flow, payment after completion
2. Define entities and their fields.
   - `User`, `Driver`, `Ride`, `Vehicle`, `Location`, `Payment`
3. Define core APIs and flow.
   - request ride -> match driver -> accept ride -> start trip -> complete trip -> charge
4. Explain key validations.
   - no driver available, duplicate request, cancellation, payment failure
5. Discuss concurrency concerns.
   - one driver should not be assigned to two riders at the same time
6. Close with tradeoffs.
   - simple in-memory matching is okay for low scale; a geo-index or queue is needed for scale

### Questions to ask the interviewer
- Do you want only ride creation or also driver matching and dispatch?
- Should a driver accept a ride manually or automatically?
- Is pricing fixed or dynamic based on distance and surge?
- Do we need trip history and cancellation policies?

## Example: Splitwise LLD

### Entities
- `User`
- `Group`
- `Expense`
- `ExpenseParticipant`
- `Settlement`

### Services
- `ExpenseService.addExpense(groupId, payerId, amount, participants)`
- `SettlementService.calculateBalances(groupId)`
- `UserService.getNetBalances(userId)`

### Design reasoning
- Store each expense and derive balances across participants.
- Avoid storing only aggregated balances if you need historical traceability.
- Settlement logic is a classic balancing problem: compute net amounts and settle minimal transfers.

### Edge cases
- shared expenses with uneven splits
- one user owes multiple members
- repeated updates to an expense
- invalid group membership

## Example: Payment System LLD

### Entities
- `Customer`
- `Order`
- `Payment`
- `PaymentMethod`
- `Refund`

### Services
- `PaymentService.authorizePayment(order, method)`
- `PaymentService.capturePayment(payment)`
- `RefundService.refund(orderId, amount)`
- `InvoiceService.generateInvoice(order)`

### Design rules
- payment state transitions matter: `INITIATED -> AUTHORIZED -> CAPTURED -> SETTLED`
- idempotency is critical for retry-safe payment flows
- store audit logs for reconciliation and disputes
- never depend on a single immediate state update without retry handling

### How to answer in an interview

1. State the business workflow.
   - create order -> authorize -> capture -> settle -> refund if needed
2. Define state transitions explicitly.
   - payment should not move forward unless previous step succeeds
3. Describe reliability requirements.
   - idempotency keys, retry-safe APIs, audit logs, ledger consistency
4. Discuss edge cases.
   - failed authorization, duplicate payment retries, timeout after charge
5. Mention tradeoffs.
   - stronger consistency makes the system safer but slower and more complex

### Questions to ask the interviewer
- Is this a single payment provider or multiple gateways?
- Do we need refunds and partial payments?
- Does the business need strict audit logs or eventual reconciliation?
- Should the system support retry and idempotency at the API layer?

## Good LLD style

- keep classes focused on one responsibility
- define crisp interfaces and contracts
- separate business logic from persistence details
- think in terms of states, validations, and retries
- mention edge cases and failure handling
- describe interactions using class diagrams or sequence sketches

## LLD interview answer structure

1. Define the problem and assumptions.
2. List entities and relationships.
3. Identify the main services and repositories.
4. Describe the key workflows and state transitions.
5. Explain APIs and data models.
6. Mention edge cases, constraints, and tradeoffs.

## Interview-level LLD checklist

- Which classes are entities, services, repositories, and controllers?
- What is the core workflow from request to response?
- Which states does the object go through?
- Where do validations happen?
- What are the idempotency and retry concerns?
- Which operation is eventually consistent and which must be strongly consistent?
- How do you avoid race conditions?

## Key takeaway

LLD is about designing a feature in enough detail that another engineer could implement it correctly, with clear responsibilities, validations, and failure handling.
