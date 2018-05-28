package basecamp.everest.com.basecamp;

import android.content.Context;


import basecamp.everest.com.basecamp.service.repository.RestClientApi;
import basecamp.everest.com.basecamp.service.repository.RequestApi;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public class Application extends android.app.Application {

    private RequestApi requestApi;
    private Scheduler scheduler;

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
