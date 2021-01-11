package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.Interfaces.ViewInterface;
import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Addonsinfo;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import java.util.List;

import retrofit2.http.POST;

public class AddonsDetailsAdapter extends RecyclerView.Adapter<AddonsDetailsAdapter.ViewHolder> {
    private Context context;
    private List<Addonsinfo> list;
    private List<com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo> list2;
    private Boolean s = false;
    private ViewInterface viewInterface;
    private int addonsSelected = 0;

    private double prizenow1 = 0, prizenow2 = 0;

    public AddonsDetailsAdapter(Context applicationContext, List<Addonsinfo> addonsinfoList1, ViewInterface viewInterfac) {
        this.context = applicationContext;
        this.list = addonsinfoList1;
        SharedPref.init(context);
        s = true;
        this.viewInterface = viewInterfac;
        SharedPref.write("booleanstat","false");
    }

    public AddonsDetailsAdapter(MainActivity applicationContext, List<com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo> addonsinfoList, ViewInterface viewInterfac) {
        this.context = applicationContext;
        this.list2 = addonsinfoList;
        this.viewInterface = viewInterfac;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlelayoutaddons, viewGroup, false);
        return new AddonsDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddonsDetailsAdapter.ViewHolder holder, int position) {
        if (s == true) {
            holder.plusbuttonaddonsitems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addonsSelected = Integer.parseInt(holder.itemquantityaddonsitems.getText().toString()) + 1;
                    holder.itemquantityaddonsitems.setText(String.valueOf(addonsSelected));
                    prizenow1 = (Double.parseDouble(list.get(position).getAddonsprice()) * addonsSelected);
                    SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
                }
            });
            holder.minusbuttonaddonsitems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(holder.itemquantityaddonsitems.getText().toString()) != 0) {
                        addonsSelected = Integer.parseInt(holder.itemquantityaddonsitems.getText().toString()) - 1;
                        holder.itemquantityaddonsitems.setText(String.valueOf(addonsSelected));
                        prizenow1 = (Double.parseDouble(list.get(position).getAddonsprice()) * addonsSelected);
                        SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
                    }
                }
            });
            holder.itempriceaddons.setText(list.get(position).getAddonsprice());
            holder.itemsizeaddons.setText(list.get(position).getAddOnName());
            holder.iteminformationaddons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        SharedPref.write("booleanstat",String.valueOf(isChecked));
//                    prizenow1 += (Double.parseDouble(list.get(position).getAddonsprice()) * addonsSelected);
//                    SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
//                    Log.wtf("SumOfAddonsaaa", SharedPref.read("SumOfAddons", ""));

//                    if (isChecked) {
//                        Log.wtf("datttttt", String.valueOf(addonsSelected));
//                        prizenow1 += (Double.parseDouble(list.get(position).getAddonsprice()) * addonsSelected);
//                        SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
//                        Log.wtf("SumOfAddonsaaa", SharedPref.read("SumOfAddons", ""));
//                    }

                }
            });
//            holder.iteminformationaddons.setOnClickListener(v -> {
////                if (holder.iteminformationaddons.isChecked()) {
//                prizenow1 += (Double.parseDouble(list.get(position).getAddonsprice()) * addonsSelected);
////                    SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
////                    Log.wtf("SumOfAddonsaaa",SharedPref.read("SumOfAddons",""));
//                Log.wtf("datttttt", String.valueOf(addonsSelected));
//                Log.wtf("SumOfAddonsaaa", String.valueOf(list.get(position).getAddonsprice() + "," + String.valueOf(addonsSelected)));
////                }
//            });
        } else {
            holder.plusbuttonaddonsitems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addonsSelected += Integer.parseInt(holder.itemquantityaddonsitems.getText().toString()) + 1;
                    holder.itemquantityaddonsitems.setText(String.valueOf(addonsSelected));
                    prizenow1 = (Double.parseDouble(list2.get(position).getAddonsprice()) * addonsSelected);
                    SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
                }
            });
            holder.minusbuttonaddonsitems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.parseInt(holder.itemquantityaddonsitems.getText().toString()) != 0) {
                        addonsSelected += Integer.parseInt(holder.itemquantityaddonsitems.getText().toString()) - 1;
                        holder.itemquantityaddonsitems.setText(String.valueOf(addonsSelected));
                        prizenow1 = (Double.parseDouble(list2.get(position).getAddonsprice()) * addonsSelected);
                        SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
                    }
                }
            });
            holder.itempriceaddons.setText(list2.get(position).getAddonsprice());
            holder.itemsizeaddons.setText(list2.get(position).getAddOnName());
            holder.iteminformationaddons.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPref.write("booleanstat",String.valueOf(isChecked));
                }
            });
//            holder.iteminformationaddons.setOnClickListener(v -> {
////                if (holder.iteminformationaddons.isChecked()) {
//                prizenow1 += (Double.parseDouble(list2.get(position).getAddonsprice()) * addonsSelected);
//                Log.wtf("datttttt", String.valueOf(addonsSelected));
////                    SharedPref.write("SumOfAddons", String.valueOf(prizenow1));
////                    Log.wtf("SumOfAddonsaaa",SharedPref.read("SumOfAddons",""));
////                    Log.wtf("SumOfAddonsaaa", String.valueOf(list2.get(position).getAddonsprice()+","+String.valueOf(addonsSelected)));
////                }
//            });
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
        private TextView itemsizeaddons, itempriceaddons, itemquantityaddonsitems;
        private ImageView plusbuttonaddonsitems, minusbuttonaddonsitems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iteminformationaddons = itemView.findViewById(R.id.iteminformationaddons);
            itemsizeaddons = itemView.findViewById(R.id.itemsizeaddons);
            itempriceaddons = itemView.findViewById(R.id.itempriceaddons);
            itemquantityaddonsitems = itemView.findViewById(R.id.itemquantityaddonsitems);
            plusbuttonaddonsitems = itemView.findViewById(R.id.plusbuttonaddonsitems);
            minusbuttonaddonsitems = itemView.findViewById(R.id.minusbuttonaddonsitems);

        }
    }
}
