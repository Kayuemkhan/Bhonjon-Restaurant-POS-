package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderData;

import java.util.List;

public class QROrderAdapter extends RecyclerView.Adapter<QROrderAdapter.ViewHolder> {
    List<QROrderData> qrOrderDataList;
    Context context;


    public QROrderAdapter(Context applicationContext, List<QROrderData> qrOrderData) {
        this.qrOrderDataList = qrOrderData;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleitemforqrcoderecylerview,parent,false);
        return new QROrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.invoiceIdQROrder.setText(qrOrderDataList.get(position).getOrderid());
            holder.customernameQROrder.setText(qrOrderDataList.get(position).getCustomerName());
            holder.waiterQROrder.setText(qrOrderDataList.get(position).getWaiter());
            holder.tableNoQROrder.setText(qrOrderDataList.get(position).getTablename());
            holder.paymentStatusQROrder.setText("Paid");
            holder.dateQROrder.setText(qrOrderDataList.get(position).getOrderDate());
            holder.ammountQROrder.setText(qrOrderDataList.get(position).getTotalamount());
    }

    @Override
    public int getItemCount() {
        return qrOrderDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView invoiceIdQROrder,customernameQROrder,waiterQROrder,tableNoQROrder,paymentStatusQROrder,dateQROrder,ammountQROrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invoiceIdQROrder = itemView.findViewById(R.id.invoiceIdQROrder);
            customernameQROrder = itemView.findViewById(R.id.customernameQROrder);
            waiterQROrder = itemView.findViewById(R.id.waiterQROrder);
            tableNoQROrder = itemView.findViewById(R.id.tableNoQROrder);
            paymentStatusQROrder = itemView.findViewById(R.id.paymentStatusQROrder);
            dateQROrder = itemView.findViewById(R.id.dateQROrder);
            ammountQROrder = itemView.findViewById(R.id.ammountQROrder);

        }
    }
}
