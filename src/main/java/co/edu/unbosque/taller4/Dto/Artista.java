package co.edu.unbosque.taller4.Dto;

public class Artista {
    private String email;
    private Integer fcoins;
    private String password;
    private String descrip;



    public Artista(String email, Integer fcoins,String password,String descrip) {
        this.email = email;
        this.fcoins=fcoins;
        this.password=password;
        this.descrip=descrip;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFcoins() {
        return fcoins;
    }

    public void setFcoins(Integer fcoins) {
        this.fcoins = fcoins;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "email='" + email + '\'' +
                ", fcoins=" + fcoins +
                ", password='" + password + '\'' +
                ", descrip='" + descrip + '\'' +
                '}';
    }
}
