package a0817moact03c_2.a0817moact03c_02.Model;

/**
 * Created by Rodrigo on 1/12/2017.
 */

public class Favoritos {
    private Pelicula unaPelicula;
    private Serie unaSerie;


    public Favoritos(Pelicula unaPelicula, Serie unaSerie) {
        this.unaPelicula = unaPelicula;
        this.unaSerie = unaSerie;
    }

    public Pelicula getUnaPelicula() {
        return unaPelicula;
    }

    public void setUnaPelicula(Pelicula unaPelicula) {
        this.unaPelicula = unaPelicula;
    }

    public Serie getUnaSerie() {
        return unaSerie;
    }

    public void setUnaSerie(Serie unaSerie) {
        this.unaSerie = unaSerie;
    }


    public void agregarPeliculaAFavorito ( Pelicula unaPelicula){



    }


    public void  agregarSerieAFavorito(Serie unaSerie){


    }


}
