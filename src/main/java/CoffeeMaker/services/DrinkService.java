package CoffeeMaker.services;

import CoffeeMaker.api.Drink;

import java.util.ArrayList;

import static CoffeeMaker.CoffeeMakerApplication.*;

public class DrinkService {
    //TODO: Ponder moving ArrayList variable and functions over to MachineService
    private ArrayList drinks;

    public DrinkService(ArrayList drinks) {
        this.drinks = drinks;
    }

    public ArrayList getDrinks() {
        return drinks;
    }

    public Drink getDrinkFromId(int drinkId) {
        if (drinkId < 0 || drinkId > this.drinks.size()) {
            //TODO: Raise exception instead
            drinkId = 0;
        }
        Drink drink = (Drink) this.drinks.get(drinkId);
        return drink;
    }

    public static void enforceDrinkSizeLimit(Drink drink) {
        int size = drink.getSize();
        int water = drink.getWater();
        int coffee = drink.getCoffee();
        int milk = drink.getMilk();

        //TODO: Think about removing most abundant unit first, then shave off all in order given here
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

        // We shouldn't be able to remove coffee
        amount = (amount<0)?(0):(amount);

        // Don't try to fill more coffee than the cup can hold
        amount = (amount > (MAX_DRINK_SIZE+coffee))?(MAX_DRINK_SIZE-coffee):(amount);

        // Add more coffee without replacing anything if there's room in the cup
        int roomInCup = MAX_DRINK_SIZE-size;
        coffee += (amount < roomInCup)?(amount):(roomInCup);

        // Try to replace the rest of the requested added coffee
        int tryToAdd = amount - roomInCup;
        int ableToAdd = 0;

        // Try to replace all the water with coffee first
        if (tryToAdd > 0) {
            // Try to replace water with coffee first
            if (water < tryToAdd) {
                tryToAdd -= water;
                ableToAdd += water;
                water = 0;
            } else {
                water -= tryToAdd;
                ableToAdd += tryToAdd;
                tryToAdd = 0;
            }
            // Next replace remaining milk with coffee
            if (milk < tryToAdd) {
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
        //TODO: Exception/warning if (tryToAdd > 0) ?
        drink.updateRecipe(water, coffee, milk);
    }

    public static void setDrinkTemperature(Drink drink, double temperature) {
        if (temperature > MAX_DRINK_TEMPERATURE) {
            temperature = MAX_DRINK_TEMPERATURE;
        }
        else if (temperature < MIN_DRINK_TEMPERATURE) {
            temperature = MIN_DRINK_TEMPERATURE;
        }
        drink.setTemperature(temperature);
    }
    //TODO: enforce temperature range
}
