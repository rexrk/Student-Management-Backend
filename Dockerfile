FROM maven:3.9.6-amazoncorretto-21-al2023 AS build
WORKDIR /home/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:24-jdk-slim
EXPOSE 8080
COPY --from=build /home/app/target/*.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]