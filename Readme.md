# Shop API

## Project Description
Shop API is an online store where users can order products. Administrators have access to a management panel, where they can create, edit, or delete products.

## Technologies
This project uses:
- **Java**: version 17
- **Spring Boot**: version 3.3.5
- **Hibernate**: for JPA operations
- **Liquibase**: for database schema management
- **PostgreSQL**: for data storage
- **JWT**: for authentication and authorization
- **Lombok**: to reduce boilerplate code

## System Requirements
- Java 17
- PostgreSQL installed and running

## Setup
1. Clone the repository to your local machine:
   ```bash
   git clone <repository_URL>
   cd shop_api

2. Configure the application.properties file to connect to your database.

    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```
3. Run Liquibase to create the database schema:

    ```bash
    mvn liquibase:update
    ```
   
4. Run the application:

    ```bash
   mvn spring-boot:run
    ```