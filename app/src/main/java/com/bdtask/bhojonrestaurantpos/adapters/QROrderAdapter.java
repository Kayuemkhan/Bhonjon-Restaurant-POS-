package com.bdtask.bhojonrestaurantpos.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.AcceptOrder.AcceptOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CancelOrder.CancelOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderData;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QROrderAdapter extends RecyclerView.Adapter<QROrderAdapter.ViewHolder> {
    List<QROrderData> qrOrderDataList;
    Context context;
    private String id;
    private String orderId;
    WaiterService waiterService;

    public QROrderAdapter(Context applicationContext, List<QROrderData> qrOrderData) {
        this.qrOrderDataList = qrOrderData;
        this.context = applicationContext;
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        SharedPref.init(context);
        id = SharedPref.read("ID", "");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleitemforqrcoderecylerview, parent, false);
        return new QROrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
                                    Log.d("TAG", "onResponse: "+response.body().getStatus());
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

            }
        });
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
}
