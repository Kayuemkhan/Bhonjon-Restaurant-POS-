package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.datamodel.ListClassData;

import java.text.DecimalFormat;
import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.ViewHolder> {
    private Context context;
    private List<ListClassData> listClassData;
    String quantity;
    private RecyclerView itemshowRecylerview;

    public ItemDetailsAdapter(MainActivity mainActivity, List<ListClassData> listClassData) {
        this.context = mainActivity;
        this.listClassData = listClassData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleitemforrecylerview, parent, false);
        return new ItemDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemname.setText(listClassData.get(position).getProductname());
        holder.variantname.setText(listClassData.get(position).getSize());
        holder.itemquantityinitemview.setText(String.valueOf(listClassData.get(position).getQuantity()));
        double d= Double.parseDouble(listClassData.get(position).getPrice()) * listClassData.get(position).getQuantity();
        holder.priceid.setText(String.valueOf(d));
        holder.plusbutton.setOnClickListener(v -> {
            int p= Integer.parseInt(holder.itemquantityinitemview.getText().toString());
            p++;

            holder.itemquantityinitemview.setText(String.valueOf( p));
            double e= Double.parseDouble(listClassData.get(position).getPrice()) * listClassData.get(position).getQuantity();
            holder.priceid.setText(String.valueOf(e));


        });
    }

    @Override
    public int getItemCount() {
        return listClassData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemname, variantname, priceid, itemquantityinitemview;
        private ImageView plusbutton, minusbutton, deletebutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname = itemView.findViewById(R.id.itemname);
            variantname = itemView.findViewById(R.id.variantname);
            priceid = itemView.findViewById(R.id.priceid);
            itemquantityinitemview = itemView.findViewById(R.id.itemquantityinitemview);
            plusbutton = itemView.findViewById(R.id.plusbutton);
            minusbutton = itemView.findViewById(R.id.minusbutton);
            deletebutton = itemView.findViewById(R.id.deletebutton);

        }
    }
}
