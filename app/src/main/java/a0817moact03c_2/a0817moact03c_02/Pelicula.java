package a0817moact03c_2.a0817moact03c_02;

/**
 * Created by ma on 26/10/17.
 */

public class Pelicula {
    private String pelicula;
    private int imagen;

    public Pelicula(String nombre, int imagen) {
        this.pelicula = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return pelicula;
    }

    public int getImagen() {
        return imagen;
    }
}


