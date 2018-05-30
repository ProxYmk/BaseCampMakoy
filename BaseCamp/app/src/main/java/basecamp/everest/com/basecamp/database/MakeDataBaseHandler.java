package basecamp.everest.com.basecamp.database;

import java.util.ArrayList;
import java.util.List;

import basecamp.everest.com.basecamp.service.model.Make;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.realm.Realm;
import io.realm.RealmResults;

public class MakeDataBaseHandler {

    private Realm realm;

    public MakeDataBaseHandler() {
        realm = Realm.getDefaultInstance();
    }

    public void insertMakes(List<Make> makeList){
        realm.executeTransaction((inRealm) -> inRealm.copyToRealmOrUpdate(makeList));
    }
    
    public List<Make> getMakeList(){
        List<Make> makeList = new ArrayList<>();
        realm.executeTransaction(inRealm -> {
            final RealmResults<Make> makes = inRealm.where(Make.class).findAll();
            if (makes.size() > 0) {
                makeList.addAll(realm.copyFromRealm(makes));
            }
        });
        return makeList;
    }

    public void closeInstance(){
        realm.close();
    }

    public void deleteAll(){
        realm.deleteAll();
    }
}
