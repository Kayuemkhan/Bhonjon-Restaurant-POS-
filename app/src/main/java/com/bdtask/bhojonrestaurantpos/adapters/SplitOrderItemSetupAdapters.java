package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;

public class SplitOrderItemSetupAdapters extends RecyclerView.Adapter<SplitOrderItemSetupAdapters.ViewhOlder> {
    private Context context;
    private int itemsOfSplit;

    public SplitOrderItemSetupAdapters(FragmentActivity activity, int size) {
        this.context = activity;
        this.itemsOfSplit = size;
    }

    @NonNull
    @Override
    public ViewhOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singledesignforspilitorders,parent,false);
        return new SplitOrderItemSetupAdapters.ViewhOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhOlder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return itemsOfSplit;
    }

    public class ViewhOlder extends RecyclerView.ViewHolder {
        public ViewhOlder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
