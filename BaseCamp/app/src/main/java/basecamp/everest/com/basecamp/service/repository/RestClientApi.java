package basecamp.everest.com.basecamp.service.repository;

import android.content.Context;

import java.util.Observable;

import basecamp.everest.com.basecamp.Application;
import basecamp.everest.com.basecamp.service.listeners.GetAllMakesListener;
import basecamp.everest.com.basecamp.utils.Constants;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClientApi extends Observable{


    public static RequestApi create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(RequestApi.class);
    }

}
