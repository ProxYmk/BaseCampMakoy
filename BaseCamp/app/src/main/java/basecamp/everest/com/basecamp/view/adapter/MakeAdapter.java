package basecamp.everest.com.basecamp.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import basecamp.everest.com.basecamp.R;
import basecamp.everest.com.basecamp.databinding.ItemMakeBinding;
import basecamp.everest.com.basecamp.service.model.Make;
import basecamp.everest.com.basecamp.viewmodel.ItemMakeViewModel;

public class MakeAdapter extends RecyclerView.Adapter<MakeAdapter.MakeAdapterViewHolder> {

    private List<Make> makeList;

    public MakeAdapter() {this.makeList = Collections.emptyList();}

    @NonNull
    @Override
    public MakeAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMakeBinding itemMakeBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_make ,parent, false);
        return new MakeAdapterViewHolder(itemMakeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MakeAdapterViewHolder holder, int position) {
        holder.bindUser(makeList.get(position));
    }

    @Override
    public int getItemCount() {
        return makeList.size();
    }

    public void setMakeList(List<Make> makeList) {
        this.makeList = makeList;
        notifyDataSetChanged();
    }

    public static class MakeAdapterViewHolder extends RecyclerView.ViewHolder {

        ItemMakeBinding itemMakeBinding;

        public MakeAdapterViewHolder(ItemMakeBinding itemMakeBinding) {
            super(itemMakeBinding.itemMake);
            this.itemMakeBinding = itemMakeBinding;
        }

        void bindUser(Make make){
            if(itemMakeBinding.getItemMakeViewModel() == null){
                itemMakeBinding.setItemMakeViewModel(new ItemMakeViewModel(make, itemView.getContext()));
            }else {
                itemMakeBinding.getItemMakeViewModel().setMake(make);
            }
        }
    }
}
