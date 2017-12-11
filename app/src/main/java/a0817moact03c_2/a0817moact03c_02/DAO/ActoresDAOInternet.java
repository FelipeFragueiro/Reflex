package a0817moact03c_2.a0817moact03c_02.DAO;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.ActoresContainer;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;
import a0817moact03c_2.a0817moact03c_02.Util.TMDBHelper;

/**
 * Created by ma on 01/12/17.
 */

public class ActoresDAOInternet {

    public void getActorDetail(final ResultListener<Actores> listenerFromController, String unId) {
        String id = unId;
        RetrieveActorDetailTask retrieveActorDetailTask = new RetrieveActorDetailTask(listenerFromController,id);
        retrieveActorDetailTask.execute();
    }

    class RetrieveActorDetailTask extends AsyncTask<Object, Object, Actores> {
        private ResultListener<Actores> listener;
        private String id;

        public RetrieveActorDetailTask(ResultListener<Actores> listener, String id) {
            this.listener = listener;
            this.id = id;
        }

        @Override
        protected Actores doInBackground(Object... strings) {
            HTTPConnectionManager connectionManager = new HTTPConnectionManager();
            String input = null;

            try {
                input = connectionManager.getRequestString(TMDBHelper.getPersonDetails(id,TMDBHelper.language_SPANISH));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Gson gson = new Gson();
            ActoresContainer actoresContainer = gson.fromJson(input, ActoresContainer.class);

            return actoresContainer.getActor();
        }

        @Override
        protected void onPostExecute(Actores actores) {
            super.onPostExecute(actores);
            listener.finish(actores);
        }
    }
}
