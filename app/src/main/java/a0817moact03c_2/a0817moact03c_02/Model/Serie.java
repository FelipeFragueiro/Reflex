package a0817moact03c_2.a0817moact03c_02.Model;

import java.util.List;

/**
 * Created by ma on 11/11/17.
 */

public class Serie {
    private String name;
    private String poster_path;
 //   private String genre_ids;
    private String overview;
    private int posicion;
    private List<Capitulo> temporadas;
    private Capitulo capitulo;

    public Serie(String name, String imagenSerie, String descripcionSerie) {
        this.name = name;
        this.poster_path = imagenSerie;
        //this.genre_ids = generoSerie;
        this.overview = descripcionSerie;
        this.posicion = posicion;
        this.temporadas = temporadas;
        this.capitulo = capitulo;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getName() {
        return name;
    }

    public String getPoster_path() {
       return  "https://image.tmdb.org/t/p/w500"+poster_path;
    }

   /* public String getGenre_ids() {
        return genre_ids;
    }
*/
    public String getOverview() {
        return overview;
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
