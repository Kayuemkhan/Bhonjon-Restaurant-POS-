package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;

public class SplitOrderItemSetupAdapters extends RecyclerView.Adapter<SplitOrderItemSetupAdapters.ViewhOlder> {
    private Context context;
    private int itemsOfSplit;
    private Boolean checkStatus ;

    public SplitOrderItemSetupAdapters(FragmentActivity activity, int size, Boolean onclickEd) {
        this.context = activity;
        this.itemsOfSplit = size;
        this.checkStatus = onclickEd;
    }

    @NonNull
    @Override
    public ViewhOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singledesignforspilitorders,parent,false);
        return new SplitOrderItemSetupAdapters.ViewhOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhOlder holder, int position) {
        holder.splititemDetailsRV.setLayoutManager(new LinearLayoutManager(context));

        if(checkStatus == true){
            holder.splititemDetailsRV.setAdapter(new SplitOrdersDetailsAdapter(context));
        }
    }

    @Override
    public int getItemCount() {
        return itemsOfSplit;
    }

    public class ViewhOlder extends RecyclerView.ViewHolder {
        private RecyclerView splititemDetailsRV;
        public ViewhOlder(@NonNull View itemView) {
            super(itemView);
            splititemDetailsRV = itemView.findViewById(R.id.splititemDetailsRV);

        }
    }
}
