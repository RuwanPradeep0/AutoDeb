# Step 1: Build the application using Maven or Gradle
# Use an official Maven/Gradle image as a build stage (Maven example)
FROM maven:3.8.4-jdk-11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven/Gradle project files
COPY ./pom.xml ./
COPY ./src ./src

# Package the application
RUN mvn clean package -DskipTests

# Step 2: Use the official OpenJDK image for the runtime
FROM openjdk:11-jre-slim

# Set the working directory for the app
WORKDIR /app

# Copy the packaged JAR file from the build stage
COPY --from=build /app/target/your-app-name.jar /app/your-app-name.jar

# Expose the port on which the Spring Boot app runs
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "/app/your-app-name.jar"]
