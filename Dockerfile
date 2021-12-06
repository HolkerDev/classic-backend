FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} classic.jar
ENTRYPOINT ["java","-jar","/classic.jar"]