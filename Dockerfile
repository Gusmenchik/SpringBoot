FROM openjdk:8-jdk-alpine
EXPOSE 9090
ADD target/demo-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java", "-jar", "/myapp.jar"]
