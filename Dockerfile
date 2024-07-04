FROM openjdk:21
EXPOSE 8080
ADD target/test_ci_cd.jar jpa_specification.jar
ENTRYPOINT ["java","-jar","/jpa_specification.jar"]