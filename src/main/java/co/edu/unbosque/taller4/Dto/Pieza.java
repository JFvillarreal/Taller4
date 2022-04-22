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
}
