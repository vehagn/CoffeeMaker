package CoffeeMaker.api;

public class Drink {
    private int drinkId;
    private String name;
    private double temperature;
    private int water;
    private int coffee;
    private int milk;
    private int size;

    public Drink() {
        this.drinkId = -1;
        this.name = "";
        this.temperature = 0.0;
        this.water = 0;
        this.coffee = 0;
        this.milk = 0;
        this.size = 0;
    }

    public Drink(Drink drink) {
        this.drinkId = drink.getDrinkId();
        this.name = drink.getName();
        this.temperature = drink.getTemperature();
        this.water = drink.getWater();
        this.coffee = drink.getCoffee();
        this.milk = drink.getMilk();
        this.size = drink.getSize();
    }

    public Drink(int uuid, String name, double temperature, int water, int coffee, int milk) {
        this.drinkId = uuid;
        this.name = name;
        this.temperature = temperature;
        this.water = water;
        this.coffee = coffee;
        this.milk = milk;
        this.size = water + coffee + milk;
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int uuid) {
        this.drinkId = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
        this.updateSize();
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
        this.updateSize();
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
        this.updateSize();
    }

    private void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void updateRecipe(int water, int coffee, int milk) {
        this.water = water;
        this.coffee = coffee;
        this.milk = milk;
        this.updateSize();
    }

    public void updateSize() {
        this.size = this.getWater() + this.getCoffee() + this.getMilk();
    }

}
