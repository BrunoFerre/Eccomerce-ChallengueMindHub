FROM gradle:8.0.2-jdk11-alpine

COPY . .

EXPOSE 8080

RUN gradle build

ENTRYPOINT ["java", "-jar", "build/libs/Matoffe-E-commerce-0.0.1-SNAPSHOT.jar"]