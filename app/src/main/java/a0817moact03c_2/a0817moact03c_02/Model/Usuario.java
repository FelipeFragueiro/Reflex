package a0817moact03c_2.a0817moact03c_02.Model;

import android.net.Uri;

import java.util.List;

/**
 * Created by Ines on 18/12/2017.
 */

public class Usuario {
    private String Foto;
    private String ID;
    private String Nombre;
    private PeliculaFavorita peliculaFavorita;

    public PeliculaFavorita getPeliculaFavorita() {
        return peliculaFavorita;
    }

    public void setPeliculaFavorita(PeliculaFavorita peliculaFavorita) {
        this.peliculaFavorita = peliculaFavorita;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
