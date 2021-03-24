package com.bdtask.bhojonrestaurantpos.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.BillAdjustment.BillAdjustmentResponse;
import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.fragments.OngoingOrderFragment;
import com.bdtask.bhojonrestaurantpos.modelClass.AcceptOrder.AcceptOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.AdaptersModel;
import com.bdtask.bhojonrestaurantpos.modelClass.BankList.BankListData;
import com.bdtask.bhojonrestaurantpos.modelClass.Billadjustment.Cardpinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Billadjustment.PaymentInfo;
import com.bdtask.bhojonrestaurantpos.modelClass.CancelOrder.CancelOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderData;
import com.bdtask.bhojonrestaurantpos.modelClass.UpdateOrder.UpdateOrderData;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QROrderAdapter extends RecyclerView.Adapter<QROrderAdapter.ViewHolder> {
    Spinner spinnerdiscounttype;
    private EditText discountETPayment;
    List<QROrderData> qrOrderDataList;
    Context context;
    private String id;
    private String orderId;
    WaiterService waiterService;
    private TextView paynowTV;
    private List<AdaptersModel> adaptersDat;
    private List<Cardpinfo> cardpinfos;
    private List<PaymentInfo> paymentInfos;
    private RecyclerView paymentOptionsRV;
    private List<String> terminalName;
    private List<BankListData> bankListData;
    private List<String> bankListName;
    private ImageView closepaymentpageIV;
    private LinearLayout paymentTV, addnewpaymentTV;
    private String grandTotal;
    private List<Integer> sizeList;
    private int size = 0;
    private List<UpdateOrderData> updateOrderDataList;
    private PaymentOptionsAdapters paymentOptionsAdapters;

    public QROrderAdapter(Context applicationContext, List<QROrderData> qrOrderData) {
        this.qrOrderDataList = qrOrderData;
        this.context = applicationContext;
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        SharedPref.init(context);
        id = SharedPref.read("ID", "");
        adaptersDat = new ArrayList<>();
        cardpinfos = new ArrayList<>();
        paymentInfos = new ArrayList<>();
        bankListData = new ArrayList<>();
        bankListName = new ArrayList<>();
        sizeList = new ArrayList<>();
        updateOrderDataList= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleitemforqrcoderecylerview, parent, false);
        return new QROrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        orderId = qrOrderDataList.get(position).getOrderid();
        holder.invoiceIdQROrder.setText(qrOrderDataList.get(position).getOrderid());
        holder.customernameQROrder.setText(qrOrderDataList.get(position).getCustomerName());
        holder.waiterQROrder.setText(qrOrderDataList.get(position).getWaiter());
        holder.tableNoQROrder.setText(qrOrderDataList.get(position).getTablename());
        holder.paymentStatusQROrder.setText(qrOrderDataList.get(position).getPaidStatus());
        holder.dateQROrder.setText(qrOrderDataList.get(position).getOrderDate());
        holder.ammountQROrder.setText(qrOrderDataList.get(position).getTotalamount());
        holder.acceptOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
                pDialog.setTitleText("Do you want to accpet Invoice");
                pDialog.setConfirmText("Accpet");
                pDialog.setCancelText("Reject");
                orderId = qrOrderDataList.get(position).getOrderid();
                Log.d("id_orderid", id + " " + orderId);
                waiterService.acceptOrder(id, orderId).enqueue(new Callback<AcceptOrderResponse>() {
                    @Override
                    public void onResponse(Call<AcceptOrderResponse> call, Response<AcceptOrderResponse> response) {
                        Log.d("statusaccept", "" + response.body().getMessage());
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                pDialog.dismissWithAnimation();
                                holder.acceptOrder.setVisibility(View.GONE);
                            }
                        }).setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                pDialog.dismissWithAnimation();
                            }
                        }).show();
                    }

                    @Override
                    public void onFailure(Call<AcceptOrderResponse> call, Throwable t) {

                    }
                });
            }
        });
        holder.deleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater2 = (LayoutInflater) context.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view2 = inflater2.inflate(R.layout.aleartcancel, null);
                final EditText reasonET3 = view2.findViewById(R.id.reasonET);
                final TextView orderIdCO = view2.findViewById(R.id.orderIdCO);
                orderId = qrOrderDataList.get(position).getOrderid();
                orderIdCO.setText(orderId);
                TextView cancelOrderSubmit = view2.findViewById(R.id.cancelOrderSubmit);

                builder.setView(view2);
                AlertDialog alert = builder.create();
                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                ImageView cancelorderclose = view2.findViewById(R.id.cancelorderclose);
                cancelOrderSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String reason = reasonET3.getText().toString();
                        {
                            Log.d("checaa", "" + new Gson().toJson("OrderId: " + orderId + "id: " + id + "reason: " + reason));
                            waiterService.cancelOderResponse(id, orderId, reason).enqueue(new Callback<CancelOrderResponse>() {
                                @Override
                                public void onResponse(Call<CancelOrderResponse> call, Response<CancelOrderResponse> response) {
                                    Log.d("TAG", "onResponse: " + response.body().getStatus());
                                    Toasty.info(context, "Item removed Successfully", Toasty.LENGTH_SHORT, true).show();
                                    holder.deleteOrder.setVisibility(View.INVISIBLE);
                                    alert.dismiss();
                                }

                                @Override
                                public void onFailure(Call<CancelOrderResponse> call, Throwable t) {

                                }
                            });
                        }
                    }

                });
                cancelorderclose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });

                alert.show();
                WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                DisplayMetrics metrics = new DisplayMetrics();
                display.getMetrics(metrics);
                Double width = metrics.widthPixels * .7;
                Double height = metrics.heightPixels * .7;
                Window win = alert.getWindow();
                win.setLayout(width.intValue(), height.intValue());

            }

        });
        holder.viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getCustomerName = qrOrderDataList.get(position).getCustomerName();
                String getDate = qrOrderDataList.get(position).getOrderDate();
                String getTotal = qrOrderDataList.get(position).getTotalamount();
                String getOrderId = qrOrderDataList.get(position).getOrderid();
                viewOrder(getCustomerName,getDate,getTotal,getOrderId);
            }
        });
        holder.printOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // completeorder();
            }
        });
        holder.editOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrder();
            }
        });
    }

    private void updateOrder() {
        if(orderId.isEmpty() || orderId!=null){
            Intent intent = new Intent(context, MainActivity.class);
            String strName = null;
            intent.putExtra("STRING_I_NEED", orderId);
            context.startActivity(intent);
        }
    }


    @Override
    public int getItemCount() {
        return qrOrderDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView invoiceIdQROrder, customernameQROrder, waiterQROrder, tableNoQROrder, paymentStatusQROrder, dateQROrder, ammountQROrder;
        private ImageView acceptOrder, deleteOrder, viewOrder, editOrder, printOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invoiceIdQROrder = itemView.findViewById(R.id.invoiceIdQROrder);
            customernameQROrder = itemView.findViewById(R.id.customernameQROrder);
            waiterQROrder = itemView.findViewById(R.id.waiterQROrder);
            tableNoQROrder = itemView.findViewById(R.id.tableNoQROrder);
            paymentStatusQROrder = itemView.findViewById(R.id.paymentStatusQROrder);
            dateQROrder = itemView.findViewById(R.id.dateQROrder);
            ammountQROrder = itemView.findViewById(R.id.ammountQROrder);
            acceptOrder = itemView.findViewById(R.id.acceptOrder);
            deleteOrder = itemView.findViewById(R.id.deleteOrder);
            viewOrder = itemView.findViewById(R.id.viewOrder);
            editOrder = itemView.findViewById(R.id.editOrder);
            printOrder = itemView.findViewById(R.id.printOrder);


        }
    }

    //    private void completeorder() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = (LayoutInflater) context.
//                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view2 = inflater.inflate(R.layout.aleartdialog_paymentpage, null);
//        builder.setView(view2);
//        paynowTV = view2.findViewById(R.id.paynowTV);
//        LinearLayout mergeOrderLayout = view2.findViewById(R.id.mergeOrderLayout);
//        mergeOrderLayout.setVisibility(View.GONE);
//        Spinner spinnerdiscounttype;
//        spinnerdiscounttype = view2.findViewById(R.id.spinnerdiscounttype);
//        spinnerdiscounttypedataSelection();
//        discountETPayment = view2.findViewById(R.id.discountETPayment);
//        discountETPayment.setText("");
//        getdiscountAmmount();
//        paymentOptionsRV = view2.findViewById(R.id.paymentOptionsRV);
//        paymentOptionsRV.setLayoutManager(new LinearLayoutManager(context));
//        closepaymentpageIV = view2.findViewById(R.id.closepaymentpageIV);
//        paymentTV = view2.findViewById(R.id.paymentTV);
//        addnewpaymentTV = view2.findViewById(R.id.addnewpaymentTV);
//        TextView totalAmount = view2.findViewById(R.id.totalAmount);
//        TextView totalDueAmount = view2.findViewById(R.id.totalDueAmount);
//        TextView payableAmount = view2.findViewById(R.id.payableAmount);
//        TextView changeamount = view2.findViewById(R.id.changeamount);
//        try {
//            if (!grandTotal.isEmpty()) {
//                totalAmount.setText(grandTotal);
//                totalDueAmount.setText(String.valueOf(Double.valueOf(grandTotal)));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        paymentTV.setOnClickListener(v -> {
//            addnewpaymentTV.setVisibility(View.VISIBLE);
//            if (sizeList.size() == 0) {
//                size++;
//                sizeList.add(0, size);
//                paymentOptionsAdapters = new PaymentOptionsAdapters(context, sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
//                paymentOptionsRV.setAdapter(paymentOptionsAdapters);
//                //createnewPaymentPage(sizeList);
//            }
//
//        });
//        addnewpaymentTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (sizeList.size() == 0) {
////                    size = size + 1;
//                    size++;
//                    sizeList.add(0, size);
//                    paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
//                    paymentOptionsRV.setAdapter(paymentOptionsAdapters);
//                    Log.d("sizelist", "" + new Gson().toJson(sizeList));
//                } else {
//                    sizeList.add(size);
//                    paymentOptionsAdapters = new PaymentOptionsAdapters(getActivity(), sizeList, OngoingOrderFragment.this, paymentName, terminalName, bankListName, adaptersDat);
//                    paymentOptionsAdapters.notifyItemInserted(sizeList.size() - 1);
//                    paymentOptionsRV.scrollToPosition(sizeList.size() - 1);
//                }
//            }
//        });
//        AlertDialog alert = builder.create();
//        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        paynowTV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i = 0; i < adaptersDat.size(); i++) {
//                    PaymentInfo paymentInfo = new PaymentInfo();
//                    paymentInfo.setCardpinfo(cardpinfos);
//                    paymentInfo.setPayment_type_id(adaptersDat.get(i).getPayment_type_id());
//                    paymentInfo.setAmount(adaptersDat.get(i).getAmount());
//                    Log.d("paymentInfo", "" + new Gson().toJson(paymentInfo));
//                    paymentInfos.add(i, paymentInfo);
//
//                }
//                Log.d("checkpayda", "id " + id + "orderid " + orderid + "grandtotal " + grandTotal + "discount " + discountETPaymentammount);
//                Log.d("paymentInfo", "" + new Gson().toJson(paymentInfos));
//                String payinfo = new Gson().toJson(paymentInfos).toLowerCase();
//                Log.d("paymentInfo", "" + new Gson().toJson(payinfo));
////                Log.d("checkallPay","id"+id+" "+"Discount"+)
//                waiterService.billAdjustmentResponse(id, discountETPaymentammount, grandTotal, orderid, payinfo).enqueue(new Callback<BillAdjustmentResponse>() {
//                    @Override
//                    public void onResponse(Call<BillAdjustmentResponse> call, Response<BillAdjustmentResponse> response) {
//                        Log.d("billadjustmentResponse", "" + new Gson().toJson(response.body()));
//                        alert.dismiss();
//                    }
//
//                    @Override
//                    public void onFailure(Call<BillAdjustmentResponse> call, Throwable t) {
//
//                    }
//                });
//
//            }
//        });
//        closepaymentpageIV.setOnClickListener(v -> {
//            alert.dismiss();
//        });
//
//        alert.show();
//        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        DisplayMetrics metrics = new DisplayMetrics();
//        display.getMetrics(metrics);
//        Double width = metrics.widthPixels * .7;
//        Double height = metrics.heightPixels * .7;
//        Window win = alert.getWindow();
//        win.setLayout(width.intValue(), height.intValue());
//
//    }
//    private void spinnerdiscounttypedataSelection() {
//        spinnerdiscounttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedDiscountType = spinnerdiscounttype.getSelectedItem().toString();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//
//        });
//    }
//    private void getdiscountAmmount() {
//        discountETPayment.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                discountETPaymentammount = String.valueOf(s);
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//    }
    private void viewOrder(String getCustomerName, String getDate, String getTotal, String getOrderId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater2 = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater2.inflate(R.layout.singleprintorderdesign, null);
        builder.setView(view2);
        ImageView cancelorderclose = view2.findViewById(R.id.cancelorderclose);
        TextView invoice_numInViewOrder = view2.findViewById(R.id.invoice_numInViewOrder);
        TextView grandTotalInViewOrder = view2.findViewById(R.id.grandTotalInViewOrder);
        TextView customerInViewOrder = view2.findViewById(R.id.customerInViewOrder);
        TextView billingDate = view2.findViewById(R.id.billingDate);
        billingDate.setText(getDate);
        invoice_numInViewOrder.setText(getOrderId);
        grandTotalInViewOrder.setText(getTotal);
        customerInViewOrder.setText(getCustomerName);
        AlertDialog alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        cancelorderclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        alert.show();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .7;
        Double height = metrics.heightPixels * .7;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());


    }

//    private void updateOrder() {
//        waiterService.updateOrder(id,orderId).enqueue(new Callback<AcceptOrderResponse>() {
//            @Override
//            public void onResponse(Call<AcceptOrderResponse> call, Response<AcceptOrderResponse> response) {
////                updateOrderDataList = response.body().getData();
//            }
//
//            @Override
//            public void onFailure(Call<AcceptOrderResponse> call, Throwable t) {
//
//            }
//        });
//    }


}
