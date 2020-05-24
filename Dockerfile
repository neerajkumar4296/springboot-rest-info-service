FROM openjdk:8
ADD target/rest-info-service.jar rest-info-service.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "rest-info-service.jar"]