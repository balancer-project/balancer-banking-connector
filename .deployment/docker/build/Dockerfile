FROM maven:3-openjdk-17 AS build

COPY code /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml clean package

#FROM gcr.io/distroless/java17-debian11
FROM openjdk:17-alpine3.14
WORKDIR /app
COPY --from=build /usr/src/app/boot/target/*.jar ./application.jar
COPY .deployment/docker/app-build/scripts/entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

ENTRYPOINT ["sh","/app/entrypoint.sh"]

CMD ["application.jar"]
