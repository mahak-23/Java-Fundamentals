import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AuthenticationAuthorizationDemo {
    public static void main(String[] args) {
        DemoTokenVerifier verifier = new DemoTokenVerifier();
        verifier.register("demo-customer-token", new UserPrincipal("USER-1", Role.CUSTOMER));
        verifier.register("demo-admin-token", new UserPrincipal("ADMIN-1", Role.ADMIN));

        AuthenticationService authentication = new AuthenticationService(verifier);
        AuthorizationService authorization = new AuthorizationService();

        UserPrincipal customer = authentication.authenticate("demo-customer-token");
        authorization.requireRole(customer, Role.CUSTOMER);
        authorization.requireOwner(customer, "USER-1");
        System.out.println("Customer authenticated and can read their own profile");

        try {
            authorization.requireOwner(customer, "USER-2");
        } catch (SecurityException exception) {
            System.out.println("Other user's profile blocked: " + exception.getMessage());
        }

        UserPrincipal admin = authentication.authenticate("demo-admin-token");
        authorization.requireRole(admin, Role.ADMIN);
        System.out.println("Admin authenticated and can manage users");
    }

    enum Role {
        CUSTOMER,
        SUPPORT,
        ADMIN
    }

    static final class UserPrincipal {
        final String userId;
        final Set<Role> roles;

        UserPrincipal(String userId, Role... roles) {
            this.userId = userId;
            this.roles = new HashSet<>(Arrays.asList(roles));
        }
    }

    interface TokenVerifier {
        Optional<UserPrincipal> verify(String token);
    }

    // Demo only: a real verifier validates token signature, issuer, audience, and expiry.
    static final class DemoTokenVerifier implements TokenVerifier {
        private final Map<String, UserPrincipal> knownTokens = new HashMap<>();

        void register(String demoToken, UserPrincipal principal) {
            knownTokens.put(demoToken, principal);
        }

        @Override
        public Optional<UserPrincipal> verify(String token) {
            return Optional.ofNullable(knownTokens.get(token));
        }
    }

    static final class AuthenticationService {
        private final TokenVerifier verifier;

        AuthenticationService(TokenVerifier verifier) {
            this.verifier = verifier;
        }

        UserPrincipal authenticate(String token) {
            return verifier.verify(token)
                    .orElseThrow(() -> new SecurityException("Invalid or expired token"));
        }
    }

    static final class AuthorizationService {
        void requireRole(UserPrincipal principal, Role requiredRole) {
            if (!principal.roles.contains(requiredRole)) {
                throw new SecurityException("Missing role: " + requiredRole);
            }
        }

        void requireOwner(UserPrincipal principal, String resourceOwnerId) {
            if (!principal.userId.equals(resourceOwnerId)
                    && !principal.roles.contains(Role.ADMIN)) {
                throw new SecurityException("User cannot access this resource");
            }
        }
    }
}
