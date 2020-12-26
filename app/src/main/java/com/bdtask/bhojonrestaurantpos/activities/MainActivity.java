package com.bdtask.bhojonrestaurantpos.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.bdtask.bhojonrestaurantpos.activities.retrofit.WaiterService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryData;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView subcategoryName;
    private WaiterService waiterService;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(MainActivity.this);
        subcategoryName = findViewById(R.id.subcategoryName);
        subcategoryName.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));
        getSubCategoryName();
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        id = SharedPref.read("ID", "");
    }

    public void getSubCategoryName() {
        waiterService.getAllCategories(id).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.d("Response","Onresponse"+new Gson().toJson(response.body()));
                List<CategoryData> categorieslist = response.body().getData();
                subcategoryName.setAdapter(new CateroiesListNameAdapter(getApplicationContext(),categorieslist));

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }
}