package basecamp.everest.com.basecamp;

import android.content.Context;


import basecamp.everest.com.basecamp.service.repository.RestClientApi;
import basecamp.everest.com.basecamp.service.repository.RequestApi;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class Application extends android.app.Application {

    private RequestApi requestApi;
    private Scheduler scheduler;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);
    }

    private static Application get(Context context) {
        return (Application) context.getApplicationContext();
    }

    public static Application create(Context context) {
        return Application.get(context);
    }

    public RequestApi getMakeService() {
        if (requestApi == null) {
            requestApi = RestClientApi.create();
        }

        return requestApi;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setMakeService(RequestApi usersService) {
        this.requestApi = usersService;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

}
