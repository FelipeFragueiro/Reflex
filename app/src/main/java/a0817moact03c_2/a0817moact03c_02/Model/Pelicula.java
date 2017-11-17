package a0817moact03c_2.a0817moact03c_02.Model;

/**
 * Created by ma on 26/10/17.
 */

public class Pelicula {
    private String title;
    private Integer id;
    private String poster_path;
    private String genre;
    private String overview;
    private int posicion;
    private String release_date;

    public Pelicula(String title, Integer id, String poster_path, String genre, String overview, int posicion, String release_date) {
        this.title = title;
        this.id = id;
        this.poster_path = poster_path;
        this.genre = genre;
        this.overview = overview;
        this.posicion = posicion;
        this.release_date = release_date;
    }



    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getNombre() {
        return title;
    }

    public String getPoster_path() {
        return "https://image.tmdb.org/t/p/w500"+poster_path;
    }

    public String getGenre() {
        return genre;
    }

    public int getPosicion() {
        return posicion;
    }

    public String getOverview() {
        return overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setGenre(String genre) {
        this.genre = genre;
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



