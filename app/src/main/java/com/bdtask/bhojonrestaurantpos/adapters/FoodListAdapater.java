package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Foodinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodinfoFoodList;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class FoodListAdapater extends RecyclerView.Adapter<FoodListAdapater.ViewHolder> implements Filterable {
    private String productVat;
    Context context;
    private List<FoodinfoFoodList> lists;
    List<Addonsinfo> addonsinfoList;
    private String addonsStatus;
    private MainActivity mainActivity;
    private String baseprice;

    public FoodListAdapater(Context context, List<FoodinfoFoodList> lists, MainActivity mainActivity) {
        this.context = context;
        this.lists = lists;
        this.mainActivity = mainActivity;
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
        holder.view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                productVat = lists.get(position).getProductvat();
                baseprice = lists.get(position).getPrice();
                addonsinfoList = lists.get(position).getAddonsinfo();
                Log.d("Addons Info", "" + new Gson().toJson(addonsinfoList));
                addonsStatus = lists.get(position).getAddons().toString();
                baseprice = lists.get(position).getPrice();
                mainActivity.AddonsCheckingForAllCategories(productVat,baseprice,addonsStatus, String.valueOf(lists.get(position).getProductName()), lists.get(position).getPrice(), lists.get(position).getVariantName(), lists.get(position).getProductsID(), addonsinfoList);

            }
        });
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
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.catergoryNameId);
            fgoodimageView = itemView.findViewById(R.id.catergoryImageId);
            view = itemView;
        }
    }
}
