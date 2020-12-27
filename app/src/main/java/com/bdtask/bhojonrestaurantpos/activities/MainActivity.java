package com.bdtask.bhojonrestaurantpos.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.SpacingItemDecoration;
import com.bdtask.bhojonrestaurantpos.Tools;
import com.bdtask.bhojonrestaurantpos.adapters.AllCategoriesInfo;
import com.bdtask.bhojonrestaurantpos.adapters.CateroiesListNameAdapter;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoriesData;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Foodinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryData;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView subcategoryName,itemRecylerview;
    private WaiterService waiterService;
    private String id;
    private String getCategoryId;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(MainActivity.this);
        subcategoryName = findViewById(R.id.subcategoryName);
        subcategoryName.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));
        itemRecylerview = findViewById(R.id.itemRecylerview);

        itemRecylerview.setLayoutManager(new GridLayoutManager(MainActivity.this,5));
        itemRecylerview.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(this, 2), true));
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        id = SharedPref.read("ID", "");
        Log.wtf("chekID", "ID" + id);
        getSubCategoryName();
    }

    public void getSubCategoryName() {
        waiterService.getAllCategories(id).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                List<CategoryData> categorieslist = new ArrayList<>();
                categorieslist.add(new CategoryData("0", "All Categories"));
                categorieslist.addAll(response.body().getData());
                subcategoryName.setAdapter(new CateroiesListNameAdapter(getApplicationContext(), categorieslist,MainActivity.this));

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    public void getCategoriesItem(String categoryID) {
        getCategoryId = categoryID;
        if (categoryID.contains("")) {
            waiterService.allcategoryResponse(id).enqueue(new Callback<AllCategoryResponse>() {
                @Override
                public void onResponse(Call<AllCategoryResponse> call, Response<AllCategoryResponse> response) {
                    Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                    List<Foodinfo> categoriesData = response.body().getData().getFoodinfo();
                    itemRecylerview.setAdapter(new AllCategoriesInfo(getApplicationContext(),categoriesData));
                }

                @Override
                public void onFailure(Call<AllCategoryResponse> call, Throwable t) {

                }
            });
        }

    }
}