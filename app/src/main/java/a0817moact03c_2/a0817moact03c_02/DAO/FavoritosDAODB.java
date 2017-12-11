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
 * Created by ma on 01/12/17.
 */

public class FavoritosDAODB extends DataBaseHelper {
    public static final String NOMBRE = "nombre_pelicula";
    public static final String GENERO = "genero_pelicula";
    public static final String DESCRIPCION = "descripcion_pelicula";
    public static final String IMAGEN = "imagen";
    public static final String POSICION = "posicion";
    public static final String TABLE_FAVORITOS = "favoritos";

    public FavoritosDAODB(Context context) {
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

        row.put(NOMBRE, pelicula.getNombre());
        row.put(DESCRIPCION, pelicula.getOverview());
       // row.put(GENERO, pelicula.getGenre_ids());
        row.put(POSICION, pelicula.getPosicion());
        row.put(IMAGEN, pelicula.getPoster_path());

        database.insert(TABLE_FAVORITOS, null, row);

        database.close();

    }

    private Boolean checkIfExist(String aPostID) {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FAVORITOS
                + " WHERE " + NOMBRE + "==\"" + aPostID + "\"";

        Cursor result = database.rawQuery(selectQuery, null);
        Integer count = result.getCount();

        Log.v("PostDAO", "Post " + aPostID + " ya esta en la base");

        database.close();

        return (count > 0);
    }

    public List<Pelicula> getAllMoviesFavoriteFromDatabase() {
        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + NOMBRE;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Pelicula> peliculaList = new ArrayList<>();
        while (cursor.moveToNext()) {

            String nombrePelicula = cursor.getString(cursor.getColumnIndex(NOMBRE));
            String descripcionPelicula = cursor.getString(cursor.getColumnIndex(DESCRIPCION));
            String imagenPelicula = cursor.getString(cursor.getColumnIndex(IMAGEN));
            String generoPelicula = cursor.getString(cursor.getColumnIndex(GENERO));
            int posicionPelicula = cursor.getInt(cursor.getColumnIndex(POSICION));

            Pelicula pelicula = new Pelicula(nombrePelicula, imagenPelicula, "1", null, descripcionPelicula, posicionPelicula, "1/1/2001");

            peliculaList.add(pelicula);

        }
        return peliculaList;
    }


}






