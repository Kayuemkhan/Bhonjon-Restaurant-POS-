package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodinfoFoodList;
import com.bumptech.glide.Glide;
import java.util.List;

public class FoodListAdapater extends RecyclerView.Adapter<FoodListAdapater.ViewHolder> implements Filterable {
    Context context;
    List<FoodinfoFoodList> lists;

    public FoodListAdapater(Context context, List<FoodinfoFoodList> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public FoodListAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem, viewGroup, false);
        return new FoodListAdapater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListAdapater.ViewHolder holder, int position) {
        holder.itemname.setText(lists.get(position).getProductName());
        Glide.with(context).load(lists.get(position).getProductImage()).placeholder(R.drawable.platefood).into(holder.fgoodimageView);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemname;
        ImageView fgoodimageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.catergoryNameId);
            fgoodimageView = itemView.findViewById(R.id.catergoryImageId);
        }
    }
}
