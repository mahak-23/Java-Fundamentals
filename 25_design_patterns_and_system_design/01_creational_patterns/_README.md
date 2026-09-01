# Creational Patterns

Creational patterns control how objects are created. They help hide construction logic, centralize creation, and make it easier to add new product types without changing the client code.

## 1. Factory Pattern

Use when:
- you want to centralize object creation
- the exact class should depend on runtime conditions

Example idea:
- `VehicleFactory.createVehicle(type)` returns a `Car`, `Bike`, or `Truck`

Why it matters:
- clients do not need to know the concrete type
- adding a new type is isolated to the factory

## 2. Abstract Factory Pattern

Use when:
- you need a family of related objects
- different families should be created under the same interface

Example:
- UI theme factory: `LightThemeFactory` vs `DarkThemeFactory`
- each factory creates buttons and text boxes consistent with the theme

## 3. Builder Pattern

Use when:
- object creation is complex
- many optional fields exist

Example:
- `MealBuilder` building a burger with drink, side, and extras
- `StringBuilder` is a familiar Java example of builder-like composition

## 4. Singleton Pattern

Use when:
- only one shared instance should exist
- configuration, logger, or connection manager objects are global

Important warning:
- use it carefully in multi-threaded applications
- prefer dependency injection in modern systems when possible

## 5. Prototype Pattern

Use when:
- creating an object is expensive
- you want to clone an existing instance

Example:
- prototype objects for `Document` or `Shape`

## Interview perspective

When explaining a creational pattern, say:
- what is being created
- what part is centralized
- why this reduces coupling

## Key takeaway

Creational patterns improve extensibility by moving object construction logic away from the client.
