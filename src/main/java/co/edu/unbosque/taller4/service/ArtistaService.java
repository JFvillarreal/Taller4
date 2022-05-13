package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.Artista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaService {
    // Objects for handling connection
    private Connection conn;

    public ArtistaService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers() {
        // Object for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        List<Artista> artista= new ArrayList<Artista>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM usuario";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                Integer fcois= rs.getInt("fcoins");
                String email = rs.getString("email");

                String role = rs.getString("role");

                // Creating a new UserApp class instance and adding it to the array list
                artista.add(new Artista(email,fcois,role));

            }

            // Printing results
            System.out.println("Email | Password | Role");
            for (Artista arte : artista) {
                System.out.println(arte.toString());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + artista.size() + "\n");

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

