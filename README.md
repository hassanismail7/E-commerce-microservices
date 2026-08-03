# E-Commerce Microservices

A scalable e-commerce application built using **Spring Boot** and **Spring Cloud**, following a microservices architecture. The project demonstrates service discovery, inter-service communication, JWT-based authentication, and independent databases for each service.

---

## Tech Stack

- Spring Boot
- Spring Cloud
  - Eureka Server
  - OpenFeign
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Cloudinary (Product Image Storage)

---

## Architecture

- Eureka Server (Service Discovery)
- Auth Service
- Wallet Service
- Inventory Service
- Shop Service
- API Gateway *(Planned)*

---

## Current Features

### Eureka Server
- Service registration and discovery
- Dynamic service lookup for microservices

### Auth Service
- User registration
- User login
- Password encryption using BCrypt
- JWT generation
- JWT validation
- Automatic wallet creation after successful registration using OpenFeign

### Wallet Service
- Automatic wallet creation for newly registered users
- Deposit funds
- Withdraw funds
- Retrieve wallet information
- Transaction history
- JWT validation for protected endpoints
- Global exception handling

### Inventory Service
- Product CRUD operations
- Automatic inventory creation for every new product
- Product image upload using Cloudinary
- Inventory management
- Increase stock
- Decrease stock
- Update inventory quantity
- Product availability check
- JWT validation for protected endpoints
- Global exception handling

### Shop Service
- Automatic cart creation
- Add products to cart
- Update cart item quantity
- Remove items from cart
- Clear shopping cart
- Checkout workflow
- Order creation
- Retrieve user orders
- Retrieve order by ID
- Filter orders by status
- Communication with Inventory and Wallet services using OpenFeign
- JWT validation for protected endpoints

---

## Microservices Communication

### Auth Service → Wallet Service
- Automatically creates a wallet after user registration

### Shop Service → Inventory Service
- Retrieves product information
- Checks product availability
- Updates inventory after successful checkout

### Shop Service → Wallet Service
- Withdraws the total order amount during checkout

Service discovery for all inter-service communication is handled through **Eureka Server**, while communication is implemented using **Spring Cloud OpenFeign**.

---

## Security

- JWT Authentication
- Stateless session management
- Spring Security
- BCrypt password hashing

---

## Database

Each microservice owns its own database.

- Auth Database
- Wallet Database
- Inventory Database
- Shop Database

---

## Planned Features

- API Gateway
- Product Search
- Role-Based Authorization (Admin / Customer)
- Distributed transactions between microservices
- Docker & Docker Compose
- Unit & Integration Testing

---

## Project Status

| Service | Status |
|---------|--------|
| Eureka Server | Completed |
| Auth Service | Completed |
| Wallet Service | Completed |
| Inventory Service | Completed |
| Shop Service | Completed |
| API Gateway | In Progress |