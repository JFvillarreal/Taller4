package co.edu.unbosque.taller4.resource;
import co.edu.unbosque.taller4.Dto.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Usuaarioresorce {
    private  Connection   conn;

    public Usuaarioresorce(Connection conn){
        this.conn=conn;
    }

    public void listusers(){
        Statement stmt=null;

        List<Usuario> lista_usuarios =new ArrayList<Usuario>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM usuario";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String email = rs.getString("email");
                String password = rs.getString("password");
                String role = rs.getString("role");

                // Creating a new UserApp class instance and adding it to the array list
                lista_usuarios.add(new Usuario(email, password, role));
            }

            // Printing results
            System.out.println("Email | Password | Role");
            for (Usuario user : lista_usuarios) {
                System.out.println(user.getEmail()+" | "+user.getPassword()+" | "+user.getRole());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + lista_usuarios.size() + "\n");

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
    public Connection connect() throws SQLException {
        String url="jdbc:postgresql://localhost/postgres";
        String user="postgres";
        String password="Holapgadmin1999";
        return DriverManager.getConnection(url, user, password);
    }

    public long insertuser(Usuario user){
        String SQL= "INSERT INTO usuario(email, password, role)"+"VALUES(?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
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

    public static void main(String[] args) {
        Usuario user=new Usuario("email","1234","Artist");

    }
}
