FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /build/libs/fitness-viking-1.0.0.jar fitnessviking.jar
ENTRYPOINT ["java", "-jar", "fitnessviking.jar"]
