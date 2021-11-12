FROM openjdk:8-jdk-alpine

LABEL Jether Rodrigues<jetherrodrigues@gmail.com>
LABEL Andre Carvalho<andre.carvalho@inpe.br>

RUN apk add --no-cache curl

VOLUME /tmp

ARG JAR_FILE
COPY ${JAR_FILE} business-api.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/business-api.jar"]