package CoffeeMaker.services;

import CoffeeMaker.api.Drink;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DrinkServiceTest {

    @org.junit.jupiter.api.Test
    void doubleDrinkSizeIsConsistentWithRecipe() {
        Drink drink = new Drink("testDrink", 10.0, 4, 5, 5);
        assertEquals(drink.getSize(),drink.getWater()+drink.getCoffee()+drink.getMilk(),"Drink size does not equal recipe.");
    }

    @org.junit.jupiter.api.Test
    void checkTemperature() {

    }
}