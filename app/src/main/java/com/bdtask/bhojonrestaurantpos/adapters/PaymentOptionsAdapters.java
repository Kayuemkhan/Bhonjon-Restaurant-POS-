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
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentData;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentOptionsAdapters extends RecyclerView.Adapter<PaymentOptionsAdapters.ViewHolder> {
    private Context context;
    private WaiterService waiterService;
    private String id;
    private List<PaymentData> paymentData;
    private List<String> paymentName;

    public PaymentOptionsAdapters(FragmentActivity activity) {
        this.context = activity;
        paymentName= new ArrayList<>();
        SharedPref.init(context);
        id = SharedPref.read("ID", "");
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singlepaymentoptionslayout, parent, false);
        return new PaymentOptionsAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        waiterService.paymentListResponse(id).enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                paymentData = response.body().getData();
                for(int i =0;i<paymentData.size();i++){
                    //Log.d("aaaaaaaa",""+new Gson().toJson(paymentData.get(i).getPayname()));
                    paymentName.add(paymentData.get(i).getPayname());
                    //Log.d("aaaaaaaa",""+new Gson().toJson(paymentName));
                }
                Log.d("aaaaaaaa",""+new Gson().toJson(paymentName));
                holder.spinnerPaymentType.setAdapter(new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,paymentName));
            }
            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
            }
        });
        Log.d("paymentD",""+new Gson().toJson(paymentName));
    }

    @Override
    public int getItemCount() {
        return 1;
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
