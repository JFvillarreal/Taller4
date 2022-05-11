package co.edu.unbosque.taller4.Dto;

public class Artista {
    private String email;
    private Integer fcoins;
    private String paswoord;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
                ", role='" + role + '\'' +
                '}';
    }
}
