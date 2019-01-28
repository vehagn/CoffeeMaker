package CoffeeMaker.api;

public class Machine {
    private int machineId;
    private String brand;
    private String make;
    private int waterReservoir;
    private int coffeeReservoir;
    private int milkReservoir;
    private int cups;

    public Machine() {
        this.machineId = -1;
        this.brand = null;
        this.make = null;
        this.waterReservoir = 0;
        this.coffeeReservoir = 0;
        this.milkReservoir = 0;
        this.cups = 0;
    }

    public Machine(Machine machine) {
        this.machineId = machine.getMachineId();
        this.brand = machine.getBrand();
        this.make = machine.getMake();
        this.waterReservoir = machine.getWaterReservoir();
        this.coffeeReservoir = machine.getCoffeeReservoir();
        this.milkReservoir = machine.getMilkReservoir();
        this.cups = machine.getCups();
    }

    public Machine(int machineId, String brand, String make, int waterReservoir, int coffeeReservoir, int milkReservoir, int cups) {
        this.machineId = machineId;
        this.brand = brand;
        this.make = make;
        this.waterReservoir = waterReservoir;
        this.coffeeReservoir = coffeeReservoir;
        this.milkReservoir = milkReservoir;
        this.cups = cups;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getWaterReservoir() {
        return waterReservoir;
    }

    public void setWaterReservoir(int waterReservoir) {
        this.waterReservoir = waterReservoir;
    }

    public int getCoffeeReservoir() {
        return coffeeReservoir;
    }

    public void setCoffeeReservoir(int coffeeReservoir) {
        this.coffeeReservoir = coffeeReservoir;
    }

    public int getMilkReservoir() {
        return milkReservoir;
    }

    public void setMilkReservoir(int milkReservoir) {
        this.milkReservoir = milkReservoir;
    }

    public int getCups() {
        return cups;
    }

    public void setCups(int cups) {
        this.cups = cups;
    }

    public void update(int waterReservoir, int coffeeReservoir, int milkReservoir, int cups) {
        this.waterReservoir = waterReservoir;
        this.coffeeReservoir = coffeeReservoir;
        this.milkReservoir = milkReservoir;
        this.cups = cups;
    }
}
