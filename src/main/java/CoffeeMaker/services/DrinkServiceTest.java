package CoffeeMaker.services;

import CoffeeMaker.api.Drink;

import static CoffeeMaker.CoffeeMakerApplication.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkServiceTest {

    @org.junit.jupiter.api.Test
    void enforceDrinkSizeLimitEnforced() {
        Drink drink = new Drink(0, "testDrink", 10.0, 4, 4, 4);

        // Assert that drink is correctly downsized
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE, drink.getSize());
        assertEquals(3, drink.getWater());
        assertEquals(4, drink.getCoffee());
        assertEquals(3, drink.getMilk());

        // Assert that only water is downsized if it's the only ingredient
        drink.updateRecipe(13, 0, 0);
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE, drink.getSize());
        assertEquals(10, drink.getWater());
        assertEquals(0, drink.getCoffee());
        assertEquals(0, drink.getMilk());

        // Assert that only coffee is downsized if it's the only ingredient
        drink.updateRecipe(0, 15, 0);
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE, drink.getSize());
        assertEquals(0, drink.getWater());
        assertEquals(10, drink.getCoffee());
        assertEquals(0, drink.getMilk());

        // Assert that only milk is downsized if it's the only ingredient
        drink.updateRecipe(0, 0, 11);
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE, drink.getSize());
        assertEquals(0, drink.getWater());
        assertEquals(0, drink.getCoffee());
        assertEquals(10, drink.getMilk());
    }

    @org.junit.jupiter.api.Test
    void drinkSizeIsConsistentWithRecipe() {
        Drink drink = new Drink(0,"testDrink", 10.0, 4, 5, 5);

        // Assert that drink size in consistent with recipe
        assertEquals(drink.getSize(),drink.getWater()+drink.getCoffee()+drink.getMilk(),"Drink size does not equal recipe.");
    }

    @org.junit.jupiter.api.Test
    void addCoffeeDoesNotExceedMaxDrinkSize() {
        Drink drink = new Drink(0,"testDrink",10.0, 0, 0, 0);

        // Assert that adding no coffee adds no coffee
        DrinkService.addCoffee(drink,0);
        assertEquals(0, drink.getCoffee());

        // Assert that we can't remove coffee with addCoffee
        DrinkService.addCoffee(drink, -5);
        assertEquals(0, drink.getCoffee());
        assertEquals(0, drink.getSize());

        // Assert that the correct amount of coffee is added
        DrinkService.addCoffee(drink, 5);
        assertEquals(5, drink.getSize());
        DrinkService.addCoffee(drink,  5);
        assertEquals(10, drink.getSize());

        // Assert that we can't add too much coffee (is there such a thing?)
        DrinkService.addCoffee(drink, 2*MAX_DRINK_SIZE);
        assertEquals(MAX_DRINK_SIZE, drink.getSize());

    }

    @org.junit.jupiter.api.Test
    void addCoffeeReplaceLogic() {
        Drink drink = new Drink(0,"testDrink",10.0,3,3,3);

        // Assert that adding no coffee doesn't change the recipe
        DrinkService.addCoffee(drink, 0);
        assertEquals(3, drink.getWater());
        assertEquals(3, drink.getCoffee());
        assertEquals(3, drink.getMilk());
        assertEquals(9, drink.getSize());

        // Assert that water is being replaced after filling the cup
        DrinkService.addCoffee(drink, 2);
        assertEquals(2, drink.getWater());
        assertEquals(5, drink.getCoffee());
        assertEquals(3, drink.getMilk());
        assertEquals(MAX_DRINK_SIZE, drink.getSize());

        // Assert that milk is being replaced after water
        DrinkService.addCoffee(drink, 3);
        assertEquals(0, drink.getWater());
        assertEquals(8, drink.getCoffee());
        assertEquals(2, drink.getMilk());
        assertEquals(MAX_DRINK_SIZE, drink.getSize());

        // Assert that all other ingredients are replaced by coffee until the cup is filled
        DrinkService.addCoffee(drink, 3);
        assertEquals(0, drink.getWater());
        assertEquals(10, drink.getCoffee());
        assertEquals(0, drink.getMilk());
        assertEquals(MAX_DRINK_SIZE, drink.getSize());

    }

    @org.junit.jupiter.api.Test
    void checkTemperature() {
        Drink drink = new Drink(0, "testDrink", 10.0, 3, 3, 3);

        // Assert that temperature is set correctly in Drink constructor
        assertEquals(10.0, drink.getTemperature());

        // Assert that temperature is changed correctly
        DrinkService.setDrinkTemperature(drink, 50.0);
        assertEquals(50.0, drink.getTemperature());

        // Assert that we can't exceed minimum temperature
        DrinkService.setDrinkTemperature(drink, -2*MIN_DRINK_TEMPERATURE);
        assertEquals(MIN_DRINK_TEMPERATURE, drink.getTemperature());

        // Assert that we can't exceed maximum temperature
        DrinkService.setDrinkTemperature(drink, 2*MAX_DRINK_TEMPERATURE);
        assertEquals(MAX_DRINK_TEMPERATURE,drink.getTemperature());
    }
}