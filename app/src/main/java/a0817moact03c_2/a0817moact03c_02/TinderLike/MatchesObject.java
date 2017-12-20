package a0817moact03c_2.a0817moact03c_02.TinderLike;

import a0817moact03c_2.a0817moact03c_02.Model.PeliculaFavorita;

/**
 * Created by Ines on 19/12/2017.
 */

public class MatchesObject {
    private String userID;
    private String foto;
    private String nombre;



   // public ChatObject(String userID) {
   //     this.userID = userID;
   // }



    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
