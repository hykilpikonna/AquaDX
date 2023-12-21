# Use a multi-stage build to keep the image size small
# Start with a Gradle image for building the project
FROM gradle:jdk11 as builder

# Copy the Gradle wrapper and configuration files separately to leverage Docker cache
COPY --chown=gradle:gradle gradlew /home/gradle/
COPY --chown=gradle:gradle gradle /home/gradle/gradle
COPY --chown=gradle:gradle build.gradle.kts settings.gradle.kts /home/gradle/

# Set working directory
WORKDIR /home/gradle

# Download dependencies - cached if build.gradle.kts and settings.gradle.kts are unchanged
RUN ./gradlew dependencies

# Copy the project source, this layer is rebuilt whenever a file has changed
COPY --chown=gradle:gradle src /home/gradle/src

# Build the application
RUN ./gradlew build -x test

# Start with a fresh image for the runtime
FROM openjdk:11-jre-slim

# Set the deployment directory
WORKDIR /app

# Copy only the built JAR from the builder image
COPY --from=builder /home/gradle/build/libs/aqua-?.?.??.jar /app/

# The command to run the application
CMD java -jar aqua-*.jar
