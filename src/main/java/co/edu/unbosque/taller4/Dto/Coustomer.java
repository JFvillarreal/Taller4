package co.edu.unbosque.taller4.Dto;

public class Coustomer {
    private String email;
    private Integer fcoins;



    public Coustomer(String email, Integer fcoins) {
        this.email = email;


        this.fcoins=fcoins;
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


