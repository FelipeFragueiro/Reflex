package a0817moact03c_2.a0817moact03c_02;

/**
 * Created by ma on 26/10/17.
 */

public class Pelicula {
    private String pelicula;
    private int imagen;
    private String genero;

    public Pelicula(String nombre, int imagen, String genero) {
        this.pelicula = nombre;
        this.imagen = imagen;
        this.genero = genero;
    }

    public String getNombre() {
        return pelicula;
    }

    public int getImagen() {
        return imagen;
    }

    public String getGenero() {
        return genero;
    }
}


