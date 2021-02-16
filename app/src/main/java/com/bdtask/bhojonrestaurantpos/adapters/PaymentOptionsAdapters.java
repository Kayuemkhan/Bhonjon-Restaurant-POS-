package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.fragments.OngoingOrderFragment;
import com.bdtask.bhojonrestaurantpos.modelClass.AdaptersModel;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentData;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PaymentOptionsAdapters extends RecyclerView.Adapter<PaymentOptionsAdapters.ViewHolder> {
    private String selectedPaymentOptions;
    private Context context;
    private WaiterService waiterService;
    private String id;
    private List<PaymentData> paymentData;
    private List<String> paymentName;
    private int sizes;
    private List<String> terminalName;
    private List<String> bankListName;
    private OngoingOrderFragment ongoingOrderFragment;
    private List<Integer> sizeList= new ArrayList<>();
    private List<AdaptersModel> adaptersData = new ArrayList<>();
    private Boolean cardPaymentOrNot = false;
    public PaymentOptionsAdapters(FragmentActivity activity, List<Integer> size, OngoingOrderFragment fragmentActivityClass, List<String> paymentNames, List<String> terminalName, List<String> bankListName, List<AdaptersModel> adaptersDat) {
        SharedPref.init(context);
        id = SharedPref.read("ID", "");
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        this.context = activity;
        this.paymentName = new ArrayList<>();
        //this.sizes = size;
        this.ongoingOrderFragment = fragmentActivityClass;
        this.paymentName = paymentNames;
        this.terminalName = terminalName;
        this.bankListName = bankListName;
        this.sizeList = size;
        this.adaptersData = adaptersDat;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlepaymentoptionslayout, parent, false);
        return new PaymentOptionsAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.spinnerPaymentType.setAdapter(new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, paymentName));
        Log.d("position",""+new Gson().toJson(holder.getAdapterPosition()));
       if(adaptersData.size() > holder.getAdapterPosition()){
           if(adaptersData.get(position).getPosition() == holder.getAdapterPosition() &&  adaptersData.get(position).getAdaptersData().contains("Card Payment")){
               cardPaymentOrNot = true;
           }
           else {
               cardPaymentOrNot = false;
           }
       }
        holder.spinnerPaymentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(cardPaymentOrNot == true){
                    holder.spinnerPaymentType.setSelection(1);
                }
               else {
                    selectedPaymentOptions = holder.spinnerPaymentType.getSelectedItem().toString();
                    if (selectedPaymentOptions.contains("Card Payment")) {
                        holder.cardterminalLayout.setVisibility(View.VISIBLE);
                    } else {
                        holder.cardterminalLayout.setVisibility(View.GONE);
                    }
                }
                ongoingOrderFragment.getSelectedOptions(selectedPaymentOptions,holder.getAdapterPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return sizeList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner spinnerPaymentType;
        private EditText customerpaymentET;
        private LinearLayout addnewpaymentTV;
        private LinearLayout cardterminalLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinnerPaymentType = itemView.findViewById(R.id.spinnerPaymentType);
            customerpaymentET = itemView.findViewById(R.id.customerpaymentET);
            addnewpaymentTV = itemView.findViewById(R.id.addnewpaymentTV);
            cardterminalLayout = itemView.findViewById(R.id.cardterminalLayout);
        }
    }
}
