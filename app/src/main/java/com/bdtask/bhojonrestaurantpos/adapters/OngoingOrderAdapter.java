package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import java.util.List;
public class OngoingOrderAdapter extends RecyclerView.Adapter<OngoingOrderAdapter.Viewholder> {
    private Context context;
    private List<OngoingOrderData> ongoingOrderData;
    public OngoingOrderAdapter(Context applicationContext, List<OngoingOrderData> ongoingOrderData) {
        this.context = applicationContext;
        this.ongoingOrderData = ongoingOrderData;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singletablepage,parent, false);
        return new OngoingOrderAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.ongoingOrderTableNo.setText(ongoingOrderData.get(position).getTablename());
        holder.ongoingOrderCustomerName.setText(ongoingOrderData.get(position).getCustomerName());
        holder.ongoingOrderOderId.setText(ongoingOrderData.get(position).getOrderid());
        holder.ongoingOrderWaiterName.setText(ongoingOrderData.get(position).getWaiter());
    }

    @Override
    public int getItemCount() {
        return ongoingOrderData.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView ongoingOrderTableNo,ongoingOrderCustomerName,ongoingOrderOderId,ongoingOrderWaiterName;
        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            ongoingOrderTableNo = itemView.findViewById(R.id.ongoingOrderTableNo);
            ongoingOrderCustomerName= itemView.findViewById(R.id.ongoingOrderCustomerName);
            ongoingOrderOderId = itemView.findViewById(R.id.ongoingOrderOderId);
            ongoingOrderWaiterName = itemView.findViewById(R.id.ongoingOrderWaiterName);
        }
    }
}
