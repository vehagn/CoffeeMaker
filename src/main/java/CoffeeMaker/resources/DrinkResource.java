package CoffeeMaker.resources;

import CoffeeMaker.api.Drink;
import CoffeeMaker.services.DrinkService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/drink")
public class DrinkResource {
    //TODO: Think about removing this resource and porting the functions over to MachineResource
    private DrinkService drinkService;

    public DrinkResource(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDrinks() {
        List drinks = drinkService.getDrinks();

        return Response.ok(drinks).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrinkFromId(@QueryParam("drinkId") int drinkId) {
        Drink drink = drinkService.getDrinkFromId(drinkId);

        return Response.ok(drink).build();
    }

    @GET
    @Path("/addCoffee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCoffee(@QueryParam("dinkId")int drinkId, @QueryParam("addCoffee") int addCoffee) {
        Drink drink = new Drink(drinkService.getDrinkFromId(drinkId));
        drinkService.addCoffee(drink, addCoffee);

        return Response.ok(drink).build();
    }
}
