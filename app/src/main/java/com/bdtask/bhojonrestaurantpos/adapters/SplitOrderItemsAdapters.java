package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitorder.SplitData;

import java.util.ArrayList;
import java.util.List;

public class SplitOrderItemsAdapters extends RecyclerView.Adapter<SplitOrderItemsAdapters.ViewHolder> {
    private Context context;
    private List<SplitData> splitData = new ArrayList<>();
    public SplitOrderItemsAdapters(FragmentActivity activity, List<SplitData> splitData) {
        this.context = activity;
        this.splitData = splitData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlesplititemsandnumbers,parent,false);
        return new SplitOrderItemsAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.splititemnames.setText(splitData.get(position).getProductName());
        holder.splititemnumber.setText(splitData.get(position).getItemqty());
    }

    @Override
    public int getItemCount() {
        return splitData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView splititemnames;
        private TextView splititemnumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            splititemnames = itemView.findViewById(R.id.splititemnames);
            splititemnumber = itemView.findViewById(R.id.splititemnumber);
        }
    }
}
