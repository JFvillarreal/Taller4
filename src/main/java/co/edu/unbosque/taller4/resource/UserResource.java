package co.edu.unbosque.taller4.resource;

import co.edu.unbosque.taller4.Dto.*;
import co.edu.unbosque.taller4.service.ArtistaService;
import co.edu.unbosque.taller4.service.CoustumerService;
import co.edu.unbosque.taller4.service.UserService;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/Users")
public class UserResource {
    static final String USER = "postgres";
    static final String PASS = "Holapgadmin1999";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    @Context
    ServletContext context;

    public UserResource() throws SQLException {
    }

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
    @POST
    @Path("/found")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response found(User user){
        Usuaarioresorce bass =new Usuaarioresorce(conn);
        ArtistaService bassa=new ArtistaService(conn);
        CoustumerService bassc=new CoustumerService(conn);
        Usuario n=new Usuario(null,null,null,null);
        String username_n=user.getUsername();
        String password_n=user.getPassword();

        System.out.println("linea 85");
        System.out.println(user.getUsername()+" este es el username");
        System.out.println(user.getPassword()+" este es el password");
        System.out.println();
        List<Usuario> users = bass.listusers();
        System.out.println("linea 57");
        Usuario user_n = users.stream()
                .filter(u -> u.getEmail().equals(username_n) && u.getPassword().equals(password_n))
                .findFirst()
                .orElse(null);
        System.out.println("linea 62");
        if (user_n != null) {
            System.out.println("este es el username en java "+user_n.getUsername());
            System.out.println("este es el password en java "+user_n.getPassword());
            System.out.println("este es el role en java "+user_n.getRole());
            System.out.println("este es el email en java "+user_n.getEmail());
            user.setUsername(user_n.getUsername());
            user.setPassword(user_n.getPassword());
            user.setRole(user_n.getRole());
            if(user.getRole().equals("Artist")){
                List<Artista> artistas=bassa.listartista();
                Artista artista=artistas.stream().filter(u -> u.getEmail().equals(username_n) && u.getPassword().equals(password_n)).findFirst()
                        .orElse(null);
                user.setFcoins(Integer.toString(artista.getFcoins()));
            }else if(user.getRole().equals("Costumer")){
                List<Coustomer> coustomers=bassc.listarcoustumer();
                Coustomer costum=coustomers.stream().filter(u -> u.getEmail().equals(username_n) && u.getPassword().equals(password_n)).findFirst().orElse(null);
                user.setFcoins(Integer.toString(costum.getFcoins()) );
            }
            System.out.println("linea 64");
            System.out.println("linea nueva 65");
            System.out.println("Este el email usuario "+ user_n.getEmail());
            return Response.ok()
                    .entity(user)
                    .build();
        } else {
            System.out.println("esta es la linea 111");
            return Response.status(404)
                    .entity(new ExceptionMessage(404, "User not found"))
                    .build();
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
            @FormParam("email")String email
    ){
        System.out.println("linea 91");

        String contextPath =context.getRealPath("") + File.separator;

        try {
            User user = new UserService().createUser(username, password, role,email, contextPath);
           System.out.println("Estes es el rol"+ role);

            Usuaarioresorce usersService = new Usuaarioresorce(conn);
            ArtistaService artistaservice=new ArtistaService(conn);
            CoustumerService costuemrservice=new CoustumerService(conn);
            Usuario user_n=new Usuario(email,password,role,username);
            usersService.insertuser(user_n);
            System.out.println("estes es el email "+user_n.getEmail());
            System.out.println("estes es el password "+user_n.getPassword());
            System.out.println("estes es el role "+user_n.getRole());
            System.out.println("estes es el username "+user_n.getUsername());
            System.out.println("se esta pasasndo despues de crear usuario");
            if(user_n.getRole().equals("Artist")){
                System.out.println("se esta ingresando el artista");
                Artista nuevo_artista=new Artista(user_n.getEmail(),0,user_n.getPassword());
                artistaservice.insertArtist(nuevo_artista);
                System.out.println("se esta pasasndo despues de la insercion");
            }else if(user_n.getRole().equals("Costumer")){
                System.out.println("se esta ingresando el costumer");
                Coustomer new_costumer=new Coustomer(user_n.getEmail(),0,user_n.getPassword());
                costuemrservice.insertArtist(new_costumer);
                System.out.println("se esta pasasndo despues de la insercion");
            }

            return Response.created(UriBuilder.fromResource(UserResource.class).path(username).build())
                    .entity(user)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
        }

    }

    @POST
    @Path("/recargar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response  recarcgar(
            @FormParam("username") String username,
            @FormParam("password") String password,
            @FormParam("fcoins") String fcoins

    ){


        Usuaarioresorce bass =new Usuaarioresorce(conn);
        ArtistaService artistaservice=new ArtistaService(conn);
        CoustumerService costuemrservice=new CoustumerService(conn);

        System.out.println("linea 218");
        List<Usuario> users = bass.listusers();
        System.out.println(username+" este es el username");
        System.out.println(password+" este es el password");
        System.out.println(fcoins+" este es fcoins");
        System.out.println();

        int saldo = Integer.parseInt(fcoins);

        Usuario user_n = users.stream()
                .filter(u -> u.getEmail().equals(username)&&u.getEmail().equals(password))
                .findFirst()
                .orElse(null);

        System.out.println("linea 232");
        if (user_n!=null){
        if(user_n.getRole().equals("Artist")){
            List<Artista> art=artistaservice.listartista();
            Artista art_n = art.stream()
                    .filter(u -> u.getEmail().equals(username)&&u.getEmail().equals(password))
                    .findFirst()
                    .orElse(null);
            artistaservice.updateartist(new Artista(username,art_n.getFcoins()+saldo,password));


        }
        else if(user_n.getRole().equals("Costumer")){
            List<Coustomer> cos=costuemrservice.listarcoustumer();
            Coustomer cos_n = cos.stream()
                    .filter(u -> u.getEmail().equals(username)&&u.getEmail().equals(password))
                    .findFirst()
                    .orElse(null);
            costuemrservice.updatecoustumer(new Coustomer(username,cos_n.getFcoins()+saldo,password));

        }
            System.out.println("linea 253");
            System.out.println("linea nueva 254");
            System.out.println("Este el email usuario "+ user_n.getEmail());
            return Response.ok()
                    .entity(user_n)
                    .build();

        }

        else {
            System.out.println("esta es la linea 263");
            return Response.status(404)
                    .entity(new ExceptionMessage(404, "User not found"))
                    .build();
        }
    }
    @POST
    @Path("/formindex")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createlogin(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response

            // @FormParam("username") String username,
            //@FormParam("password") String password
    ) throws Exception  {

        try{

            List<User> users = new UserService().getUsers().get();
            System.out.println("esta es el username de createlogin "+request.getParameter("username"));
            System.out.println("linea 131");
            System.out.println("linea 132");
            User user = users.stream()
                    .filter(u -> u.getUsername().equals(request.getParameter("username")) && u.getPassword().equals(request.getParameter("password")))
                    .findFirst()
                    .orElse(null);
            System.out.println("linea 137");

            if (user != null) {
                System.out.println("linea 139");
                System.out.println("linea nueva 141");
                System.out.println("esta es la nueva linea 142");



                //return Response.temporaryRedirect(URI.create("./Users/formindex")).build();
                request.setAttribute("usuername",user.getUsername());
                request.setAttribute("Fcoins",user.getFcoins());
                request.setAttribute("role",user.getRole());

               if(user.getRole().equals("Artist")){
                 System.out.println("Crear artista");

                return Response.temporaryRedirect(new URI(StringUtils.join("http://localhost:8080/Taller4-1.0-SNAPSHOT/Artista.jsp"))).build();
               }

               else if(user.getRole().equals("Costumer")) {
                   System.out.println("Crear costumer");
                   return Response.temporaryRedirect(new URI(StringUtils.join("http://localhost:8080/Taller4-1.0-SNAPSHOT/comprador.jsp"))).build();

               }
            } else {
                System.out.println("linea 133");
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "User not found"))
                        .build();
            }

        } catch (IOException e) {
            System.out.println("linea 73");
            return Response.serverError().build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return  null;
    }

      @POST
      @Path("/formarecarga")
      @Produces(MediaType.APPLICATION_JSON)
      @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
      public Response createcarga(
              @Context HttpServletRequest request,
              @Context HttpServletResponse response

      ) throws Exception  {
          System.out.println("linea 187");
          UserService userservice=new UserService();
          System.out.println("esta es la linea 19 de servelets fcoins");
          response.setContentType("text/html");
          System.out.println("esta pasando en el servidor de Fcoinsserver");
          String Username=request.getParameter("username");
          String password=request.getParameter("password");
          String Fcoins=request.getParameter("fcoins");
          request.setAttribute("username",Username);

          userservice.mandarfcoins(Username,password,Fcoins,context.getRealPath("") + File.separator);



          return Response.temporaryRedirect(new URI(StringUtils.join("http://localhost:8080/Taller4-1.0-SNAPSHOT/LoadS.jsp"))).build();
      }


}




