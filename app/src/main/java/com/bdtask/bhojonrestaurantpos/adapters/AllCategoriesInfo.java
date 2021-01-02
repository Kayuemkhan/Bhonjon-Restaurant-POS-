package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Addonsinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Foodinfo;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import java.util.List;

public class AllCategoriesInfo extends RecyclerView.Adapter<AllCategoriesInfo.ViewHolder> {
    Context context;
    List<Foodinfo> list;
    MainActivity mainActivity;
    int listPosition = 0;
    String addonsStatus;
    List<Addonsinfo> addonsinfoList;

    public AllCategoriesInfo(Context applicationContext, List<Foodinfo> categoriesData, MainActivity mainActivity2) {
        this.context = applicationContext;
        this.list = categoriesData;
        this.mainActivity = mainActivity2;
    }

    @NonNull
    @Override
    public AllCategoriesInfo.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleitem, viewGroup, false);
        return new AllCategoriesInfo.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoriesInfo.ViewHolder holder, int position) {
        holder.catergoryNameId.setText(list.get(position).getProductName());
        Glide.with(context).load(list.get(position).getProductImage()).placeholder(R.drawable.platefood).into(holder.catergoryImageId);
        listPosition = position;
        holder.view.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                addonsinfoList = list.get(position).getAddonsinfo();
                Log.d("Addons Info", "" + new Gson().toJson(addonsinfoList));
                addonsStatus = list.get(position).getAddons().toString();
                // When the addons are available
                if (addonsStatus.contains("1")) {
                    mainActivity.AddonsChecking(String.valueOf(list.get(position).getProductName()), list.get(position).getPrice(), list.get(position).getVariantName(), list.get(position).getProductsID(), addonsinfoList);
                }
                // When the addons are not available
                else {

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView catergoryImageId;
        private TextView catergoryNameId;
        private LinearLayout cardView;
        private View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catergoryImageId = itemView.findViewById(R.id.catergoryImageId);
            catergoryNameId = itemView.findViewById(R.id.catergoryNameId);
            cardView = itemView.findViewById(R.id.cardview);
            view = itemView;
        }
    }
}
