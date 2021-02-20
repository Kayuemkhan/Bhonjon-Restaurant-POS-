package com.bdtask.bhojonrestaurantpos.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.adapters.KitchenStatuAdapter;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.Iteminfo;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.KitchenStatusData;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.KitchenStatusResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

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
    private LinearLayout layoutId, addnewpaymentTV;
    private List<Iteminfo> iteminfoList;

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
        View view = inflater.inflate(R.layout.fragment_kitchen_status, container, false);
        kitchenStatusrecylerview = view.findViewById(R.id.kitchenStatusrecylerview);
        kitchenStatusrecylerview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        iteminfoList = new ArrayList<>();
        layoutId = view.findViewById(R.id.layoutId);
        try {
            waiterService.kithcenStatus(id).enqueue(new Callback<KitchenStatusResponse>() {
                @Override
                public void onResponse(Call<KitchenStatusResponse> call, Response<KitchenStatusResponse> response) {
                    kitchenStatusData = response.body().getData();
                    Log.d("iteminfolist",""+new Gson().toJson(iteminfoList));
                    try {
                        for (int i = 0; i < kitchenStatusData.size(); i++) {
                            iteminfoList = kitchenStatusData.get(i).getIteminfo();
                            Log.d("iteminfodataaa", "" + new Gson().toJson(iteminfoList));
                            for (int j = 0; j < iteminfoList.size(); j++) {
                                if (iteminfoList.get(j).getStatus().equals("Ready")) {
                                    iteminfoList.remove(j);
                                }
                            }
                        }
                        for (int k = 0; k < kitchenStatusData.size(); k++) {
                            iteminfoList = kitchenStatusData.get(k).getIteminfo();
                            if (iteminfoList.size() == 0) {
                                kitchenStatusData.remove(k);
                                k--;
                            }
                        }
                    } catch (Exception e) {

                    }
                    try {
                        if (kitchenStatusData.size() <= 0) {
                            layoutId.setVisibility(View.VISIBLE);
                            kitchenStatusrecylerview.setVisibility(View.GONE);
                        } else {
                            layoutId.setVisibility(View.GONE);
                            kitchenStatusrecylerview.setVisibility(View.VISIBLE);
                            kitchenStatusrecylerview.setAdapter(new KitchenStatuAdapter(getActivity(), kitchenStatusData));
                        }
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<KitchenStatusResponse> call, Throwable t) {

                }
            });
        } catch (Exception e) {

        }

        return view;
    }


}