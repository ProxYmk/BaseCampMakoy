package basecamp.everest.com.basecamp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import basecamp.everest.com.basecamp.service.model.Make;

public class ItemMakeViewModel extends BaseObservable{

    private Make make;
    private Context context;

    public ItemMakeViewModel(Make make, Context context) {
        this.make = make;
        this.context = context;
    }

    public String getName() { return make.getMakeName(); }


    public void onItemClick(View v){
        //TODO: call activity models passing the make
//        context.startActivity(activity.fillDetail(v.getContext(), make));
    }

    public void setMake(Make make) {
        this.make = make;
        notifyChange();
    }
}
