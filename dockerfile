# Stage 1: Build con Gradle
FROM gradle:8.10-jdk17 AS build
WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Construimos el JAR (o WAR, según tu proyecto)
RUN gradle clean bootJar --no-daemon

# Stage 2: Run con OpenJDK
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el jar generado desde el build
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
