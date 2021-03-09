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
import com.bdtask.bhojonrestaurantpos.modelClass.Billadjustment.Cardpinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Billadjustment.PaymentInfo;
import com.bdtask.bhojonrestaurantpos.modelClass.CancelOrder.CancelOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentData;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitorder.SplitData;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitorder.SplitResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitordernum.SplitordernumData;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitordernum.SplitordernumResponse;
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
    private int size = 0;
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
    private Boolean haveToinsert = false;
    private String orderid;
    private String reason;
    private String grandTotal;
    private TextView paynowTV;
    private List<SplitData> splitData;
    private List<Integer> itemsOfSplit;
    private Spinner spinerSplitItems;
    private RecyclerView splitOrderRV;
    private int selectedSplitSizes;
    private Boolean checkBoolean = false;
    private List<SplitordernumData> splitordernumData;
    private List<PaymentInfo> paymentInfos;
    private PaymentOptionsAdapters paymentOptionsAdapters;
    private List<Cardpinfo> cardpinfos;
    String searchValues;
    private String suborderid;
    List<SplitData> splitDatas;

    public OngoingOrderFragment(String searchValues) {
        this.searchValues = searchValues;
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
        splitordernumData = new ArrayList<>();
        paymentInfos = new ArrayList<>();
        cardpinfos = new ArrayList<>();
        splitDatas = new ArrayList<>();
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
            Toasty.info(getContext(), "Single Item Can't be Merged", Toasty.LENGTH_SHORT).show();
        });
        editTV.setOnClickListener(v -> {
            Toasty.info(getContext(), "No Action", Toasty.LENGTH_SHORT).show();
        });
        posinvoiceTV.setOnClickListener(v -> {
            PosInvoice();
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
        splitOrderRV.setLayoutManager(new GridLayoutManager(getActivity(), 2));

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
                    splitData = response.body().getData().getIteminfo();

                    for (int i = 0; i < splitData.size(); i++) {
                        splitItemsize += Integer.parseInt(splitData.get(i).getItemqty());
                    }
                    for (int j = 0; j < splitItemsize; j++) {
                        itemsOfSplit.add(j + 1);
                    }
                    Log.d("splitItemsize", "" + new Gson().toJson(splitItemsize));

                    if (splitData.size() > 0) {
                        splitorderitemsnamelists.setAdapter(new SplitOrderItemsAdapters(getActivity(), splitData, OngoingOrderFragment.this));
                        spinerSplitItems.setAdapter(new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, itemsOfSplit));
                        spinerSplitItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id22) {
                                selectedSplitSizes = Integer.parseInt(spinerSplitItems.getSelectedItem().toString());
                                if (selectedSplitSizes > 1) {
                                    waiterService.spilitItemNumResponse(id, orderid, spinerSplitItems.getSelectedItem().toString())
                                            .enqueue(new Callback<SplitordernumResponse>() {
                                                @Override
                                                public void onResponse(Call<SplitordernumResponse> call, Response<SplitordernumResponse> response) {
                                                    splitordernumData.addAll(response.body().getData());
                                                    splitOrderRV.setAdapter(new SplitOrderItemSetupAdapters(getActivity().getApplicationContext(), selectedSplitSizes, checkBoolean, splitordernumData, splitDatas,
                                                            OngoingOrderFragment.this));
                                                }

                                                @Override
                                                public void onFailure(Call<SplitordernumResponse> call, Throwable t) {

                                                }
                                            });


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

//                paymentInfos.add(paymentInfo);
                for (int i = 0; i < adaptersDat.size(); i++) {
                    PaymentInfo paymentInfo = new PaymentInfo();
                    paymentInfo.setCardpinfos(cardpinfos);
                    paymentInfo.setPayment_type_id(adaptersDat.get(i).getPayment_type_id());
                    paymentInfo.setAmount(adaptersDat.get(i).getAmount());
                    Log.d("paymentInfo",""+new Gson().toJson(paymentInfo));
                    paymentInfos.add(i,paymentInfo);
                }
                Log.d("checkpayda", "id " + id + "orderid " + orderid + "grandtotal " + grandTotal + "discount " + discountETPaymentammount);
                Log.d("adaptersDat", "" + new Gson().toJson(adaptersDat));
                String payinfo = new Gson().toJson(paymentInfos);

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
                size++;
                sizeList.add(0, size);
                paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
                paymentOptionsRV.setAdapter(paymentOptionsAdapters);
                //createnewPaymentPage(sizeList);
            }

        });
        addnewpaymentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizeList.size() == 0) {
//                    size = size + 1;
                    size++;
                    sizeList.add(0, size);
                    paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
                    paymentOptionsRV.setAdapter(paymentOptionsAdapters);
                    Log.d("sizelist", "" + new Gson().toJson(sizeList));
                } else {
                    sizeList.add(size);
                    paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
                    paymentOptionsAdapters.notifyItemInserted(sizeList.size() - 1);
                    paymentOptionsRV.scrollToPosition(sizeList.size() - 1);
                }
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

    private void PosInvoice() {
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
                size++;
                sizeList.add(0, size);
                paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
                paymentOptionsRV.setAdapter(paymentOptionsAdapters);
                //createnewPaymentPage(sizeList);
            }

        });
        addnewpaymentTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sizeList.size() == 0) {
//                    size = size + 1;
                    size++;
                    sizeList.add(0, size);
                    paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
                    paymentOptionsRV.setAdapter(paymentOptionsAdapters);
                    Log.d("sizelist", "" + new Gson().toJson(sizeList));
                } else {
                    sizeList.add(size);
                    paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
                    paymentOptionsAdapters.notifyItemInserted(sizeList.size() - 1);
                    paymentOptionsRV.scrollToPosition(sizeList.size() - 1);
                }
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
        String paymentTypeId = "";
        for (int j = 0; j < paymentData.size(); j++) {
            if (selectedPaymentOptions.equals(paymentData.get(j).getPayname())) {
                paymentTypeId = paymentData.get(j).getPayid();
            }
        }
        haveToinsert = false;
        AdaptersModel adaptersModel = new AdaptersModel(adapterPosition, selectedPaymentOptions, customerpaymentETTExt, paymentTypeId);
        if (adaptersDat.size() == 0) {
            adaptersDat.add(adaptersModel);
        }
        for (int i = 0; i < adaptersDat.size(); i++) {
            if (adapterPosition == adaptersDat.get(i).getAdapterPosition()) {
//                Log.d("TAG", "getSelectedOptions: "+);
                adaptersDat.get(i).setPaymentName(customerpaymentETTExt);
                adaptersDat.get(i).setAmount(selectedPaymentOptions);
                adaptersDat.get(i).setPayment_type_id(paymentTypeId);
                haveToinsert = false;
                break;
            } else {
                haveToinsert = true;
            }
        }
        if (haveToinsert == true) {
            haveToinsert = false;
            adaptersDat.add(adaptersModel);
        }
        Log.d("adaptersdat",""+new Gson().toJson(adaptersDat));
    }

    public void setStatus(Boolean onclickEd, String menuid) {
        checkBoolean = onclickEd;
        if (!menuid.isEmpty() && !suborderid.isEmpty()) {
            Log.d("allidcheckk", "" + menuid + " " + " " + suborderid + " " + id + " " + orderid);
        }
        splitOrderRV.setAdapter(new SplitOrderItemSetupAdapters(getActivity().getApplicationContext(), selectedSplitSizes, onclickEd, splitordernumData, splitDatas, OngoingOrderFragment.this));
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

    public void serchingTable(String searchingKey) {
        Log.d("valueeeee", "" + searchingKey);
        List<OngoingOrderData> searchingList = new ArrayList<>();

        if (searchingKey != null && !searchingKey.isEmpty()) {
            searchingList.clear();
            for (int i = 0; i < ongoingOrderData.size(); i++) {
                if (ongoingOrderData.get(i).getOrderid().toLowerCase().startsWith(searchingKey)) {
                    searchingList.add(ongoingOrderData.get(i));
                }

            }

            OngoingOrderAdapter ongoingOrderAdapter = new OngoingOrderAdapter(getActivity(), searchingList, OngoingOrderFragment.this);
            tableListRecylerview.setAdapter(ongoingOrderAdapter);
        } else {
            OngoingOrderAdapter ongoingOrderAdapter = new OngoingOrderAdapter(getActivity(), ongoingOrderData, OngoingOrderFragment.this);
            tableListRecylerview.setAdapter(ongoingOrderAdapter);
        }
    }


    public void setSubOrderId(Integer splitid) {
        suborderid = splitid.toString();
    }
}