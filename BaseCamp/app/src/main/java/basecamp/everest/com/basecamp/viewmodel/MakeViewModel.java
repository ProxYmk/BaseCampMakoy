package basecamp.everest.com.basecamp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import basecamp.everest.com.basecamp.Application;
import basecamp.everest.com.basecamp.R;
import basecamp.everest.com.basecamp.service.model.Make;
import basecamp.everest.com.basecamp.service.model.MakesResponse;
import basecamp.everest.com.basecamp.service.repository.RequestApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MakeViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt makeRecycler;
    public ObservableInt makeLabel;
    public ObservableField<String> messageLabel;

    private List<Make> makeList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MakeViewModel(@NonNull Context context) {
        this.context = context;
        this.makeList = new ArrayList<>();
        progressBar = new ObservableInt(View.GONE);
        makeRecycler = new ObservableInt(View.GONE);
        makeLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_message_loading_makes));
    }

    public void onLoadMakeList() {
//        initializeViews();
//        fetchUsersList();
        makeList.add(new Make(1,"Honda"));
        makeList.add(new Make(2,"Honda2"));
        progressBar.set(View.GONE);
        makeLabel.set(View.GONE);
        makeRecycler.set(View.VISIBLE);
//        makeList.addAll(makes);
        setChanged();
        notifyObservers();
    }

    public void initializeViews() {
        makeLabel.set(View.GONE);
        makeRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    private void fetchUsersList() {

        Application applicationController = Application.create(context);
        RequestApi requestApi = applicationController.getMakeService();

        compositeDisposable.add(requestApi.fetchMakes("json")
                .subscribeOn(applicationController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(MakesResponse makesResponse) {
        updateMakeDataList(makesResponse.getMakeList());
        progressBar.set(View.GONE);
        makeLabel.set(View.GONE);
        makeRecycler.set(View.VISIBLE);
    }


    private void handleError(Throwable error) {
        Log.e("TAG",error.toString(),error);
        messageLabel.set(context.getString(R.string.error_message_loading_makes));
        progressBar.set(View.GONE);
        makeLabel.set(View.VISIBLE);
        makeRecycler.set(View.GONE);
    }

    private void updateMakeDataList(List<Make> makes) {
        makes.add(new Make(1,"Honda"));
        makes.add(new Make(2,"Honda2"));
//        makeList.addAll(makes);
        setChanged();
        notifyObservers();
    }

    public List<Make> getMakesList() {
        return makeList;
    }

    private void unSubscribeFromObservable() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    public void reset() {
        unSubscribeFromObservable();
        compositeDisposable.clear();
        context = null;
    }
}
