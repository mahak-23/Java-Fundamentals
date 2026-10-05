## Authentication and authorization basics

### Authentication vs authorization

#### Authentication

Authentication answers the question: "Who are you?"

It verifies the identity of a user, service, or device.

Examples:

- login with username and password
- OAuth login via Google or GitHub
- API key validation
- certificate-based client validation

#### Authorization

Authorization answers the question: "What are you allowed to do?"

It decides whether an authenticated principal can access a resource or action.

Examples:

- user can read their own profile
- admin can delete a product
- service can call internal billing API

Important distinction:

- authentication verifies identity
- authorization checks permissions

A user can be authenticated but still not authorized for a specific action.

### JWT structure

JWT stands for JSON Web Token.

A JWT is a compact, stateless token used to carry identity and claims.

A JWT consists of three parts:

- Header
- Payload
- Signature

Format:

```text
<base64(header)>.<base64(payload)>.<base64(signature)>
```

#### Header

Typically contains:

- alg: signing algorithm, such as HS256 or RS256
- typ: token type, usually JWT

Example:

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

#### Payload

Contains claims about the user or token.

Common claims:

- sub: subject or user ID
- iss: issuer
- aud: audience
- exp: expiration time
- iat: issued-at time
- role: user role or permissions

Example:

```json
{
  "sub": "user_123",
  "role": "ADMIN",
  "iss": "myapp",
  "aud": "api",
  "exp": 1730000000
}
```

#### Signature

The signature is computed using the header, payload, and a secret or private key.

This ensures:

- integrity of the token
- detection of tampering
- trust when the server verifies it

JWTs are stateless, so the server can validate them without storing session data on the server side.

### OAuth flow

OAuth is an authorization framework that lets a user grant an application limited access to data on another service without sharing the password.

Typical OAuth flow:

1. User clicks "Login with Google"
2. App redirects user to the provider
3. User signs in and approves permissions
4. Provider redirects back with an authorization code
5. App exchanges the code for tokens
6. App uses access token to call protected APIs

Example flow:

```mermaid
flowchart LR
    U[User] --> A[App]
    A --> P[Authorization Server]
    P -->|grant consent| U
    U -->|redirect with code| A
    A -->|exchange code for tokens| P
    P -->|return access + refresh tokens| A
    A --> API[Protected API]
    API -->|validate token| A
```

The flow often includes:

- client ID
- client secret
- authorization endpoint
- token endpoint
- redirect URI
- scopes

Common scopes:

- read:user
- write:profile
- email
- admin

OAuth is often used with OpenID Connect (OIDC) for identity and login information.

### Access token vs refresh token

#### Access token

An access token is used to access protected resources.

Properties:

- short-lived
- sent in requests
- usually included in Authorization header
- used for API access and authorization checks

Example:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Refresh token

A refresh token is used to get a new access token without logging the user in again.

Properties:

- longer-lived than access token
- stored securely on the client or server
- exchanged when access token expires
- sensitive and should be protected carefully

Typical pattern:

- login -> get access + refresh tokens
- access token expires -> use refresh token to renew access token
- rotate refresh tokens when possible for better security

### Session-based auth

Session-based authentication stores user authentication state on the server.

Typical flow:

1. user logs in
2. server creates a session record
3. server stores session ID in a cookie
4. client sends the cookie with each request
5. server checks session store to validate the user

Pros:

- easy to revoke sessions
- server has direct control over login state
- useful for web apps with server-side rendering

Cons:

- server memory or database overhead
- requires session storage and scaling strategy
- sticky sessions or distributed session storage may be needed

Session-based auth is common in traditional monolithic web apps.

### Role-based access control (RBAC)

RBAC assigns permissions based on roles rather than individual users.

Examples of roles:

- USER
- ADMIN
- MODERATOR
- SUPPORT

Role-based rules:

- user can view their profile
- admin can delete records
- moderator can review flagged content

This is easier to manage than assigning permissions one-by-one to every user.

More advanced patterns include:

- permission-based access control
- attribute-based access control (ABAC)
- policy-based authorization

### Securing APIs

APIs need strong security controls at multiple layers.

Common protections:

- use HTTPS everywhere
- validate and sanitize all input
- use authentication and authorization
- avoid leaking sensitive data in responses
- rotate keys and tokens regularly
- store secret keys in environment variables or secret managers
- use rate limiting and request throttling
- log failed requests and access events
- use CSRF protection for browser-based apps
- validate tokens and payloads carefully

Good API security design should also include:

- least privilege access
- strong password hashing
- session or token expiration
- API gateway protections
- monitoring for suspicious patterns

### AI threat modeling

AI systems introduce new security and trust concerns.

Examples of threats:

- prompt injection
- data leakage from model outputs
- model poisoning during training
- misuse of generated content
- insecure API integration with model providers
- prompt-based bypass of application controls

Threat modeling for AI systems asks:

- what data does the model see?
- who can influence prompts or user input?
- what external systems are called by the model?
- how is sensitive data protected?
- what are the failure modes of generated outputs?

Examples of controls:

- validate model inputs and outputs
- restrict tool access for LLMs
- redact sensitive data before sending to external model APIs
- maintain audit logs of prompts and responses
- use a strict allowlist for allowed actions
- implement human review for critical decisions

### Interview perspective

For system design interviews, the key points are:

- authentication proves identity
- authorization decides allowed actions
- JWTs are compact, stateless, and signed tokens
- OAuth is a delegation protocol, not a user authentication mechanism by itself
- access tokens are short-lived and used for API calls
- refresh tokens are longer-lived and used to get new access tokens
- session auth is server-managed and common for web apps
- RBAC is a practical pattern for permission management
- API security requires defense in depth
- AI systems need specific threat analysis because model behavior can be manipulated or abused

### Asked in real interviews

Practice explaining the design choices and security trade-offs behind questions such as:

| Interview question |
| --- |
| Design authentication for a web and mobile application. |
| Compare JWTs and server-side sessions. What are the trade-offs? |
| Walk through the OAuth 2.0 authorization-code flow. |
| How should passwords be stored securely? |
| Design a role-based permission system. |
| How do access and refresh tokens work together? |
| How would you secure a public REST API? |

### Model answers

#### 1. Design authentication for a web and mobile application

Answer in simple terms:

- Use HTTPS everywhere.
- Use an identity provider with OIDC/OAuth 2.0.
- For browser apps, use secure cookies with `HttpOnly`, `Secure`, and proper `SameSite` settings.
- For mobile and public clients, use the authorization-code flow with PKCE.
- Issue short-lived access tokens.
- Store refresh tokens securely and rotate them.
- Every backend API must still check authorization, not just trust the client.

Why this works:

- Browser apps can keep session state safely on the server side.
- Mobile apps cannot safely hold a client secret, so PKCE is required.
- Short token lifetime reduces damage if a token is stolen.

Interview-friendly summary:

> Use a trusted identity provider, secure cookies for browsers, PKCE for mobile apps, short-lived access tokens, and secure refresh-token rotation. Authentication proves identity; authorization decides what the user can do.

#### 2. Compare JWTs and server-side sessions

Short answer:

- Session-based auth: server stores session state; client gets a session ID.
- JWT auth: client keeps the signed token; server validates it locally.

Comparison:

| Option | Advantages | Drawbacks |
| --- | --- | --- |
| Server-side session | Easy to revoke, easy to invalidate, strong server control | Requires session storage and scaling strategy |
| JWT | Stateless, good for distributed APIs, easy validation | Harder to revoke before expiry, token payload is visible to clients |

Best choice:

- Use sessions for traditional web apps with a server and browser.
- Use short-lived JWTs when multiple services need to validate tokens independently.
- In both cases, use HTTPS, proper expiration, and secure storage.

Important note:

- JWTs are signed, not encrypted.
- Never put secrets or sensitive user data in the token payload.

#### 3. Walk through the OAuth 2.0 authorization-code flow

Step-by-step:

1. User opens the app and clicks login.
2. App redirects the user to the authorization server.
3. User signs in and grants consent.
4. Authorization server redirects back with an authorization code.
5. App validates the `state` value.
6. App sends the code and PKCE verifier to the token endpoint.
7. Server returns access token and refresh token.
8. App calls the protected API using the access token.

Security points:

- Use PKCE for public clients.
- Use a redirect URI that is tightly controlled.
- Validate issuer, audience, expiry, and scopes on the API side.
- Use OIDC if the app needs user identity information as well.

Interview-ready explanation:

> OAuth is for delegated authorization. The app asks for consent, receives a code, exchanges it for tokens, and then uses an access token to call the API. OIDC adds identity information about the user.

#### 4. How should passwords be stored securely?

Best practice:

- Never store plain text passwords.
- Never use reversible encryption for passwords.
- Hash each password with a unique salt.
- Use a slow password hashing algorithm such as Argon2id, bcrypt, or scrypt.
- Store the algorithm parameters with the hash.

Why:

- A password database leak should not reveal user passwords.
- Salting prevents precomputed rainbow-table attacks.
- Slow hashing makes brute-force attempts much more expensive.

In addition:

- enforce a reasonable minimum password length
- rate-limit login attempts
- add MFA for sensitive accounts
- protect password reset flows carefully

Simple answer:

> Store password hashes, not passwords. Use unique salts and a slow adaptive hashing function such as Argon2id or bcrypt.

#### 5. Design a role-based permission system

Model the design as separate entities:

- Users
- Roles
- Permissions
- Role-to-user assignments
- Resource ownership or tenant information

Example:

- `ADMIN`: can manage users and delete records
- `EDITOR`: can create and update content
- `VIEWER`: can only read content

Design rules:

- Enforce checks at the backend, not only in the UI.
- Default to deny if no rule matches.
- Validate both user identity and resource access.
- Use least privilege.
- Audit high-risk actions.

If the permission depends on business context, upgrade to ABAC or a policy engine.

Interview summary:

> Roles group permissions; authorization checks compare the user's permissions with the requested action and resource. We always enforce the check on the backend and default to deny.

#### 6. How do access and refresh tokens work together?

Simple flow:

- Login succeeds -> server issues access token + refresh token.
- Access token is used for API requests.
- Access token expires after a short time.
- When expired, client sends refresh token to the auth server.
- Auth server validates the refresh token and returns a new access token.
- Refresh token is rotated when possible.

Important security rules:

- Access token is short-lived and should not carry long-term authority.
- Refresh token is more sensitive and must be stored securely.
- Refresh token should not be sent to resource APIs.
- Reuse of refresh tokens should be detected and treated as suspicious.

Why this is important:

- Short-lived access tokens reduce risk if stolen.
- Refresh tokens allow the user to stay logged in without re-entering credentials.

#### 7. How would you secure a public REST API?

Use a layered defense approach:

- Enforce HTTPS only.
- Require strong authentication and least-privilege authorization.
- Validate all inputs and protect against injection.
- Add rate limiting and request quotas.
- Use a secret manager for API keys and credentials.
- Log security events without logging passwords or tokens.
- Set strict CORS and proper CSRF protections for browser clients.
- Add timeouts, retries, idempotency, and input size limits.

For public APIs, also:

- return generic error messages
- hide internal system details
- rotate keys regularly
- monitor suspicious patterns and alert on abuse

Interview-ready answer:

> Secure the API at the edge and inside the service. Use HTTPS, authentication, authorization, rate limits, input validation, least privilege, secret management, and monitoring. The goal is to stop abuse early and reduce the blast radius if a credential is leaked.
