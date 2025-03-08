FROM gradle:8.8-jdk21 AS buildsystem
WORKDIR /gateway-server
COPY . .
RUN gradle bootJar --no-daemon -x test


FROM eclipse-temurin:21-jdk
WORKDIR /gateway-server
COPY --from=buildsystem /gateway-server/build/libs/gateway-server-1.0.jar gateway-server.jar
ENTRYPOINT [ "java", "-jar", "gateway-server.jar"]