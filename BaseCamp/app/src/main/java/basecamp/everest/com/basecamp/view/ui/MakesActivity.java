package basecamp.everest.com.basecamp.view.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Observable;
import java.util.Observer;

import basecamp.everest.com.basecamp.R;
import basecamp.everest.com.basecamp.databinding.ActivityMakesBinding;
import basecamp.everest.com.basecamp.view.adapter.MakeAdapter;
import basecamp.everest.com.basecamp.viewmodel.MakeViewModel;

public class MakesActivity extends AppCompatActivity implements Observer {

    private MakeViewModel makeViewModel;
    private ActivityMakesBinding makesActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setUpListOfMakesView(makesActivityBinding.listMakes);
        setUpObserver(makeViewModel);
    }

    private void initDataBinding() {
        makesActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_makes);
        makeViewModel = new MakeViewModel(this);
        makesActivityBinding.setMakeViewModel(makeViewModel);
    }

    private void setUpListOfMakesView(RecyclerView listMake) {
        MakeAdapter makeAdapter = new MakeAdapter();
        listMake.setAdapter(makeAdapter);
        listMake.setLayoutManager((new LinearLayoutManager(this)));
    }

    public void setUpObserver(Observable observable) {
        observable.addObserver(this);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  MakeViewModel) {
            MakeAdapter makeAdapter = (MakeAdapter) makesActivityBinding.listMakes.getAdapter();
            MakeViewModel makeViewModel = (MakeViewModel) o;
            makeAdapter.setMakeList(makeViewModel.getMakesList());
        }
    }

    private void startActivityActionView() {
      makeViewModel.onLoadMakeList();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.load_makes) {
            startActivityActionView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        makeViewModel.reset();
    }
}
