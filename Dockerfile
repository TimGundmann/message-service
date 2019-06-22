FROM openjdk:10.0.2-13-jre-sid

COPY target/plan-service.jar /app/plan-service.jar

CMD ["java", "-jar", "/app/plan-service.jar", "--spring.profiles.active=prod"]
