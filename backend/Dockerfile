FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the project files (including mvnw and mvnw.cmd)
COPY . .

# Make mvnw executable
RUN chmod +x ./mvnw

# Install dependencies using Maven Wrapper
RUN ./mvnw dependency:resolve

# Package the application
RUN ./mvnw package

# Expose port (if necessary)
EXPOSE 8080

# Run the application
CMD ["./mvnw", "spring-boot:run"]
