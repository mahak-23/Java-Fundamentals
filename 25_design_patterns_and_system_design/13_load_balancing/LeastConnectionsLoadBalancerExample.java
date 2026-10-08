import java.util.ArrayList;
import java.util.List;

public class LeastConnectionsLoadBalancerExample {
    private final List<Backend> backends;
    private int tieBreakStart;

    public LeastConnectionsLoadBalancerExample(List<String> serverNames) {
        if (serverNames == null || serverNames.isEmpty()) {
            throw new IllegalArgumentException("At least one server is required");
        }
        this.backends = serverNames.stream().map(Backend::new).toList();
    }

    public synchronized Lease acquire() {
        Backend selected = null;
        int selectedIndex = -1;

        for (int offset = 0; offset < backends.size(); offset++) {
            int index = (tieBreakStart + offset) % backends.size();
            Backend candidate = backends.get(index);
            if (selected == null || candidate.activeRequests < selected.activeRequests) {
                selected = candidate;
                selectedIndex = index;
            }
        }

        selected.activeRequests++;
        tieBreakStart = (selectedIndex + 1) % backends.size();
        return new Lease(this, selected);
    }

    private synchronized void release(Backend backend) {
        if (backend.activeRequests <= 0) {
            throw new IllegalStateException("Backend has no active requests to release");
        }
        backend.activeRequests--;
    }

    private static final class Backend {
        private final String name;
        private int activeRequests;

        private Backend(String name) {
            this.name = name;
        }
    }

    public static final class Lease implements AutoCloseable {
        private final LeastConnectionsLoadBalancerExample owner;
        private final Backend backend;
        private boolean closed;

        private Lease(LeastConnectionsLoadBalancerExample owner, Backend backend) {
            this.owner = owner;
            this.backend = backend;
        }

        public String serverName() {
            return backend.name;
        }

        @Override
        public synchronized void close() {
            if (!closed) {
                owner.release(backend);
                closed = true;
            }
        }
    }

    public static void main(String[] args) {
        LeastConnectionsLoadBalancerExample loadBalancer =
            new LeastConnectionsLoadBalancerExample(List.of("server-1", "server-2", "server-3"));
        List<Lease> activeLeases = new ArrayList<>();

        for (int request = 1; request <= 3; request++) {
            Lease lease = loadBalancer.acquire();
            activeLeases.add(lease);
            System.out.println("Request " + request + " -> " + lease.serverName());
        }

        activeLeases.get(0).close();
        Lease nextLease = loadBalancer.acquire();
        activeLeases.add(nextLease);
        System.out.println("Request 4 after server-1 completes -> " + nextLease.serverName());

        for (Lease lease : activeLeases) {
            lease.close();
        }
    }
}
