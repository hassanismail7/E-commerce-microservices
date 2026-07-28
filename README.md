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

---

## Architecture

- Eureka Server (Service Discovery)
- Auth Service
- Wallet Service
- Inventory Service
- Shop Service *(In Progress)*

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
- Inventory management
- Increase stock
- Decrease stock
- Update inventory quantity
- Product availability check
- JWT validation for protected endpoints
- Global exception handling

---

## Microservices Communication

### Auth Service → Wallet Service

- Uses Spring Cloud OpenFeign
- Service discovery through Eureka
- Automatically creates a wallet when a new user registers

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
- Shop Database *(Planned)*

---

## Planned Features

- API Gateway
- Shop Service
- Order Management
- Shopping Cart
- Product Search
- Payment Workflow
- Role-Based Authorization (Admin / Customer)
- Distributed transactions between microservices

---

## Project Status

| Service | Status |
|---------|--------|
| Eureka Server | Completed |
| Auth Service | Completed |
| Wallet Service | Completed |
| Inventory Service | Completed |
| Shop Service | In Progress |
| API Gateway | Planned |