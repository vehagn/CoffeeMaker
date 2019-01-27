FROM java:8

WORKDIR /app
COPY . /app

RUN mvn clean install

EXPOSE 8080

RUN javac Main.java
CMD ["java","-jar target/CoffeeMaker-1.0-SNAPSHOT.jar server config.yml"]
