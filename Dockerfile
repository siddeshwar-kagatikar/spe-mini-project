FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/scientific-calculator-1.0.0-shaded.jar app.jar
CMD ["java", "-jar", "app.jar"]
