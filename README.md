# HireHub – Role-Based Job Portal Backend

A secure, role-based **Job Portal backend** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, and **MySQL**.
The system supports multiple user roles such as **Admin, Recruiter, and Job Seeker**, enabling job posting, application management, and user control through RESTful APIs.

This project demonstrates **production-style backend architecture**, secure authentication, and role-based authorization.

---

## Features

### Authentication & Security

* JWT-based authentication
* Secure login and registration
* Role-based access control (RBAC)
* Password encryption using BCrypt
* Protected REST APIs

### User Roles

#### Admin

* Manage all users
* View and control job postings
* Full system-level access

#### Recruiter

* Post new jobs
* Update or delete job listings
* View applicants for their jobs

#### Job Seeker

* Register and login
* Browse available jobs
* Apply to jobs
* Track application status

---

## Tech Stack

| Layer       | Technology                  |
| ----------- | --------------------------- |
| Backend     | Spring Boot                 |
| Security    | Spring Security + JWT       |
| Database    | MySQL                       |
| ORM         | Spring Data JPA (Hibernate) |
| Build Tool  | Maven                       |
| API Testing | Postman                     |

---

## Project Architecture

The project follows a **layered architecture**:

```
Controller → Service → Repository → Database
```

### Main Components

* **Controllers** – Handle HTTP requests and responses
* **Services** – Contain business logic
* **Repositories** – Database access layer
* **Entities** – JPA models
* **Security Configuration** – JWT authentication and authorization

---

## Core Modules

### 1. Authentication

* User registration
* User login
* JWT token generation

### 2. Job Management

* Create job (Recruiter)
* Update job
* Delete job
* View all jobs
* View job by ID

### 3. Applications

* Apply to job (Job Seeker)
* View applications
* Manage application status

### 4. Admin Panel

* View all users
* Manage system data

---

## Database Schema

### User

* id
* name
* email
* password
* role

### Job

* id
* title
* description
* location
* salary
* recruiter_id

### Application

* id
* job_id
* user_id
* status
* applied_date

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/hirehub-backend.git
cd hirehub-backend
```

### 2. Configure MySQL Database

Create a database:

```sql
CREATE DATABASE hirehub;
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hirehub
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run the main class from your IDE.

Server will start at:

```
http://localhost:8080
```

---

## Authentication Flow

1. User registers an account.
2. User logs in with credentials.
3. Server generates a JWT token.
4. Token is sent in request headers for protected routes.

Example header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Sample API Endpoints

| Method | Endpoint       | Description           | Access        |
| ------ | -------------- | --------------------- | ------------- |
| POST   | /auth/register | Register new user     | Public        |
| POST   | /auth/login    | Login & get JWT token | Public        |
| GET    | /jobs          | Get all jobs          | Authenticated |
| POST   | /jobs          | Create job            | Recruiter     |
| PUT    | /jobs/{id}     | Update job            | Recruiter     |
| DELETE | /jobs/{id}     | Delete job            | Recruiter     |
| POST   | /applications  | Apply to job          | Job Seeker    |
| GET    | /admin/users   | Get all users         | Admin         |

---

## Example Request (Login)

```json
POST /auth/login
{
  "email": "user@example.com",
  "password": "password123"
}
```

### Example Response

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

## Future Enhancements

* Resume upload support
* Email notifications
* Job search filters
* Pagination
* Company profiles
* Frontend integration (React/Angular)

---

## Author

**Sarthak Sharma**
B.Tech Computer Science & Engineering
Backend & AI Developer
* Help you push this with a **clean commit history**
* Convert this into a **resume-ready project entry** that attracts recruiters.
