# Use OpenJDK 11 as the base image for building
FROM openjdk:11-jdk as BUILD

# Copy the entire project directory into the container
COPY . /src

# Set the working directory to /src
WORKDIR /src

# Build your Spring Boot application using Maven
RUN ./mvnw clean package

# Switch to a smaller OpenJDK 11 image for running the application
FROM openjdk:11-jre

# Copy the JAR file from the build stage to /bin/runner
COPY --from=BUILD /src/target/challenge-0.0.1-SNAPSHOT.jar /bin/runner/run.jar

# Set the working directory to /bin/runner
WORKDIR /bin/runner

# Command to run your Spring Boot application
CMD ["java", "-jar", "run.jar"]