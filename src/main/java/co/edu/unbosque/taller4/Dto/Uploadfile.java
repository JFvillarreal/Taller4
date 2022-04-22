package co.edu.unbosque.taller4.Dto;

import co.edu.unbosque.taller4.service.ImageServices;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "uploadFile", value = "/uploadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Uploadfile extends HttpServlet {
    private String UPLOAD_DIRECTORY = "uploads";
    private ImageServices imageServices;
    public Uploadfile(){
        this.imageServices=new ImageServices();
    }
    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Extracting parameters other than uploaded file
        System.out.println("Name: " + request.getParameter("name"));
        System.out.println("esta es la linea 24 ");

        // Getting an instance of the upload path
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

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
            System.out.println("este es el servelet context "+getServletContext().getRealPath("")+File.separator);
            String path=getServletContext().getRealPath("")+File.separator;
            for (Part part : request.getParts()) {
                // Storing the file using the same name
                part.write(uploadPath + File.separator + fileName);
                System.out.println("Este es el alfa numerico"+fileName);
                System.out.println("Este es nombre original"+part.getSubmittedFileName());


            }
            imageServices.create_peace(request.getParameter("titulo"),request.getParameter("fcoins"),request.getParameter("artist"),fileName,getServletContext().getRealPath("") + File.separator);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Redirecting
        response.sendRedirect("./resultado.html");
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