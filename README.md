# Quantity Measurement System (QMA) – Backend

A Spring Boot–based microservices backend system for performing quantity measurement operations such as conversion, comparison, and arithmetic calculations, with secure JWT-based authentication and scalable cloud deployment.

---

## Architecture

The backend follows a microservices architecture with centralized routing and security.

```
Client (Frontend)
        │
        ▼
API Gateway (8080)
  • JWT Validation
  • Request Routing
        │
 ┌──────┴──────────────┐
 │                     │
 ▼                     ▼
Auth Service        QMA Service
(8082)              (8081)
MySQL DB            MySQL DB
                     │
                     ▼
                   Redis
```

---

## Project Structure

```
QuantityMeasurementApp/
│
├── api-gateway/
│   ├── src/main/java/
│   └── src/main/resources/
│
├── auth-service/
│   ├── src/main/java/
│   └── src/main/resources/
│
├── qma-service/
│   ├── src/main/java/
│   └── src/main/resources/
│
├── eureka-server/   (used in development, removed in production)
│   └── src/main/resources/
│
├── docker-compose.yml   (optional for containerized setup)
├── pom.xml              (parent/root configuration)
└── README.md
```

### Structure Overview

* **api-gateway**: Handles routing, JWT validation, and request filtering
* **auth-service**: Manages authentication and token generation
* **qma-service**: Contains core business logic for quantity operations
* **eureka-server**: Service discovery (used initially, later removed)
* **docker-compose.yml**: Runs all services in containers
* **pom.xml**: Centralized dependency management

---

## Microservices

### API Gateway (Port: 8080)

* Single entry point for all client requests
* Routes requests to appropriate services
* Validates JWT tokens
* Handles CORS and request filtering

---

### Auth Service (Port: 8082)

* User registration and login
* Password encryption using BCrypt
* JWT token generation
* User data stored in MySQL

---

### QMA Service (Port: 8081)

* Handles quantity operations:

  * Conversion
  * Comparison
  * Arithmetic calculations
* Stores operation history
* Uses Redis for caching frequently accessed results

---

## Features

* JWT-based stateless authentication
* Microservices architecture with API Gateway
* Redis caching for performance optimization
* Secure password storage using BCrypt
* Operation history persistence
* Scalable and cloud-ready deployment

---

## API Routing

All requests pass through the API Gateway:

| Endpoint Pattern      | Service      |
| --------------------- | ------------ |
| /api/v1/auth/**       | Auth Service |
| /api/v1/quantities/** | QMA Service  |

---

## Authentication Flow

1. Client sends login request
2. Auth Service validates credentials
3. JWT token is generated and returned
4. Client includes token in Authorization header
5. API Gateway validates token before forwarding request

---

## Data Model

### Auth Service

* `users` table

### QMA Service

* `quantity_measurements` table:

  * id
  * operation
  * result
  * username

---

## Caching Strategy

Redis is used to cache frequently accessed operations, reducing database load and improving response time.

---

## Deployment

The backend is deployed using cloud platforms:

* Auth Service → Railway
* QMA Service → Railway
* API Gateway → Render

**Note:**
Service discovery (Eureka) was initially implemented but later removed due to cross-platform deployment constraints. Static service URLs are used instead.

---

## Running Locally

### Prerequisites

* Java 17
* Maven
* MySQL
* Redis

### Steps

1. Clone the repository

```
git clone https://github.com/kanhiya-sh/QuantityMeasurementApp.git
cd QuantityMeasurementApp
```

2. Create databases

```
CREATE DATABASE auth_db;
CREATE DATABASE qma_db;
```

3. Configure application properties for each service

4. Run services in order:

```
# Eureka Server (for development)
cd eureka-server && mvn spring-boot:run

# Auth Service
cd auth-service && mvn spring-boot:run

# QMA Service
cd qma-service && mvn spring-boot:run

# API Gateway
cd api-gateway && mvn spring-boot:run
```

All requests will be routed through:

```
http://localhost:8080
```

---

## Docker

Each service is containerized using Docker.

To run using Docker Compose:

```
docker compose up --build
```

---

## Technology Stack

| Layer            | Technology                  |
| ---------------- | --------------------------- |
| Language         | Java 17                     |
| Framework        | Spring Boot                 |
| Security         | Spring Security + JWT       |
| API Gateway      | Spring Cloud Gateway        |
| Database         | MySQL                       |
| Cache            | Redis                       |
| ORM              | Spring Data JPA / Hibernate |
| Build Tool       | Maven                       |
| Containerization | Docker                      |
| Deployment       | Railway, Render             |

---

## Key Design Decisions

* Microservices architecture for scalability and modularity
* API Gateway for centralized routing and security
* JWT authentication for stateless security
* Redis caching for performance optimization
* Removal of Eureka to support multi-platform deployment

---

## Author

Kanhiya Sharma
B.Tech CSE (Cloud Computing and Virtualization)
