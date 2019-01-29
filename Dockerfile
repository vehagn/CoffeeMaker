FROM maven:slim

WORKDIR /app
COPY . /app

RUN mvn clean install -Dmaven.test.skip=true

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/CoffeeMaker-1.0-SNAPSHOT.jar", "server", "config.yml"]
