package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.Iteminfo;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.KitchenStatusData;

import java.util.ArrayList;
import java.util.List;

public class KitchenStatuAdapter extends RecyclerView.Adapter<KitchenStatuAdapter.Viewholder>{
    private Context context;
    private List<KitchenStatusData> kitchenStatusData;
    private List<Iteminfo> iteminfos;
    private RecyclerView recyclerView;

    public KitchenStatuAdapter(FragmentActivity activity, List<KitchenStatusData> kitchenStatusData) {
        this.context = activity;
        this.kitchenStatusData = kitchenStatusData;
        iteminfos = new ArrayList<>();
    }

    @NonNull
    @Override
    public KitchenStatuAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlekitchenstatus,parent,false);
        return new KitchenStatuAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenStatuAdapter.Viewholder holder, int i) {
        try {
            if(kitchenStatusData.get(i).getIteminfo().get(i).getStatus()== "Ready"){
                int actualPosition = holder.getAdapterPosition();
                kitchenStatusData.remove(actualPosition);
                notifyItemRemoved(actualPosition);
                notifyItemRangeChanged(actualPosition,kitchenStatusData.size());
            }
        }catch (Exception e){

        }
        holder.tableidinkitchenstatus.setText(kitchenStatusData.get(i).getTable());
        holder.toeknidinkitchenstatus.setText(kitchenStatusData.get(i).getToken());
        holder.waiternameinkitchenstatus.setText(kitchenStatusData.get(i).getWaiter());
        holder.orderidinkitchenstatus.setText(kitchenStatusData.get(i).getOrderid());
        holder.singlekitchenstatusitemlistrecylerview.setAdapter(new KitchenStatusAdapterListAdapter(context,kitchenStatusData.get(i).getIteminfo()));
    }

    @Override
    public int getItemCount() {
        return kitchenStatusData.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView tableidinkitchenstatus, toeknidinkitchenstatus,waiternameinkitchenstatus,orderidinkitchenstatus;
        private RecyclerView singlekitchenstatusitemlistrecylerview;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tableidinkitchenstatus = itemView.findViewById(R.id.tableidinkitchenstatus);
            toeknidinkitchenstatus = itemView.findViewById(R.id.toeknidinkitchenstatus);
            waiternameinkitchenstatus = itemView.findViewById(R.id.waiternameinkitchenstatus);
            orderidinkitchenstatus = itemView.findViewById(R.id.orderidinkitchenstatus);
            singlekitchenstatusitemlistrecylerview = itemView.findViewById(R.id.singlekitchenstatusitemlistrecylerview);
            singlekitchenstatusitemlistrecylerview.setLayoutManager(new LinearLayoutManager(context));
        }
    }
}
