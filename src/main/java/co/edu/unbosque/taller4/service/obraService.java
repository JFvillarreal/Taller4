package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.Artista;
import co.edu.unbosque.taller4.Dto.Obra;
import co.edu.unbosque.taller4.Dto.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class obraService {
    private Connection conn;



    public obraService(Connection conn) {
        this.conn = conn;
    }
    public Connection connect() throws SQLException {
        String url="jdbc:postgresql://localhost/Arte";
        String user="postgres";
        String password="jota73456";
        return DriverManager.getConnection(url, user, password);
    }

    public long insertobra(Obra user){
        System.out.print("se esta pasando por la funcion de insertuser");
        String SQL= "INSERT INTO obra(colecctionid, pieceid, precio,titulo,imagen,owner)"+"VALUES(?,?,?,?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, user.getColecction());
            pstmt.setInt(2, user.getPieceid());
            pstmt.setInt(3, user.getPrecio());
            pstmt.setString(4,user.getTitulo());
            pstmt.setString(5,user.getImagen());
            pstmt.setString(6,user.getOwner());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                        System.out.println("se esta ingresando el artista en linea 88");
                    }
                } catch (SQLException ex) {
                    System.out.println("se esta pasasndo por la primera exception");
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.print("se esta pasasndo por el segundo error");
            System.out.println(ex.getMessage());
        }
        return id;

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

    public List<Obra> listaobra(){
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
                Integer precio=rs.getInt("precio");
                String owner=rs.getString("owner");


                // Creating a new UserApp class instance and adding it to the array list
                Obra obra_n=new Obra();
                obra_n.setOwner(owner);
                obra_n.setPrecio(precio);
                obra_n.setColecction(colecctionid);
                obra_n.setImagen(imagen);
                obra_n.setTitulo(titulo);
                obra_n.setPieceid(pieceid);
                art.add(obra_n);

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
        return art;
    }
}
