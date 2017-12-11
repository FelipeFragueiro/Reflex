package a0817moact03c_2.a0817moact03c_02.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import a0817moact03c_2.a0817moact03c_02.Model.Actores;
import a0817moact03c_2.a0817moact03c_02.Model.Pelicula;

/**
 * Created by ma on 01/12/17.
 */

public class ActoresDAODB extends DataBaseHelper {
    public static final String NOMBRE = "nombre_actor";
    public static final String ID = "id_actor";
    public static final String IMAGEN = "imagen_actor";
    public static final String PERSONAJE = "personaje_actor";
    public static final String BIRTHDAY = "birthday_actor";
    public static final String BIOGRAFIA = "biografia_actor";
    public static final String ALSO_KNOWN_AS = "also_known_as_actor";
    public static final String TABLE_ACTORES = "table_actores";

    public ActoresDAODB(Context context) {
        super(context);
    }

    public void addPosts(List<Actores> postList) {
        for (Actores aPost : postList) {
            if (!checkIfExist(aPost.getName())) {
                this.addProduct(aPost);
            }
        }
    }

    public void addProduct(Actores actores) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues row = new ContentValues();

        //Obtengo los datos y los cargo en el row
        row.put(NOMBRE, actores.getName());
        row.put(ID,actores.getId());
        row.put(IMAGEN, actores.getProfile_path());
        row.put(PERSONAJE, actores.getCharacter());
        row.put(BIRTHDAY, actores.getBirthday());
        row.put(BIOGRAFIA, actores.getBiography());
        row.put(ALSO_KNOWN_AS, actores.getAlso_known_as());

        database.insert(TABLE_ACTORES, null, row);

        database.close();
    }

    private Boolean checkIfExist(String aPostID) {

        SQLiteDatabase database = getReadableDatabase();
        String nameWithoutComs = aPostID;
        if(nameWithoutComs.contains("\"")){
            nameWithoutComs = nameWithoutComs.replace("\"", "");
        }



                String selectQuery = "SELECT * FROM " + TABLE_ACTORES
                + " WHERE " + NOMBRE + "==\""+ nameWithoutComs +"\"";

        Cursor result = database.rawQuery(selectQuery, null);
        Integer count = result.getCount();

        Log.v("PostDAO", "Post " + aPostID + " ya esta en la base");

        database.close();

        return (count > 0);
    }

    public List<Actores> getAllActoresFromDatabase() {

        SQLiteDatabase database = getReadableDatabase();

        String selectQuery = "SELECT * FROM " + NOMBRE;
        Cursor cursor = database.rawQuery(selectQuery, null);

        List<Actores> actoresList = new ArrayList<>();
        while (cursor.moveToNext()) {

            //TOMO LOS DATOS DE CADA POST

            String nombreActor = cursor.getString(cursor.getColumnIndex(NOMBRE));
            String idActor = cursor.getString(cursor.getColumnIndex(ID));
            String imagenActor = cursor.getString(cursor.getColumnIndex(IMAGEN));
            String personajeActor = cursor.getString(cursor.getColumnIndex(PERSONAJE));
            String birthdayActor = cursor.getString(cursor.getColumnIndex(BIRTHDAY));
            String biografiaActor = cursor.getString(cursor.getColumnIndex(BIOGRAFIA));
            String alsoKnownAsActor = cursor.getString(cursor.getColumnIndex(ALSO_KNOWN_AS));

            Actores actor = new Actores(idActor,nombreActor,imagenActor,personajeActor,birthdayActor,biografiaActor,alsoKnownAsActor);

            //AGREGO UN POST A LA LISTA
            actoresList.add(actor);
        }
        return actoresList;
    }
}
