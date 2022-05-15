package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.Artista;
import co.edu.unbosque.taller4.Dto.Colecction;
import co.edu.unbosque.taller4.Dto.Obra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColecctionService {
    private Connection conn;



    public ColecctionService(Connection conn) {
        this.conn = conn;
    }

    public void updatecolection(Colecction colecction) {
        // Object for handling SQL statement
        PreparedStatement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Updating owner...");
            stmt = this.conn.prepareStatement("UPDATE colecction SET precio = ? WHERE email = ?");
            stmt.setString(2, colecction.getEmail());
            System.out.println(colecction.getEmail()+ " linae 29");
            stmt.setInt(1, colecction.getPrecio());
            System.out.println(colecction.getPrecio()+ " linae 31");
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

    public void listacolection(){
        Statement stmt=null;

        List<Colecction> art=new ArrayList<Colecction>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing colection...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM colecction";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String email = rs.getString("email");
                Integer fcoins = rs.getInt("fcoins");
                Integer colecctionid= rs.getInt("colecctionid");
                String titulo= rs.getString("titulo");

                // Creating a new UserApp class instance and adding it to the array list
                art.add(new Colecction(titulo,fcoins,email,colecctionid));

            }

            // Printing results
            System.out.println("titulo| fcoins| email| colection");
            for (Colecction col : art) {
                System.out.println(col.getTitulo()+" | "+col.getPrecio()+" | "+col.getEmail()+" | "+col.getColecctionid());
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






