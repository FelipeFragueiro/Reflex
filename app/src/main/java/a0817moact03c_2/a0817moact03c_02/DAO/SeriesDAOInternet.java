package a0817moact03c_2.a0817moact03c_02.DAO;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.ActoresContainer;
import a0817moact03c_2.a0817moact03c_02.Model.Serie;
import a0817moact03c_2.a0817moact03c_02.Model.SeriesContainer;
import a0817moact03c_2.a0817moact03c_02.Model.Trailer;
import a0817moact03c_2.a0817moact03c_02.Model.TrailerContainer;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.Util.TMDBHelper;

/**
 * Created by ma on 23/11/17.
 */

public class SeriesDAOInternet {

    public void getAllPopularSeries(final ResultListener<List<Serie>> listResultListener){
        Integer pagina = 1;
        RetrieveSeriesPopularesTask retrieveSeriesPopularesTask = new RetrieveSeriesPopularesTask(listResultListener,pagina);
        retrieveSeriesPopularesTask.execute();
    }
    public void getAllTVByGenre(final ResultListener<List<Serie>> listenerFromController, String unGenero) {
        Integer pagina = 1;
        String genero = unGenero;
        RetrieveTVFromGenreTask retrieveMoviesFromGenreTask = new RetrieveTVFromGenreTask(listenerFromController, pagina, genero);
        retrieveMoviesFromGenreTask.execute();
    }
    public void getAllSeriesSimilar(final ResultListener<List<Serie>> listenerFromController,String unId) {
        Integer pagina = 1;
        String id = unId;
        RetrieveSerieSimilarTask retrieveMoviesSimilarTask = new RetrieveSerieSimilarTask(listenerFromController, pagina,id);
        retrieveMoviesSimilarTask.execute();
    }
    public void getSerieTrailer(final ResultListener<List<Trailer>> listenerFromController,String unId) {
        String id = unId;
        RetrieveMovieTrailer retrieveMoviesSimilarTask = new RetrieveMovieTrailer(listenerFromController,id);
        retrieveMoviesSimilarTask.execute();
    }
    public void getAllTopSeries(final ResultListener<List<Serie>> listResultListener){
        Integer pagina = 1;
        RetrieveSeriesTopTask retrieveSeriesTopTask = new RetrieveSeriesTopTask(listResultListener,pagina);
        retrieveSeriesTopTask.execute();
    }

    public void getAllSeriesEnTv(final ResultListener<List<Serie>> listResultListener){
        Integer pagina = 1;
        RetrieveSeriesEnTvTask retrieveSeriesEnTvTask = new RetrieveSeriesEnTvTask(listResultListener,pagina);
        retrieveSeriesEnTvTask.execute();
    }

    public void getSerieDetail(final ResultListener<List<Serie>> listResultListener, String unSerieId){
        String serieId = unSerieId;
        RetrieveSeriesDetailTask retrieveSeriesDetailTask = new RetrieveSeriesDetailTask(serieId,listResultListener);
        retrieveSeriesDetailTask.execute();
    }

    public void getTvCredits(final ResultListener<List<Actores>> listenerFromController, String unId) {
        String id = unId;
        RetrieveTvCreditsTask retrieveTvCreditsTask = new RetrieveTvCreditsTask(listenerFromController,id);
        retrieveTvCreditsTask.execute();
    }


    public class RetrieveSeriesPopularesTask extends AsyncTask<String, Void, List<Serie>>{
        private Integer pagina;
        private ResultListener<List<Serie>>listener;

        public RetrieveSeriesPopularesTask(ResultListener<List<Serie>> listener, Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            String input = null;

            try{
                input = httpConnectionManager.getRequestString(TMDBHelper.getTVPopular(TMDBHelper.language_SPANISH,pagina));
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

    class RetrieveTVFromGenreTask extends AsyncTask<String, Void, List<Serie>> {
        private ResultListener<List<Serie>> listener;
        private Integer pagina;
        private String genero;

        public RetrieveTVFromGenreTask(ResultListener<List<Serie>> listener, Integer pagina, String genero) {
            this.listener = listener;
            this.pagina = pagina;
            this.genero = genero;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getTVByGenre(genero, pagina, TMDBHelper.language_SPANISH));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            SeriesContainer peliculasContainer = gson.fromJson(input,SeriesContainer.class);

            return peliculasContainer.getResults();
        }

        protected void onPostExecute(List<Serie> peliculaList) {

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
                input = connectionManager.getRequestString(TMDBHelper.getSerieTrailer(id,TMDBHelper.language_SPANISH));
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

    class RetrieveSerieSimilarTask extends AsyncTask<String, Void, List<Serie>> {
        private ResultListener<List<Serie>> listener;
        private Integer pagina;
        private String id;

        public RetrieveSerieSimilarTask(ResultListener<List<Serie>> listener, Integer pagina,String id) {
            this.listener = listener;
            this.pagina = pagina;
            this.id = id;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getSimilarSeries(id,TMDBHelper.language_SPANISH, pagina));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            SeriesContainer peliculasContainer = gson.fromJson(input, SeriesContainer.class);

            return peliculasContainer.getResults();
        }

        protected void onPostExecute(List<Serie> peliculaList) {

            this.listener.finish(peliculaList);
        }
    }

    public class RetrieveSeriesTopTask extends AsyncTask<String, Void, List<Serie>>{
        private Integer pagina;
        private ResultListener<List<Serie>>listener;

        public RetrieveSeriesTopTask(ResultListener<List<Serie>> listener, Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            String input = null;

            try{
                input = httpConnectionManager.getRequestString(TMDBHelper.getTVTopRated(TMDBHelper.language_SPANISH,pagina));
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

    public class RetrieveSeriesEnTvTask extends AsyncTask<String, Void, List<Serie>>{
        private Integer pagina;
        private ResultListener<List<Serie>>listener;

        public RetrieveSeriesEnTvTask(ResultListener<List<Serie>> listener, Integer pagina) {
            this.listener = listener;
            this.pagina = pagina;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            String input = null;

            try{
                input = httpConnectionManager.getRequestString(TMDBHelper.getTVAiringToday(TMDBHelper.language_SPANISH,pagina));
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

    public class RetrieveSeriesDetailTask extends AsyncTask<String, Void, List<Serie>>{
        private String tvShowId;
        private ResultListener<List<Serie>>listener;

        public RetrieveSeriesDetailTask(String tvShowId, ResultListener<List<Serie>> listener) {
            this.tvShowId = tvShowId;
            this.listener = listener;
        }

        @Override
        protected List<Serie> doInBackground(String... strings) {
            HTTPConnectionManager httpConnectionManager = new HTTPConnectionManager();
            String input = null;

            try{
                input = httpConnectionManager.getRequestString(TMDBHelper.getTVShowDetail(tvShowId,TMDBHelper.language_SPANISH));
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

    class RetrieveTvCreditsTask extends AsyncTask<Object, Object, List<Actores>> {
        private ResultListener<List<Actores>> listener;
        private String id;

        public RetrieveTvCreditsTask(ResultListener<List<Actores>> listener, String id) {
            this.listener = listener;
            this.id = id;
        }

        @Override
        protected List<Actores> doInBackground(Object... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getTvCredits(id,TMDBHelper.language_SPANISH));
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

}
