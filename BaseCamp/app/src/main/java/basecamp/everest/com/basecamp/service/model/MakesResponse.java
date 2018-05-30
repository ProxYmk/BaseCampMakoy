package basecamp.everest.com.basecamp.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MakesResponse implements Serializable {

    @SerializedName("Results")
    @Expose
    private List<Make> makeList = null;

    public MakesResponse(List<Make> makeList) {
        this.makeList = makeList;
    }

    public List<Make> getMakeList() {
        return makeList;
    }

    public void setMakeList(List<Make> makeList) {
        this.makeList = makeList;
    }
}
