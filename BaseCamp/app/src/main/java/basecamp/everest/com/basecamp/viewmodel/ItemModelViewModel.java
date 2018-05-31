package basecamp.everest.com.basecamp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import basecamp.everest.com.basecamp.service.model.Model;

public class ItemModelViewModel extends BaseObservable{

    private Model model;
    private Context context;

    public ItemModelViewModel(Model model, Context context) {
        this.model = model;
        this.context = context;
    }

    public String getName() { return model.getModelName(); }

    public String getModelID() { return model.getModelID().toString(); }


    public void setModel(Model model) {
        this.model = model;
        notifyChange();
    }
}
