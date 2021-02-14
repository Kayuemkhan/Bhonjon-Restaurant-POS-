package com.bdtask.bhojonrestaurantpos.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OngoingOrderFragment extends Fragment {
    private ImageView closepaymentpageIV;
    private WaiterService waiterService;
    private LinearLayout completeorderTV, spilitTV, mergeTV, editTV, posinvoiceTV, duePOSTV, cancelTV;
    private String id,selectedDiscountType,discountETPaymentammount;
    private RecyclerView tableListRecylerview;
    List<OngoingOrderData> ongoingOrderData = new ArrayList<>();
    LinearLayout lowerpartOfOngoingLayout, lowerpartOfOngoingLayout2;
    private Spinner spinnerdiscounttype;
    private EditText discountETPayment;
    public OngoingOrderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ongoing_order, container, false);
        lowerpartOfOngoingLayout = view.findViewById(R.id.lowerpartOfOngoingLayout);
        lowerpartOfOngoingLayout2 = view.findViewById(R.id.lowerpartOfOngoingLayout2);
        tableListRecylerview = view.findViewById(R.id.tableListRecylerview);
        completeorderTV = view.findViewById(R.id.completeorderTV);
        spilitTV = view.findViewById(R.id.spilitTV);
        mergeTV = view.findViewById(R.id.mergeTV);
        editTV = view.findViewById(R.id.editTV);
        posinvoiceTV = view.findViewById(R.id.posinvoiceTV);
        duePOSTV = view.findViewById(R.id.duePOSTV);
        cancelTV = view.findViewById(R.id.cancelTV);
//        tableListRecylerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        tableListRecylerview.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 4));
        tableListRecylerview.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(getActivity().getApplicationContext(), 2), true));
        waiterService.getallOngoingOrder(id).enqueue(new Callback<OngoingOrderResponse>() {
            @Override
            public void onResponse(Call<OngoingOrderResponse> call, Response<OngoingOrderResponse> response) {
                Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                ongoingOrderData = response.body().getData();
                tableListRecylerview.setAdapter(new OngoingOrderAdapter(getActivity(), ongoingOrderData, OngoingOrderFragment.this));
            }

            @Override
            public void onFailure(Call<OngoingOrderResponse> call, Throwable t) {

            }
        });
        completeorderTV.setOnClickListener(v -> {
            completeorder();
        });
        spilitTV.setOnClickListener(v -> {
            Toasty.info(getContext(), "No Action", Toasty.LENGTH_SHORT).show();
        });
        mergeTV.setOnClickListener(v -> {
            Toasty.info(getContext(), "No Action", Toasty.LENGTH_SHORT).show();
        });
        editTV.setOnClickListener(v -> {
            Toasty.info(getContext(), "No Action", Toasty.LENGTH_SHORT).show();
        });
        posinvoiceTV.setOnClickListener(v -> {
            Toasty.info(getContext(), "No Action", Toasty.LENGTH_SHORT).show();
        });
        duePOSTV.setOnClickListener(v -> {
            Toasty.info(getContext(), "No Action", Toasty.LENGTH_SHORT).show();
        });
        cancelTV.setOnClickListener(v -> {
            Toasty.info(getContext(), "No Action", Toasty.LENGTH_SHORT).show();
        });
        return view;
    }

    private void completeorder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater.inflate(R.layout.aleartdialog_paymentpage, null);
        builder.setView(view2);
        spinnerdiscounttype = view2.findViewById(R.id.spinnerdiscounttype);
        spinnerdiscounttypedataSelection();
        discountETPayment = view2.findViewById(R.id.discountETPayment);
        getdiscountAmmount();
        closepaymentpageIV = view2.findViewById(R.id.closepaymentpageIV);
        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        closepaymentpageIV.setOnClickListener(v -> {
            alert.dismiss();
        });
        alert.show();
//        Window window = alert.getWindow();
//
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        alert.setCancelable(false);
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .7;
        Double height = metrics.heightPixels * .7;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());
//        alert.show();
    }

    private void getdiscountAmmount() {
        discountETPayment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                discountETPaymentammount = String.valueOf(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void spinnerdiscounttypedataSelection() {
        spinnerdiscounttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDiscountType = spinnerdiscounttype.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void postworkForTable(Boolean aBoolean) {
        if (aBoolean == true) {
            lowerpartOfOngoingLayout2.setVisibility(View.GONE);
        }
    }
}