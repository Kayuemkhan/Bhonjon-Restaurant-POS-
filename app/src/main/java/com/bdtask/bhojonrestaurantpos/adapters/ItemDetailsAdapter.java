package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.datamodel.ListClassData;
import com.bdtask.bhojonrestaurantpos.modelClass.datamodel.Subtotal;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.ViewHolder> {
    private Context context;
    private List<ListClassData> listClassData;
    String quantity;
    private RecyclerView itemshowRecylerview;
    private double totalPriceCount;
    private double d = 0;
    private MainActivity mainActivity;
    private double e = 0;
    private Double subtotal = 0.00;
    private Double total = 0.00;
    private List<Double> list;
    private List<Subtotal> listSubtotal;
    private String statusCheck;
    private String SumOfAddons;

    public ItemDetailsAdapter(MainActivity mainActivity, List<ListClassData> listClassData) {
        this.context = mainActivity;
        this.listClassData = listClassData;
        SharedPref.init(context);
        this.mainActivity = mainActivity;
        list = new ArrayList<>();
        listSubtotal = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleitemforrecylerview, parent, false);
        return new ItemDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        totalPriceCount = Double.parseDouble(listClassData.get(position).getBaseprice());
        holder.totalPriceIdInsingleView.setText(listClassData.get(position).getBaseprice());
        holder.itemname.setText(listClassData.get(position).getProductName());
        holder.variantname.setText(listClassData.get(position).getSize());
        holder.itemquantityinitemview.setText(String.valueOf(listClassData.get(position).getQuantity()));
        d = Double.parseDouble(listClassData.get(position).getBaseprice()) * listClassData.get(position).getQuantity();
        statusCheck = String.valueOf(SharedPref.read("booleanstat", ""));
        if (!statusCheck.isEmpty()) {
            if (statusCheck.contains("true")) {
                String sumofAddons = String.valueOf(SharedPref.read("SumOfAddons", ""));
                if(!sumofAddons.isEmpty()){
                    d += Double.parseDouble(sumofAddons);
                }
                //SharedPref.write("SumOfAddons",null);
                //SharedPref.write("booleanstat","false");
            }
        }
        holder.priceid.setText(String.valueOf(d));
        listClassData.get(position).setPrice(String.valueOf(d));
        holder.plusbutton.setOnClickListener(v -> {
            String datacheck;
            int p = Integer.parseInt(holder.itemquantityinitemview.getText().toString());
            p++;
            holder.itemquantityinitemview.setText(String.valueOf(p));
            e = Double.parseDouble(String.valueOf(Double.parseDouble(String.valueOf(Double.parseDouble(listClassData.get(position).getBaseprice()) * Double.parseDouble(holder.itemquantityinitemview.getText().toString())))));
            datacheck = SharedPref.read("SumOfAddons", "");
            if (!datacheck.equals("")) {
                e += Double.parseDouble(SharedPref.read("SumOfAddons", ""));
            }
            listClassData.get(position).setPrice(String.valueOf(d));
            holder.priceid.setText(String.valueOf(e));
            listClassData.get(position).setQuantity(p);
            subtotal = 0.0;
            for (int i = 0; i < listClassData.size(); i++) {
                subtotal += Double.parseDouble(listClassData.get(i).getPrice());
            }
            mainActivity.getalltaxes(String.valueOf(subtotal));
            notifyDataSetChanged();


        });
        holder.minusbutton.setOnClickListener(v -> {
            int q = Integer.parseInt(holder.itemquantityinitemview.getText().toString());
            q--;
            if (q != 0) {
                holder.itemquantityinitemview.setText(String.valueOf(q));
                e = Double.parseDouble(String.valueOf(Double.parseDouble(String.valueOf(Double.parseDouble(listClassData.get(position).getBaseprice()) * Double.parseDouble(holder.itemquantityinitemview.getText().toString())))));
                listClassData.get(position).setPrice(String.valueOf(d));
                holder.priceid.setText(String.valueOf(e));
                listClassData.get(position).setQuantity(q);
                subtotal = 0.0;
                for (int i = 0; i < listClassData.size(); i++) {
                    subtotal += Double.parseDouble(listClassData.get(i).getPrice());
                }
                mainActivity.getalltaxes(String.valueOf(subtotal));
                notifyDataSetChanged();
            }

        });
        holder.deletebutton.setOnClickListener(v -> {
            try {
                int actualPosition = holder.getAdapterPosition();
                listClassData.remove(actualPosition);
                notifyItemRemoved(actualPosition);
                notifyItemRangeChanged(actualPosition, listClassData.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        subtotal = 0.0;
        for (int i = 0; i < listClassData.size(); i++) {
            subtotal += Double.parseDouble(listClassData.get(i).getPrice());
        }
        mainActivity.getalltaxes(String.valueOf(subtotal));
    }

    @Override
    public int getItemCount() {
        return listClassData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemname, variantname, priceid, itemquantityinitemview, totalPriceIdInsingleView;
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
            totalPriceIdInsingleView = itemView.findViewById(R.id.totalPriceIdInsingleView);

        }
    }
}
