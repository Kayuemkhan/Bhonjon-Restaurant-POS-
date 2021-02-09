package com.bdtask.bhojonrestaurantpos.retrofit;


import com.bdtask.bhojonrestaurantpos.BuildConfig;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppConfig {

    public static String BASE_URL = "https://soft14.bdtask.com/bhojon23_latest/V1/";

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
