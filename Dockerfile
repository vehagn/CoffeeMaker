FROM java:8
FROM maven:3.6-jdk-8

WORKDIR /app
COPY . /app

RUN mvn clean install

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/CoffeeMaker-1.0-SNAPSHOT.jar", "server", "config.yml"]
