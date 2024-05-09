# Spring Boot Microservice with PostgreSQL Database

This is a sample RESTful microservice built using Spring Boot that interacts with a PostgreSQL database. The microservice exposes endpoints for inserting and updating entities, implements optimistic locking, handles exceptions gracefully, and it is packaged as a Docker image for easy deployment.

## Table of Contents

- [ ] [Features]()
- [ ] [Technologies Used]()
- [ ] [Prerequisites]()
- [ ] [Running the Application]()
- [ ] [Unit Tests]()
- [ ] [Integration Tests]()
- [ ] [Dockerization]()
- [ ] [Contributing]()

## Features

- [ ] Insert and update main entities
- [ ] Implement one-to-many relationship between parent and child entities
- [ ] Optimistic locking mechanism to handle concurrent updates
- [ ] Proper exception handling for graceful error responses
- [ ] Packaged as a Docker image for easy deployment


## Technologies Used

- [ ] Spring Boot
- [ ] PostgreSQL
- [ ] Docker
- [ ] Maven


## Prerequisites

- [ ] Java 17 or higher
- [ ] Docker installed on your machine
- [ ] PostgreSQL database (running locally or accessible via network)


## Running the Application

- [ ] Clone the repository: git clone https://github.com/alexandruhoza/spring-boot-microservice.git
- [ ] Navigate to the project directory: cd spring-boot-microservice
- [ ] Build the project using Maven: mvn clean install
- [ ] Run the Docker container: 1. docker build -t microservice .
                                2. docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=dev" microservice
- [ ] The microservice should now be running and accessible at http://localhost:8080.


## Unit Tests

- [ ] Unit tests for the service classes can be run using Maven: mvn test


## Integration Tests

- [ ] Integration tests for the endpoints can be run using Maven: mvn verify


## Dockerization

- [ ] The microservice is Dockerized and can be built and run as a Docker container as described in the "Running the Application" section.


## Contributing

- [ ] Contributions are welcome! Please feel free to open an issue or submit a pull request for any enhancements or bug fixes.