package com.bdtask.bhojonrestaurantpos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private String id;
    private RecyclerView qrOrderRecylerview;
    List<QROrderData> qrOrderData = new ArrayList<>();


    public QROrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = SharedPref.read("ID", "");
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_q_r_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        qrOrderRecylerview = view.findViewById(R.id.qrOrderRecylerview);
        qrOrderRecylerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        waiterService.getAllQrORder(id).enqueue(new Callback<QROrderResponse>() {
            @Override
            public void onResponse(Call<QROrderResponse> call, Response<QROrderResponse> response) {
                qrOrderData = response.body().getData();
                qrOrderRecylerview.setAdapter(new QROrderAdapter(getActivity().getApplicationContext(),qrOrderData));
            }

            @Override
            public void onFailure(Call<QROrderResponse> call, Throwable t) {

            }
        });
    }
}