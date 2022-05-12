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
                System.out.println(user.toString());
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
}
