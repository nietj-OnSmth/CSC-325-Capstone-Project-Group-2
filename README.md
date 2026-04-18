SmartPark – Smart Parking Management System:
SmartPark is a full-stack parking management system designed to help Farmingdale State College students and faculty efficiently find and reserve parking based on real-time availability and user role. 
Project Overview: Parking on campus is often inefficient due to limited visibility into lot availability, and a large portion of Farmingdale students being commuter students. SmartPark solves this problem by: 
 * Recommending the best parking lot based on availability and distance
 * Allowing users to reserve and release parking spots
 * Providing admins with full control over parking lot management 
Team Members 
* Joseph N– Project Manager
* Joseph M – Backend Lead
* Kaley – Frontend Lead
* Claudia – Testing Lead 
Tech Stack 
Backend 
* Java
* Spring Boot
* REST API
* JPA (planned for database integration) 
Frontend
* JavaFX
* HttpURLConnection (API communication) 
Database (In Progress)
* MySQL (planned for parking lots and reservations) 
 
Features 
Authentication & Role-Based Access: 
* Login system with role-based routing 
Roles:
* Student
* Faculty
* Admin 
Student & Faculty Features
* Request parking recommendations
* View:
  * Lot name
  * Available spaces
  * Distance
  * Status (AVAILABLE / LIMITED / FULL) 
* Reserve a parking spot
* Release a parking spot
* Restriction:
   * One active reservation per user 
Admin Features
* Add parking lots
* Update parking lots
* Delete parking lots
* View parking lots
* Manage system availability 
Recommendation System
* Selects optimal lot based on:
  * Availability
  * Distance
  * Status priority (AVAILABLE > LIMITED > FULL) 
Reservation System
* Real-time updates to: 
   * Available spaces
   * Lot status 
* Prevents:
  * Multiple reservations per user
  * Releasing without a reservation 
 
System Architecture 
The backend follows a layered architecture: 
* Controller Layer – Handles API requests
* Service Layer – Business logic (recommendation, reservation)
* Repository Layer – Data access
* Model Layer – Entities (ParkingLot, User, etc.) 
Frontend communicates with backend via HTTP requests. 

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

Testing documentation is maintained in: /docs/TESTING.md 

Known Limitations: 
- Data is currently stored in-memory 
- Reservations reset on backend restart 

Future Improvements: 
- Integrate MySQL database for: 
- Parking lots 
- Reservations 
- Improve UI/UX design 
- Enhance error handling and user feedback 
- Optional: move authentication to database 

How to Run the Project: 
 
Backend 
1. Navigate to backend folder 
2. Run Spring Boot application: BackendApiApplication.java 
 
Frontend 
1. Navigate to JavaFX client 
2. Run: SmartParkApp.java 
