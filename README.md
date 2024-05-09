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
- [ ] [Endpoints]()
- [ ] [Endpoint Details]()

## Features

- [ ] Insert and update main entities
- [ ] Implement one-to-many relationship between parent and child entities
- [ ] Optimistic locking mechanism to handle concurrent updates
- [ ] Proper exception handling for graceful error responses
- [ ] Packaged as a Docker image for easy deployment
- [ ] When clients request details of a parent entity (GET /parents/<parentId>), the microservice first checks the Hazelcast cache. If the requested entity is found in the cache, it is returned directly without querying the database. This helps to minimize latency and decrease the number of database calls.
- [ ] The microservice uses InMemoryUserDetailsManager as a temporary alternative to OAuth or JWT for authentication. This allows users to authenticate with the microservice using simple username and password authentication.


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


## Endpoints

The microservice exposes the following endpoints:
- [ ] POST /parents
- [ ] PUT /parents
- [ ] GET /parents/<parentId>


## Endpoint Details

- [ ] POST /parents
    - [ ] Description: This endpoint allows clients to create a new parent entity.
    - [ ] Request Body: JSON representation of the parent entity object.
    - [ ] Response: The created parent entity with its unique identifier.

- [ ] PUT /parents
    - [ ] Description: This endpoint allows clients to update an existing parent entity.
    - [ ] Request Body: JSON representation of the updated parent entity object.
    - [ ] Response: The updated parent entity.
    
- [ ] GET /parents/<parentId>
    - [ ] Description: This endpoint allows clients to retrieve details of a parent entity by its unique identifier.
    - [ ] Path Parameter: {parentId} - The unique identifier of the parent entity.
    - [ ] Response: JSON representation of the parent entity object.


## Endpoint Example for POST /parents:

curl --location --request POST 'http://localhost:8080/parents' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=EE7AB8FE74A6D867C7D4C9A865BB7959' \
--data-raw '{
    "id": null,
    "version": null,
    "name": "name",
    "description": "desc",
    "children": [
            {
                "id": null,
                "version": null,
                "comment": "comment on first child",
                "rating": 3
                },
                {
                "id": null,
                "version": null,
                "comment": "comment on second one",
                "rating": 5
            }
        ]
}'

## Endpoint Example for GET /parents/<parentId>:

curl --location --request GET 'http://localhost:8080/parents/1' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--header 'Cookie: JSESSIONID=EE7AB8FE74A6D867C7D4C9A865BB7959'