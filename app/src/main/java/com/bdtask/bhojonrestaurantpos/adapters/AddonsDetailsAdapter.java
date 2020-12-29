package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Addonsinfo;

import java.util.List;

public class AddonsDetailsAdapter extends RecyclerView.Adapter<AddonsDetailsAdapter.ViewHolder> {
    private Context context;
    private List<Addonsinfo> list;

    public AddonsDetailsAdapter(Context applicationContext, List<Addonsinfo> addonsinfoList) {
        this.context = applicationContext;
        this.list = addonsinfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
         View view = LayoutInflater.from(context).inflate(R.layout.addonssinglelayout,viewGroup,false);
        return new AddonsDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
