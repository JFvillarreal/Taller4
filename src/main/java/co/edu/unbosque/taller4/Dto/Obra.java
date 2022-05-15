package co.edu.unbosque.taller4.Dto;

public class Obra {

    private Integer colecction;
    private Integer pieceid;
    private String imagen ;
    private String titulo;

    public Obra(Integer colecction, Integer pieceid,String imagen,String titulo) {
        this.colecction = colecction;
        this.imagen=imagen;
        this.titulo=titulo;
        this.pieceid=pieceid;
    }

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
