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

    public void getPopularSeriesList(final ResultListener<List<Serie>> listenerFromView,final Context context){

        if(HTTPConnectionManager.isNetworkingOnline(context)){
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
}
