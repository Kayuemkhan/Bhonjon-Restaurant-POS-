package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.fragments.OngoingOrderFragment;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import java.util.List;
public class OngoingOrderAdapter extends RecyclerView.Adapter<OngoingOrderAdapter.Viewholder> {
    private Context context;
    private List<OngoingOrderData> ongoingOrderData;
    private OngoingOrderFragment ongoingOrderFragment;
    private Boolean aBoolean = true;
    private int row_index = -1;
    public OngoingOrderAdapter(Context applicationContext, List<OngoingOrderData> ongoingOrderData, OngoingOrderFragment ongoingOrderFragment) {
        this.context = applicationContext;
        this.ongoingOrderData = ongoingOrderData;
        this.ongoingOrderFragment= ongoingOrderFragment;
        SharedPref.init(context);
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singletablepage,parent, false);
        return new OngoingOrderAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int i) {
        holder.ongoingOrderTableNo.setText(ongoingOrderData.get(i).getTablename());
        holder.ongoingOrderCustomerName.setText(ongoingOrderData.get(i).getCustomerName());
        holder.ongoingOrderOderId.setText(ongoingOrderData.get(i).getOrderid());
        holder.ongoingOrderWaiterName.setText(ongoingOrderData.get(i).getWaiter());
        holder.tabledetailslayout.setOnClickListener(v -> {
            if (true) {
                row_index = i;
                notifyDataSetChanged();
                Log.d("dfgdfsa", "onClick: ");
            } else {
                row_index = i+1;
                notifyDataSetChanged();
                Log.d("dfgdfsa", "onClick: else"+row_index);
                SharedPref.write("UPDATETABLE", String.valueOf(row_index));
            }
            ongoingOrderFragment.postworkForTable(aBoolean);
        });
        if (row_index == i) {
            holder.tabledetailslayout.setBackgroundResource(R.color.back);
            holder.ongoingOrderCustomerName.setTextColor(Color.parseColor("#FFFFFF"));
            holder.ongoingOrderOderId.setTextColor(Color.parseColor("#FFFFFF"));
            holder.ongoingOrderWaiterName.setTextColor(Color.parseColor("#FFFFFF"));
            holder.cusnamelayout.setTextColor(Color.parseColor("#FFFFFF"));
            holder.cusorderlayout.setTextColor(Color.parseColor("#FFFFFF"));
            holder.cuswaiterlayout.setTextColor(Color.parseColor("#FFFFFF"));
            //holder.categoryImage.setColorFilter(Color.argb(255, 255, 255, 255));

        } else {
            holder.tabledetailslayout.setBackgroundColor(0xFFFFFFFF);
            holder.ongoingOrderCustomerName.setTextColor(Color.parseColor("#000000"));
            holder.ongoingOrderOderId.setTextColor(Color.parseColor("#000000"));
            holder.ongoingOrderWaiterName.setTextColor(Color.parseColor("#000000"));
            holder.cusnamelayout.setTextColor(Color.parseColor("#000000"));
            holder.cusorderlayout.setTextColor(Color.parseColor("#000000"));
            holder.cuswaiterlayout.setTextColor(Color.parseColor("#000000"));
//            holder.categoryImage.setColorFilter(Color.argb(100, 0, 0, 0));
        }
    }

    @Override
    public int getItemCount() {
        return ongoingOrderData.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView ongoingOrderTableNo,ongoingOrderCustomerName,ongoingOrderOderId,ongoingOrderWaiterName;
        private CardView tabledetailslayout;
        private TextView cusnamelayout, cusorderlayout,cuswaiterlayout;
        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            tabledetailslayout= itemView.findViewById(R.id.tabledetailslayout);
            ongoingOrderTableNo = itemView.findViewById(R.id.ongoingOrderTableNo);
            ongoingOrderCustomerName= itemView.findViewById(R.id.ongoingOrderCustomerName);
            ongoingOrderOderId = itemView.findViewById(R.id.ongoingOrderOderId);
            ongoingOrderWaiterName = itemView.findViewById(R.id.ongoingOrderWaiterName);
            cusnamelayout = itemView.findViewById(R.id.cusnamelayout);
            cusorderlayout = itemView.findViewById(R.id.cusorderlayout);
            cuswaiterlayout = itemView.findViewById(R.id.cuswaiterlayout);

        }
    }
}
