package co.edu.unbosque.taller4.resource;

import co.edu.unbosque.taller4.Dto.ExceptionMessage;
import co.edu.unbosque.taller4.Dto.User;
import co.edu.unbosque.taller4.service.UserService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Path("/Users")
public class UserResource {

    @Context
    ServletContext context;
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response list(){
        try{
            List<User> users= new UserService().getUsers().get();
            return Response.ok()
                    .entity(users)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(User user){

        String contextPath =context.getRealPath("") + File.separator;
        try{
            user =new  UserService().createUser(user.getUsername(), user.getPassword(), user.getRole(), user.getFcoins(),context.getRealPath("") + File.separator);
            return Response.created(UriBuilder.fromResource(UserResource.class).path(user.getUsername()).build())
                    .entity(user)
                    .build();
        }catch (IOException e){
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("username") String username){
        System.out.println("linea 85");
        try{
            List<User> users = new UserService().getUsers().get();
            System.out.println("linea 57");
            User user = users.stream()
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst()
                    .orElse(null);
            System.out.println("linea 62");
            if (user != null) {
                System.out.println("linea 64");
                System.out.println("linea nueva 65");
                return Response.ok()
                        .entity(user)
                        .build();
            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "User not found"))
                        .build();
            }

        } catch (IOException e) {
            System.out.println("linea 73");
            return Response.serverError().build();
        }
    }
    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response  createForm(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("role") String role,
            @FormParam("fcoins")String Fcoins
    ){
        System.out.println("linea 91");

        String contextPath =context.getRealPath("") + File.separator;

        try {
            User user = new UserService().createUser(username, password, role,Fcoins, contextPath);

            return Response.created(UriBuilder.fromResource(UserResource.class).path(username).build())
                    .entity(user)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }

    }

    }