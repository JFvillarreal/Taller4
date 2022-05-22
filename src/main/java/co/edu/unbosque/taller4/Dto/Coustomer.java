package co.edu.unbosque.taller4.Dto;

public class Coustomer {
    private String email;
    private Integer fcoins;
    private String password;



    public Coustomer(String email, Integer fcoins,String password) {
        this.email = email;
        this.fcoins=fcoins;
        this.password=password;
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

    @Override
    public String toString() {
        return "Artista{" +
                "email='" + email + '\'' +
                ", fcoins=" + fcoins +


                '}';
    }
}


