package a0817moact03c_2.a0817moact03c_02.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ma on 11/11/17.
 */

public class PeliculasContainer {

    @SerializedName("results")
    private List<Pelicula> peliculasList;

    public List<Pelicula> getResults() {

        return peliculasList;
    }

    public void setPostList(List<Pelicula> postList) {
        this.peliculasList = postList;
    }
}
