package CoffeeMaker.services;

import CoffeeMaker.api.Drink;

import java.util.ArrayList;

public class DrinkService {
    private ArrayList drinks;

    public DrinkService(ArrayList drinks) {
        this.drinks = drinks;
    }

    public ArrayList getAllDrinks() {
        return drinks;
    }

    public Drink doubleCoffee(Drink drink) {
        int water = drink.getWater();
        int milk = drink.getMilk();
        int coffee = drink.getCoffee();

        //TODO: More logic
        milk -= 2;
        coffee += 2;

        drink.setMilk(milk);
        drink.setCoffee(coffee);
        drink.updateSize();

        return drink;
    }

}
