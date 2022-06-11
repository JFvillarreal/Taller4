package co.edu.unbosque.taller4.service;

import co.edu.unbosque.taller4.Dto.Obra;
import co.edu.unbosque.taller4.Dto.Wallet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WalletHistory {
    private Connection conn;

    public WalletHistory(Connection conn){
        this.conn=conn;
    }

    public Connection connect() throws SQLException {
        String url="jdbc:postgresql://localhost/postgres";
        String user="postgres";
        String password="Holapgadmin1999";
        return DriverManager.getConnection(url, user, password);
    }
    public long insertobra(Wallet billetera){
        System.out.print("se esta pasando por la funcion de insertuser");
        String SQL= "INSERT INTO wallethistory(idwallet, email, fcoins,date)"+"VALUES(?,?,?,?)";
        long id=0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, billetera.getIdwellet());
            pstmt.setString(2, billetera.getEmail());
            pstmt.setInt(3, billetera.getFcoins());
            pstmt.setString(4,billetera.getDate());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                        System.out.println("se esta ingresando a la billetera en linea 88");
                    }
                } catch (SQLException ex) {
                    System.out.println("se esta pasasndo por la primera exception de billetera");
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.print("se esta pasasndo por el segundo error");
            System.out.println(ex.getMessage());
        }
        return id;

    }

    public List<Wallet> listaobra(){
        Statement stmt=null;

        List<Wallet> art=new ArrayList<Wallet>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing obra...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM wallethistory";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                Integer idwalletid=rs.getInt("idwallet");
                String email= rs.getString("email");
                Integer  fcoins= rs.getInt("fcoins");
                String date=rs.getString("date");



                // Creating a new UserApp class instance and adding it to the array list
                Obra obra_n=new Obra();
                Wallet cartera=new Wallet();
                cartera.setEmail(email);
                cartera.setFcoins(fcoins);
                cartera.setDate(date);
                cartera.setIdwellet(idwalletid);
                art.add(cartera);

            }

            // Printing results
            System.out.println("Idwallet | fcoins |email|date");
            for (Wallet obra : art) {
                System.out.println(obra.getIdwellet()+" | "+obra.getFcoins()+" | "+obra.getEmail()+" | "+obra.getDate());
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
