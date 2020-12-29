package com.bdtask.bhojonrestaurantpos.retrofit;

import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.loginModel.LoginResponse;

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
}
