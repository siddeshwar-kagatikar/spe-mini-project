# Use a lightweight JDK base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the packaged JAR from Maven target
COPY target/scientific-calculator-1.0.0-shaded.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
