package a0817moact03c_2.a0817moact03c_02.Controller;

import android.content.Context;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.DAO.FavoritosDAODB;
import a0817moact03c_2.a0817moact03c_02.DAO.PeliculasDAODB;
import a0817moact03c_2.a0817moact03c_02.DAO.PeliculasDAOInternet;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;

/**
 * Created by ma on 01/12/17.
 */

public class FavoritosController {

    public void getFavoritesList (final ResultListener<List<Pelicula>> listenerFromView, final Context context){

            //CASO OFFLINE: Solicito al DAO la lista de POST que esta almacenada en la base de datos
            FavoritosDAODB favoritosDAODB = new FavoritosDAODB(context);

            List<Pelicula> postList = favoritosDAODB.getAllMoviesFavoriteFromDatabase();

            //Le aviso al listener de la vista que ya tengo la lista.
            listenerFromView.finish(postList);
        }
    }



