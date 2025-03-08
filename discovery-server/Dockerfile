FROM gradle:8.8-jdk21 AS buildsystem
WORKDIR /discovery-server
COPY . .
RUN gradle bootJar --no-daemon -x test


FROM eclipse-temurin:21-jdk
WORKDIR /discovery-server
COPY --from=buildsystem /discovery-server/build/libs/discovery-server-1.0.jar discovery-server.jar
ENTRYPOINT [ "java", "-jar", "discovery-server.jar"]