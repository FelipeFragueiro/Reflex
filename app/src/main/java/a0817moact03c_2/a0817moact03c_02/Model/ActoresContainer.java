package a0817moact03c_2.a0817moact03c_02.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ma on 01/12/17.
 */

public class ActoresContainer {

    @SerializedName("cast")
    private List<Actores> actoresList;

    public List<Actores> getResults() {

        return actoresList;
    }

    public void setActoresList(List<Actores> postList) {
        this.actoresList = postList;
    }
}