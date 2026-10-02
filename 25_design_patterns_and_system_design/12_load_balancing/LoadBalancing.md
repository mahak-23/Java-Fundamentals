## Load balancing and infrastructure basics

### Load balancing basics

Load balancing is the practice of sending incoming traffic across multiple backend servers so that no single machine becomes a bottleneck.

A good load balancer helps with:

- better utilization of servers
- higher availability during failures
- smoother scaling under traffic spikes
- easier deployment and maintenance

Typical setup:

- users call a domain or public IP
- load balancer receives the request
- it forwards the request to one of the healthy application instances
- the response is returned to the user

In real systems, the load balancer can also handle TLS termination, routing, rate limiting, session stickiness, and health verification.

### Layer 4 vs Layer 7 load balancers

#### Layer 4 (L4) load balancer

L4 load balancers work at the transport/network layer.

They route based on:

- IP address
- TCP/UDP port
- connection metadata

Examples:

- TCP load balancing
- UDP load balancing
- simple packet or connection-level routing

Advantages:

- very fast
- low overhead
- good for general traffic distribution

Disadvantages:

- cannot inspect HTTP path, headers, or cookies
- limited application-aware routing

Use cases:

- game servers
- TCP-based services
- simple service routing where content awareness is not required

#### Layer 7 (L7) load balancer

L7 load balancers work at the application layer.

They can inspect:

- HTTP method
- URL path
- headers
- cookies
- request body (in some cases)

Examples:

- routing /api to one service and /admin to another
- sending mobile traffic to a different backend
- SSL termination and request-based rules

Advantages:

- smarter routing decisions
- better content-based traffic control
- easier security and caching control

Disadvantages:

- more CPU and complexity
- more latency than a raw L4 balancer

Use cases:

- web apps
- APIs
- microservices with path-based routing

### Common load balancing algorithms

#### Runnable round-robin example

The thread-safe Java sample is [RoundRobinLoadBalancerExample.java](RoundRobinLoadBalancerExample.java). Compile and run it with:

```powershell
javac RoundRobinLoadBalancerExample.java
java RoundRobinLoadBalancerExample
```

Dry run with three servers: the index advances once per request and wraps back to the first server after the third.

```text
Request 1 -> server-1
Request 2 -> server-2
Request 3 -> server-3
Request 4 -> server-1
Request 5 -> server-2
Request 6 -> server-3
Request 7 -> server-1
```

#### Round robin

Requests are distributed in a circular order.

Good when:

- all servers have similar capacity
- workloads are evenly distributed

Example:

- server 1 receives request 1
- server 2 receives request 2
- server 3 receives request 3
- then repeat

#### Least connections

The balancer sends the new request to the server with the fewest active connections.

Good when:

- server workloads vary by request intensity
- some backend services are slower than others

This is often better than round robin when traffic is uneven.

#### Weighted load balancing

Some servers get more traffic because they are stronger or have more capacity.

Example:

- server A weight = 3
- server B weight = 1
- server A gets roughly three times the traffic

#### IP hash

The balancer computes a hash from the client's IP and consistently maps the client to the same backend.

Useful for:

- sticky sessions
- caching efficiency
- stateful or session-based services

### Reverse proxy concept

A reverse proxy sits in front of one or more backend servers and represents them to the outside world.

It is different from a forward proxy, which is used by clients to access external networks.

A reverse proxy can:

- hide backend server details
- terminate SSL
- cache static content
- compress responses
- apply rate limiting
- protect against abuse
- route to multiple app servers

In many deployments, the reverse proxy and load balancer are combined in one system such as Nginx or HAProxy.

### Nginx basics

Nginx is a popular reverse proxy and load balancer.

Common responsibilities:

- listen on port 80 or 443
- forward requests to app servers
- serve static files
- handle HTTPS certificates
- load balance between upstream backends

Basic upstream example:

```nginx
upstream app_servers {
    server 10.0.0.11:8080;
    server 10.0.0.12:8080;
    server 10.0.0.13:8080;
}

server {
    listen 80;
    server_name example.com;

    location / {
        proxy_pass http://app_servers;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
    }
}
```

This configuration sends incoming traffic to backend app servers while keeping the application servers behind a single public entry point.

### Health checks

Health checks verify whether a backend instance is able to serve traffic.

A server is marked healthy only if it passes the check.

Common health check methods:

- HTTP GET to /health
- TCP connection check
- custom application-level verification

Examples:

- if /health returns 200, mark as healthy
- if the response is timeout or 500, route traffic away
- if a node fails repeatedly, remove it from the pool

Benefits:

- avoids sending requests to dead instances
- improves availability
- prevents cascading failures

Good health checks should include:

- timeout value
- retry count
- interval
- failure threshold
- graceful recovery behavior

### Auto scaling groups

An Auto Scaling Group (ASG) automatically adds or removes instances based on traffic and health.

Typical goals:

- handle traffic spikes automatically
- reduce cost during idle periods
- maintain enough healthy instances for availability

Key parts of scaling:

- minimum number of instances
- desired number of instances
- maximum number of instances
- scale-out policy when CPU, memory, or request rate rises
- scale-in policy when demand falls

Common triggers:

- CPU utilization > 70%
- application latency > threshold
- request rate > target
- queue length growing beyond a limit

ASGs are usually combined with:

- load balancers
- launch templates or machine images
- health checks
- alarms and metrics

This makes the system easier to scale without manual intervention.

### AI-assisted infrastructure planning

AI can help in early-stage architecture design by:

- generating possible system layouts
- suggesting scaling strategies
- identifying bottlenecks from traffic estimates
- comparing tradeoffs between SQL and NoSQL, caching, queues, and replication
- reviewing infrastructure-as-code templates
- assisting with incident response and troubleshooting steps

Example prompt:

- "Design a scalable e-commerce architecture for 1M daily users with 200ms p95 latency targets."

AI is strongest when used for:

- brainstorming architecture options
- summarizing tradeoffs
- producing initial drafts and checklists
- clarifying operational risk areas

AI is not a replacement for:

- capacity planning
- security review
- failure-mode analysis
- production operations judgment

The best approach is to use AI as a planning assistant, while engineers validate the architecture against real requirements, costs, and reliability constraints.

### Interview perspective

For interviews, explain these concepts clearly:

- L4 balancers are simpler and faster, but less aware of application logic.
- L7 balancers are smarter and more flexible, but heavier.
- Round robin is simple when servers are similar.
- Least connections works better when workloads differ.
- Reverse proxies protect and centralize traffic handling.
- Health checks are necessary for safe and automatic failover.
- Auto scaling is key for handling variable user load.
- AI helps draft architecture, but engineering judgment still decides the final design.
