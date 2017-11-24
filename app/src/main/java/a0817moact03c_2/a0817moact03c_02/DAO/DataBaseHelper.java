package a0817moact03c_2.a0817moact03c_02.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import a0817moact03c_2.a0817moact03c_02.Model.Serie;

/**
 * Created by ma on 11/11/17.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "PostDB";
    private static final Integer DATABASEVERSION = 1;


    public DataBaseHelper(Context context) {
        super(context,DATABASENAME,null,DATABASEVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tablePersona = "CREATE TABLE "+PeliculasDAODB.TABLE_PELICULA + "("+
                PeliculasDAODB.NOMBRE + " TEXT ,"+
                PeliculasDAODB.DESCRIPCION +" TEXT ,"+
                PeliculasDAODB.GENERO +" TEXT ," +
                PeliculasDAODB.IMAGEN +" INTEGER ," +
                PeliculasDAODB.POSICION +" INTEGER )";
        sqLiteDatabase.execSQL(tablePersona);

        String tableSerie = "CREATE TABLE "+SeriesDAODB.TABLE_SERIE + "("+
                SeriesDAODB.name + " TEXT ,"+
                SeriesDAODB.overview +" TEXT ,"+
                SeriesDAODB.genre_ids +" TEXT ," +
                SeriesDAODB.poster_path +" TEXT )";
        sqLiteDatabase.execSQL(tableSerie);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
