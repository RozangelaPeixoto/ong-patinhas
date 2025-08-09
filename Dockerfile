## Etapa 1: Build
#FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#RUN mvn clean package -DskipTests -X
#
#
## Etapa 2: Execução
#FROM eclipse-temurin:21-alpine
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "app.jar"]
#

FROM openjdk:21
VOLUME /tmp
COPY target/OngPatinhas-0.0.1-SNAPSHOT.jar /OngPatinhas-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/OngPatinhas-0.0.1-SNAPSHOT.jar"]


