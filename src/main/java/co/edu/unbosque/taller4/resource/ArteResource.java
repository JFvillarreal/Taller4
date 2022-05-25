package co.edu.unbosque.taller4.resource;

import co.edu.unbosque.taller4.Dto.ExceptionMessage;
import co.edu.unbosque.taller4.Dto.Pieza;
import co.edu.unbosque.taller4.Dto.User;
import co.edu.unbosque.taller4.service.ImageServices;
import co.edu.unbosque.taller4.service.UserService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Path("/Arte")
public class ArteResource {
    private String UPLOAD_DIRECTORY = "/imagen";
    @Context
    ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {

        System.out.println("se esta entrando por la funcion erronea");

        try {
            List<Pieza> piezas = new ImageServices().getPieces().get();

            return Response.ok()
                    .entity(piezas)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Pieza pieza) {
        String contextPath =context.getRealPath("") + File.separator;

        try {

            pieza = new ImageServices().create_peace(pieza.getTitulo(), pieza.getPrecio(), pieza.getArtist(), pieza.getImg(),pieza.getCollection(),contextPath);

            return Response.created(UriBuilder.fromResource(ArtesResource.class).path(pieza.getTitulo()).build())
                    .entity(pieza)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("username") String username) {
        try {
            List<Pieza>pieza = new ImageServices().getPieces().get();

            Pieza pieza1 = pieza.stream()
                    .filter(u -> u.getArtist().equals(username))
                    .findFirst()
                    .orElse(null);

            if (pieza1 != null) {
                return Response.ok()
                        .entity(pieza1)
                        .build();
            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "User not found"))
                        .build();
            }
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/form")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createForm(
            @FormParam("titulo") String titulo,
            @FormParam("username") String username,
            @FormParam("Imagen") String imagen,
            @FormParam("precio") String precio,
            @FormParam("colecction") String coleccion
    ) {
        String contextPath =context.getRealPath("") + File.separator;

        try {
           Pieza pieza = new ImageServices().create_peace(titulo,username, imagen, precio,coleccion ,contextPath);

            return Response.created(UriBuilder.fromResource(ImageServices.class).path(username).build())
                    .entity(pieza)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/listfiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getimages(){
        System.out.print("esta entrando aget images");
        String uploadpath=context+ File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadpath);
        List<String> files = new ArrayList<String>();
        for(int i=0;i< uploadDir.listFiles().length;i++){
            files.add(UPLOAD_DIRECTORY + File.separator + "Captura de pantalla (6).png");
        }
        return Response.ok().entity(files).build();
    }
}