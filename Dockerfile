FROM openjdk:8-jdk-alpine

LABEL Jether Rodrigues<jetherrodrigues@gmail.com>

VOLUME /tmp

ARG JAR_FILE
COPY ${JAR_FILE} business-api.jar

RUN apk update \
  && apk add --no-cache --update curl \
  && rm -rf /var/cache/apk/*

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/business-api.jar"]