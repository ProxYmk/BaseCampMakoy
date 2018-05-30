package basecamp.everest.com.basecamp.service.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Make extends RealmObject implements Serializable{

    @PrimaryKey
    @SerializedName("Make_ID")
    @Expose
    private Integer makeID;
    @SerializedName("Make_Name")
    @Expose
    private String makeName;

    private final static long serialVersionUID = -8563885441691134758L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Make() {
    }

    /**
     *
     * @param makeName
     * @param makeID
     */
    public Make(Integer makeID, String makeName) {
        super();
        this.makeID = makeID;
        this.makeName = makeName;
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

}
