FROM openjdk:11
ADD target/arm-service.jar arm-service.jar
EXPOSE 8301
ENTRYPOINT ["java","-jar","arm-service.jar"]