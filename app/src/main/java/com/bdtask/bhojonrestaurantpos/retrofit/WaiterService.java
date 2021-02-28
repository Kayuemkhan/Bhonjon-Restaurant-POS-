package com.bdtask.bhojonrestaurantpos.retrofit;

import com.bdtask.bhojonrestaurantpos.BillAdjustment.BillAdjustmentResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.BankList.BankListResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CancelOrder.CancelOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerList.CustomerListResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerType.CustomerTypeResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.KithcenStatus.KitchenStatusResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.PaymentList.PaymentResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.PlaceOrder.PlaceOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.SignupNewCustomer.SignupNewCustomerResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Splitorder.SplitResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.TerminalList.TerminalResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.WaiterList.WaiterlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.loginModel.LoginResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.tablelist.TableResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WaiterService {
    @FormUrlEncoded
    @POST("sign_in")
    Call<LoginResponse> doSignIn(@Field("email") String email, @Field("password") String password, @Field("token") String token);
    @FormUrlEncoded
    @POST("categorylist")
    Call<CategoryResponse> getAllCategories(@Field("id") String id);
    @FormUrlEncoded
    @POST("allfoodlist")
    Call<AllCategoryResponse> allcategoryItemResponse(@Field("id") String id);
    @FormUrlEncoded
    @POST("foodlist")
    Call<FoodlistResponse> foodListResponse(@Field("id") String id, @Field("CategoryID") String categoryId);
    @FormUrlEncoded
    @POST("tablelist")
    Call<TableResponse> getTableList(@Field("id") String id);
    @FormUrlEncoded
    @POST("customerfullist")
    Call<CustomerListResponse> getallCustomersName(@Field("id") String id);
    @FormUrlEncoded
    @POST("allcustomertype")
    Call<CustomerTypeResponse> getallCustomerTypes(@Field("id") String id);
    @FormUrlEncoded
    @POST("waiterlist")
    Call<WaiterlistResponse> getallWaitersList(@Field("id") String id);
    @FormUrlEncoded
    @POST("getongoingorder")
    Call<OngoingOrderResponse> getallOngoingOrder(@Field("id") String id);
    @FormUrlEncoded
    @POST("qrorderlist")
    Call<QROrderResponse> getAllQrORder(@Field("id") String id);
     @FormUrlEncoded
    @POST("onlinellorder")
    Call<QROrderResponse> getallOnlineOrders(@Field("id") String id);
    @FormUrlEncoded
    @POST("foodcart")
    Call<PlaceOrderResponse> postFoodCart(@Field("id") String id, @Field("VatAmount") String VAT, @Field("TableId") String TableId,
                                          @Field("CustomerID") String CustomerID, @Field("TypeID") String TypeID, @Field("ServiceCharge") String ServiceCharge,
                                          @Field("Discount") String Discount, @Field("Total") String Total,
                                          @Field("Grandtotal") String Grandtotal, @Field("foodinfo") String foodinfo, @Field("CustomerNote") String CustomerNote);
    @FormUrlEncoded
    @POST("kitchenstatus")
    Call<KitchenStatusResponse> kithcenStatus(@Field("id") String id);

    @FormUrlEncoded
    @POST("sign_up")
    Call<SignupNewCustomerResponse> signUpNewCustomer(@Field("email") String email,
                                                        @Field("password") String password,
                                                      @Field("id") String id,
                                                      @Field("mobile") String mobile,
                                                      @Field("customer_name") String customer_name);
    @FormUrlEncoded
    @POST("paymentlist")
    Call<PaymentResponse> paymentListResponse(@Field("id") String id);
    @FormUrlEncoded
    @POST("terminallist")
    Call<TerminalResponse> terminalListResponse(@Field("id") String id);
    @FormUrlEncoded
    @POST("ordercancel")
    Call<CancelOrderResponse> cancelOderResponse(@Field("id") String id,@Field("orderid") String orderid,@Field("reason") String reason);
    @FormUrlEncoded
    @POST("banklist")
    Call<BankListResponse> bankListResponse(@Field("id") String id);
    @FormUrlEncoded
    @POST("billadjustment")
    Call<BillAdjustmentResponse> billAdjustmentResponse(@Field("id") String id,@Field("discount") String discount,@Field("grandtotal") String grandtotal,
                                                        @Field("orderid") String orderid,@Field("payinfo") String payinfo);
    @FormUrlEncoded
    @POST("splitorder")
    Call<SplitResponse> spilitItemResponse (@Field("id")String id,@Field("Orderid")String orderId);
}
