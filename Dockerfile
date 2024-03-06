FROM openjdk:17

WORKDIR /app

COPY target/rinha-backend.jar /app/rinha-backend.jar

CMD ["java", "-jar", "rinha-backend.jar"]