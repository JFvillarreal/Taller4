import co.edu.unbosque.taller4.Dto.Artista;
import co.edu.unbosque.taller4.Dto.Colecction;
import co.edu.unbosque.taller4.Dto.Coustomer;
import co.edu.unbosque.taller4.resource.Usuaarioresorce;
import co.edu.unbosque.taller4.service.ArtistaService;
import co.edu.unbosque.taller4.service.ColecctionService;
import co.edu.unbosque.taller4.service.CoustumerService;
import co.edu.unbosque.taller4.service.obraService;

import java.sql.*;
import java.sql.DriverManager;

public class Main {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "";

    public static void main(String[] args) {
        System.out.println("pasa linea 12");

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

            //artista.updateartist(new Artista("hugo@gmail.com", 250));


            CoustumerService costumer = new CoustumerService(conn);
            //costumer.updatecoustumer(new Coustomer("h@gmail.com", 40));

            costumer.listarcoustumer();

            ColecctionService col= new ColecctionService(conn);
            col.updatecolection(new Colecction("h@gmail.com", 40,"dd",9));
            col.listacolection();

            obraService obra =new obraService(conn);
            obra.listaobra();
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

