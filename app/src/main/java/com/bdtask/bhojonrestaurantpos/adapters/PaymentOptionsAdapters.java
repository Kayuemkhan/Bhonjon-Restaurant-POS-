package com.bdtask.bhojonrestaurantpos.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
    private List<String> terminalName;
    private List<String> bankListName;
    private OngoingOrderFragment ongoingOrderFragment;
    private List<Integer> sizeList = new ArrayList<>();
    private List<AdaptersModel> adaptersData = new ArrayList<>();
    private Boolean cardPaymentOrNot = false;
    private String payid;
    private String customerpaymentETgetText = "";
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
//    public PaymentOptionsAdapters(Context context,List<Integer> size,Ongo)

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlepaymentoptionslayout, parent, false);
        return new PaymentOptionsAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("viewholderdata", "" + new Gson().toJson(adaptersData));
        holder.spinnerPaymentType.setAdapter(new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, paymentName));
        holder.spinnerPaymentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPaymentOptions = holder.spinnerPaymentType.getSelectedItem().toString();
                ongoingOrderFragment.getSelectedOptions(customerpaymentETgetText, selectedPaymentOptions, holder.getAdapterPosition());
                Log.d("selected", "" + new Gson().toJson(selectedPaymentOptions));
                if (selectedPaymentOptions.contains("Card Payment")) {
                    holder.cardterminalLayout.setVisibility(View.VISIBLE);
                } else {
                    holder.cardterminalLayout.setVisibility(View.GONE);
                }
                // when the card payment is selected
                if (holder.cardterminalLayout.getVisibility() == View.VISIBLE) {
                    holder.spinnercardterminall.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, terminalName));
                    holder.spinnerselectbankk.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, bankListName));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //ongoingOrderFragment.getSelectedOptions(selectedPaymentOptions, holder.getAdapterPosition());
        Log.d("viewholderdata", "" + new Gson().toJson(selectedPaymentOptions + holder.getAdapterPosition()));
        holder.customerpaymentET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                customerpaymentETgetText = s.toString();
                ongoingOrderFragment.getSelectedOptions(customerpaymentETgetText, selectedPaymentOptions, holder.getAdapterPosition());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("sizelistsize",""+new Gson().toJson(sizeList.size()));
        return sizeList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner spinnerPaymentType;
        private EditText customerpaymentET;
        private LinearLayout addnewpaymentTV;
        private LinearLayout cardterminalLayout;
        private Spinner spinnercardterminall;
        private Spinner spinnerselectbankk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinnerPaymentType = itemView.findViewById(R.id.spinnerPaymentType);
            customerpaymentET = itemView.findViewById(R.id.customerpaymentET);
            addnewpaymentTV = itemView.findViewById(R.id.addnewpaymentTV);
            cardterminalLayout = itemView.findViewById(R.id.cardterminalLayout);
            spinnercardterminall = itemView.findViewById(R.id.spinnercardterminall);
            spinnerselectbankk = itemView.findViewById(R.id.spinnerselectbankk);

        }
    }
}
