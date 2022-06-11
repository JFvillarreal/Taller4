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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/comprar")
public class ComprarResource {
    static final String USER = "postgres";
    static final String PASS = "Holapgadmin1999";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
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
        WalletHistory walletHistory=new WalletHistory(conn);
        Obra obr=new Obra();
        Wallet cartera1=new Wallet();
        Wallet cartera2=new Wallet();
        List<Obra>  obras = obra.listaobra();
        for (int i =0;i<obras.size();i++){
            System.out.println("este el id de la obra dentro la i"+ obras.get(i).getPieceid());
        }
        System.out.println("entrnado a confirmar 40");
        Obra o = obras.stream()
                .filter(u -> u.getPieceid()== comp.getPieza())
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

            artistaservice.updateartist(new Artista(art_n.getEmail(),art_n.getFcoins()+o.getPrecio(),art_n.getPassword(),art_n.getDescrip()));
                System.out.println("paso despues de artista");
                o.setOwner(comp.getEmail());
             obra.updateobra(o);
             cartera1.setIdwellet(walletHistory.listaobra().size()+1);
                cartera1.setEmail(cos_n.getEmail());
                cartera1.setFcoins(cos_n.getFcoins());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                String date= dtf.format(LocalDateTime.now());
                System.out.println("esta es la fecha "+date);
                cartera1.setDate(date);
                walletHistory.insertobra(cartera1);
                cartera2.setIdwellet(walletHistory.listaobra().size()+1);
                cartera2.setEmail(art_n.getEmail());
                cartera2.setFcoins(art_n.getFcoins());
                cartera2.setDate(date);
                walletHistory.insertobra(cartera2);
            }
            else {
                System.out.println("fcoins insufucientes");
            }
        }else{
            return Response.status(404)
                    .entity(new ExceptionMessage(404, "User not found"))
                    .build();
        }

        return Response.status(201)
                .entity("This update  "+o.getTitulo())
                .build();
    }

    @POST
    @Path("/transaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @FormParam("usuario" )String usuario,
            @FormParam("password")String password,
            @FormParam("pieceid")String piece,
            @FormParam("precio")String priecio){
        System.out.println("entrando a transaciones");
        System.out.println("usuario "+ usuario);
        System.out.println("passord "+ password);
        System.out.println("pieceid  "+ piece);
        System.out.println("precio "+ priecio);
        int saldo = Integer.parseInt(priecio);
        int pieza = Integer.parseInt(piece);
        System.out.println("linea 57"+ saldo);

        Usuario n=new Usuario(null,null,null,null);
        Usuaarioresorce bass =new Usuaarioresorce(conn);
        obraService obra=new obraService(conn);
        Obra obr=new Obra();
        List<Obra>  obras = obra.listaobra();
        Obra o = obras.stream()
                .filter(u -> u.getPieceid()== pieza)
                .findFirst()
                .orElse(null);
        List<Usuario> users = bass.listusers();
        System.out.println("linea 121");
        Usuario user_n = users.stream()
                .filter(u -> u.getEmail().equals(usuario) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        System.out.println("obra es null "+ o.getTitulo());

        System.out.println("usuario "+ user_n.getUsername());
         if(o!=null && user_n!=null){

             System.out.println("entrando a la condicional");
             o.setPrecio(saldo);

             obra.updateobra1(o);

         }
        System.out.println("sale");
        return null;
    }
}
