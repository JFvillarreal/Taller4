package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.Artista;
import co.edu.unbosque.taller4.Dto.Obra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class obraService {
    private Connection conn;



    public obraService(Connection conn) {
        this.conn = conn;
    }

    public void updateobra(Obra obra) {
        // Object for handling SQL statement
        PreparedStatement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Updating owner...");
            stmt = this.conn.prepareStatement("UPDATE obra SET imagen=? SET titulo=? SET fcoins = ? WHERE colectionid = ?");
            stmt.setString(1, obra.getTitulo());
            System.out.println(obra.getTitulo()+ " linae 29");
            stmt.setInt(2, obra.getPieceid());
            System.out.println(obra.getPieceid()+ " linae 31");
            int rowsUpdated = stmt.executeUpdate(); // executeUpdate is also used for inserting records

            // Printing results
            System.out.println("Rows updated: " + rowsUpdated + "\n");

            // Closing resources
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void listaobra(){
        Statement stmt=null;

        List<Obra> art=new ArrayList<Obra>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing obra...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM obra";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                Integer colecctionid=rs.getInt("colecctionid");
                Integer pieceid= rs.getInt("pieceid");
                String  imagen= rs.getString("imagen");
                String titulo=rs.getString("titulo");


                // Creating a new UserApp class instance and adding it to the array list
                art.add(new Obra(colecctionid,pieceid,imagen,titulo));

            }

            // Printing results
            System.out.println("colcctionid | precio |imgen|titulo");
            for (Obra obra : art) {
                System.out.println(obra.getColecction()+" | "+obra.getPieceid()+" | "+obra.getImagen()+" | "+obra.getTitulo());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + art.size() + "\n");

            // Closing resources
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
