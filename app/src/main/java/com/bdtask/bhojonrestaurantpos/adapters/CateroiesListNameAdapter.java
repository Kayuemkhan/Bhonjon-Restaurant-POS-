package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryData;

import java.util.List;

public class CateroiesListNameAdapter extends RecyclerView.Adapter<CateroiesListNameAdapter.ViewHolder> {

    private Context context;
    private List<CategoryData> list;
    MainActivity mainActivity;


    int rowId = 0;

    public CateroiesListNameAdapter(Context context2, List<CategoryData> list2, MainActivity mainActivity) {
        this.context = context2;
        this.list = list2;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.designsubcategorylistnamessingleitem, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.subcatergoryNameId.setText(list.get(position).getName());
        holder.subcatergoryNameId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowId = position;
                notifyDataSetChanged();
                //holder.tvId.setVisibility(View.VISIBLE);
            }
        });
        if (rowId == position) {
            holder.tvId.setVisibility(View.VISIBLE);
            mainActivity.getCategoriesItem(String.valueOf(position), String.valueOf(list.get(position).getCategoryID()));
        } else {
            holder.tvId.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subcatergoryNameId, tvId;
        LinearLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subcatergoryNameId = itemView.findViewById(R.id.subcatergoryNameId);
            tvId = itemView.findViewById(R.id.tvId);
            itemView = itemView.findViewById(R.id.itemView);
        }
    }

}
