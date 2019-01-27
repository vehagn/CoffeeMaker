package CoffeeMaker.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/info")
public class InfoResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBrand() {
       return Response.ok("Coffee Maker 5000+").build();
    }

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlus(@QueryParam("num") int num) {
        int a;
        a = num+2;
        return Response.ok(a).build();
    }
}
