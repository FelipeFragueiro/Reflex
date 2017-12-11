package a0817moact03c_2.a0817moact03c_02.Controller;

import android.content.Context;

import java.util.List;

import a0817moact03c_2.a0817moact03c_02.DAO.ActoresDAODB;
import a0817moact03c_2.a0817moact03c_02.DAO.ActoresDAOInternet;
import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Util.HTTPConnectionManager;
import a0817moact03c_2.a0817moact03c_02.Util.ResultListener;

/**
 * Created by ma on 01/12/17.
 */

public class ActoresController {

    public void getActorDetail(final ResultListener<Actores> listenerFromView, final Context context, String unId){

        if(HTTPConnectionManager.isNetworkingOnline(context)){

            ActoresDAOInternet actoresDAOInternet = new ActoresDAOInternet();

            //SI ESTOY ONLINE PIDO AL DAO QUE ME TRAIGA LAS COSAS DESDE EL SERVICIO
            actoresDAOInternet.getActorDetail(new ResultListener<Actores>() {
                @Override
                public void finish(Actores resultado) {
                   // ActoresDAODB actoresDAODB = new ActoresDAODB(context);
                    //actoresDAODB.addProduct(resultado);

                    listenerFromView.finish(resultado);
                }
            },unId);
        }
        else{/*
            ActoresDAODB actoresDAODB = new ActoresDAODB(context);

            List<Actores> postList = actoresDAODB.getAllActoresFromDatabase();

            listenerFromView.finish(postList.get(0));*/
        }
    }
}