package co.edu.unbosque.taller4.Dto;

import com.opencsv.bean.CsvBindByName;

public class User {
    @CsvBindByName
    private String username;

    @CsvBindByName
    private String password;

    @CsvBindByName
    private String role;

    @CsvBindByName
    private String Fcoins;

    @CsvBindByName
    private String correo;




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFcoins() {
        return Fcoins;
    }

    public void setFcoins(String fcoins) {
        Fcoins = fcoins;
    }
    @Override
    public String toString() {
        return String.format(getUsername(),getRole(),getPassword(),getFcoins());
    }
    public String[] toarray() {
        String[] array_string = new String[] {getUsername(),getRole(),getPassword(),getFcoins()};
        return array_string ;
    }




    }


