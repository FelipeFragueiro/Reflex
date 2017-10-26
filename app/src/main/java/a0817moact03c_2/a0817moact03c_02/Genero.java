package a0817moact03c_2.a0817moact03c_02;

/**
 * Created by ma on 26/10/17.
 */

public class Genero {
    private String genero;
    private int imagen;

    public Genero(String nombre, int imagen) {
        this.genero = nombre;
        this.imagen = imagen;
    }

    public String getNombre() {
        return genero;
    }

    public int getImagen() {
        return imagen;
    }
}


