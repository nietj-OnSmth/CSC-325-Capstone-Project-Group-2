SmartPark – Smart Parking Management System

SmartPark is a full-stack parking management system designed to help Farmingdale State College students and faculty efficiently find and reserve parking based on real-time availability and user role. 

Project Overview: Parking on campus is often inefficient due to limited visibility into lot availability, and a large portion of Farmingdale students being commuter students. SmartPark solves this problem by: 
 * Recommending the best parking lot based on availability and distance
 * Allowing users to reserve and release parking spots
 * Providing admins with full control over parking lot management
   
Team Members:
* Joseph N– Project Manager
* Joseph M – Backend Lead
* Kaley – Frontend Lead
* Claudia – Testing Lead

Tech Stack:

Backend
* Java
* Spring Boot
* REST API
* Spring Data JPA

Frontend
* JavaFX
* HttpURLConnection (API communication)

Database
* MySQL (Dockerized)
* Persistent storage for
* Parking lots
* Reservations
* User accounts


Tools
* IntelliJ IDEA
* GitHub
* Docker

Features:
Authentication & Role-Based Access
 * Login system with database-backed authentication (MySQL)
 * Role-based routing after login
Roles:
* Student
* Faculty
* Admin


Student & Faculty Features:
* Request parking recommendations
* View:
  * Lot name
  * Available spaces
  * Distance
  * Status (AVAILABLE / LIMITED / FULL) 

Reservation System
* Fully integrated with MySQL database
Real-time updates to:
* Available spaces
* Lot status
Prevents:
* Multiple reservations per user
* Releasing without an active reservation
Reservation data persists across:
* Backend restarts
* Docker container restarts

   
Restriction:
   * One active reservation per user 

Admin Features:
* Add parking lots
* Update parking lots
* Delete parking lots
* View all parking lots (Admin View Page)
* Parking Lot UI

- Parking lots displayed as structured UI cards  
- Real-time data retrieved from backend API  
- Status color indicators:
  - AVAILABLE  
  - LIMITED  
  - FULL  

* Manage system availability 
Recommendation System:
* Selects optimal lot based on:
  * Availability
  * Distance
  * Status priority (AVAILABLE > LIMITED > FULL) 
Reservation System:
* Real-time updates to: 
   * Available spaces
   * Lot status 
* Prevents:
  * Multiple reservations per user
  * Releasing without a reservation 
 
System Architecture: The backend follows a layered architecture:
*  Controller Layer – Handles API requests
*  Service Layer – Business logic (recommendation, reservation)
*  Repository Layer – Data access (JPA repositories)
*  Model Layer – Entities (ParkingLot, Reservation, etc.)

Scrum Process: This project is developed using Scrum methodology. 
Current Practices: 
* Weekly standup meetings
* Incremental commits
* Feature-based development 
Improvements (In Progress):
* GitHub Kanban Board for task tracking
* Backlog and sprint organization 

Testing: 

Testing is performed to ensure: 
* Login validation
* Correct role-based routing
* Accurate parking recommendations
* Reservation and release constraints
* Admin CRUD functionality
* Full-stack functionality
* Verification of database persistence and full frontend-backend integration using Dockerized MySQL.

Testing documentation is maintained in: docs

Known Limitations
* Passwords are stored in plain text (for demonstration purposes)
* No full authentication framework (e.g., JWT or Spring Security)
* No user registration system (users are pre-seeded)

Future Improvements
* Implement password hashing
* Add user registration and account management
* Integrate Spring Security with JWT authentication
* Improve UI/UX design
* Add real-time updates 


Docker Integration: 
SmartPark uses a Dockerized MySQL database to ensure a consistent development environment.
Benefits:
* No local database installation required
* Consistent setup across all team members
* Persistent data storage


Database Configuration (Development Only)
The project uses a Dockerized MySQL database for development.

Default credentials:
* Host: localhost
* Port: 3306
* Database: smartpark_db
* Username: root
* Password: root

Note: These credentials are for local development only and are not intended for production use. In a production environment, environment variables or secure
credential storage would be used instead of hardcoded values.

How to Run the Project: 
 
Backend
1. Ensure Docker is running:
docker compose up -d
2. Navigate to backend folder
3. Run Spring Boot application:
BackendApiApplication.java 

Frontend 
1. Navigate to JavaFX client 
2. Run: SmartParkApp.java 
