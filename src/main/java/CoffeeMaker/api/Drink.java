package CoffeeMaker.api;

public class Drink {
    private int uuid;
    private String name;
    private double temperature;
    private int water;
    private int coffee;
    private int milk;
    private int size;

    public Drink(int uuid, String name, double temperature, int water, int coffee, int milk) {
        this.uuid = uuid;
        this.name = name;
        this.temperature = temperature;
        this.water = water;
        this.coffee = coffee;
        this.milk = milk;
        this.size = water + coffee + milk;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
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
    }

    public int getCoffee() {
        return coffee;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public void updateSize() {
        this.size = this.getWater() + this.getCoffee() + this.getCoffee();
    }

}
