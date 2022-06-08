package co.edu.unbosque.taller4.Dto;

public class Obra {

    private Integer colecction;
    private Integer pieceid;
    private String imagen ;
    private String titulo;
    private Integer precio;
    private String owner;



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getPieceid() {
        return pieceid;
    }

    public void setPieceid( Integer pieceid) {
        this.pieceid = pieceid;
    }

    public Integer getColecction() {
        return colecction;
    }

    public void setColecction(Integer colecction) {
        this.colecction = colecction;
    }

    public Integer getPrecio() {
        return precio;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Obra{" +
                "colecction=" + colecction +
                ", pieceid='" + pieceid + '\'' +
                ", imagen='" + imagen + '\'' +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
