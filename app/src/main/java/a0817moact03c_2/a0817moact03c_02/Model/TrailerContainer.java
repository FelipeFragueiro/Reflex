package a0817moact03c_2.a0817moact03c_02.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ines on 14/12/2017.
 */

public class TrailerContainer {
    @SerializedName("results")
    private List<Trailer> trailerList;

    public List<Trailer> getResults() {

        return trailerList;
    }

    public void setTrailerList(List<Trailer> postList) {
        this.trailerList = postList;
    }
}

