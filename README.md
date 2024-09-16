# Student Management System

This project is a **Student Management System** built using **Spring Boot** for backend services, **MongoDB** for data storage, and **JWT-based Spring Security** for authentication and authorization. The application features a **React frontend**, with the full stack deployed using **Docker**, **Docker Compose**, and **Kubernetes**.

## Features

- JWT Authentication and Authorization
- CRUD operations for students
- Role-based access control
- Student data can only be modified by authenticated students
- Dockerized backend, frontend, and database
- Kubernetes deployment on Google Cloud

## Tech Stack

- **Backend**: Spring Boot, Spring Security (JWT)
- **Database**: MongoDB
- **Frontend**: React
- **Containerization**: Docker, Docker Compose
- **Orchestration**: Kubernetes (GKE - Google Kubernetes Engine)
- **CI/CD**: Docker Hub, Google Cloud Platform

## Prerequisites

- Java 17
- Node.js 14+
- Docker & Docker Compose
- Kubernetes (kubectl and minikube or GKE)
- MongoDB (local or cloud instance)

## Getting Started

### Backend Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/username/student-management-system.git
   cd student-management-system/backend
   ```
2. Build the Spring Boot application:
   ```bash
   ./mvnw clean install
   ```
3. Run the application locally:
   ```bash
   ./mvnw spring-boot:run
   ```
   
### Frontend Setup
1. Navigate to the frontend directory:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the frontend application:
   ```bash
   npm run start
   ```

## API Endpoints

- `POST /authenticate`: Authenticate users and generate JWT token.
- `POST /register`: Add a new student.
- 
- `GET /students/{id}`: Retrieve a student by ID (Authenticated students only, their own data).
- `PUT /students/{id}`: Update student data (Authenticated students only, their own data).
- `DELETE /students/{id}`: Delete student (Authenticated students only, their own data).

   
