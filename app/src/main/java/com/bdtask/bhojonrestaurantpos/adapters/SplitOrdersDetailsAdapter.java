package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

public class SplitOrdersDetailsAdapter extends RecyclerView.Adapter<SplitOrdersDetailsAdapter.ViewHolder> {
    private Context context;
    private int position;
    public SplitOrdersDetailsAdapter(Context context, int adapterPosition) {
        this.context = context;
        position = adapterPosition;
        SharedPref.init(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlesplititemdetails,parent,false);
        return new SplitOrdersDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int posNow = Integer.parseInt(SharedPref.read("CurrentPos",""));
        if(posNow == position){
            holder.singlespilititemdetails.setVisibility(View.VISIBLE);
        }
        else {
            holder.singlespilititemdetails.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout singlespilititemdetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            singlespilititemdetails = itemView.findViewById(R.id.singlespilititemdetails);

        }
    }
}
