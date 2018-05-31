package basecamp.everest.com.basecamp.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ModelsResponse implements Serializable {

    @SerializedName("Results")
    @Expose
    private List<Model> modelList = null;

    public ModelsResponse(List<Model> modelList) {
        this.modelList = modelList;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }
}
