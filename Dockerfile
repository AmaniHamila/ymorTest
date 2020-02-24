FROM openjdk:11.0.6-jdk
ADD target/spring-boot-ymortest.jar spring-boot-ymortest.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","spring-boot-ymortest.jar"]
