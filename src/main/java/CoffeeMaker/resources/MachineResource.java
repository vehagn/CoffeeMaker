package CoffeeMaker.resources;

import CoffeeMaker.api.Drink;
import CoffeeMaker.api.Machine;
import CoffeeMaker.services.DrinkService;
import CoffeeMaker.services.MachineService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/machine")
public class MachineResource {

    private MachineService machineService;

    public MachineResource(MachineService machineService) {
        this.machineService = machineService;
    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInfo() {
        Machine machine = machineService.getMachine();
        return Response.ok(machine).build();
    }

    @GET
    @Path("/dispense")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dispense(@QueryParam("drinkId") int drinkId, @QueryParam("addCoffee") int addCoffee) {
        //TODO: This should talk with drinkService, or remove drinkService
        Drink drink = new Drink(machineService.getDrinkFromId(drinkId));

        // Add requested coffee
        DrinkService.addCoffee(drink, addCoffee);
        // Make sure that temperature is between MIN_DRINK_TEMPERATURE and MAX_DRINK_TEMPERATURE
        DrinkService.setDrinkTemperature(drink, drink.getTemperature());

        try {
            machineService.dispenseDrink(drink);
            Machine machine = machineService.getMachine();

            ArrayList info = new ArrayList();
            info.add(machine);
            info.add(drink);

            return Response.ok(info).build();
        }
        catch (Exception err) {
            return Response.ok(err.toString()).build();
        }
    }

}
