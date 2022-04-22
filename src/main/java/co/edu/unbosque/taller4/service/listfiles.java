package co.edu.unbosque.taller4.service;


import co.edu.unbosque.taller4.Dto.Pieza;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listFiles", value = "/list-files")
public class listfiles extends HttpServlet {
    private String UPLOAD_DIRECTORY = "uploads";
    private ImageServices imageServices;
    public listfiles(){
        this.imageServices=new ImageServices();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {



        // Specifying the content type for the response
        response.setContentType("application/json");
        System.out.println("fiel en doget de la linea 25");
        // Getting an instance of the upload path
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);

        // Listing file names in path
        List<String> files = new ArrayList<String>();
        List<Pieza> piezas=imageServices.getPieces().get();



        for(int i=0;i< uploadDir.listFiles().length;i++){
            files.add(UPLOAD_DIRECTORY + File.separator + uploadDir.listFiles()[i].getName()+"&&"+piezas.get(i).getArtist()+"&&"+
                    piezas.get(i).getPrecio()+"&&"+piezas.get(i).getTitulo());
        }

        // Adding the data to response, parsing it to json using Gson library
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(files));
    }



}