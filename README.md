# Expense Management System

## Table of Contents
1. [Overview](#overview)
2. [Backend Setup](#backend-setup)
   - [Technologies Used](#technologies-used)
   - [Installation](#installation)
   - [Environment Variables](#environment-variables)
   - [Running the Application](#running-the-application)
   - [API Endpoints](#api-endpoints)
3. [Frontend Setup](#frontend-setup)
   - [Technologies Used](#frontend-technologies-used)
   - [Installation](#frontend-installation)
   - [Running the Application](#running-the-application)

---

## Overview
The Expense Management System is a web application designed to track and manage employee expenses efficiently. It consists of a **Spring Boot backend** and a **React frontend**.

---

## Backend Setup

### Technologies Used
- Java 17
- Spring Boot 3
- Spring Security
- JPA/Hibernate
- PostgreSQL/MySQL
- JWT Authentication
- Maven

### Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-repo/expense-management.git
   cd expense-management/backend
   ```
2. **Configure Database:**
   - Update `application.properties` or `application.yml`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/expense_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     ```
3. **Build and run:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Environment Variables
Create a `.env` file with:
```properties
JWT_SECRET=your_secret_key
DB_USERNAME=root
DB_PASSWORD=yourpassword
```

### Running the Application
- **Start the backend:**
  ```bash
  mvn spring-boot:run
  ```
- **Access API Docs (if enabled):**
  - `http://localhost:8080/swagger-ui.html`

### API Endpoints
| Method | Endpoint          | Description          |
|--------|------------------|----------------------|
| POST   | `/login`         | User authentication |
| GET    | `/employee/{id}` | Get employee details |
| POST   | `/expense/add`   | Add an expense      |
| GET    | `/expense/all`   | Get all expenses    |

---

## Frontend Setup

### Frontend Technologies Used
- React 18
- Vite
- Tailwind CSS
- React Router
- Axios

### Installation
1. **Navigate to frontend directory:**
   ```bash
   cd ../frontend
   ```
2. **Install dependencies:**
   ```bash
   npm install
   ```
3. **Set up environment variables:**
   - Create a `.env` file:
     ```
     VITE_API_URL=http://localhost:8080
     ```

### Running the Application
- **Start the frontend:**
  ```bash
  npm run dev
  ```
- **Access the app:**
  - `http://localhost:5173`

---

## Contribution
1. Fork the repository
2. Create a feature branch
3. Commit changes
4. Open a Pull Request


---

## Contact
For any issues, contact `tmganthan@gmail.com`

