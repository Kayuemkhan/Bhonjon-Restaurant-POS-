package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitordernum.SplitordernumData;

import java.util.List;

public class SplitOrderItemSetupAdapters extends RecyclerView.Adapter<SplitOrderItemSetupAdapters.ViewhOlder> {
    private Context context;
    private int itemsOfSplit;
    private Boolean checkStatus ;
    private List<SplitordernumData> splitordernumData;

    public SplitOrderItemSetupAdapters(FragmentActivity activity, int size, Boolean onclickEd, List<SplitordernumData> splitordernumData) {
        this.context = activity;
        this.itemsOfSplit = size;
        this.checkStatus = onclickEd;
        this.splitordernumData = splitordernumData;
    }

    @NonNull
    @Override
    public ViewhOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singledesignforspilitorders,parent,false);
        return new SplitOrderItemSetupAdapters.ViewhOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhOlder holder, int position) {
        holder.orderidSplit.setText(String.valueOf(splitordernumData.get(position).getSplitid()));
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
        private TextView orderidSplit;
        public ViewhOlder(@NonNull View itemView) {
            super(itemView);
            splititemDetailsRV = itemView.findViewById(R.id.splititemDetailsRV);
            orderidSplit = itemView.findViewById(R.id.orderidSplit);

        }
    }
}
