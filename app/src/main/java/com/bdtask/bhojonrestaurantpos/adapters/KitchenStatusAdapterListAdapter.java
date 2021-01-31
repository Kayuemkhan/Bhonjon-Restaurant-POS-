package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.Iteminfo;

import java.util.List;

public class KitchenStatusAdapterListAdapter extends RecyclerView.Adapter<KitchenStatusAdapterListAdapter.Viewholder> {
    private Context context;
    private List<Iteminfo> iteminfos ;
    public KitchenStatusAdapterListAdapter(Context context, List<Iteminfo> iteminfo) {
        this.context = context;
        this.iteminfos = iteminfo;
    }

    @NonNull
    @Override
    public KitchenStatusAdapterListAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlekitchenstatusitemlist,parent,false);
        return new KitchenStatusAdapterListAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenStatusAdapterListAdapter.Viewholder holder, int i) {
        holder.itemnameinkitchenstatus.setText(iteminfos.get(i).getItemname());
        holder.itemcountinkitchenstatus.setText(iteminfos.get(i).getQty()+" x");
        holder.kitchenStatusinKithcenStatus.setText(iteminfos.get(i).getStatus());
    }

    @Override
    public int getItemCount() {
        return iteminfos.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView itemnameinkitchenstatus,itemcountinkitchenstatus,kitchenStatusinKithcenStatus;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            itemnameinkitchenstatus = itemView.findViewById(R.id.itemnameinkitchenstatus);
            itemcountinkitchenstatus=itemView.findViewById(R.id.itemcountinkitchenstatus);
            kitchenStatusinKithcenStatus=itemView.findViewById(R.id.kitchenStatusinKithcenStatus);

        }
    }
}
