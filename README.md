# CoffeeMaker

## How to start the CoffeeMaker application
---
This coffee maker application can be started in on of two ways.

### Using docker
1. Run `docker build . -t coffeemaker` to build docker image application
2. Start application with `docker run -p 8080:8080 coffeemaker`

### Compiling locally
1. Run `mvn clean install` in root directory to build
2. Start application with `java -jar target/CoffeeMaker-1.0-SNAPSHOT.jar server config.yml`

To check that the application is running enter url [`http://localhost:8080/api`](http://localhost:8080/api).
You should be greeted with a `{"code":404,"message":"HTTP 404 Not Found"}` message.

__TODO: Implement more friendly welcome message__

---
~~To see your applications health enter url `http://localhost:8081/healthcheck`~~

__TODO: Implement Health Check__

---

## How to use the CoffeeMaker application

### Drink rules
* Each Drink consists of 7 fields of information
  1. `drinkId` -- unique identifier for drink
  2. `name` -- name of drink
  3. `temperature` -- serving temperature of drink
  4. `water` -- units of water in drink
  5. `coffee` -- units of coffee in drink
  6. `milk` -- units of milk in drink
  7. `size` -- total ingredient units in drink
* Each drink consists of 3 ingredients: water, coffee, and milk.
* Each ingredient is given in unit increments between 0 and 10.
* The size of a drink is the sum off all ingredients.
* The maximum size of any drink is 10 (`MAX_DRINK_SIZE`) units.
* Coffee can be added to drinks
  * If adding coffee exceeds `MAX_DRINK_SIZE` size it displaces other ingredients
* If a drink exceeds `MAX_DRINK_SIZE` ingredients are removed until it no longer exceeds
  * Ingredients are removed one at a time, trying to balance the final drink
* The drink should be served between 1.0 (`MIN_DRINK_TEMPERATURE`) and 95.0 (`MAX_DRINK_TEMPERATURE`)

A list of available drinks with their corresponding recipe can be view by entering url
[`http://localhost:8080/api/drink/list`](http://localhost:8080/api/drink/list).

Each individual drink can be seen at
[`http://localhost:8080/api/drink/get?drinkId=[id]`](http://localhost:8080/api/drink/get?drinkId=[id])
by replacing `[id]` with the requested drink ID.

Adding coffee to a given drink can be done using
[`http://localhost:8080/api/drink/addCoffee?drinkId=[id]&addCoffee=[amount]`](http://localhost:8080/api/drink/get?drinkId=[id]&addCoffee=[amount])
where `[id]` is the selected drink and `[amount]` is the requested amount of added coffee.

__TODO: Functions for adding other ingredients__

__TODO: Better temperature management__

__TODO: Read drink list from database, not CSV file.__

### Machine rules
* Each Machine consists of 7 fields of information
  1. `machineId` -- unique machine identifier
  2. `brand` -- machine brand
  3. `make` -- machine make
  4. `waterReservoir` -- amount of water left
  5. `coffeeReservoir` -- amount of coffee left
  6. `milkReservoir` -- amount of milk left
  7. `cups` -- amount of cups left
* The machine dispenses drinks based on recipe
  * It's possible to add more coffee before dispensing drink
* The machine should have enough resources present before trying to dispense
  * Dispensing 1 unit of water consumes 1 unit of water
  * Dispensing 1 unit of coffee consumes 1 unit of water and 1 unit of coffee
  * Dispensing 1 unit of milk consumes 1 unit of milk
  * Dispensing 1 drink consumes 1 cup
* Return an Exception if not enough resources are present

Too see machine info go to
[`http://localhost:8080/api/machine/info`](http://localhost:8080/api/machine/info)

In order to dispense a drink invoke 
[`http://localhost:8080/api/machine/dispense?drinkId=[id]&addCoffee=[amount]`](http://localhost:8080/api/machine/dispense?drinkId=[id]&addCoffee=[amount])
where [id] is the ID of the requested drink and [amount] is the requested amount of added coffee.

__TODO: Add refill functions__

__TODO: Read machine status from database__
