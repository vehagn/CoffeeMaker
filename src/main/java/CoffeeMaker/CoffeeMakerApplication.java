package CoffeeMaker;

import CoffeeMaker.api.Drink;
import CoffeeMaker.api.Machine;
import CoffeeMaker.resources.DrinkResource;
import CoffeeMaker.resources.MachineResource;
import CoffeeMaker.services.DrinkService;
import CoffeeMaker.services.MachineService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoffeeMakerApplication extends Application<CoffeeMakerConfiguration> {
    public static final int MAX_DRINK_SIZE = 10;
    public static final double MAX_DRINK_TEMPERATURE = 95.0;
    public static final double MIN_DRINK_TEMPERATURE =  1.0;

    public static void main(final String[] args) throws Exception {
        new CoffeeMakerApplication().run(args);
    }

    @Override
    public String getName() {
        return "CoffeeMaker";
    }

    @Override
    public void initialize(final Bootstrap<CoffeeMakerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CoffeeMakerConfiguration configuration, final Environment environment) throws IOException {

        //TODO: Read drinks and machine status from a database
        ArrayList drinks = initialiseDrinks();
        Machine machine = new Machine(0,"Neversleep","5000+",100,100,100,20);

        final MachineResource machineResource = new MachineResource(new MachineService(machine, drinks));
        final DrinkResource drinkResource = new DrinkResource(new DrinkService(drinks));

        environment.jersey().register(machineResource);
        environment.jersey().register(drinkResource);
    }

    private ArrayList<Drink> initialiseDrinks() throws IOException {
        //TODO: Use an actual database
        List<List<String>> data = new ArrayList<>();
        ArrayList<Drink> drinks = new ArrayList<>();

        String drinksList = "./src/main/java/CoffeeMaker/drinks.csv";
        BufferedReader br = new BufferedReader(new FileReader(drinksList));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(";");
            data.add(Arrays.asList(values));
        }

        for(List<String> d : data) {
            int uuid = Integer.parseInt(d.get(0));
            String name = d.get(1);
            float temperature = Float.parseFloat(d.get(2));
            int water = Integer.parseInt(d.get(3));
            int coffee = Integer.parseInt(d.get(4));
            int milk = Integer.parseInt(d.get(5));

            Drink drink = new Drink(uuid, name, temperature, water, coffee, milk);

            drinks.add(drink);
        }

        return drinks;
    }

}
