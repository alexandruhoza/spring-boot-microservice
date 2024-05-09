# Use a base image with JDK and Maven
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project file
COPY pom.xml .

# Copy the source code
COPY src ./src

ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE}

# Build the project
RUN mvn clean package -DskipTests

# Use a lightweight JDK image for runtime
FROM khipu/openjdk17-alpine

ARG SPRING_PROFILES_ACTIVE
ENV SPRING_PROFILES_ACTIVE ${SPRING_PROFILES_ACTIVE}

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage to the container
COPY --from=build /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

RUN echo $SPRING_PROFILES_ACTIVE
# Define the command to run the application
#CMD ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar" ]
