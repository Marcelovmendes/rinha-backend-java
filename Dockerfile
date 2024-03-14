FROM openjdk:17

WORKDIR /app

COPY target/api-0.0.1-SNAPSHOT.jar /app/rinha-backend.jar

CMD ["java", "-jar", "rinha-backend.jar"]