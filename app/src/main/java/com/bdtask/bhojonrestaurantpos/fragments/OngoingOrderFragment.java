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
import android.widget.TextView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.SpacingItemDecoration;
import com.bdtask.bhojonrestaurantpos.Tools;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.adapters.OngoingOrderAdapter;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.WaiterList.WaiterlistResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OngoingOrderFragment extends Fragment {
    private WaiterService waiterService;
    private String id;
    private RecyclerView tableListRecylerview;
    List<OngoingOrderData> ongoingOrderData = new ArrayList<>();

    public OngoingOrderFragment() {
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
        return inflater.inflate(R.layout.fragment_ongoing_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tableListRecylerview = view.findViewById(R.id.tableListRecylerview);
//        tableListRecylerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        tableListRecylerview.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 4));
        tableListRecylerview.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(getActivity().getApplicationContext(), 2), true));
        waiterService.getallOngoingOrder(id).enqueue(new Callback<OngoingOrderResponse>() {
            @Override
            public void onResponse(Call<OngoingOrderResponse> call, Response<OngoingOrderResponse> response) {
                Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                ongoingOrderData = response.body().getData();
                tableListRecylerview.setAdapter(new OngoingOrderAdapter(getActivity().getApplicationContext(),ongoingOrderData));
            }

            @Override
            public void onFailure(Call<OngoingOrderResponse> call, Throwable t) {

            }
        });

    }
}