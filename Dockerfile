# Use OpenJDK 21 base image
FROM openjdk:21-jdk-slim

# Set application metadata via build arguments
# ARG PROJECT_NAME=task-list
# ARG VERSION=1.0.0

# Set working directory
WORKDIR /app

# Copy JAR file built by Maven
COPY target/*.jar app.jar

# Expose port 8085
EXPOSE 8085

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
