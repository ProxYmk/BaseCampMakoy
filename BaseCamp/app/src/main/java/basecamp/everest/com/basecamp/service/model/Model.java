package basecamp.everest.com.basecamp.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Model extends RealmObject implements Serializable{

    @SerializedName("Make_ID")
    @Expose
    private Integer makeID;
    @SerializedName("Make_Name")
    @Expose
    private String makeName;
    @PrimaryKey
    @SerializedName("Model_ID")
    @Expose
    private Integer modelID;
    @SerializedName("Model_Name")
    @Expose
    private String modelName;

    /**
     * No args constructor for use in serialization
     *
     */
    public Model() {
    }

    /**
     *
     * @param makeName
     * @param modelID
     * @param modelName
     * @param makeID
     */
    public Model(Integer makeID, String makeName, Integer modelID, String modelName) {
        super();
        this.makeID = makeID;
        this.makeName = makeName;
        this.modelID = modelID;
        this.modelName = modelName;
    }

    public Integer getMakeID() {
        return makeID;
    }

    public void setMakeID(Integer makeID) {
        this.makeID = makeID;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
