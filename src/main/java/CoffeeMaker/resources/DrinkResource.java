package CoffeeMaker.resources;

import CoffeeMaker.services.DrinkService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
