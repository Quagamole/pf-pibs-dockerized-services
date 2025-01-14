FROM amazoncorretto:21
COPY target/app-person-service-1.0-SNAPSHOT.jar app-person.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=secret", "/app-person.jar"]

# docker build -t app-person .
# docker run --network=host -p 8081:8081 -d app-person