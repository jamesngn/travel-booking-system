# Stage 1: Build the application
FROM maven:3.9-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml /app

RUN mvn verify --fail-never

COPY . /app

RUN mvn package -Dmaven.test.skip

# Stage 2: Create the final image
FROM tomcat:9.0-jdk11-corretto

COPY --from=build /app/target/TravelBookingSystem.war /usr/local/tomcat/webapps/TravelBookingSystem.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
