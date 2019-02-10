FROM openjdk:10.0.2-13-jre-sid

COPY target/message-service.jar /app/message-service.jar

CMD ["java", "-jar", "/app/message-service.jar", "--spring.config.location=classpath:application.properties,file:/security/security.properties"]
