package co.edu.unbosque.taller4.resource;

import co.edu.unbosque.taller4.Dto.ExceptionMessage;
import co.edu.unbosque.taller4.Dto.User;
import co.edu.unbosque.taller4.service.ImageServices;
import co.edu.unbosque.taller4.service.UserService;
import com.sun.jndi.toolkit.url.Uri;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

@Path("/Users")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UserResource {
    private String UPLOAD_DIRECTORY = "uploads";
    private ImageServices imageServices;
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
           System.out.println("Estes es el rol"+ role);


            return Response.created(UriBuilder.fromResource(UserResource.class).path(username).build())
                    .entity(user)
                    .build();
        } catch (IOException e) {
            return Response.serverError().build();
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


    @POST
   @Path("/crear")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response hacer( @Context HttpServletRequest request,
                           @Context HttpServletResponse response) throws ServletException, IOException, URISyntaxException {
        System.out.println("linea 44");
        this.imageServices=new ImageServices();
        // Extracting parameters other than uploaded file
        System.out.println("Name: " + request.getParameter("name"));
        System.out.println("esta es la linea 24 ");

        // Getting an instance of the upload path
        String uploadPath = context.getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        System.out.println(uploadPath+ " esta es la ruta ");
        System.out.println("esta es el nombre "+request.getParameter("titulo"));
        System.out.println("esta es el precio "+request.getParameter("fcoins"));
        System.out.println("esta es el user "+request.getParameter("artist"));

        File uploadDir = new File(uploadPath);

        // If path doesn`t exist, create it
        if (!uploadDir.exists()) uploadDir.mkdir();

        try {
            // Getting each part from the request
            String fileName = crear()+".jpg";
            System.out.println("este es el servelet context "+context.getRealPath("")+File.separator);
            String path=context.getRealPath("")+File.separator;
            for (Part part : request.getParts()) {
                // Storing the file using the same name
                part.write(uploadPath + File.separator + fileName);
                System.out.println("Este es el alfa numerico"+fileName);
                System.out.println("Este es nombre original"+part.getSubmittedFileName());


            }
            imageServices.create_peace(request.getParameter("titulo"),request.getParameter("fcoins"),request.getParameter("artist"),fileName,context.getRealPath("") + File.separator);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Redirecting

        return Response.temporaryRedirect(new URI(StringUtils.join("http://localhost:8080/Taller4-1.0-SNAPSHOT/loadS.jsp"))).build();
    }

    public void destroy() {}

    public String crear(){

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
        return generatedString;
    }
}




