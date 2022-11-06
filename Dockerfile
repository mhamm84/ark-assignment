FROM gradle:7.5.1-jdk17 AS build-stage

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY build.gradle settings.gradle gradlew $APP_HOME
RUN ./gradlew build || return 0
COPY . .
RUN ./gradlew clean bootJar -x test

# For Java 11,
FROM eclipse-temurin:17.0.4.1_1-jdk-alpine
ENV ARTIFACT_NAME=assignment-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=build-stage $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
ENTRYPOINT ["java","-jar","assignment-0.0.1-SNAPSHOT.jar"]
