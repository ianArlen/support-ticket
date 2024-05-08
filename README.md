# Orchestrator Microservice

The Orchestrator microservice is responsible for handling user requests, validating data, and managing exceptions in the ticket support system. It interacts with the Domain microservice to store ticket information in a database.

## Key Features:
- Receives and validates ticket creation requests.
- Manages exceptions for missing or incorrect data.
- Connects to the Domain microservice to store ticket information.

## Technologies:
- Java 17
- Spring Boot
- Maven

## Endpoints:
- POST `/tickets`: Creates a new ticket with validation and exception handling.
- POST `/users`: Handles user creation requests.

## How to Run:
1. Clone the repository.
2. Navigate to the `orchestrator` directory.
3. Run the following command to build and run the microservice:
4. Put instructions: mvn clean install
5. Run: mvn spring-boot:run


---

# Domain Microservice

The Domain microservice interacts with the database to store and manage ticket and user information in the ticket support system.

## Key Features:
- Manages ticket and user data in the database.
- Provides endpoints for CRUD operations on tickets and users.

## Technologies:
- Java
- Spring Boot
- MongoDB

## Endpoints:
- GET `/tickets`: Retrieves all tickets.
- GET `/tickets/{id}`: Retrieves a ticket by ID.
- POST `/tickets`: Creates a new ticket.
- PUT `/tickets/{id}`: Updates an existing ticket.
- DELETE `/tickets/{id}`: Deletes a ticket.

## How to Run:
1. Clone the repository.
2. Navigate to the `domain` directory.
3. Run the following command to build and run the microservice:
4. Put instructions: mvn clean install
5. Run: mvn spring-boot:run


---

# Support View Component

The Support View component is a React application that allows users to interact with the ticket support system by registering users and submitting tickets.

## Key Features:
- User registration form.
- Ticket submission form.
- Error handling and success messages.

## Technologies:
- React
- Material-UI
- React Hook Form
- Axios

## Components:
- `User.js`: Handles user registration.
- `Ticket.js`: Handles ticket submission.

## How to Run:
1. Clone the repository.
2. Navigate to the `support-view` directory.
3. Install dependencies by running: npm install
4. Start the development server with the following command: npm start
5. Access the application at `http://localhost:3000`
**Note:** Make sure you have Node.js version 18 installed to run the Support View Component.
