package basecamp.everest.com.basecamp.viewmodel;

import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import basecamp.everest.com.basecamp.Application;
import basecamp.everest.com.basecamp.R;
import basecamp.everest.com.basecamp.database.MakeDatabaseHandler;
import basecamp.everest.com.basecamp.service.model.Make;
import basecamp.everest.com.basecamp.service.model.Model;
import basecamp.everest.com.basecamp.service.model.ModelsResponse;
import basecamp.everest.com.basecamp.service.repository.RequestApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class ModelViewModel extends Observable {

    public ObservableInt progressBar;
    public ObservableInt modelRecycler;
    public ObservableInt modelLabel;
    public ObservableField<String> messageLabel;

    private List<Model> modelList;
    private Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MakeDatabaseHandler makeDBHandler;

    private Make make;

    public ModelViewModel(@NonNull Context context, Make make) {
        this.context = context;
        this.modelList = new ArrayList<>();
        this.make = make;
        progressBar = new ObservableInt(View.GONE);
        modelRecycler = new ObservableInt(View.GONE);
        modelLabel = new ObservableInt(View.VISIBLE);
        messageLabel = new ObservableField<>(context.getString(R.string.default_message_loading_models));
        makeDBHandler = new MakeDatabaseHandler();
    }

    public void onLoadModelList() {
        initializeViews();
        modelList.clear();
        modelList = makeDBHandler.getModelListFromMakeId(make.getMakeID());
        if(modelList.size() == 0){
            fetchModelList(make.getMakeName());
        }else{
            updateModelDataList();
        }
    }

    public void initializeViews() {
        modelRecycler.set(View.GONE);
        progressBar.set(View.VISIBLE);
    }

    private void fetchModelList(String makeName) {

        Application applicationController = Application.create(context);
        RequestApi requestApi = applicationController.getMakeService();

        compositeDisposable.add(requestApi.fetchModels( makeName,"json")
                .subscribeOn(applicationController.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse,this::handleError));
    }

    private void handleResponse(ModelsResponse modelsResponse) {
        makeDBHandler.insertModelListInMakeById(modelsResponse.getModelList(), make.getMakeID());
        modelList.addAll(modelsResponse.getModelList());
        updateModelDataList();
    }


    private void handleError(Throwable error) {
        messageLabel.set(context.getString(R.string.error_message_loading_models));
        progressBar.set(View.GONE);
        modelLabel.set(View.VISIBLE);
        modelRecycler.set(View.GONE);
    }


    private void updateModelDataList() {
        progressBar.set(View.GONE);
        modelLabel.set(View.GONE);
        modelRecycler.set(View.VISIBLE);
        setChanged();
        notifyObservers();
    }

    public List<Model> getModelList() {
        return modelList;
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
