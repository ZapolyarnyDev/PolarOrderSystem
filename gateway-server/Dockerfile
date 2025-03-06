FROM gradle:8.8-jdk21 AS buildsystem
WORKDIR /order-app-gateway
COPY . .
RUN gradle bootJar --no-daemon -x test


FROM eclipse-temurin:21-jdk
WORKDIR /order-app-gateway
COPY --from=buildsystem /order-app-gateway/build/libs/gateway-server-1.0.jar gateway-server.jar
ENTRYPOINT [ "java", "-jar", "gateway-server.jar"]