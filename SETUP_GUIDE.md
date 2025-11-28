# Interview Scheduler System - Complete Setup Guide

## ✅ What Was Done

### 1. **Fixed Build Issues**
- Removed Lombok dependency (was causing compilation errors)
- Downgraded Spring Boot from 3.2.0 to 2.7.18 (compatible with Java 11)
- Changed from `jakarta.*` to `javax.*` imports
- Added manual getters/setters to all model classes

### 2. **Database Configuration**
- Switched from MySQL to **H2 in-memory database** (no installation required!)
- Database URL: `jdbc:h2:mem:interview_db`
- H2 Console available at: http://localhost:8080/h2-console

### 3. **Application Status**
✅ **BUILD SUCCESSFUL**  
✅ **SERVER RUNNING** on http://localhost:8080  
✅ **All tables created** (interviewer, availability, candidate, interview_slot)

---

## 🚀 How to Use

### Running the Application

```bash
.\mvnw.cmd spring-boot:run
```

The application will start on **port 8080**.

### Testing the API

**Option 1: Use the Interactive Web Tester**
- Open `api-tester.html` in your browser
- It's already open! Just click the buttons to test each endpoint

**Option 2: Use curl or Postman**

#### 1. Create an Interviewer
```bash
POST http://localhost:8080/api/interviewers
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "maxInterviewsPerWeek": 5
}
```

#### 2. Set Interviewer Availability
```bash
POST http://localhost:8080/api/interviewers/1/availability
Content-Type: application/json

[
  {
    "dayOfWeek": "MONDAY",
    "startTime": "10:00:00",
    "endTime": "11:00:00"
  },
  {
    "dayOfWeek": "TUESDAY",
    "startTime": "14:00:00",
    "endTime": "15:00:00"
  }
]
```

#### 3. Create a Candidate
```bash
POST http://localhost:8080/api/candidates
Content-Type: application/json

{
  "name": "Jane Smith",
  "email": "jane@example.com"
}
```

#### 4. View Available Slots
```bash
GET http://localhost:8080/api/slots/available
```

#### 5. Book a Slot
```bash
POST http://localhost:8080/api/slots/1/book
Content-Type: application/json

{
  "candidateId": 1
}
```

#### 6. Reschedule a Slot
```bash
PUT http://localhost:8080/api/slots/reschedule
Content-Type: application/json

{
  "oldSlotId": 1,
  "newSlotId": 2,
  "candidateId": 1
}
```

---

## 📊 View Database

Access the H2 Console at: **http://localhost:8080/h2-console**

**Connection Settings:**
- JDBC URL: `jdbc:h2:mem:interview_db`
- Username: `sa`
- Password: (leave empty)

---

## 🎯 System Flow

1. **Interviewer** creates account with max interviews per week
2. **Interviewer** sets weekly availability (e.g., Monday 10-11 AM)
3. **System** automatically generates slots for next 2 weeks
4. **Candidate** views available slots
5. **Candidate** books a slot (system checks interviewer's weekly limit)
6. **Candidate** can reschedule if needed

---

## 📁 Project Structure

```
e:/practice/spring/
├── src/
│   ├── main/
│   │   ├── java/com/example/interviewscheduler/
│   │   │   ├── controller/     # REST API endpoints
│   │   │   ├── model/          # JPA entities
│   │   │   ├── repository/     # Data access layer
│   │   │   ├── service/        # Business logic
│   │   │   └── dto/            # Data transfer objects
│   │   └── resources/
│   │       └── application.properties
├── target/
│   └── interview-scheduler-0.0.1-SNAPSHOT.jar
├── pom.xml
├── api-tester.html            # Interactive API tester
└── README.md
```

---

## 🛠️ Technologies Used

- **Java 11**
- **Spring Boot 2.7.18**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Maven** (via wrapper)

---

## 💡 Tips

- The H2 database is **in-memory**, so data is lost when you stop the application
- To persist data, switch back to MySQL by updating `pom.xml` and `application.properties`
- Use the `api-tester.html` for quick testing without needing Postman
- Check the H2 console to see the database tables and data

---

## ✨ Next Steps

You can now:
1. Test all endpoints using the web interface (api-tester.html)
2. View the database in H2 Console
3. Modify the code to add more features
4. Switch to MySQL for production use

Enjoy your Interview Scheduler System! 🎉
