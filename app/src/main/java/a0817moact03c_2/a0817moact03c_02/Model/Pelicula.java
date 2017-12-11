package a0817moact03c_2.a0817moact03c_02.Model;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Util.TMDBHelper;

import static a0817moact03c_2.a0817moact03c_02.Util.TMDBHelper.DiccionarioDeGeneros.unDiccionario;

/**
 * Created by ma on 26/10/17.
 */

public class Pelicula {

    //hacer pelicula serialiaada para el bundle

    private String title;
    private String id;
    private String poster_path;
    private List<String> genre_ids;
    private List<String> genre_list_string;
    private String overview;
    private int posicion;
    private String release_date;

    public Pelicula(String title, String id, String poster_path, List<String> genre_ids, String overview, int posicion, String release_date) {
        this.title = title;
        this.id = id;
        this.poster_path = poster_path;
        this.genre_ids = genre_ids;
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

    public List<String> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<String> genre_ids) {
        this.genre_ids = genre_ids;
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

    public void setOverview(String overview) {
        this.overview = overview;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }


}



