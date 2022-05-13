package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.Artista;
import co.edu.unbosque.taller4.Dto.Coustomer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoustumerService {
    private Connection conn;



    public CoustumerService(Connection conn) {
        this.conn = conn;
    }

    public void updatecoustumer(Coustomer coustomer) {
        // Object for handling SQL statement
        PreparedStatement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Updating coustumer...");
            stmt = this.conn.prepareStatement("UPDATE costumer SET fcoins = ? WHERE email = ?");
            stmt.setString(2, coustomer.getEmail());
            System.out.println(coustomer.getEmail()+ " linae 29");
            stmt.setInt(1, coustomer.getFcoins());
            System.out.println(coustomer.getFcoins()+ " linae 31");
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

    public void listarcoustumer(){
        Statement stmt=null;

        List<Coustomer> cos=new ArrayList<Coustomer>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing coustumer...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM costumer";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String email = rs.getString("email");
                Integer fcoins = rs.getInt("fcoins");

                // Creating a new UserApp class instance and adding it to the array list
                cos.add(new Coustomer(email,fcoins));

            }

            // Printing results
            System.out.println("Email | fcoins");
            for (Coustomer coscu : cos) {
                System.out.println(coscu.getEmail()+" | "+coscu.getFcoins());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + cos.size() + "\n");

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


