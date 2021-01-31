package com.bdtask.bhojonrestaurantpos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.adapters.KitchenStatuAdapter;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.KitchenStatusData;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.KitchenStatusResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KitchenStatusFragment extends Fragment {
    private WaiterService waiterService;
    private String id;
    private RecyclerView kitchenStatusrecylerview;
    List<KitchenStatusData> kitchenStatusData = new ArrayList<>();
    public KitchenStatusFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = SharedPref.read("ID", "");
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        SharedPref.init(getActivity());

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kitchen_status,container,false);
        kitchenStatusrecylerview = view.findViewById(R.id.kitchenStatusrecylerview);
        kitchenStatusrecylerview.setLayoutManager(new GridLayoutManager(getActivity(),3));
        waiterService.kithcenStatus(id).enqueue(new Callback<KitchenStatusResponse>() {
            @Override
            public void onResponse(Call<KitchenStatusResponse> call, Response<KitchenStatusResponse> response) {
                kitchenStatusData = response.body().getData();
                kitchenStatusrecylerview.setAdapter(new KitchenStatuAdapter(getActivity(),kitchenStatusData));
            }

            @Override
            public void onFailure(Call<KitchenStatusResponse> call, Throwable t) {

            }
        });

        return view;
    }


}