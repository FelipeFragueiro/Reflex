package a0817moact03c_2.a0817moact03c_02.Model;

/**
 * Created by ma on 26/10/17.
 */

public class Pelicula {
    private String nombrePelicula;
    private int imagenPelicula;
    private String generoPelicula;
    private String descripcionPelicula;
    private int posicion;

    public Pelicula(String nombre, int imagenPelicula, String generoPelicula, String descripcionPelicula, int posicion) {
        this.nombrePelicula = nombre;
        this.imagenPelicula = imagenPelicula;
        this.generoPelicula = generoPelicula;
        this.descripcionPelicula = descripcionPelicula;
        this.posicion = posicion;

    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return nombrePelicula;
    }

    public int getImagenPelicula() {
        return imagenPelicula;
    }

    public String getGeneroPelicula() {
        return generoPelicula;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getDescripcionPelicula() {
        return descripcionPelicula;
    }
}


