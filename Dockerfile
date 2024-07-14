FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/devopsTestsUnitairesFacturation-1.0.0.jar devopsTestsUnitairesFacturation-1.0.0.jar
ENTRYPOINT ["java","-jar","/devopsTestsUnitairesFacturation-1.0.0.jar"]
