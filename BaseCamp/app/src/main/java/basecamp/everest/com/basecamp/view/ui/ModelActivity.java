package basecamp.everest.com.basecamp.view.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Observable;
import java.util.Observer;

import basecamp.everest.com.basecamp.R;
import basecamp.everest.com.basecamp.databinding.ActivityModelBinding;
import basecamp.everest.com.basecamp.service.model.Make;
import basecamp.everest.com.basecamp.view.adapter.ModelAdapter;
import basecamp.everest.com.basecamp.viewmodel.ModelViewModel;

public class ModelActivity extends AppCompatActivity implements Observer{

    private static final String EXTRA_BUNDLE = "EXTRA_MAKE";

    private ModelViewModel modelViewModel;
    private ActivityModelBinding activityModelBinding;
    private Make selectedMake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getExtrasFromIntent();
        initDataBinding();
        setUpListOfMakesView(activityModelBinding.listModels);
        setUpObserver(modelViewModel);
        modelViewModel.onLoadModelList();
    }

    public static Intent fillMake(Context context, Make make) {
        Intent intent = new Intent(context, ModelActivity.class);
        intent.putExtra(EXTRA_BUNDLE, make);
        return intent;
    }

    private void getExtrasFromIntent(){
        selectedMake = (Make) getIntent().getSerializableExtra(EXTRA_BUNDLE);
        setTitle(selectedMake.getMakeName());
    }

    private void initDataBinding() {
        activityModelBinding = DataBindingUtil.setContentView(this, R.layout.activity_model);
        modelViewModel = new ModelViewModel(this, selectedMake);
        activityModelBinding.setModelViewModel(modelViewModel);
    }

    private void setUpListOfMakesView(RecyclerView listModel) {
        ModelAdapter modelAdapter = new ModelAdapter();
        listModel.setAdapter(modelAdapter);
        listModel.setLayoutManager((new LinearLayoutManager(this)));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  ModelViewModel) {
            ModelAdapter modelAdapter = (ModelAdapter) activityModelBinding.listModels.getAdapter();
            ModelViewModel modelViewModel = (ModelViewModel) o;
            modelAdapter.setModelList(modelViewModel.getModelList());
        }
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        modelViewModel.reset();
    }
}
