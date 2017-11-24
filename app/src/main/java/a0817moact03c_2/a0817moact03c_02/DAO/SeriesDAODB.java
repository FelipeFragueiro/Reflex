package a0817moact03c_2.a0817moact03c_02.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Serie;






public class SeriesDAODB extends DataBaseHelper {


    public static final String name = "nombre_serie";
    public static final String genre_ids = "genero_serie";
    public static final String overview = "descripcion_serie";
    public static final String poster_path = "imagen";
    public static final String TABLE_SERIE = "serie";

    public SeriesDAODB(Context context) {
        super(context);
    }

    public void addPosts(List<Serie> postList) {
        for (Serie aPost : postList) {
            if (!checkIfExist(aPost.getName())) {
                this.addProduct(aPost);
            }
        }
    }


    public void addProduct(Serie serie) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues row = new ContentValues();

        //Obtengo los datos y los cargo en el row
        row.put(name, serie.getName());
        row.put(overview, serie.getOverview());
        //row.put(genre_ids, serie.getGenre_ids());
        //row.put(POSICION, serie.getPosicion());
        row.put(poster_path, serie.getPoster_path());
       // row.put(TEMPORADA, serie.getTemporadas());
        //row.put(CAPITULOS, serie.getCapitulo());

        database.insert(TABLE_SERIE, null, row);

        database.close();
    }

    private Boolean checkIfExist(String aPostID) {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_SERIE
                + " WHERE " + name + "==\""+ aPostID +"\"";

        Cursor result = database.rawQuery(selectQuery, null);
        Integer count = result.getCount();

        Log.v("PostDAO", "Post " + aPostID + " ya esta en la base");

        database.close();

        return (count > 0);
    }

    public List<Serie> getAllSeriesFromDatabase() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + name;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Serie> seriesList = new ArrayList<>();
        while (cursor.moveToNext()) {

            //TOMO LOS DATOS DE CADA POST

            String nombreSerie = cursor.getString(cursor.getColumnIndex(name));
            String descripcionSerie = cursor.getString(cursor.getColumnIndex(overview));
            String imagenSerie = cursor.getString(cursor.getColumnIndex(poster_path));
           // String generoSerie = cursor.getString(cursor.getColumnIndex(genre_ids));
            //int posicionSerie = cursor.getInt(cursor.getColumnIndex(POSICION));
            //String temporadaSerie = cursor.getString(cursor.getColumnIndex(TEMPORADA));
           // String capitulosSerie = cursor.getString(cursor.getColumnIndex(CAPITULOS));


            Serie serie = new Serie(nombreSerie,imagenSerie,descripcionSerie);//, temporadaSerie, capitulosSerie);

            //AGREGO UN POST A LA LISTA
            seriesList.add(serie);
        }
        return seriesList;
    }



}