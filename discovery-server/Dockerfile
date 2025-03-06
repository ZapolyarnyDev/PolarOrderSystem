FROM gradle:8.8-jdk21 AS buildsystem
WORKDIR /order-app-discovery
COPY . .
RUN gradle bootJar --no-daemon -x test


FROM eclipse-temurin:21-jdk
WORKDIR /order-app-discovery
COPY --from=buildsystem /order-app-discovery/build/libs/discovery-server-1.0.jar discovery-server.jar
ENTRYPOINT [ "java", "-jar", "discovery-server.jar"]