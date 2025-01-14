FROM amazoncorretto:21
COPY target/app-account-service-1.0-SNAPSHOT.jar app-account.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=secret", "/app-account.jar"]

# docker build -t app-account .
# docker run --network=host -p 8082:8082  app-accout