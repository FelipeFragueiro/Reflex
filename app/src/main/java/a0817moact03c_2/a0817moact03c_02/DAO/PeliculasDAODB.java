package a0817moact03c_2.a0817moact03c_02.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;

/**
 * Created by ma on 11/11/17.
 */

public class PeliculasDAODB extends DataBaseHelper {
    public static final String NOMBRE = "nombre_pelicula";
    public static final String GENERO = "genero_pelicula";
    public static final String DESCRIPCION = "descripcion_pelicula";
    public static final String IMAGEN = "imagen";
    public static final String POSICION = "posicion";
    public static final String ID = "id";
    public static final String TABLE_PELICULA = "pelicula";

    public PeliculasDAODB(Context context) {
        super(context);
    }

    public void addPosts(List<Pelicula> postList) {
        for (Pelicula aPost : postList) {
            if (!checkIfExist(aPost.getNombre())) {
                this.addProduct(aPost);
            }
        }
    }


    public void addProduct(Pelicula pelicula) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues row = new ContentValues();

        //Obtengo los datos y los cargo en el row
        row.put(NOMBRE, pelicula.getNombre());
        row.put(DESCRIPCION, pelicula.getOverview());
        row.put(GENERO, pelicula.getGenre_ids().toString());
        row.put(POSICION, pelicula.getPosicion());
        row.put(IMAGEN, pelicula.getPoster_path());
        row.put(ID,pelicula.getId());

        database.insert(TABLE_PELICULA, null, row);

        database.close();
    }

    private Boolean checkIfExist(String aPostID) {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_PELICULA
                + " WHERE " + NOMBRE + "==\""+ aPostID +"\"";

        Cursor result = database.rawQuery(selectQuery, null);
        Integer count = result.getCount();

        Log.v("PostDAO", "Post " + aPostID + " ya esta en la base");

        database.close();

        return (count > 0);
    }

    public List<Pelicula> getAllMoviesFromDatabase() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + NOMBRE;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Pelicula> peliculasList = new ArrayList<>();
        while (cursor.moveToNext()) {

            //TOMO LOS DATOS DE CADA POST

            String nombrePelicula = cursor.getString(cursor.getColumnIndex(NOMBRE));
            String descripcionPelicula = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
            String imagenPelicula = cursor.getString(cursor.getColumnIndex(IMAGEN));
            String generoPelicula = cursor.getString(cursor.getColumnIndex(GENERO));
            int posicionPelicula = cursor.getInt(cursor.getColumnIndex(POSICION));
            String idPelicula = cursor.getString(cursor.getColumnIndex(ID));

            Pelicula pelicula = new Pelicula(nombrePelicula,idPelicula,imagenPelicula,null,descripcionPelicula,posicionPelicula,"1/1/2001");

            //AGREGO UN POST A LA LISTA
            peliculasList.add(pelicula);
        }
        return peliculasList;
    }
}
