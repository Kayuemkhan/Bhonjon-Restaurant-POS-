package com.bdtask.bhojonrestaurantpos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.SpacingItemDecoration;
import com.bdtask.bhojonrestaurantpos.Tools;
import com.bdtask.bhojonrestaurantpos.adapters.QROrderAdapter;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderData;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QROrderFragment extends Fragment {
    private WaiterService waiterService;
    private String id,orderID;
    private RecyclerView qrOrderRecylerview;
    List<QROrderData> qrOrderData = new ArrayList<>();
    private LinearLayout layoutId1;
    private LinearLayout qrOrderRecylerviewLayout;

    public QROrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = SharedPref.read("ID", "");
        orderID =  SharedPref.read("OrderID","");
        Log.d("id&orderid",id+" "+orderID);
        //orderID = SharedPref.read("")
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        SharedPref.init(getActivity());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_r_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutId1= view.findViewById(R.id.layoutId1);
        qrOrderRecylerviewLayout= view.findViewById(R.id.qrOrderRecylerviewLayout);
        qrOrderRecylerview = view.findViewById(R.id.qrOrderRecylerview);
        qrOrderRecylerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        waiterService.getAllQrORder(id).enqueue(new Callback<QROrderResponse>() {
            @Override
            public void onResponse(Call<QROrderResponse> call, Response<QROrderResponse> response) {
                qrOrderData = response.body().getData();
                if(qrOrderData.size()<=0){
                    layoutId1.setVisibility(View.VISIBLE);
                    qrOrderRecylerviewLayout.setVisibility(View.GONE);
                }
                else {
                    layoutId1.setVisibility(View.GONE);
                    qrOrderRecylerviewLayout.setVisibility(View.VISIBLE);
                    qrOrderRecylerview.setAdapter(new QROrderAdapter(getActivity(),qrOrderData));
                }

            }

            @Override
            public void onFailure(Call<QROrderResponse> call, Throwable t) {

            }
        });
    }
}