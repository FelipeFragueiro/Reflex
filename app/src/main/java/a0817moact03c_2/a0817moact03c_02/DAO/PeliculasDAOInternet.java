package a0817moact03c_2.a0817moact03c_02.DAO;

import android.database.CursorJoiner;
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

    public void getAllMoviesPlayingNowFromInternet(final ResultListener<List<Pelicula>> listenerFromController) {
        Integer pagina = 1;
        RetrieveMoviesPlayingNowTask retrieveMoviesPlayingNowTask = new RetrieveMoviesPlayingNowTask(listenerFromController, pagina);
        retrieveMoviesPlayingNowTask.execute();
    }

    public void getAllMoviesByGenre(final ResultListener<List<Pelicula>>listenerFromController,String unGenero){
        Integer pagina = 1;
        String genero = unGenero;
        RetrieveMoviesFromGenreTask retrieveMoviesFromGenreTask = new RetrieveMoviesFromGenreTask(listenerFromController,pagina,genero);
        retrieveMoviesFromGenreTask.execute();
    }


    class RetrieveMoviesPlayingNowTask extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;
        private Integer pagina;

        public RetrieveMoviesPlayingNowTask(ResultListener<List<Pelicula>> listener, Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getNowPlayingMovies(TMDBHelper.language_SPANISH, pagina));
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


    class RetrieveMoviesFromGenreTask extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;
        private Integer pagina;
        private String genero;

        public RetrieveMoviesFromGenreTask(ResultListener<List<Pelicula>> listener, Integer pagina,String genero) {
            this.listener = listener;
            this.pagina = pagina;
            this.genero = genero;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getMoviesByGenre(genero,pagina,TMDBHelper.language_SPANISH));
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

