package basecamp.everest.com.basecamp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import basecamp.everest.com.basecamp.R;
import basecamp.everest.com.basecamp.databinding.ItemModelBinding;
import basecamp.everest.com.basecamp.service.model.Model;
import basecamp.everest.com.basecamp.viewmodel.ItemModelViewModel;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ModelAdapterViewHolder>{

    private List<Model> modelList;

    public ModelAdapter() {this.modelList = Collections.emptyList();}

    @NonNull
    @Override
    public ModelAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemModelBinding itemModelBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_model ,parent, false);
        return new ModelAdapterViewHolder(itemModelBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ModelAdapterViewHolder holder, int position) {
        holder.bind(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    public static class ModelAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemModelBinding itemModelBinding;

        public ModelAdapterViewHolder(ItemModelBinding itemModelBinding) {
            super(itemModelBinding.itemModel);
            this.itemModelBinding = itemModelBinding;
        }

        void bind(Model model){
            if(itemModelBinding.getItemModelViewModel() == null){
                itemModelBinding.setItemModelViewModel(new ItemModelViewModel(model, itemView.getContext()));
            }else {
                itemModelBinding.getItemModelViewModel().setModel(model);
            }
        }
    }
}
