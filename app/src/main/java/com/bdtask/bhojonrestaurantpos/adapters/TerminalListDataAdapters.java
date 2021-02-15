package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;

import java.util.List;

public class TerminalListDataAdapters extends RecyclerView.Adapter<TerminalListDataAdapters.Viewholder> {
    private Context context;
    private List<String> terminalName;
    private List<String> bankListName;
    public TerminalListDataAdapters(Context context, List<String> terminalName, List<String> bankListName) {
        this.context = context;
        this.terminalName = terminalName;
        this.bankListName = bankListName;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleterminalist,parent,false);
        return new TerminalListDataAdapters.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
