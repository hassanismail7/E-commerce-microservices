# E-Commerce Microservices

A scalable **Spring Boot** and **Spring Cloud** e-commerce application built using a microservices architecture. The project demonstrates service discovery, secure inter-service communication, JWT-based authentication, and independent databases for each microservice.

---

# Tech Stack

### Backend
- Spring Boot
- Spring Cloud
  - Eureka Server
  - OpenFeign
  - Spring Cloud Gateway
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- Maven

### Database
- MySQL

### Cloud
- Cloudinary (Product Image Storage)

---

# Microservices

## Eureka Server
- Service registration and discovery
- Dynamic service lookup

---

## API Gateway

### Features
- Central entry point for all client requests
- JWT authentication and authorization
- Routes requests to the appropriate microservices
- Forwards authenticated requests to downstream services

---

## Auth Service

### Features
- User registration
- User login
- Password encryption using BCrypt
- JWT generation
- JWT validation
- Automatic wallet creation after successful registration using OpenFeign

---

## Wallet Service

### Features
- Automatic wallet creation
- Deposit funds
- Withdraw funds
- Retrieve wallet information
- Transaction history
- JWT validation for protected endpoints
- Global exception handling

---

## Inventory Service

### Features
- Product CRUD operations
- Product image upload using Cloudinary
- Automatic inventory creation
- Increase stock
- Decrease stock
- Update inventory quantity
- Product availability checking
- JWT validation for protected endpoints
- Global exception handling

---

## Shop Service

### Cart
- Automatic cart creation
- Retrieve shopping cart
- Add products to cart
- Update cart item quantity
- Remove cart items
- Clear shopping cart

### Orders
- Checkout
- Order creation
- Retrieve authenticated user's orders
- Retrieve order by ID
- Filter orders by status

### Communication
- Retrieve product details from the Inventory Service
- Validate inventory availability
- Update inventory after successful checkout
- Withdraw payment from the Wallet Service
- JWT propagation between microservices using OpenFeign

---

# Checkout Workflow

```text
Client
    │
    ▼
API Gateway
    │
    ▼
Shop Service
    │
    ├── Retrieve Cart
    ├── Get Product Details (Inventory Service)
    ├── Validate Stock
    ├── Withdraw Balance (Wallet Service)
    ├── Decrease Inventory
    ├── Create Order
    ├── Clear Cart
    ▼
Return Created Order
```

---

# Inter-Service Communication

### Auth Service → Wallet Service
- Automatically creates a wallet for newly registered users.

### Shop Service → Inventory Service
- Retrieve product details.
- Validate stock availability.
- Decrease inventory after successful checkout.

### Shop Service → Wallet Service
- Withdraw the total order amount during checkout.

### Communication Technology
- Eureka Server for service discovery.
- Spring Cloud OpenFeign for synchronous communication.
- JWT propagation between microservices using Feign Request Interceptors.

---

# Security

- JWT Authentication
- Stateless session management
- Spring Security
- BCrypt password hashing
- JWT validation across all protected microservices
- Secure inter-service communication

---

# Database Design

Each microservice owns its own database.

| Service | Database |
|----------|----------|
| Auth Service | Auth Database |
| Wallet Service | Wallet Database |
| Inventory Service | Inventory Database |
| Shop Service | Shop Database |

---

# Current Features

- User registration and login
- JWT authentication
- API Gateway routing
- Service discovery with Eureka
- Product management
- Inventory management
- Wallet management
- Shopping cart
- Checkout
- Order management
- Product image upload using Cloudinary
- OpenFeign communication
- Automatic wallet creation
- Global exception handling

---

# Project Status

| Microservice | Status |
|--------------|--------|
| Eureka Server | Completed |
| API Gateway | Completed |
| Auth Service | Completed |
| Wallet Service | Completed |
| Inventory Service | Completed |
| Shop Service | Completed |