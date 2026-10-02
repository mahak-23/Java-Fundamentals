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
