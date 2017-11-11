package a0817moact03c_2.a0817moact03c_02.Model;

/**
 * Created by ma on 26/10/17.
 */

public class Pelicula {
    private String tittle;
    private Integer id;
    private String poster_path;
    private String generoPelicula;
    private String overview;
    private int posicion;
    private String release_date;

    public Pelicula(String tittle, Integer id, String poster_path, String generoPelicula, String overview, int posicion, String release_date) {
        this.tittle = tittle;
        this.id = id;
        this.poster_path = poster_path;
        this.generoPelicula = generoPelicula;
        this.overview = overview;
        this.posicion = posicion;
        this.release_date = release_date;
    }

    public Pelicula() {
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return tittle;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getGeneroPelicula() {
        return generoPelicula;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getOverview() {
        return overview;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setGeneroPelicula(String generoPelicula) {
        this.generoPelicula = generoPelicula;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}



