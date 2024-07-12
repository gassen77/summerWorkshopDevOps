FROM openjdk:8-jdk-alpine
EXPOSE 8083
ADD target/devopsTestsUnitairesFacturation-1.0.0.jar devopsTestsUnitairesFacturation-1.0.0.jar
ENTRYPOINT ["java","-jar","/devopsTestsUnitairesFacturation-1.0.0.jar"]
