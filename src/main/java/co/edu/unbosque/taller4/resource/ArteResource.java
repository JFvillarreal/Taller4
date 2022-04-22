package co.edu.unbosque.taller4.resource;

import co.edu.unbosque.taller4.Dto.User;
import co.edu.unbosque.taller4.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/Arte")
public class ArteResource {
    @GET
    @Produces("application/json")
    public Response getAll() throws IOException {
           List<User> users=new UserService().getUsers().get();
       return Response.ok().entity(users).build();

    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(User user){

        return Response.status(201).entity(user).build();
    }
}