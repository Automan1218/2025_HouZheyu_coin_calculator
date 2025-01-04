
FROM openjdk:17-jdk-slim


WORKDIR /app


COPY target/coin_calculator-1.0-SNAPSHOT.jar /app/coin_calculator.jar


COPY config.yml /app/config.yml


EXPOSE 8080


CMD ["java", "-jar", "coin_calculator.jar", "server", "config.yml"]
