package com.bdtask.bhojonrestaurantpos.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.SpacingItemDecoration;
import com.bdtask.bhojonrestaurantpos.Tools;
import com.bdtask.bhojonrestaurantpos.adapters.OngoingOrderAdapter;
import com.bdtask.bhojonrestaurantpos.adapters.PaymentOptionsAdapters;
import com.bdtask.bhojonrestaurantpos.adapters.SplitOrderItemSetupAdapters;
import com.bdtask.bhojonrestaurantpos.adapters.SplitOrderItemsAdapters;
import com.bdtask.bhojonrestaurantpos.modelClass.AdaptersModel;
import com.bdtask.bhojonrestaurantpos.modelClass.BankList.BankListData;
import com.bdtask.bhojonrestaurantpos.modelClass.BankList.BankListResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CancelOrder.CancelOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentData;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitorder.SplitData;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitorder.SplitResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.TerminalList.TerminalData;
import com.bdtask.bhojonrestaurantpos.modelClass.TerminalList.TerminalResponse;
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
    private String discount, grandtotal, payinfo;
    private List<PaymentData> paymentData;
    private List<String> paymentName;
    int size = 0;
    private List<Integer> setListPlace;
    private LinearLayout paymentTV;
    private ImageView closepaymentpageIV;
    private WaiterService waiterService;
    private LinearLayout completeorderTV, spilitTV, mergeTV, editTV, posinvoiceTV, duePOSTV, cancelTV;
    private String id, selectedDiscountType, discountETPaymentammount;
    private RecyclerView tableListRecylerview;
    private List<OngoingOrderData> ongoingOrderData = new ArrayList<>();
    private LinearLayout lowerpartOfOngoingLayout, lowerpartOfOngoingLayout2, addnewpaymentTV;
    private Spinner spinnerdiscounttype;
    private EditText discountETPayment;
    private RecyclerView paymentOptionsRV;
    private List<TerminalData> terminalData;
    private List<String> terminalName;
    private List<BankListData> bankListData;
    private List<String> bankListName;
    private Parcelable recyclerViewState;
    private List<Integer> sizeList;
    private List<AdaptersModel> adaptersDat;
    private Boolean checkState = false;
    private String orderid;
    private String reason;
    private String grandTotal;
    private TextView paynowTV;
    private List<SplitData> splitData;
    private List<Integer> itemsOfSplit;
    Spinner spinerSplitItems;
    private RecyclerView splitOrderRV;
    private int selectedSplitSizes;
    private Boolean checkBoolean = false;
    public OngoingOrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = SharedPref.read("ID", "");
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        SharedPref.init(getActivity());
        paymentData = new ArrayList<>();
        paymentName = new ArrayList<>();
        terminalData = new ArrayList<>();
        bankListName = new ArrayList<>();
        bankListData = new ArrayList<>();
        terminalName = new ArrayList<>();
        sizeList = new ArrayList<>();
        adaptersDat = new ArrayList<>();
        setListPlace = new ArrayList<>();
        splitData = new ArrayList<>();
        itemsOfSplit = new ArrayList<>();

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
        tableListRecylerview.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 3));
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
        waiterService.paymentListResponse(id).enqueue(new Callback<PaymentResponse>() {
            @Override
            public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                paymentData = response.body().getData();
                paymentName.add(0, "");
                for (int i = 0; i < paymentData.size(); i++) {
                    paymentName.add(paymentData.get(i).getPayname());
                }
                Log.d("aaaaaaaa", "" + new Gson().toJson(paymentName));
            }

            @Override
            public void onFailure(Call<PaymentResponse> call, Throwable t) {
            }
        });
        waiterService.terminalListResponse(id).enqueue(new Callback<TerminalResponse>() {
            @Override
            public void onResponse(Call<TerminalResponse> call, Response<TerminalResponse> response) {
                terminalData = response.body().getData();
                for (int i = 0; i < terminalData.size(); i++) {
                    terminalName.add(terminalData.get(i).getTerminalname());
                }
            }

            @Override
            public void onFailure(Call<TerminalResponse> call, Throwable t) {

            }
        });
        waiterService.bankListResponse(id).enqueue(new Callback<BankListResponse>() {
            @Override
            public void onResponse(Call<BankListResponse> call, Response<BankListResponse> response) {
                bankListData = response.body().getData();
                for (int i = 0; i < bankListData.size(); i++) {
                    bankListName.add(bankListData.get(i).getBankname());
                }
            }

            @Override
            public void onFailure(Call<BankListResponse> call, Throwable t) {

            }
        });

        completeorderTV.setOnClickListener(v -> {
            completeorder();
        });
        spilitTV.setOnClickListener(v -> {
            splitOrder();
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
            duePOSPrint();
        });
        cancelTV.setOnClickListener(v -> {
            cancelOrder();
        });
        return view;
    }

    private void splitOrder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater2 = (LayoutInflater) getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater2.inflate(R.layout.aleartspilit, null);
        ImageView closeAleartSplit = view2.findViewById(R.id.closeAleartSplit);
        Log.d("id&orderId", "" + id + " " + orderid);
        RecyclerView splitorderitemsnamelists;
        splitOrderRV = view2.findViewById(R.id.splitOrderRV);
        splitOrderRV.setLayoutManager(new GridLayoutManager(getActivity(),2));

        splitorderitemsnamelists = view2.findViewById(R.id.splitorderitemsnamelist);
        spinerSplitItems = view2.findViewById(R.id.spinerSplitItems);
        getsplitData(splitorderitemsnamelists, spinerSplitItems);
        builder.setView(view2);
        AlertDialog alert = builder.create();
        splitorderitemsnamelists.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        closeAleartSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                itemsOfSplit.clear();
            }
        });
        alert.show();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .9;
        Double height = metrics.heightPixels * .8;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());
    }

    private void getsplitData(RecyclerView splitorderitemsnamelists, Spinner spinerSplitItems) {
        if (!id.isEmpty() && !orderid.isEmpty()) {
            waiterService.spilitItemResponse(id, orderid).enqueue(new Callback<SplitResponse>() {
                @Override
                public void onResponse(Call<SplitResponse> call, Response<SplitResponse> response) {
                    int splitItemsize = 0;
                    Log.d("id111111", "" + id + " " + orderid);
                    Log.d("getdata", "" + new Gson().toJson(splitData));
                    splitData = response.body().getData().getIteminfo();

                    for (int i = 0; i < splitData.size(); i++) {
                        splitItemsize += Integer.parseInt(splitData.get(i).getItemqty());
                    }
                    for (int j = 0; j < splitItemsize; j++) {
                        itemsOfSplit.add(j + 1);
                    }
                    Log.d("splitItemsize", "" + new Gson().toJson(splitItemsize));

                    if (splitData.size() > 0) {
//                        if(itemsOfSplit.size()>1){
//                            splitOrderRV.setAdapter(new SplitOrderItemSetupAdapters(getActivity(), itemsOfSplit.size()));
//                        }
                        splitorderitemsnamelists.setAdapter(new SplitOrderItemsAdapters(getActivity(), splitData,OngoingOrderFragment.this));
                        spinerSplitItems.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemsOfSplit));
                        spinerSplitItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                selectedSplitSizes = Integer.parseInt(spinerSplitItems.getSelectedItem().toString());
                                if(selectedSplitSizes >1){
                                    splitOrderRV.setAdapter(new SplitOrderItemSetupAdapters(getActivity(), selectedSplitSizes, checkBoolean));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<SplitResponse> call, Throwable t) {

                }
            });
        }
    }

    public void cancelOrder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater2 = (LayoutInflater) getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater2.inflate(R.layout.aleartcancel, null);
        final EditText reasonET3 = view2.findViewById(R.id.reasonET);
        final TextView orderIdCO = view2.findViewById(R.id.orderIdCO);
        orderIdCO.setText(orderid);
        TextView cancelOrderSubmit = view2.findViewById(R.id.cancelOrderSubmit);

        builder.setView(view2);
        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView cancelorderclose = view2.findViewById(R.id.cancelorderclose);
        cancelOrderSubmit.setOnClickListener(v -> {
            reason = reasonET3.getText().toString();
            Log.d("getText", "" + reason + orderid);
            if (!orderid.isEmpty() && !reason.isEmpty()) {
                Log.d("checaa", "" + new Gson().toJson("OrderId: " + orderid + "id: " + id + "reason: " + reason));
                waiterService.cancelOderResponse(id, orderid, reason).enqueue(new Callback<CancelOrderResponse>() {
                    @Override
                    public void onResponse(Call<CancelOrderResponse> call, Response<CancelOrderResponse> response) {
                        Toasty.info(getActivity(), "Item removed Successfully", Toasty.LENGTH_SHORT, true).show();
                        alert.dismiss();
                    }

                    @Override
                    public void onFailure(Call<CancelOrderResponse> call, Throwable t) {

                    }
                });
            }

        });
        cancelorderclose.setOnClickListener(v -> {
            alert.dismiss();
        });
        alert.show();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .7;
        Double height = metrics.heightPixels * .7;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());

    }

    private void completeorder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater.inflate(R.layout.aleartdialog_paymentpage, null);
        builder.setView(view2);
        paynowTV = view2.findViewById(R.id.paynowTV);
        paynowTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checkpayda", "id " + id + "orderid " + orderid + "grandtotal " + grandTotal + "discount " + discountETPaymentammount);
                Toasty.info(getContext(), "Not Implemented", Toasty.LENGTH_SHORT).show();
            }
        });
        spinnerdiscounttype = view2.findViewById(R.id.spinnerdiscounttype);
        spinnerdiscounttypedataSelection();
        discountETPayment = view2.findViewById(R.id.discountETPayment);
        getdiscountAmmount();
        paymentOptionsRV = view2.findViewById(R.id.paymentOptionsRV);
        paymentOptionsRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        closepaymentpageIV = view2.findViewById(R.id.closepaymentpageIV);
        paymentTV = view2.findViewById(R.id.paymentTV);
        addnewpaymentTV = view2.findViewById(R.id.addnewpaymentTV);
        TextView totalAmount = view2.findViewById(R.id.totalAmount);
        TextView totalDueAmount = view2.findViewById(R.id.totalDueAmount);
        TextView payableAmount = view2.findViewById(R.id.payableAmount);
        TextView changeamount = view2.findViewById(R.id.changeamount);
        try {
            if (!grandTotal.isEmpty()) {
                totalAmount.setText(grandTotal);
                totalDueAmount.setText(String.valueOf(Double.valueOf(grandTotal)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        paymentTV.setOnClickListener(v -> {
            addnewpaymentTV.setVisibility(View.VISIBLE);
            if (sizeList.size() == 0) {
                sizeList.add(0, 1);
                createnewPaymentPage(sizeList);
            }

        });
        addnewpaymentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size = size + 1;
                sizeList.add(size, 1);
//                PaymentOptionsAdapters paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
//                //paymentOptionsAdapters.notifyItemInserted(size);
//                paymentOptionsRV.setAdapter(paymentOptionsAdapters);
                createnewPaymentPage(sizeList);
            }
        });
        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        closepaymentpageIV.setOnClickListener(v -> {
            alert.dismiss();
        });
        alert.show();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .7;
        Double height = metrics.heightPixels * .7;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());

    }

    public void getSelectedOptions(String customerpaymentETTExt, String selectedPaymentOptions, int adapterPosition) {
        Log.d("adapterpos", "" + adapterPosition);
        AdaptersModel adaptersModel = new AdaptersModel(sizeList.size() - 1, selectedPaymentOptions, customerpaymentETTExt);
        Log.d("aga", "I'm Here" + adapterPosition);
        if (adaptersDat.size() > 0) {
            if (sizeList.size() > adaptersDat.size()) {
                adaptersDat.add(sizeList.size() - 1, adaptersModel);
            }
            for (int i = 0; i < sizeList.size(); i++) {
                Log.d("ipos", "" + i);
                // When the selectedpayment is selected
//                if (adaptersDat.get(i).getPosition() == adapterPosition && adaptersDat.get(i).getAmmount().isEmpty() && !selectedPaymentOptions.isEmpty()) {
//                    adaptersDat.get(i).setPosition(adapterPosition);
//                    adaptersDat.get(i).setAmmount(customerpaymentETTExt);
//                }
//                // When the payment method is selected
//                else if (adaptersDat.get(i).getPosition() == adapterPosition && adaptersDat.get(i).getPaymentName().isEmpty() && !customerpaymentETTExt.isEmpty()) {
//                    adaptersDat.get(i).setPosition(adapterPosition);
//                    adaptersDat.get(i).setAmmount(customerpaymentETTExt);
//                }
//                // when the paymentname is empty but amount is here
//                else if (adaptersDat.get(i).getPosition() == adapterPosition && adaptersDat.get(i).getPaymentName().isEmpty()
//                        && !adaptersDat.get(i).getAmmount().isEmpty() && !customerpaymentETTExt.isEmpty()
//                )
//                {
//                    adaptersDat.get(i).setPosition(adapterPosition);
//                    adaptersDat.get(i).setAmmount(customerpaymentETTExt);
//                }
//                // when the amount is empty but paymentment name is here
//                else if (adaptersDat.get(i).getPosition() == adapterPosition && !adaptersDat.get(i).getPaymentName().isEmpty() && !selectedPaymentOptions.isEmpty()
//                        && adaptersDat.get(i).getAmmount().isEmpty()
//                ) {
//                    adaptersDat.get(i).setPosition(adapterPosition);
//                    adaptersDat.get(i).setPaymentName(selectedPaymentOptions);
//                }
                if (adaptersDat.get(i).getPosition() == i && adaptersDat.get(i).getPaymentName().isEmpty() && !selectedPaymentOptions.isEmpty()) {
                    Log.d("getAdapterPosition", "" + new Gson().toJson(adapterPosition));
                    Log.d("Imhere", "1");
                    adaptersDat.get(i).setPosition(i);
                    adaptersDat.get(i).setPaymentName(selectedPaymentOptions);
                    adaptersDat.get(i).setAmmount(customerpaymentETTExt);
                } else if (adaptersDat.get(i).getPosition() == i && adaptersDat.get(i).getAmmount().isEmpty() && !customerpaymentETTExt.isEmpty()) {
                    Log.d("Imhere", "2");
                    adaptersDat.get(i).setPosition(i);
                    adaptersDat.get(i).setAmmount(customerpaymentETTExt);

                }
//                else if (adaptersDat.get(i).getPosition() == adapterPosition && !adaptersDat.get(i).getPaymentName().isEmpty()) {
//                    continue;
//                }
                else if (adaptersDat.get(i).getPosition() == i && !adaptersDat.get(i).getPaymentName().isEmpty() && !adaptersDat.get(i).getAmmount().isEmpty()) {
                    Log.d("Imhere", "3");
                    continue;
                }


            }
        }
        if (adaptersDat.size() == 0) {
            adaptersDat.size();
            adaptersDat.add(sizeList.size() - 1, adaptersModel);
        }
//        }

        Log.d("adaptersData", "" + new Gson().toJson(adaptersDat));
        Log.d("sizeofadaptersData", "" + new Gson().toJson(adaptersDat.size()));
        Toasty.info(getContext(), "" + selectedPaymentOptions + "" + adapterPosition, Toasty.LENGTH_SHORT).show();
    }
    public void setStatus(Boolean onclickEd) {
        checkBoolean = onclickEd;
        splitOrderRV.setAdapter(new SplitOrderItemSetupAdapters(getActivity(), selectedSplitSizes,onclickEd));
    }


    private void createnewPaymentPage(List<Integer> size) {
//        paymentOptionsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                recyclerViewState = paymentOptionsRV.getLayoutManager().onSaveInstanceState(); // save recycleView state
//            }
//        });
        paymentOptionsRV.setAdapter(new PaymentOptionsAdapters(getActivity(), size, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat));
//        paymentOptionsRV.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        Log.d("sizelist", "" + new Gson().toJson(size.size()));
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
    private void duePOSPrint() {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater.inflate(R.layout.aleartduepos, null);
        final ImageView crossicon = view2.findViewById(R.id.crossicon);

        builder2.setView(view2);
        AlertDialog alert = builder2.create();
        //alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WebView dueposWV = view2.findViewById(R.id.dueposWV);
        // dueposWV.setWebViewClient(new WebViewClient());
        //dueposWV.getSettings().setBuiltInZoomControls(true);
        WebSettings webSettings = dueposWV.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url = "https://soft14.bdtask.com/bhojon23_latest/appv1/posorderdueinvoice/" + orderid;
        Log.d("url", "" + url);
        dueposWV.loadUrl(url);
        crossicon.setOnClickListener(v -> {
            alert.dismiss();
        });
        alert.show();
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .7;
        Double height = metrics.heightPixels * .7;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());
    }
    public void setAllId(String orderids, Integer grandtotal) {
        orderid = orderids;
        grandTotal = grandtotal.toString();
    }


}