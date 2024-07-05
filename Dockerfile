FROM openjdk:21
EXPOSE 8080
ADD target/jpa_specification.jar jpa_specification.jar
ENTRYPOINT ["java","-jar","/jpa_specification.jar"]