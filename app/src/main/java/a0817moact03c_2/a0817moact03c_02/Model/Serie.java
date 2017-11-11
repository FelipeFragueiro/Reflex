package a0817moact03c_2.a0817moact03c_02.Model;

import java.util.List;

/**
 * Created by ma on 11/11/17.
 */

public class Serie {
    private String nombreSerie;
    private int imagenSerie;
    private String generoSerie;
    private String descripcionSerie;
    private int posicion;
    private List<Capitulo> temporadas;
    private Capitulo capitulo;

    public Serie(String nombreSerie, int imagenSerie, String generoSerie, String descripcionSerie, int posicion, List<Capitulo> temporadas, Capitulo capitulo) {
        this.nombreSerie = nombreSerie;
        this.imagenSerie = imagenSerie;
        this.generoSerie = generoSerie;
        this.descripcionSerie = descripcionSerie;
        this.posicion = posicion;
        this.temporadas = temporadas;
        this.capitulo = capitulo;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombreSerie() {
        return nombreSerie;
    }

    public int getImagenSerie() {
        return imagenSerie;
    }

    public String getGeneroSerie() {
        return generoSerie;
    }

    public String getDescripcionSerie() {
        return descripcionSerie;
    }

    public int getPosicion() {
        return posicion;
    }

    public List<Capitulo> getTemporadas() {
        return temporadas;
    }

    public Capitulo getCapitulo() {
        return capitulo;
    }

}
