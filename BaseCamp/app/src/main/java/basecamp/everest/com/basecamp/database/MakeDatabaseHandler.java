package basecamp.everest.com.basecamp.database;

import java.util.ArrayList;
import java.util.List;

import basecamp.everest.com.basecamp.service.model.Make;
import basecamp.everest.com.basecamp.service.model.Model;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MakeDatabaseHandler extends BaseDatabaseHandler{

    public MakeDatabaseHandler() {
       super();
    }

    public void insertMakes(List<Make> makeList){
        realm.executeTransaction(inRealm -> inRealm.copyToRealmOrUpdate(makeList));
    }

    public List<Make> getMakeList(){
        List<Make> makeList = new ArrayList<>();
        realm.executeTransaction(inRealm -> {
            final RealmResults<Make> makes = inRealm.where(Make.class).findAllAsync();
            if (makes.size() > 0) {
                makeList.addAll(realm.copyFromRealm(makes));
            }
        });
        return makeList;
    }
    public List<Model> getModelListFromMakeId(int makeId){
        List<Model> modelList = new ArrayList<>();
        realm.executeTransaction(inRealm -> {
            Make makes = inRealm.where(Make.class).equalTo("makeID",makeId).findFirst();
            assert makes != null;
            if (makes.getModelList().size() > 0) {
                modelList.addAll(realm.copyFromRealm(makes.getModelList()));
            }
        });

        return modelList;
    }

    public void insertModelListInMakeById(List<Model> modelList, int makeID){
        realm.executeTransaction(inRealm -> {
            inRealm.copyToRealmOrUpdate(modelList);
            Make make = inRealm.where(Make.class).equalTo("makeID", makeID).findFirst();
            assert make != null;
            make.getModelList().addAll(modelList);
        });
    }

}
