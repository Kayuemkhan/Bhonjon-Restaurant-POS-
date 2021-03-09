package com.bdtask.bhojonrestaurantpos.adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.activities.MainActivity;
import com.bdtask.bhojonrestaurantpos.modelClass.AcceptOrder.AcceptOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderData;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
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
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.singleitemforqrcoderecylerview,parent,false);
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
                    id = SharedPref.read("ID","");
                    Log.d("id_orderid",id+" "+orderId);
                    waiterService.acceptOrder(id,orderId).enqueue(new Callback<AcceptOrderResponse>() {
                        @Override
                        public void onResponse(Call<AcceptOrderResponse> call, Response<AcceptOrderResponse> response) {
                            Log.d("statusaccept",""+response.body().getMessage());
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

    }

    @Override
    public int getItemCount() {
        return qrOrderDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView invoiceIdQROrder,customernameQROrder,waiterQROrder,tableNoQROrder,paymentStatusQROrder,dateQROrder,ammountQROrder;
        private ImageView acceptOrder,deleteOrder,viewOrder,editOrder,printOrder;
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
