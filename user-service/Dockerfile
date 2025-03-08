FROM gradle:8.8-jdk21 AS buildsystem
WORKDIR /user-service
COPY . .
RUN gradle bootJar --no-daemon -x test


FROM eclipse-temurin:21-jdk
WORKDIR /user-service
COPY --from=buildsystem /user-service/build/libs/user-service-1.0.jar user-service.jar
ENTRYPOINT [ "java", "-jar", "user-service.jar"]