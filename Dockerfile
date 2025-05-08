# Use an official OpenJDK image with the correct version
FROM openjdk:21-jdk-slim

# Copy your application JAR into the container
COPY target/chatterbox-user-service-0.0.1-SNAPSHOT.jar /app/chatterbox-user-service-0.0.1-SNAPSHOT.jar

# Expose the port your app runs on
EXPOSE 8081

# Command to run your app
CMD ["java", "-jar", "/app/chatterbox-user-service-0.0.1-SNAPSHOT.jar"]
