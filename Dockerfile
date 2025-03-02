FROM eclipse-temurin:23-jdk AS build

WORKDIR /app

COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

RUN ./mvnw dependency:resolve

COPY src ./src

RUN ./mvnw package

FROM eclipse-temurin:23-jre

COPY --from=build /app/target/*.jar app.jar

EXPOSE 6969

CMD ["java", "-jar", "app.jar"]
