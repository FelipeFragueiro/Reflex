package a0817moact03c_2.a0817moact03c_02.Model;

/**
 * Created by ma on 26/10/17.
 */

public class Genero {
    private String nombreGenero;
    private int imagenGenero;
    private Integer idGenero;

    public Genero(String nombreGenero, int imagenGenero, Integer idGenero) {
        this.nombreGenero = nombreGenero;
        this.imagenGenero = imagenGenero;
        this.idGenero = idGenero;
    }

    public Integer getIdGenero() {
        return idGenero;
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
