package a0817moact03c_2.a0817moact03c_02.DAO;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.ActoresContainer;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;
import a0817moact03c_2.a0817moact03c_02.Model.PeliculasContainer;
import a0817moact03c_2.a0817moact03c_02.Model.Trailer;
import a0817moact03c_2.a0817moact03c_02.Model.TrailerContainer;
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

    public void getAllMoviesSimilar(final ResultListener<List<Pelicula>> listenerFromController,String unId) {
        Integer pagina = 1;
        String id = unId;
        RetrieveMoviesSimilarTask retrieveMoviesSimilarTask = new RetrieveMoviesSimilarTask(listenerFromController, pagina,id);
        retrieveMoviesSimilarTask.execute();
    }

    public void getMovieTrailer(final ResultListener<List<Trailer>> listenerFromController,String unId) {
        String id = unId;
        RetrieveMovieTrailer retrieveMoviesSimilarTask = new RetrieveMovieTrailer(listenerFromController,id);
        retrieveMoviesSimilarTask.execute();
    }



    public void getMovieCredits(final ResultListener<List<Actores>> listenerFromController, String unId) {
        String id = unId;
        RetrieveMovieCreditsTask retrieveMovieCreditsTask = new RetrieveMovieCreditsTask(listenerFromController,id);
        retrieveMovieCreditsTask.execute();
    }

    public void getAllMoviesByGenre(final ResultListener<List<Pelicula>> listenerFromController, String unGenero) {
        Integer pagina = 1;
        String genero = unGenero;
        RetrieveMoviesFromGenreTask retrieveMoviesFromGenreTask = new RetrieveMoviesFromGenreTask(listenerFromController, pagina, genero);
        retrieveMoviesFromGenreTask.execute();
    }

    public void getAllUpcomingMovies(final ResultListener<List<Pelicula>> listenerFromController) {
        Integer pagina = 1;
        RetrieveUpcomingMoviesTask retrieveUpcomingMoviesTask = new RetrieveUpcomingMoviesTask(listenerFromController, pagina);
        retrieveUpcomingMoviesTask.execute();
    }

    public void getAllTopRatedMovies(final ResultListener<List<Pelicula>> listenerFromController) {
        Integer pagina = 1;
        RetrieveTopRatedMoviesTask retrieveTopRatedMoviesTask = new RetrieveTopRatedMoviesTask(listenerFromController, pagina);
        retrieveTopRatedMoviesTask.execute();
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

            Map<String,String> diccionarioGenero = TMDBHelper.DiccionarioDeGeneros.obtenerDiccionarioDeGeneros();

            for (Pelicula unaPelicula : peliculasContainer.getResults()){

                for(String unGenero : unaPelicula.getGenre_ids()){
                        diccionarioGenero.get(unGenero);
                }
        }

            return peliculasContainer.getResults();
        }

        protected void onPostExecute(List<Pelicula> peliculaList) {

            this.listener.finish(peliculaList);
        }
    }

    class RetrieveMoviesSimilarTask extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;
        private Integer pagina;
        private String id;

        public RetrieveMoviesSimilarTask(ResultListener<List<Pelicula>> listener, Integer pagina,String id) {
            this.listener = listener;
            this.pagina = pagina;
            this.id = id;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getSimilarMovies(id,TMDBHelper.language_SPANISH, pagina));
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


    class RetrieveMovieTrailer extends AsyncTask<String, Void, List<Trailer>> {
        private ResultListener<List<Trailer>> listener;
        private String id;

        public RetrieveMovieTrailer(ResultListener<List<Trailer>> listener,String id) {
            this.listener = listener;
            this.id = id;
        }

        @Override
        protected List<Trailer> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getMovieTrailer(id,TMDBHelper.language_SPANISH));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            TrailerContainer trailerContainer = gson.fromJson(input, TrailerContainer.class);

            return trailerContainer.getResults();
        }

        protected void onPostExecute(List<Trailer> trailerList) {

            this.listener.finish(trailerList);
        }
    }

    class RetrieveMovieCreditsTask extends AsyncTask<Object, Object, List<Actores>> {
        private ResultListener<List<Actores>> listener;
        private String id;

        public RetrieveMovieCreditsTask(ResultListener<List<Actores>> listener, String id) {
            this.listener = listener;
            this.id = id;
        }

        @Override
        protected List<Actores> doInBackground(Object... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getMovieCredits(id,TMDBHelper.language_SPANISH));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            ActoresContainer actoresContainer = gson.fromJson(input, ActoresContainer.class);

            return actoresContainer.getResults();
        }



        @Override
        protected void onPostExecute(List<Actores> actores) {
            super.onPostExecute(actores);
            listener.finish(actores);
        }


    }


    class RetrieveMoviesFromGenreTask extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;
        private Integer pagina;
        private String genero;

        public RetrieveMoviesFromGenreTask(ResultListener<List<Pelicula>> listener, Integer pagina, String genero) {
            this.listener = listener;
            this.pagina = pagina;
            this.genero = genero;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getMoviesByGenre(genero, pagina, TMDBHelper.language_SPANISH));
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

    class RetrieveUpcomingMoviesTask extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;
        private Integer pagina;

        public RetrieveUpcomingMoviesTask(ResultListener<List<Pelicula>> listener, Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getUpcomingMovies(TMDBHelper.language_SPANISH, pagina));
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

    class RetrieveTopRatedMoviesTask extends AsyncTask<String, Void, List<Pelicula>> {
        private ResultListener<List<Pelicula>> listener;
        private Integer pagina;

        public RetrieveTopRatedMoviesTask(ResultListener<List<Pelicula>> listener, Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Pelicula> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getTopRatedMovies(TMDBHelper.language_SPANISH, pagina));
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

