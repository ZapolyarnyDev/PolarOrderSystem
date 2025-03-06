FROM gradle:8.8-jdk21 AS buildsystem
WORKDIR /order-app-userservice
COPY . .
RUN gradle bootJar --no-daemon -x test


FROM eclipse-temurin:21-jdk
WORKDIR /order-app-userservice
COPY --from=buildsystem /order-app-userservice/build/libs/user-service-1.0.jar user-service.jar
ENTRYPOINT [ "java", "-jar", "user-service.jar"]