package a0817moact03c_2.a0817moact03c_02.Controller;

import android.content.Context;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.DAO.PeliculasDAODB;
import a0817moact03c_2.a0817moact03c_02.DAO.PeliculasDAOInternet;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;

/**
 * Created by ma on 11/11/17.
 */

public class PeliculasController {

    public void getMoviesList(final ResultListener<List<Pelicula>> listenerFromView, final Context context){

        if(HTTPConnectionManager.isNetworkingOnline(context)){

            PeliculasDAOInternet peliculasDAOInternet = new PeliculasDAOInternet();

            //SI ESTOY ONLINE PIDO AL DAO QUE ME TRAIGA LAS COSAS DESDE EL SERVICIO
            peliculasDAOInternet.getAllMoviesPlayinhNowFromInternet(new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {
                    PeliculasDAODB peliculasDAODB = new PeliculasDAODB(context);
                    peliculasDAODB.addPosts(resultado);

                    listenerFromView.finish(resultado);
                }
            });
        }
        else{
            //CASO OFFLINE: Solicito al DAO la lista de POST que esta almacenada en la base de datos
            PeliculasDAODB peliculasDAODB = new PeliculasDAODB(context);

            List<Pelicula> postList = peliculasDAODB.getAllMoviesFromDatabase();

            //Le aviso al listener de la vista que ya tengo la lista.
            listenerFromView.finish(postList);
        }
    }


    public void getMoviesFromGenre(final ResultListener<List<Pelicula>> listenerFromView,Integer genreID,Context context) {

        if(HTTPConnectionManager.isNetworkingOnline(context)) {
            PeliculasDAOInternet peliculasDAOInternet = new PeliculasDAOInternet();
           // peliculasDAOInternet.getMoviesFromGenre();
        }


    }
}
