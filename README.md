# CoffeeMaker

How to start the CoffeeMaker application
---

1. Run `docker build . -t coffeemaker` to build docker image application
1. Start application with `docker run -p 8080:8080 coffeemaker`
1. To check that your application is running enter url `http://localhost:8080`

TODO
Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
