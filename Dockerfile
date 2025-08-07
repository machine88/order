FROM ubuntu:latest
LABEL authors="martinmmorales"

# Stage 1: Build the application
FROM gradle:8-jdk21 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle clean bootjar --no-daemon

# Stage 2: Run the applicaiton
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "app.jar"]
