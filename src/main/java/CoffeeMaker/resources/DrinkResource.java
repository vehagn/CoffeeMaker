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

    private DrinkService drinkService;

    public DrinkResource(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDrinks() {
        List drinks = drinkService.getAllDrinks();

        return Response.ok(drinks).build();
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDrinkFromID(@QueryParam("id") int uuid) {
        Drink drink = drinkService.getDrinkFromID(uuid);

        return Response.ok(drink).build();
    }

    @GET
    @Path("/addCoffee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCoffee(@QueryParam("id")int id,@QueryParam("amount") int amount) {
        Drink drink = new Drink(drinkService.getDrinkFromID(id));
        drinkService.addCoffee(drink,amount);

        return Response.ok(drink).build();
    }
}
