package a0817moact03c_2.a0817moact03c_02.Controller;

import android.content.Context;

import java.util.List;

//import a0817moact03c_2.a0817moact03c_02.DAO.SeriesDAODB;
import a0817moact03c_2.a0817moact03c_02.DAO.SeriesDAODB;
import a0817moact03c_2.a0817moact03c_02.DAO.SeriesDAOInternet;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;

/**
 * Created by ma on 23/11/17.
 */

public class SeriesController {

    public void getPopularSeriesList(final ResultListener<List<Serie>> listenerFromView, final Context context) {

        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            SeriesDAOInternet seriesDAOInternet = new SeriesDAOInternet();

            seriesDAOInternet.getAllPopularSeries(new ResultListener<List<Serie>>() {
                @Override
                public void finish(List<Serie> resultado) {
                    SeriesDAODB seriesDAODB = new SeriesDAODB(context);
                    seriesDAODB.addPosts(resultado);
                    listenerFromView.finish(resultado);
                }
            });
        } else {
            SeriesDAODB seriesDAODB = new SeriesDAODB(context);

            List<Serie> seriesList = seriesDAODB.getAllSeriesFromDatabase();

            listenerFromView.finish(seriesList);
        }


    }

    public void getTopSeriesList(final ResultListener<List<Serie>> listenerFromView, final Context context) {

        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            SeriesDAOInternet seriesDAOInternet = new SeriesDAOInternet();

            seriesDAOInternet.getAllTopSeries(new ResultListener<List<Serie>>() {
                @Override
                public void finish(List<Serie> resultado) {
                    SeriesDAODB seriesDAODB = new SeriesDAODB(context);
                    seriesDAODB.addPosts(resultado);
                    listenerFromView.finish(resultado);
                }
            });
        } else {
            SeriesDAODB seriesDAODB = new SeriesDAODB(context);

            List<Serie> seriesList = seriesDAODB.getAllSeriesFromDatabase();

            listenerFromView.finish(seriesList);
        }


    }
    public void getSerieSimilarList(final ResultListener<List<Serie>> listenerFromView, final Context context,String unId){

        if(HTTPConnectionManager.isNetworkingOnline(context)){

            SeriesDAOInternet seriesDAOInternet = new SeriesDAOInternet();

            //SI ESTOY ONLINE PIDO AL DAO QUE ME TRAIGA LAS COSAS DESDE EL SERVICIO
            seriesDAOInternet.getAllSeriesSimilar(new ResultListener<List<Serie>>() {
                @Override
                public void finish(List<Serie> resultado) {
                    SeriesDAODB SERIEDAODB = new SeriesDAODB(context);
                    SERIEDAODB.addPosts(resultado);

                    listenerFromView.finish(resultado);
                }
            },unId);
        }
        else{
            //CASO OFFLINE: Solicito al DAO la lista de POST que esta almacenada en la base de datos
            SeriesDAODB serieDAODB = new SeriesDAODB(context);

            List<Serie> postList = serieDAODB.getAllSeriesFromDatabase();

            //Le aviso al listener de la vista que ya tengo la lista.
            listenerFromView.finish(postList);
        }
    }

    public void getSeriesEnTvList(final ResultListener<List<Serie>> listenerFromView, final Context context) {

        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            SeriesDAOInternet seriesDAOInternet = new SeriesDAOInternet();

            seriesDAOInternet.getAllSeriesEnTv(new ResultListener<List<Serie>>() {
                @Override
                public void finish(List<Serie> resultado) {
                    SeriesDAODB seriesDAODB = new SeriesDAODB(context);
                    seriesDAODB.addPosts(resultado);
                    listenerFromView.finish(resultado);
                }
            });
        } else {
            SeriesDAODB seriesDAODB = new SeriesDAODB(context);

            List<Serie> seriesList = seriesDAODB.getAllSeriesFromDatabase();

            listenerFromView.finish(seriesList);
        }


    }

    public void getSerieDetail(final ResultListener<List<Serie>> listenerFromView,String serieID, final Context context) {

        if (HTTPConnectionManager.isNetworkingOnline(context)) {
            SeriesDAOInternet seriesDAOInternet = new SeriesDAOInternet();

            seriesDAOInternet.getSerieDetail(new ResultListener<List<Serie>>() {
                @Override
                public void finish(List<Serie> resultado) {
                    SeriesDAODB seriesDAODB = new SeriesDAODB(context);
                    seriesDAODB.addPosts(resultado);
                    listenerFromView.finish(resultado);
                }
            },serieID);
        } else {
            SeriesDAODB seriesDAODB = new SeriesDAODB(context);

            List<Serie> seriesList = seriesDAODB.getAllSeriesFromDatabase();

            listenerFromView.finish(seriesList);
        }


    }
}
