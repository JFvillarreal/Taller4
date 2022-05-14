package co.edu.unbosque.taller4.resource;

import co.edu.unbosque.taller4.Dto.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainUsuario {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    private Usuaarioresorce log;

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "Holapgadmin1999";



    public static void main(String[] args) {

        // Objects for handling connection
        Connection conn = null;

        try {

            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Usuaarioresorce usersService = new Usuaarioresorce(conn);
            usersService.listusers();


            System.out.println("se esta ingresando al user");

            System.out.println("se termino de ingresar usuario a la db");

            //PetsService petsService = new PetsService(conn);
            //petsService.countBySpecies("dog");

            //OwnersService ownersService = new OwnersService(conn);
            //ownersService.updateOwner(new Owner(6697, null, "Pepe"));

            // Closing database connection
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
