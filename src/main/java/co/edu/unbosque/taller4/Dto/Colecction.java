package co.edu.unbosque.taller4.Dto;

public class Colecction {
    private Integer precio;
    private String email;
    private String titulo;
    private int colecctionid;



    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getColecctionid() {
        return colecctionid;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setColecctionid(int colecctionid) {
        this.colecctionid = colecctionid;
    }

    @Override
    public String toString() {
        return "Colecction{" +
                "colecctionid='" + colecctionid + '\'' +
                ", precio=" + precio +
                ", email='" + email + '\'' +
                '}';
    }
}
