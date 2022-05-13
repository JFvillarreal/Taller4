package co.edu.unbosque.taller4.Dto;

import com.opencsv.bean.CsvBindByName;

public class Pieza {
    @CsvBindByName
    private String titulo;

    @CsvBindByName
    private String precio;

    @CsvBindByName
    private String artist;

    @CsvBindByName
    private String img;

    @CsvBindByName
    private String collection;

    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getArtist() {
        return artist;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
