package co.edu.unbosque.taller4.resource;


import co.edu.unbosque.taller4.Dto.Pieza;
import co.edu.unbosque.taller4.service.ImageServices;
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

@Path("/Arte")
public class ArtesResource {


        @Context
        ServletContext context;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response list() {
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

            pieza = new ImageServices().create_peace(pieza.getTitulo(),pieza.getPrecio(),pieza.getArtist(),pieza.getImg(),contextPath);


            return Response.created(UriBuilder.fromResource(ArtesResource.class).path(pieza.getTitulo()).build())
                    .entity(pieza)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }
    }
}


