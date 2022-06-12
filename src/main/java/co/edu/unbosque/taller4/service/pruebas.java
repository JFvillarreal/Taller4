package co.edu.unbosque.taller4.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class pruebas {
    private static ColecctionService col;
    static final String USER = "postgres";
    static final String PASS = "Holapgadmin1999";
    static final String DB_URL = "jdbc:postgresql://localhost/postgres";
    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    public pruebas() throws SQLException {
        col=new ColecctionService(conn);
    }

    public static void main(String args[]){
        System.out.println(col.listacolection());
    }
}
