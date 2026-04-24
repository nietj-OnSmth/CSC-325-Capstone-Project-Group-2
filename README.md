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
* Persistent storage for parking lots and reservations

Tools
* IntelliJ IDEA
* GitHub
* Docker
 
Features:
Authentication & Role-Based Access: 
* Login system with role-based routing 
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

Reservation System:
 * Real-time updates to:
 * Available spaces
 * Lot status
Prevents:
 * Multiple reservations per user
 * Releasing without a reservation
 * Fully integrated with MySQL database
 * Reservation data persists across backend restarts
Restriction:
   * One active reservation per user 

Admin Features:
* Add parking lots
* Update parking lots
* Delete parking lots
* View parking lots
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
* Verification of database persistence using Dockerized MySQL.
Testing documentation is maintained in: docs

Known Limitations: 
* User authentication is not yet persisted in the database
* No user account management system (users are simulated)

Future Improvements:  
* Persist user authentication in MySQL 
* Improve UI/UX design
* Enhance error handling and user feedback

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
