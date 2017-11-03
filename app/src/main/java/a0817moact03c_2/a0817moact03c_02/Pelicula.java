package a0817moact03c_2.a0817moact03c_02;

/**
 * Created by ma on 26/10/17.
 */

public class Pelicula {
    private String pelicula;
    private int imagen;
    private String genero;
    private String descripcion;
    private int posicion;

    public Pelicula(String nombre, int imagen, String genero,String descripcion, int posicion) {
        this.pelicula = nombre;
        this.imagen = imagen;
        this.genero = genero;
        this.descripcion= descripcion;
        this.posicion = posicion;

    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
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

    public int getPosicion() {
        return posicion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}


