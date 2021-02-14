package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.fragments.OngoingOrderFragment;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentData;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PaymentOptionsAdapters extends RecyclerView.Adapter<PaymentOptionsAdapters.ViewHolder> {
    private Context context;
    private WaiterService waiterService;
    private String id;
    private List<PaymentData> paymentData;
    private List<String> paymentName;
    private int sizes;
    private OngoingOrderFragment ongoingOrderFragment;
    public PaymentOptionsAdapters(FragmentActivity activity, int size, OngoingOrderFragment fragmentActivityClass, List<String> paymentNames) {
        this.context = activity;
        this.paymentName = new ArrayList<>();
        SharedPref.init(context);
        id = SharedPref.read("ID", "");
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        this.sizes = size;
        this.ongoingOrderFragment = fragmentActivityClass;
        this.paymentName = paymentNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlepaymentoptionslayout, parent, false);
        return new PaymentOptionsAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.spinnerPaymentType.setAdapter(new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,paymentName));

    }

    @Override
    public int getItemCount() {
        return sizes;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner spinnerPaymentType;
        private EditText customerpaymentET;
        private LinearLayout addnewpaymentTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinnerPaymentType = itemView.findViewById(R.id.spinnerPaymentType);
            customerpaymentET = itemView.findViewById(R.id.customerpaymentET);
            addnewpaymentTV = itemView.findViewById(R.id.addnewpaymentTV);

        }
    }
}
