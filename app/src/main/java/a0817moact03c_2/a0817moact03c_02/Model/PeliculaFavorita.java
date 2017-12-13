package a0817moact03c_2.a0817moact03c_02.Model;

/**
 * Created by Ines on 11/12/2017.
 */

public class PeliculaFavorita {
    private String title;
    private String userID;
    private String id;
    private String poster_path;
    private String genre;
    private String overview;
    private int posicion;
    private String release_date;
    private String key;
    private String serieOpeli;

    public String getSerieOpeli() {
        return serieOpeli;
    }

    public void setSerieOpeli(String serieOpeli) {
        this.serieOpeli = serieOpeli;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
}
