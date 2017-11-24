package a0817moact03c_2.a0817moact03c_02.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ma on 23/11/17.
 */

public class SeriesContainer {

    @SerializedName("results")
    private List<Serie> seriesList;

    public List<Serie> getResults() {

        return seriesList;
    }

    public void setSeriesList(List<Serie> seriesList) {
        this.seriesList = seriesList;
    }
}
