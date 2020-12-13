FROM openjdk:8-jdk-alpine

WORKDIR /code
COPY . .

RUN apk add maven
RUN mvn clean install
WORKDIR /code/justice-web
CMD [ "mvn", "spring-boot:run" ]
