package a0817moact03c_2.a0817moact03c_02.Model;

import java.util.List;

/**
 * Created by Rodrigo on 1/12/2017.
 */

public class Favoritos {
    private List<Pelicula> listaDePeliculas;
    private List<Serie> listaDeSeries;


    public Favoritos(List<Pelicula> listaDePeliculas, List<Serie> listaDeSeries) {
        this.listaDePeliculas = listaDePeliculas;
        this.listaDeSeries = listaDeSeries;
    }

    public List<Pelicula> getListaDePeliculas() {
        return listaDePeliculas;
    }

    public void setListaDePeliculas(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
    }

    public List<Serie> getListaDeSeries() {
        return listaDeSeries;
    }

    public void setListaDeSeries(List<Serie> listaDeSeries) {
        this.listaDeSeries = listaDeSeries;
    }

    public void agregarPeliculaAFavorito (Pelicula unaPelicula){
        //agrega a la pelicula seleccionada a la lista de peliculas favoritas, dicha lista
        // de peliculas se ubica en el recycler de Favoritos



    }


    public void  agregarSerieAFavorito(Serie unaSerie){


    }


}
