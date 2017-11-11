package a0817moact03c_2.a0817moact03c_02.DAO;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculasContainer;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.Util.TMDBHelper;

/**
 * Created by ma on 11/11/17.
 */

public class PeliculasDAOInternet {

    public void getAllMoviesPlayinhNowFromInternet(final ResultListener<List<Pelicula>> listenerFromController) {
        Integer pagina = 1;
        RetrieveMoviesTask retrievePostTask = new RetrieveMoviesTask(listenerFromController,pagina);
        retrievePostTask.execute();
    }


    class RetrieveMoviesTask extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;
        private Integer pagina;

        public RetrieveMoviesTask(ResultListener<List<Pelicula>> listener,Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getNowPlayingMovies(TMDBHelper.language_SPANISH,pagina));
            } catch (Exception e) {
                e.printStackTrace();
            }


            Gson gson = new Gson();
            PeliculasContainer peliculasContainer = gson.fromJson(input, PeliculasContainer.class);

            return peliculasContainer.getResults();
        }
        protected void onPostExecute(List<Pelicula> peliculaList) {

            this.listener.finish(peliculaList);
        }
    }
}
