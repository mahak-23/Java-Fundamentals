# Observability

Beginner-friendly notes on logs, metrics, traces, alerting, health checks, and the modern observability stack.

## Read first

- [Observability module](Observability.md) — full walkthrough with HLD, LLD, diagrams, and interview prep

## Runnable Java examples

- [ApplicationHealthCheck.java](ApplicationHealthCheck.java) — liveness vs readiness and dependency checks
- [ObservabilityDemo.java](ObservabilityDemo.java) — structured logs, golden-signal counters, and trace ID correlation

## Suggested order

1. Skim **Why observability** and the **three pillars** in the main doc.
2. Run `ApplicationHealthCheck.java`, then toggle `databaseAvailable` and re-run.
3. Run `ObservabilityDemo.java` and follow how the same `traceId` appears in logs and spans.
4. Review **HLD / LLD** and **Test yourself** before interviews.
