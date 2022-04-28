package co.edu.unbosque.taller4.resource;


import co.edu.unbosque.taller4.Dto.Pieza;
import co.edu.unbosque.taller4.Dto.User;
import co.edu.unbosque.taller4.service.ImageServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;

import co.edu.unbosque.taller4.service.UserService;
import org.jboss.resteasy.plugins.providers.multipart.*;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Random;


@Path("/Users/{username}/imagen")
public class ArtesResource {

    @Context
    ServletContext context;

    private final String UPLOAD_DIRECTORY = "/imagen/";

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadFile(@PathParam("username") String username,
                               @Context HttpServletRequest request,
                               @Context HttpServletResponse response,
                              MultipartFormDataInput input) throws IOException {
        List<Pieza> pieza = new ImageServices().getPieces().get();
         String contextPath =context.getRealPath("") + File.separator;
        System.out.println("linea 35 del uplodfile resource");
        System.out.println("este es el multipart "+input.getFormDataMap());
        // Getting the file from form input
        Map<String, List<InputPart>> formParts = input.getFormDataMap();
        System.out.println("este es el formparts "+formParts);
        List<InputPart> inputParts = formParts.get("multiPartServlet");
        List<InputPart> inputParts2 = formParts.get("fcoins");
        List<InputPart> inputParts3 = formParts.get("titulo");
        List<InputPart> inputParts4 = formParts.get("colecction");

        System.out.println("esta es la linea despues de unpitparts");
        System.out.println("este es el inputparts "+inputParts2);
        System.out.println("este es el inputparts "+inputParts3);
        System.out.println("este es el inputparts "+inputParts4);
        String precio=request.getParameter("fcoins");
        System.out.println("este es el nombre de precio "+precio);
        String titulo= String.valueOf(formParts.get("fcoins").toString());
        System.out.println("este es el nombre de titulo "+titulo);
        String colection=request.getParameter("colecction");
        for (InputPart inputPart : inputParts) {
            try {


                // Retrieving headers and reading the Content-Disposition header to file name
                MultivaluedMap<String, String> headers = inputPart.getHeaders();
                String fileName = crear()+".jpg";
                System.out.println("este es el nombre de filename "+fileName);

                // Handling the body of the part with an InputStream
                InputStream istream = inputPart.getBody(InputStream.class,null);

                Pieza img2 = new ImageServices().create_peace(titulo,precio,username,fileName,colection,contextPath);
                System.out.println("este es el nombre de filename "+colection);
                saveFile(istream, fileName, context);
            } catch (IOException e) {
                return Response.serverError().build();
            }
        }

        return Response.status(201)
                .entity("Avatar successfully uploaded for " + username)
                .build();
    }

    // Parse Content-Disposition header to get the file name
    private String parseFileName(MultivaluedMap<String, String> headers) {
        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"","");
                return fileName;
            }
        }

        return "unknown";
    }

    // Save uploaded file to a defined location on the server
    private void saveFile(InputStream uploadedInputStream, String fileName, ServletContext context) {
        int read = 0;
        byte[] bytes = new byte[1024];

        try {
            // Complementing servlet path with the relative path on the server
            String uploadPath = context.getRealPath("") + UPLOAD_DIRECTORY;

            // Creating the upload folder, if not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            // Persisting the file by output stream
            OutputStream outpuStream = new FileOutputStream(uploadPath + fileName);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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





