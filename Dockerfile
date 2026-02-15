# Multi-stage build for Spring Boot application
# Stage 1: Use Eclipse Temurin JRE 21 on Alpine Linux for minimal image size
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside container
WORKDIR /app

# Copy the built JAR file from target directory
# Ensure you run 'mvn clean package' before building this image
COPY target/spring-demo.jar app.jar

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
