package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Addonsinfo;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import java.util.List;

public class AddonsDetailsAdapter extends RecyclerView.Adapter<AddonsDetailsAdapter.ViewHolder> {
    private Context context;
    private List<Addonsinfo> list;
    private List<com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo> list2;
    private Boolean s = false;

    private int prizenow1, prizenow2;

    public AddonsDetailsAdapter(Context applicationContext, List<Addonsinfo> addonsinfoList1) {
        this.context = applicationContext;
        this.list = addonsinfoList1;
        SharedPref.init(context);
        s = true;
    }

    public AddonsDetailsAdapter(MainActivity applicationContext, List<com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo> addonsinfoList) {
        this.context = applicationContext;
        this.list2 = addonsinfoList;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.addonssinglelayout, viewGroup, false);
        return new AddonsDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddonsDetailsAdapter.ViewHolder holder, int position) {
        if (s == true) {
            holder.itempriceaddons.setText(list.get(position).getAddonsprice());
            holder.itemsizeaddons.setText(list.get(position).getAddOnName());
            if (holder.iteminformationaddons.isChecked()) {
                String priceaddons = list.get(position).getAddonsprice();
                prizenow1 = Integer.parseInt(priceaddons);
                SharedPref.write("priceAddons", priceaddons);
            }
        } else {
            holder.itempriceaddons.setText(list2.get(position).getAddonsprice());
            holder.itemsizeaddons.setText(list2.get(position).getAddOnName());
            if (holder.iteminformationaddons.isChecked()) {
                String priceaddons = list2.get(position).getAddonsprice();
                prizenow1 = Integer.parseInt(priceaddons);
                SharedPref.write("priceAddons", priceaddons);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (s == true) {
            return list.size();
        } else {
            return list2.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox iteminformationaddons;
        private TextView itemsizeaddons, itemquantityaddons, itempriceaddons;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iteminformationaddons = itemView.findViewById(R.id.iteminformationaddons);
            itemsizeaddons = itemView.findViewById(R.id.itemsizeaddons);
            itemquantityaddons = itemView.findViewById(R.id.itemquantityaddons);
            itempriceaddons = itemView.findViewById(R.id.itempriceaddons);
        }
    }
}
