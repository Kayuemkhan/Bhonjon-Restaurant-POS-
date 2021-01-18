package com.bdtask.bhojonrestaurantpos.retrofit;

import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerList.CustomerListResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerType.CustomerTypeResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.QROrder.QROrderResponse;
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
    Call<AllCategoryResponse> allcategoryResponse(@Field("id") String id);
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


}
