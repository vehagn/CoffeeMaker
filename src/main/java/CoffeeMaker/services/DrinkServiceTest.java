package CoffeeMaker.services;

import CoffeeMaker.api.Drink;

import static CoffeeMaker.CoffeeMakerApplication.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkServiceTest {

    @org.junit.jupiter.api.Test
    void enforceDrinkSizeLimitEnforced() {
        Drink drink = new Drink(0,"testDrink",10.0,4,4,4);
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE,drink.getSize());
        assertEquals(3,drink.getWater());
        assertEquals(4,drink.getCoffee());
        assertEquals(3,drink.getMilk());

        drink.updateRecipe(11,0,0);
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE,drink.getSize());
        assertEquals(10,drink.getWater());
        assertEquals(0,drink.getCoffee());
        assertEquals(0,drink.getMilk());

        drink.updateRecipe(0,11,0);
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE,drink.getSize());
        assertEquals(0,drink.getWater());
        assertEquals(10,drink.getCoffee());
        assertEquals(0,drink.getMilk());

        drink.updateRecipe(0,0,11);
        DrinkService.enforceDrinkSizeLimit(drink);
        assertEquals(MAX_DRINK_SIZE,drink.getSize());
        assertEquals(0,drink.getWater());
        assertEquals(0,drink.getCoffee());
        assertEquals(10,drink.getMilk());
    }

    @org.junit.jupiter.api.Test
    void drinkSizeIsConsistentWithRecipe() {
        Drink drink = new Drink(0,"testDrink", 10.0, 4, 5, 5);
        assertEquals(drink.getSize(),drink.getWater()+drink.getCoffee()+drink.getMilk(),"Drink size does not equal recipe.");
    }

    @org.junit.jupiter.api.Test
    void addingCoffeeDoesNotExceedMaxDrinkSize() {
        Drink drink = new Drink(0,"testDrink",10.0, 0, 0, 0);
        DrinkService.addCoffee(drink,0);
        assertEquals(0,drink.getCoffee());
        DrinkService.addCoffee(drink,5);
        assertEquals(5, drink.getSize());
        DrinkService.addCoffee(drink, 5);
        assertEquals(10,drink.getSize());
        DrinkService.addCoffee(drink,2*MAX_DRINK_SIZE);
        assertEquals(MAX_DRINK_SIZE,drink.getSize());
    }

    @org.junit.jupiter.api.Test
    void addCoffeeReplaceLogic() {
        Drink drink = new Drink(0,"testDrink",10.0,3,3,3);
        DrinkService.addCoffee(drink,0);
        assertEquals(3,drink.getWater());
        assertEquals(3,drink.getCoffee());
        assertEquals(3,drink.getMilk());
        assertEquals(9,drink.getSize());
        DrinkService.addCoffee(drink,2);
        assertEquals(2,drink.getWater());
        assertEquals(5,drink.getCoffee());
        assertEquals(3,drink.getMilk());
        assertEquals(MAX_DRINK_SIZE,drink.getSize());
        DrinkService.addCoffee(drink,3);
        assertEquals(0,drink.getWater());
        assertEquals(8,drink.getCoffee());
        assertEquals(2,drink.getMilk());
        assertEquals(MAX_DRINK_SIZE,drink.getSize());
        DrinkService.addCoffee(drink,3);
        assertEquals(0,drink.getWater());
        assertEquals(10,drink.getCoffee());
        assertEquals(0,drink.getMilk());
        assertEquals(MAX_DRINK_SIZE,drink.getSize());

        drink.updateRecipe(10,0,0);
        DrinkService.addCoffee(drink,0);
        assertEquals(10,drink.getWater());
        assertEquals(0,drink.getCoffee());
        assertEquals(0,drink.getMilk());
        assertEquals(10,drink.getSize());
    }

    @org.junit.jupiter.api.Test
    void checkTemperature() {
        Drink drink = new Drink(0,"testDrink",10.0, 3,3,3);
        assertEquals(10.0,drink.getTemperature());
        DrinkService.changeTemperature(drink,50.0);
        assertEquals(50.0,drink.getTemperature());
        DrinkService.changeTemperature(drink,-2*MIN_DRINK_TEMPERATURE);
        assertEquals(MIN_DRINK_TEMPERATURE,drink.getTemperature());
        DrinkService.changeTemperature(drink,2*MAX_DRINK_TEMPERATURE);
        assertEquals(MAX_DRINK_TEMPERATURE,drink.getTemperature());
    }
}