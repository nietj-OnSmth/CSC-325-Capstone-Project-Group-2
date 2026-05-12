# SmartPark – Smart Parking Management System

SmartPark is a full-stack parking management system designed to help Farmingdale State College students and faculty efficiently find and reserve parking based on real-time availability and user role.

---

# Project Overview

Parking on campus is often inefficient due to limited visibility into lot availability, especially at commuter-heavy campuses such as Farmingdale State College.

SmartPark solves this problem by:

- Recommending the best parking lot based on availability and distance
- Allowing users to reserve and release parking spots
- Providing administrators with full control over parking lot management

---

# Team Members

- **Joseph N** – Project Manager  
- **Joseph M** – Backend Lead  
- **Kaley** – Frontend Lead  
- **Claudia** – Testing Lead  

---

# Tech Stack

## Backend
- Java
- Spring Boot
- REST API
- Spring Data JPA

## Frontend
- JavaFX
- HttpURLConnection (API communication)

## Database
- MySQL (Dockerized)
- Persistent storage for:
  - Parking lots
  - Reservations
  - User accounts

## Tools
- IntelliJ IDEA
- GitHub
- Docker

---

# Features

## Authentication & Role-Based Access

- Login system with database-backed authentication (MySQL)
- Role-based routing after login

### Roles
- Student
- Faculty
- Admin

---

## Student & Faculty Features

- Request parking recommendations
- View:
  - Lot name
  - Available spaces
  - Distance
  - Status (`AVAILABLE`, `LIMITED`, `FULL`)
- Reserve a parking spot
- Release a parking spot

### Reservation Restrictions
- One active reservation per user
- Cannot release without an active reservation

---

## Reservation System

- Fully integrated with MySQL database

### Real-time updates to:
- Available spaces
- Lot status

### Prevents:
- Multiple reservations per user
- Releasing without an active reservation

### Persistence:
Reservation data persists across:
- Backend restarts
- Docker container restarts

---

## Admin Features

- Add parking lots
- Update parking lots
- Delete parking lots
- View all parking lots
- Manage parking lot availability

### Parking Lot UI
- Parking lots displayed as structured UI cards
- Real-time data retrieved from backend API
- Status indicators:
  - AVAILABLE
  - LIMITED
  - FULL

---

## Recommendation System

The recommendation system selects the optimal parking lot based on:

- Availability
- Distance
- Status priority:

AVAILABLE > LIMITED > FULL

Students and faculty are guided toward the best available parking lot rather than manually browsing all lots.

---

# System Architecture

The backend follows a layered architecture:

- **Controller Layer** – REST API endpoints
- **Service Layer** – Business logic (recommendation, reservation, authentication)
- **Repository Layer** – Database access using Spring Data JPA
- **Model Layer** – Application entities (`ParkingLot`, `Reservation`, `AppUser`)

Frontend communicates with the backend through HTTP requests.

DTOs are used to transfer structured data between the frontend and backend layers.

---

# Design Patterns Used

- Layered Architecture
- Strategy Pattern (parking recommendation logic)

---

# Docker Integration

SmartPark uses a Dockerized MySQL database to ensure a consistent development environment across all machines.

## Benefits
- No local database installation required
- Consistent setup across all team members
- Persistent data storage
- Eliminates environment configuration inconsistencies

### Docker Port Configuration

SmartPark uses different internal and external database ports:

- **Container MySQL Port:** `3306`
- **Host Machine Port:** `3307`

This is configured in `docker-compose.yml`:

ports:
  - "3307:3306"

The application connects to:

localhost:3307

This prevents conflicts with local MySQL/WAMP installations using port 3306.

# Scrum Process

This project was developed using Scrum methodology.

## Current Practices
- Weekly standup meetings
- Incremental commits
- Feature-based development
- GitHub feature branches
- Pull requests

## Project Management
- GitHub Kanban board for task tracking
- Backlog and sprint organization

---

# Testing

Testing was performed to validate:

- Login validation
- Correct role-based routing
- Accurate parking recommendations
- Reservation and release constraints
- Admin CRUD functionality
- Full-stack frontend-backend integration
- Database persistence using Dockerized MySQL

Testing documentation is maintained in:

/docs

---

# Known Limitations

- Passwords are stored in plain text (for demonstration purposes)
- No full authentication framework (e.g., JWT or Spring Security)
- No user registration system (users are pre-seeded)

---

# Future Improvements

- Implement password hashing
- Add user registration and account management
- Integrate Spring Security with JWT authentication
- Improve UI/UX design
- Add real-time updates

---

# Database Configuration (Development Only)

The project uses a Dockerized MySQL database.

## Default Configuration

| Property | Value |
|---|---|
| Host | localhost |
| Host Port | 3307 |
| Container MySQL Port | 3306 |
| Database | smartpark_db |
| Username | root |
| Password | root |

> These credentials are for local development only and are not intended for production use.

In a production environment, secure credential storage and environment variables would be used instead of hardcoded values.

---

# How to Run the Project

## Prerequisites

Before running SmartPark, ensure the following are installed:

- Java 21
- JavaFX 21
- Docker Desktop

---
> IMPORTANT: Docker Desktop must be installed and running before starting the project.
---

# Backend

 1. Ensure Docker is running: docker compose up -d
 2. Navigate to backend folder
 3. Run Spring Boot application: BackendApiApplication.java

# Frontend

 1. Navigate to JavaFX client
 2. Run: SmartParkApp.java
