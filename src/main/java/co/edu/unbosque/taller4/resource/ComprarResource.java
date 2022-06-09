package co.edu.unbosque.taller4.resource;


import co.edu.unbosque.taller4.Dto.*;
import co.edu.unbosque.taller4.service.*;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/comprar")
public class ComprarResource {
    static final String USER = "postgres";
    static final String PASS = "jota73456";
    static final String DB_URL = "jdbc:postgresql://localhost/Arte";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    @Context
    ServletContext context;

    public ComprarResource() throws SQLException {
    }
    @POST
    @Path("/confirmar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(comprar comp
                          ){
        ArtistaService artistaservice=new ArtistaService(conn);
        CoustumerService costuemrservice=new CoustumerService(conn);
        obraService obra=new obraService(conn);
        Obra obr=new Obra();

        List<Obra>  obras = obra.listaobra();
        for (int i =0;i<obras.size();i++){
            System.out.println("este el id de la obra dentro la i"+ obras.get(i).getPieceid());
        }
        System.out.println("entrnado a confirmar 40");
        Obra o = obras.stream()
                .filter(u -> u.getTitulo().equals(comp.getPieza()) )
                .findFirst()
                .orElse(null);
        System.out.println("esta es la pieza" + comp.getPieza());
        System.out.println("esta es el comprador " + comp.getEmail());
        System.out.println("vendedor " + comp.getVendedor());
        System.out.println("esta es la pieza pgadmin " + o.getPieceid());
        if (o!=null){
            System.out.println("Entrando a la comdiconal 47");
            List<Artista> art=artistaservice.listartista();
            Artista art_n = art.stream()
                    .filter(u -> u.getEmail().equals(comp.getVendedor()))
                    .findFirst()
                    .orElse(null);


            List<Coustomer> cos=costuemrservice.listarcoustumer();
            Coustomer cos_n = cos.stream()
                    .filter(u -> u.getEmail().equals(comp.getEmail()) )
                    .findFirst()
                    .orElse(null);
            System.out.println("precio "+ o.getPrecio()+" cuenta "+cos_n.getFcoins());
            if (o.getPrecio()< cos_n.getFcoins()){

                System.out.println("Entrando a la comdiconal 61");
            costuemrservice.updatecoustumer(new Coustomer(cos_n.getEmail(), cos_n.getFcoins()-o.getPrecio(),cos_n.getPassword()));
                obra.updateobra(new Obra());
                o.setOwner(comp.getEmail());
                artistaservice.updateartist(new Artista(art_n.getEmail(),art_n.getFcoins()+o.getPrecio(),art_n.getPassword(),art_n.getDescrip()));

            }
            else {
                System.out.println("fcoins insufucientes");
            }
        }
        System.out.println("no se puede comprar");
        return null;
    }
}
