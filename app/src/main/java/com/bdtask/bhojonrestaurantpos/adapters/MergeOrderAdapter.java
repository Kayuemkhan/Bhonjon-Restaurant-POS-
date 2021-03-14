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
import com.bdtask.bhojonrestaurantpos.modelClass.TableListUpdate.TableDetails;

import java.util.List;

public class MergeOrderAdapter extends RecyclerView.Adapter<MergeOrderAdapter.Viewholder> {
    private List<TableDetails> tableDetails;
    private Context context;

    public MergeOrderAdapter(FragmentActivity activity, List<TableDetails> tableDetails) {
        this.context = activity;
        this.tableDetails = tableDetails;
    }

    @NonNull
    @Override
    public MergeOrderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleitemformergeorder,parent,false);
        return new MergeOrderAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MergeOrderAdapter.Viewholder holder, int position) {
        holder.orderNumberMergeOrder.setText(tableDetails.get(position).getOrderid());
        holder.totalAmountMergeOrder.setText(tableDetails.get(position).getGrandTotal());
        holder.dueAmountMergerOrder.setText("0");
        holder.paidAmountMergeOrder.setText(tableDetails.get(position).getGrandTotal());
    }

    @Override
    public int getItemCount() {
        return tableDetails.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView orderNumberMergeOrder,totalAmountMergeOrder, paidAmountMergeOrder, dueAmountMergerOrder;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            orderNumberMergeOrder= itemView.findViewById(R.id.orderNumberMergeOrder);
            totalAmountMergeOrder= itemView.findViewById(R.id.totalAmountMergeOrder);
            paidAmountMergeOrder= itemView.findViewById(R.id.paidAmountMergeOrder);
            dueAmountMergerOrder= itemView.findViewById(R.id.dueAmountMergerOrder);
        }
    }
}
