package basecamp.everest.com.basecamp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import basecamp.everest.com.basecamp.service.model.Make;
import basecamp.everest.com.basecamp.view.ui.ModelActivity;

public class ItemMakeViewModel extends BaseObservable{

    private Make make;
    private Context context;

    public ItemMakeViewModel(Make make, Context context) {
        this.make = make;
        this.context = context;
    }

    public String getName() { return make.getMakeName(); }


    public void onItemClick(View v){
        context.startActivity(ModelActivity.fillMake(v.getContext(), make));
    }

    public void setMake(Make make) {
        this.make = make;
        notifyChange();
    }
}
