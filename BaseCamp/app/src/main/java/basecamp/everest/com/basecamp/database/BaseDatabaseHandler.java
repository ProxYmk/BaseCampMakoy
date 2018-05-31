package basecamp.everest.com.basecamp.database;

import io.realm.Realm;

public abstract class BaseDatabaseHandler {

    protected Realm realm;

    protected BaseDatabaseHandler() {
        realm = Realm.getDefaultInstance();
    }

    public void closeInstance(){
        realm.close();
    }

    public void deleteAll(){
        realm.deleteAll();
    }
}
