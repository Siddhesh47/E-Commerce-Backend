# Spring Boot Product Management API

A backend REST API developed using **Java Spring Boot** for managing users and products, with **Spring Security** for authentication and authorization.

## 🚀 Features

- User management
- Product management
- RESTful API
- Spring Security integration
- Authentication and authorization
- Custom `UserDetailsService`
- JPA/Hibernate database integration
- Repository-based database operations
- Layered application architecture
- Maven project management

## 🛠️ Technologies Used

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Security**
- **Spring Data JPA**
- **Hibernate**
- **Maven**
- **SQL Database**

## 📂 Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/example/demo/
│   │       ├── Config/
│   │       │   └── SecurityConfig.java
│   │       │
│   │       ├── controller/
│   │       │   ├── AdminController.java
│   │       │   ├── TestController.java
│   │       │   └── UserController.java
│   │       │
│   │       ├── entity/
│   │       │   ├── Product.java
│   │       │   └── User.java
│   │       │
│   │       ├── repo/
│   │       │   ├── Productrepo.java
│   │       │   └── UserRepo.java
│   │       │
│   │       ├── service/
│   │       │   └── CustomUserDetailsService.java
│   │       │
│   │       └── DemoApplication.java
│   │
│   └── resources/
│       └── application.properties
│
└── test/
    └── java/
        └── com/example/demo/
            └── DemoApplicationTests.java
```

## 🔐 Security

The project uses **Spring Security** to protect application resources.

Security configuration is implemented in:

```text
SecurityConfig.java
```

A custom user authentication service is implemented using:

```text
CustomUserDetailsService.java
```

This service retrieves user information from the database during authentication.

## 👤 User Management

The `User` entity represents users stored in the database.

User-related operations are handled using:

```text
UserController
UserRepo
CustomUserDetailsService
```

The application can be extended to support user registration, login, authentication and role-based authorization.

## 📦 Product Management

The `Product` entity represents products stored in the database.

Product database operations are handled through:

```text
Productrepo
```

The application provides a foundation for implementing product CRUD operations such as:

- Create Product
- Get Products
- Update Product
- Delete Product

## 🏗️ Application Architecture

The project follows a layered architecture:

```text
        Client
          │
          ▼
     Controller
          │
          ▼
       Service
          │
          ▼
     Repository
          │
          ▼
      Database
```

### Controller Layer

Handles HTTP requests and returns responses to the client.

### Service Layer

Contains application and business logic.

### Repository Layer

Handles communication with the database using Spring Data JPA.

### Entity Layer

Contains Java classes representing database entities.

### Security Layer

Handles authentication and authorization using Spring Security.

## ⚙️ Configuration

Database configuration can be provided in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> Replace the database URL, username and password with your own configuration.

**Do not upload passwords, API keys or other sensitive credentials to GitHub.**

## 📋 Prerequisites

Before running the project, make sure you have:

- Java JDK installed
- Maven installed or use the included Maven Wrapper
- SQL database installed and running
- Git installed

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### 2. Open the project

```bash
cd demo
```

### 3. Configure the database

Open:

```text
src/main/resources/application.properties
```

and enter your database configuration.

### 4. Run the application

#### Windows

```bash
mvnw.cmd spring-boot:run
```

#### Linux / macOS

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed:

```bash
mvn spring-boot:run
```

The application will normally be available at:

```text
http://localhost:8080
```

## 🧪 Testing

Run the tests using:

```bash
mvn test
```

On Windows:

```bash
mvnw.cmd test
```

## 🔌 API Testing

The REST APIs can be tested using:

- Postman
- Insomnia
- Swagger/OpenAPI (if configured)
- Frontend applications

## 🔮 Future Improvements

- JWT authentication
- Password encryption using BCrypt
- Role-based authorization
- Global exception handling
- DTO-based request and response
- Input validation
- Swagger/OpenAPI documentation
- Pagination and sorting
- Product search and filtering
- Unit and integration testing
- Docker support
- Frontend integration

## 🤝 Contributing

Contributions are welcome.

1. Fork the repository
2. Create a new branch

```bash
git checkout -b feature/new-feature
```

3. Make your changes
4. Commit your changes

```bash
git commit -m "Add new feature"
```

5. Push your branch

```bash
git push origin feature/new-feature
```

6. Create a Pull Request

## 📄 License

This project is developed for **educational and learning purposes**.

## 👨‍💻 Author

**Siddhesh Patre**

⭐ If you found this project useful, consider giving the repository a star!
