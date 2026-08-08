# Loan Application System

A RESTful **Loan Application Management System** developed using Java, Jakarta REST, JPA/Hibernate, PostgreSQL, and Maven.

The system is designed to manage customers, users, loan applications, loans, payments, and application reviews through RESTful APIs.

---

## 🚀 Project Overview

The Loan Application System provides a backend REST API for managing the complete loan application process.

The application follows a layered architecture:

```text
Client / Postman
       ↓
Resource Layer
       ↓
Service Layer
       ↓
DAO Layer
       ↓
JPA / Hibernate
       ↓
PostgreSQL Database
```

The project was developed to practice and demonstrate:

* Java backend development
* RESTful API development
* Jakarta REST
* JPA / Hibernate
* DAO and Service Layer architecture
* Object-relational mapping
* PostgreSQL database integration
* Maven project management
* API testing using Postman

---

## 🛠️ Technologies Used

| Technology   | Purpose                           |
| ------------ | --------------------------------- |
| Java         | Backend programming               |
| Jakarta REST | RESTful API development           |
| JPA          | Persistence and ORM               |
| Hibernate    | JPA implementation                |
| PostgreSQL   | Relational database               |
| Maven        | Dependency and project management |
| WildFly      | Application server                |
| Postman      | REST API testing                  |

---

## 🏗️ Architecture

The application uses a layered architecture.

### Resource Layer

The Resource layer exposes REST endpoints and handles HTTP requests and responses.

Example:

```text
POST   /customers
GET    /customers/{id}
PUT    /customers/{id}
DELETE /customers/{id}
GET    /customers
```

### Service Layer

The Service layer contains the application's business logic and acts as a bridge between the Resource and DAO layers.

```text
Resource → Service
```

### DAO Layer

The Data Access Object (DAO) layer is responsible for communicating with the database through JPA's `EntityManager`.

```text
Service → DAO → EntityManager → Database
```

### Entity Layer

The Entity classes represent database tables and their relationships using JPA annotations.

---

## 🗂️ Main Entities

The project contains the following main entities:

### Customer

Stores customer information such as:

* Customer ID
* First name
* Last name
* NIC
* Email
* Phone
* Address
* Created date/time

### Users

Stores system user information and maintains a relationship with a customer.

Main attributes include:

* User ID
* Username
* Password
* Role
* Customer

### LoanApplication

Represents a customer's loan application.

Main attributes include:

* Application ID
* Loan type
* Requested amount
* Purpose
* Status
* Application date
* Customer

### Loan

Represents an approved/created loan associated with a loan application.

Main attributes include:

* Loan ID
* Loan application
* Amount
* Interest rate
* Duration
* Start date
* End date
* Status

### Payment

Stores payments made against loans.

Main attributes include:

* Payment ID
* Loan
* Payment date
* Amount
* Payment status

### ApplicationReview

Stores the review information associated with a loan application.

Main attributes include:

* Review ID
* Loan application
* Staff
* Decision
* Comments
* Review date

---

## 🔗 Entity Relationships

The main relationships between the entities are:

```text
Customer
   │
   ├────────── Users
   │
   └────────── LoanApplication
                    │
                    ├────────── ApplicationReview
                    │
                    └────────── Loan
                                  │
                                  └────────── Payment
```

JPA relationships such as `@ManyToOne` and `@OneToOne` are used to represent these relationships.

---

## 🌐 REST API

The application exposes RESTful endpoints for the main resources.

The Resource layer includes APIs for managing:

* Customers
* Users
* Loan Applications
* Loans
* Payments
* Application Reviews

The APIs support operations such as:

```text
GET
POST
PUT
DELETE
```

Requests and responses were tested using **Postman**.

---

## 🧪 API Testing

All implemented Resource-layer API functions were tested using Postman.

Testing included operations such as:

* Creating records
* Retrieving records by ID
* Retrieving records
* Updating records
* Deleting records
* Testing JSON request bodies
* Testing relationships between entities
* Identifying and fixing API/database errors

---

## 🗄️ Database

The project uses **PostgreSQL** as the relational database.

JPA/Hibernate is used to map Java entities to database tables.

The application uses:

```text
Java Entity
     ↓
JPA
     ↓
Hibernate
     ↓
JDBC
     ↓
PostgreSQL
```

Database relationships are represented using foreign keys and JPA relationship annotations.

---

## 📦 Maven

The project uses Maven for dependency management and project configuration.

The main dependencies include technologies for:

* Jakarta Persistence
* Hibernate ORM
* PostgreSQL JDBC connectivity
* Jakarta REST

The project's dependencies and configuration are maintained in:

```text
pom.xml
```

---

## ⚙️ Configuration

The persistence configuration is defined using:

```text
persistence.xml
```

The application is configured to connect to PostgreSQL through JPA/Hibernate.

> **Note:** Database credentials should be configured locally and should not be committed to the repository.

---

## ▶️ How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/Loan-Application-System.git
```

### 2. Open the project

Import the project into an IDE such as Eclipse as a Maven project.

### 3. Configure PostgreSQL

Create the required PostgreSQL database and configure the database connection in the persistence configuration.

### 4. Build the project

Run:

```bash
mvn clean install
```

### 5. Deploy to WildFly

Deploy the generated WAR file to the WildFly application server.

### 6. Test the REST APIs

Use Postman to send requests to the application's REST endpoints.

---

## 📁 Project Structure

```text
LoanApplication/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── loan/
│       │           │
│       │           ├── config/
│       │           │   └── ...
│       │           │
│       │           ├── dao/
│       │           │   ├── CustomerDAO.java
│       │           │   ├── LoanDAO.java
│       │           │   ├── UsersDAO.java
│       │           │   └── ...
│       │           │
│       │           ├── dto/
│       │           │   └── ...
│       │           │
│       │           ├── entity/
│       │           │   ├── Customer.java
│       │           │   ├── Users.java
│       │           │   ├── LoanApplication.java
│       │           │   └── ...
│       │           │
│       │           ├── service/
│       │           │   ├── CustomerService.java
│       │           │   ├── LoanService.java
│       │           │   └── ...
│       │           │
│       │           └── resource/
│       │               ├── CustomerResource.java
│       │               ├── LoanResource.java
│       │               └── ...
│       │
│       └── resources/
│           └── META-INF/
│               └── persistence.xml
│
├── database/
│   └── loanApplication_DB.sql
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## 🎯 Learning Objectives

This project was developed to gain practical experience with:

* Java backend development
* REST API development
* Jakarta EE technologies
* JPA and Hibernate
* PostgreSQL
* Object-relational mapping
* Entity relationships
* DAO pattern
* Service layer architecture
* REST Resource layer
* Maven
* Application server deployment
* API testing with Postman
* Debugging database and API-related issues

---

## 🔮 Future Improvements

Possible future improvements include:

* Authentication and authorization
* Role-based access control
* Input validation
* Exception handling and standardized API responses
* Improved API documentation
* Automated unit and integration testing
* Frontend integration
* Loan approval workflow
* Improved security for credentials and sensitive information

---

## 👨‍💻 Author

**Shamal**

This project was developed as a practical Java backend project to strengthen knowledge of REST APIs, Jakarta EE, JPA/Hibernate, PostgreSQL, and layered application architecture.
