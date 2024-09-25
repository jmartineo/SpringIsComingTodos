# Use a base image with Java and Maven installed
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download the dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight base image with Java installed
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/springtodos-0.0.1-SNAPSHOT.jar .

# Expose the port that the Spring Boot app listens on
EXPOSE 8080

# Run the Spring Boot app
CMD ["java", "-jar", "springtodos-0.0.1-SNAPSHOT.jar"]