import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobinLoadBalancerExample {
    private final List<String> servers;
    private final AtomicInteger nextIndex = new AtomicInteger();

    public RoundRobinLoadBalancerExample(List<String> servers) {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalArgumentException("At least one server is required");
        }
        this.servers = List.copyOf(servers);
    }

    public String nextServer() {
        int index = nextIndex.getAndUpdate(current -> (current + 1) % servers.size());
        return servers.get(index);
    }

    public static void main(String[] args) {
        RoundRobinLoadBalancerExample loadBalancer = new RoundRobinLoadBalancerExample(
            List.of("server-1", "server-2", "server-3")
        );

        for (int request = 1; request <= 7; request++) {
            System.out.println("Request " + request + " -> " + loadBalancer.nextServer());
        }
    }
}
