package CoffeeMaker.services;

import CoffeeMaker.api.Drink;
import CoffeeMaker.api.Machine;

import java.util.ArrayList;


public class MachineService {
    private Machine machine;
    private ArrayList drinks;

    public Drink getDrinkFromId(int drinkId) {
        if (drinkId < 0 || drinkId > this.drinks.size()) {
            //TODO: Raise exception instead
            drinkId = 0;
        }
        Drink drink = (Drink) this.drinks.get(drinkId);
        return drink;
    }

    public MachineService(Machine machine, ArrayList drinks) {
        this.machine = machine;
        this.drinks = drinks;
    }

    public void dispenseDrink(Drink drink) {
        //DrinkService.enforceDrinkSizeLimit(drink);

        int waterDrink = drink.getWater();
        int coffeeDrink = drink.getCoffee();
        int milkDrink = drink.getMilk();

        int waterReservoir = machine.getWaterReservoir();
        int coffeeReservoir = machine.getCoffeeReservoir();
        int milkReservoir = machine.getMilkReservoir();
        int cups = machine.getCups();

        if (    (waterDrink > waterReservoir)   ||
                (coffeeDrink > coffeeReservoir) ||
                (milkDrink > milkReservoir)     ||
                (cups < 1) ) {
            //TODO: Assign error codes and check each one in succession
            //TODO: Throw exception based on missing ingredient
        }
        else {
            waterReservoir -= (waterDrink + coffeeDrink); // Assume we consume water to brew coffee also.
            coffeeReservoir -= coffeeDrink; // 1:1 ratio of beans to water of max reservoir
            milkReservoir -= milkDrink; // Standalone milk tank
            cups -= 1; // Automatic cup dispenser

            machine.update(waterReservoir, coffeeReservoir, milkReservoir, cups);
        }
    }

    //TODO: Add refill functions

    public Machine getMachine() {
        return machine;
    }

}
