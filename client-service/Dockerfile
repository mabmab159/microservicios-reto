FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
RUN apk add curl
ENTRYPOINT ["java","-jar","/app.jar"]