package a0817moact03c_2.a0817moact03c_02;

/**
 * Created by ma on 26/10/17.
 */

public class Genero {
    private String nombreGenero;
    private int imagenGenero;

    public Genero(String nombreGenero, int imagenGenero) {
        this.nombreGenero = nombreGenero;
        this.imagenGenero = imagenGenero;
    }

    public int getImagenGenero() {
        return imagenGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    @Override
    public String toString() {
        return  "Seleccionaste el genero = '" + nombreGenero +  '\'' ;
    }
}
