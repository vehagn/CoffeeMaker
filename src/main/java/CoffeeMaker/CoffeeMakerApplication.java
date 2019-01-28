package CoffeeMaker;

import CoffeeMaker.api.Drink;
import CoffeeMaker.resources.DrinkResource;
import CoffeeMaker.resources.InfoResource;
import CoffeeMaker.services.DrinkService;
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

        ArrayList drinks = initialiseDrinks();

        final InfoResource infoResource = new InfoResource();
        final DrinkResource drinkResource = new DrinkResource(new DrinkService(drinks));
        //TODO: resource

        environment.jersey().register(infoResource);
        environment.jersey().register(drinkResource);
        //TODO: register
    }

    private ArrayList<Drink> initialiseDrinks() throws IOException {
        List<List<String>> data = new ArrayList<>();
        ArrayList<Drink> drinks = new ArrayList<>();

        String drinksdb = "./src/main/java/CoffeeMaker/drinks.csv";
        BufferedReader br = new BufferedReader(new FileReader(drinksdb));
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
