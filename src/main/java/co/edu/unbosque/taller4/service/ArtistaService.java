package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.Artista;
import co.edu.unbosque.taller4.Dto.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistaService {
    // Objects for handling connection
    private Connection conn;



    public ArtistaService(Connection conn) {
        this.conn = conn;
    }

    public void updateartist(Artista artista) {
        // Object for handling SQL statement
        PreparedStatement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Updating owner...");
            stmt = this.conn.prepareStatement("UPDATE artist SET fcoins = ? WHERE email = ?");
            stmt.setString(2, artista.getEmail());
            System.out.println(artista.getEmail()+ " linae 29");
            stmt.setInt(1, artista.getFcoins());
            System.out.println(artista.getFcoins()+ " linae 31");
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

    public List<Artista> listartista(){
        Statement stmt=null;

        List<Artista> art=new ArrayList<Artista>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing artista...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM artist";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String email = rs.getString("email");
                Integer fcoins = rs.getInt("fcoins");
                String password=rs.getString("password");

                // Creating a new UserApp class instance and adding it to the array list
                art.add(new Artista(email,fcoins,password));

            }

            // Printing results
            System.out.println("Email | fcoins");
            for (Artista arte : art) {
                System.out.println(arte.getEmail()+" | "+arte.getFcoins());
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
        return art;
    }
    public Connection connect() throws SQLException {
        String url="jdbc:postgresql://localhost/postgres";
        String user="postgres";
        String password="Holapgadmin1999";
        return DriverManager.getConnection(url, user, password);
    }
    public long insertArtist(Artista artista){
        String SQL= "INSERT INTO artist(email, fcoins,password) VALUES(?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, artista.getEmail());
            pstmt.setInt(2, artista.getFcoins());
            pstmt.setString(3, artista.getPassword());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
}




