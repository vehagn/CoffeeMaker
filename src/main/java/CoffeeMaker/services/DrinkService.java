package CoffeeMaker.services;

import CoffeeMaker.api.Drink;

import java.util.ArrayList;

import static CoffeeMaker.CoffeeMakerApplication.*;

public class DrinkService {
    private ArrayList drinks;

    public DrinkService(ArrayList drinks) {
        this.drinks = drinks;
    }

    public ArrayList getDrinks() {
        return drinks;
    }

    public Drink getDrinkFromId(int uuid) {
        if (uuid < 0 || uuid > this.drinks.size()) {
            //TODO: Raise exception instead
            uuid = 0;
        }
        Drink drink = (Drink) this.drinks.get(uuid);
        return drink;
    }

    public static void enforceDrinkSizeLimit(Drink drink) {
        int size = drink.getSize();
        int water = drink.getWater();
        int coffee = drink.getCoffee();
        int milk = drink.getMilk();

        while (size > MAX_DRINK_SIZE) {
            int overflow = (size-MAX_DRINK_SIZE);
            int spill = (overflow%3);

            switch (spill) {
                case 1: // Try to remove water first
                    if (water > 0) {
                        water -= 1;
                        break;
                    }
                case 2: // Then milk
                    if (milk > 0) {
                        milk -= 1;
                        break;
                    }
                    else if (water > 0) {
                        water -= 1;
                        break;
                    }
                case 0: // Lastly coffee
                    if (coffee > 0) {
                        coffee -= 1;
                    }
            }

            size = water + coffee + milk;
        }

        drink.updateRecipe(water, coffee, milk);
    }

    public static void addCoffee(Drink drink, int amount) {
        int water = drink.getWater();
        int milk = drink.getMilk();
        int coffee = drink.getCoffee();
        int size = drink.getSize();

        amount = (amount > (MAX_DRINK_SIZE+coffee))?(MAX_DRINK_SIZE-coffee):(amount);

        int roomInCup = MAX_DRINK_SIZE-size;

        // Add more coffee without replacing anything if there's room in the cup
        coffee += (amount < roomInCup)?(amount):(roomInCup);

        // Try to replace the rest of the requested added coffee
        int tryToAdd = amount - roomInCup;
        int ableToAdd = 0;

        // Try to replace all the water with coffee first
        if (tryToAdd > 0) {
            if (water < tryToAdd) { // Try to replace water with coffee
                tryToAdd -= water;
                ableToAdd += water;
                water = 0;
            } else {
                water -= tryToAdd;
                ableToAdd += tryToAdd;
                tryToAdd = 0;
            }
            if (milk < tryToAdd) { // Replace milk with coffee is possible
                tryToAdd -= milk;
                ableToAdd += milk;
                milk = 0;
            } else {
                milk -= tryToAdd;
                ableToAdd += tryToAdd;
                tryToAdd = 0;
            }
            // Add as much coffee we've been able to make room for
            coffee += ableToAdd;
        }

        drink.updateRecipe(water, coffee, milk);
    }

    public static void changeTemperature(Drink drink, double temperature) {
        if (temperature > MAX_DRINK_TEMPERATURE) {
            temperature = MAX_DRINK_TEMPERATURE;
        }
        else if (temperature < MIN_DRINK_TEMPERATURE) {
            temperature = MIN_DRINK_TEMPERATURE;
        }
        drink.setTemperature(temperature);
    }
}
