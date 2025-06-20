# ERP-based Learning Management System (LMS)  
Built with Spring Boot Microservices Architecture

---

## Microservice Interaction Overview

Service           | Interactions & Responsibilities
------------------|------------------------------------------------------------
Gateway Service      | Routes requests to all downstream microservices
Auth Service         | Manages authentication, OTP generation, token handling; interacts with User, Notification, Redis
User Service         | Handles user profiles and mapping; communicates with Auth, Notification, Student, and Instructor services
Student Service      | Manages enrollments and results; interacts with User, Batch, Course, Exam, Notification, and Reporting services
Instructor Service   | Manages mentorship and instructor data; interacts with User, Batch, Course, Notification
Batch Service        | Manages batches, schedules, attendance; interacts with Student, Instructor, Course, Schedule, and Attendance
Course Service       | Manages curriculum and course details; interacts with Content, Student, Instructor
Content Service      | Delivers course media content; interacts with Course, Student, Instructor
Exam Service         | Manages assessments and exam results; interacts with Student, Report, Notification
Notification Service | Receives events from all services to send alerts and notifications
Payment Service      | Handles payments and receipts; interacts with User and Notification
Reporting Service    | Provides analytics and reports; aggregates data from Student, Exam, Payment, Batch
Admin Service        | Centralized monitoring and management; interacts with all services
Config Server & Eureka Discovery | Provides centralized configuration and service registry for all microservices

---

## Service Interaction Flow Examples

1. Signup Flow:  
   Gateway Service -> Auth Service -> User Service -> Notification Service

2. Student Enrollment:  
   Admin Panel -> Student Service -> Batch Service -> Course Service -> Instructor Service

3. Class Scheduling:  
   Batch Service -> Schedule Service -> Notification Service -> Attendance Service

4. Exam Submission:  
   Student Service -> Exam Service -> Notification Service -> Reporting Service

5. Payment Confirmation:  
   Student -> Payment Service -> Notification Service

6. Report Generation:  
   Reporting Service -> Student Service, Exam Service, Batch Service, Payment Service

---

## RabbitMQ Event-Driven Flows

- Auth Service: OTP generated -> Notification Service sends OTP alert  
- Batch Service: Class scheduled -> Notification Service sends scheduling alert  
- Exam Service: Results published -> Notification and Reporting Services update students and analytics  
- Payment Service: Payment successful -> Notification and Reporting Services update users and reports  
- Student: Feedback submitted -> Notification Service alerts relevant parties  

---

## Post-Login User Flows

### Student
- View profile, enrollments, schedule, and content  
- Take quizzes, mark attendance, receive notifications  
- View payments, performance reports, download certificates  

### Admin
- Manage users, courses, batches, schedules  
- Track reports, audit logs, send mass announcements  

### Instructor
- Manage assigned batches and schedule classes  
- Upload content, evaluate student performance  
- Submit feedback and track attendance  

---

## Role-Based Access Control (RBAC) Summary

- **Student:** Access own data and enrolled content only  
- **Instructor:** Access assigned batches and teaching materials  
- **Admin:** Full access to all services and administrative controls  

---

## Post-Login Interaction Flow Summary

- **Student:** Login -> View Profile -> Courses -> Attend Class -> Quiz -> Certificate  
- **Admin:** Login -> Create Batch -> Assign Mentor -> Schedule Classes -> Monitor Activities  
- **Instructor:** Login -> Manage Batch -> Schedule Class -> Upload Content -> Track Students

