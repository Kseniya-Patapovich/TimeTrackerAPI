# Time Tracker API

Test task for Krainet

## Description


This project is a time-tracking REST API built with Spring Boot, providing user authentication and authorization using JWT, along with role-based access control for various endpoints. It integrates with Swagger for API documentation.
## Technologies
- Spring Boot
- Spring Security
- JWT
- Hibernate
- Flyway
- docker-compose
- Swagger


## Prerequisites
- Postgres
- Maven

## Getting started
- ```git clone https://github.com/Kseniya-Patapovich/TimeTrackerAPI```
- ```docker-compose up --build -d```

## API Documentation
This project uses Swagger for API documentation. After running the application, you can access the documentation at: ```http://localhost:8080/swagger-ui/index.html```

## Security Configuration
The project uses Spring Security with JWT for user authentication and authorization. Below are the details of the security configuration:

- JWT Authentication: The API uses JWT tokens for secure access. You need to obtain a token by logging in through the ```/auth/login``` endpoint.
  
- Role-Based Access Control:
  - Admins have access to user management and can create, update, delete tasks.
  - Users can view tasks, records, and manage their records.
    
- Endpoints Security:
  
  - ```POST /auth/login``` - Public, used to authenticate and get a JWT token.
  - ```GET /task```, ```GET /task/{id}``` - Accessible to both USER and ADMIN.
  - ```POST /task```, ```PUT /task/{id}```, ```DELETE /task/{id}``` - Accessible only to ADMIN.
  - ```PUT /record/{id}```, ```POST /record``` - Accessible to USER.
  - ```GET /record```, ```GET /record/{id}```, ```DELETE /record/{id}``` - Accessible to both USER and ADMIN.
  - All other endpoints are protected and require appropriate permissions.
    
- White-listed Endpoints
   
  The following endpoints are not secured and do not require authentication:
   - ```/api/v1/auth/**```
   - ```/swagger-ui/**```
   - ```/v2/api-docs```, ```/v3/api-docs```, ```/swagger-resources```, etc.

