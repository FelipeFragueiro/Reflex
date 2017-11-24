package a0817moact03c_2.a0817moact03c_02.DAO;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.Model.SeriesContainer;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.Util.TMDBHelper;

/**
 * Created by ma on 23/11/17.
 */

public class SeriesDAOInternet {

    public void getAllPopularSeries(final ResultListener<List<Serie>> listResultListener){
        Integer pagina = 1;
        RetrieveSeriesTask retrieveSeriesTask = new RetrieveSeriesTask(listResultListener,pagina);
        retrieveSeriesTask.execute();
    }


    public class RetrieveSeriesTask extends AsyncTask<String, Void, List<Serie>>{
        private Integer pagina;
        private ResultListener<List<Serie>>listener;

        public RetrieveSeriesTask(ResultListener<List<Serie>> listener,Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            String input = null;

            try{
                input = httpConnectionManager.getRequestString(TMDBHelper.getTVPopular(TMDBHelper.language_ENGLISH,pagina));
            }catch (Exception e){
                e.printStackTrace();
            }
            Gson gson = new Gson();
            SeriesContainer seriesContainer = gson.fromJson(input,SeriesContainer.class);

            return seriesContainer.getResults();
        }
        protected void onPostExecute(List<Serie> serieList) {

            this.listener.finish(serieList);
        }
    }
}
