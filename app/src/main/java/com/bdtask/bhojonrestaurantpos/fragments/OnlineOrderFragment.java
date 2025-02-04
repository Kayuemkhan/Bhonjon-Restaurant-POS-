package com.bdtask.bhojonrestaurantpos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.adapters.KitchenStatuAdapter;
import com.bdtask.bhojonrestaurantpos.adapters.QROrderAdapter;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.KitchenStatusData;
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


public class OnlineOrderFragment extends Fragment {
    private WaiterService waiterService;
    private String id;
    private RecyclerView qrOrderRecylerview;
    List<QROrderData> qrOrderData = new ArrayList<>();
    private LinearLayout layoutId2;
    private LinearLayout onlineOrderRecylerviewLayout;
    public OnlineOrderFragment() {
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
        return inflater.inflate(R.layout.fragment_online_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        layoutId2= view.findViewById(R.id.layoutId2);
        onlineOrderRecylerviewLayout= view.findViewById(R.id.onlineOrderRecylerviewLayout);
        qrOrderRecylerview = view.findViewById(R.id.onlineOrderRecylerview);
        qrOrderRecylerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        waiterService.getallOnlineOrders(id).enqueue(new Callback<QROrderResponse>() {
            @Override
            public void onResponse(Call<QROrderResponse> call, Response<QROrderResponse> response) {
                qrOrderData = response.body().getData();
                if(qrOrderData.size()<=0){
                    layoutId2.setVisibility(View.VISIBLE);
                    onlineOrderRecylerviewLayout.setVisibility(View.GONE);
                }else {
                    layoutId2.setVisibility(View.GONE);
                    onlineOrderRecylerviewLayout.setVisibility(View.VISIBLE);
                    qrOrderRecylerview.setAdapter(new QROrderAdapter(getActivity(),qrOrderData));
                }

            }
            @Override
            public void onFailure(Call<QROrderResponse> call, Throwable t) {

            }
        });
    }
    public void serchingTableInOnlineOrder(String searchingKey){
        List<QROrderData> searchingList = new ArrayList<>();

        if (searchingKey != null && !searchingKey.isEmpty()) {
            searchingList.clear();
            for (int i = 0; i < qrOrderData.size(); i++) {
                if (qrOrderData.get(i).getOrderid().toLowerCase().startsWith(searchingKey)||qrOrderData.get(i).getOrderid().toLowerCase().startsWith(searchingKey)) {
                    searchingList.add(qrOrderData.get(i));
                }

            }

            QROrderAdapter ongoingOrderAdapter = new QROrderAdapter(getActivity(), searchingList);
            qrOrderRecylerview.setAdapter(ongoingOrderAdapter);
        } else {
            QROrderAdapter ongoingOrderAdapter = new QROrderAdapter(getActivity(), qrOrderData);
            qrOrderRecylerview.setAdapter(ongoingOrderAdapter);
        }
    }
}