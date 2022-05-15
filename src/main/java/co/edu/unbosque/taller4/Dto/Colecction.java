package co.edu.unbosque.taller4.Dto;

public class Colecction {
    private Integer colecctionid;
    private Integer precio;
    private String email;
    private String titulo;

    public Colecction(String titulo, Integer precio,String email,Integer colecctionid) {
        this.colecctionid = colecctionid;
        this.email=email;
        this.precio=precio;
        this.titulo=titulo;
    }

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

    public Integer getColecctionid() {
        return colecctionid;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setColecctionid(Integer colecctionid) {
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
