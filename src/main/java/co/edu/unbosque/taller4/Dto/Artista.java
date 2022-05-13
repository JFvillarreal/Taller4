package co.edu.unbosque.taller4.Dto;

public class Artista {
    private String email;
    private Integer fcoins;
    private String paswoord;

    public Artista(String email, Integer fcois, String password) {
        this.email = email;
        this.paswoord = password;

        this.fcoins=fcoins;
    }




    public String getPaswoord() {
        return paswoord;
    }

    public void setPaswoord(String paswoord) {
        this.paswoord = paswoord;
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
                ", paswoord='" + paswoord + '\'' +

                '}';
    }
}
