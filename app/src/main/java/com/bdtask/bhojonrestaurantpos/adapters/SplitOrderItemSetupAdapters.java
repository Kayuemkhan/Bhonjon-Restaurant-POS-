package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.fragments.OngoingOrderFragment;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitorder.SplitData;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitordernum.SplitordernumData;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.List;

public class SplitOrderItemSetupAdapters extends RecyclerView.Adapter<SplitOrderItemSetupAdapters.ViewhOlder> {
    private Context context;
    private int itemsOfSplit;
    private Boolean checkStatus;
    private List<SplitordernumData> splitordernumData;
    private int row_index = -1;
    OngoingOrderFragment ongoingOrderFragment;

    public SplitOrderItemSetupAdapters(Context activity, int size, Boolean onclickEd, List<SplitordernumData> splitordernumData, List<SplitData> splitDatas, OngoingOrderFragment ongoingOrderFragment) {
        this.context = activity;
        this.itemsOfSplit = size;
        this.checkStatus = onclickEd;
        this.splitordernumData = splitordernumData;
        this.ongoingOrderFragment = ongoingOrderFragment;
        SharedPref.init(context);
    }

    @NonNull
    @Override
    public ViewhOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singledesignforspilitorders, parent, false);
        return new SplitOrderItemSetupAdapters.ViewhOlder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewhOlder holder, int position) {
        holder.splititemDetailsRV.setVisibility(View.GONE);
        holder.orderidSplit.setText(String.valueOf(splitordernumData.get(position).getSplitid()));
        holder.splititemDetailsRV.setLayoutManager(new LinearLayoutManager(context));
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true) {
                    row_index = position;
                    notifyDataSetChanged();
                    Log.d("dfgdfsa", "onClick: ");
                } else {
                    row_index = position + 1;
                    notifyDataSetChanged();
                    Log.d("dfgdfsa", "onClick: else" + row_index);
                    SharedPref.write("UPDATETABLE", String.valueOf(row_index));
                }
            }
        });
        if (row_index == position) {
            holder.singledesignforSplitOrders.setBackgroundResource(R.drawable.round_button_background_split_select);
            ongoingOrderFragment.setSubOrderId(splitordernumData.get(position).getSplitid());
            SharedPref.write("CurrentPos",String.valueOf(holder.getAdapterPosition()));
        } else {
            holder.singledesignforSplitOrders.setBackgroundResource(R.drawable.round_button_background);
        }
        if (checkStatus == true ){
            int posNow = Integer.parseInt(SharedPref.read("CurrentPos",""));
            if(posNow == position){
                holder.splititemDetailsRV.setVisibility(View.VISIBLE);
                    holder.splititemDetailsRV.setAdapter(new SplitOrdersDetailsAdapter(context,holder.getAdapterPosition()));
                    Log.d("checksate",""+new Gson().toJson(""+holder.getAdapterPosition()));
            }
            else {
                holder.splititemDetailsRV.setVisibility(View.GONE);
            }

        }
        else {
            holder.splititemDetailsRV.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemsOfSplit;
    }

    public class ViewhOlder extends RecyclerView.ViewHolder {
        private RecyclerView splititemDetailsRV;
        private TextView orderidSplit;
        private View view;
        private LinearLayout singledesignforSplitOrders;

        public ViewhOlder(@NonNull View itemView) {
            super(itemView);
            splititemDetailsRV = itemView.findViewById(R.id.splititemDetailsRV);
            orderidSplit = itemView.findViewById(R.id.orderidSplit);
            view = itemView;
            singledesignforSplitOrders = itemView.findViewById(R.id.singledesignforSplitOrders);
        }
    }
}
