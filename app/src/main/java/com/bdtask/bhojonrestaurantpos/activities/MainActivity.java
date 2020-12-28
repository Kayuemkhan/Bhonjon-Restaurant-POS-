package com.bdtask.bhojonrestaurantpos.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.SpacingItemDecoration;
import com.bdtask.bhojonrestaurantpos.Tools;
import com.bdtask.bhojonrestaurantpos.adapters.AllCategoriesInfo;
import com.bdtask.bhojonrestaurantpos.adapters.CateroiesListNameAdapter;
import com.bdtask.bhojonrestaurantpos.adapters.FoodListAdapater;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoriesData;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Foodinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryData;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodinfoFoodList;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodlistResponse;
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
    private RecyclerView subcategoryName, itemRecylerview;
    private WaiterService waiterService;
    private String id;
    private String getPosition;
    private String getCategoryId;
    private TextView iteminformation, itemsize, itemprice;
    private ImageView close;
    private EditText editextquantity;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(MainActivity.this);
        subcategoryName = findViewById(R.id.subcategoryName);
        subcategoryName.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));
        itemRecylerview = findViewById(R.id.itemRecylerview);

        itemRecylerview.setLayoutManager(new GridLayoutManager(MainActivity.this, 5));
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
                subcategoryName.setAdapter(new CateroiesListNameAdapter(getApplicationContext(), categorieslist, MainActivity.this));

            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
    }

    public void getCategoriesItem(String position, String s) {
        getPosition = position;

        if (getPosition.contains("0")) {
            itemRecylerview.setVisibility(View.VISIBLE);
            waiterService.allcategoryResponse(id).enqueue(new Callback<AllCategoryResponse>() {
                @Override
                public void onResponse(Call<AllCategoryResponse> call, Response<AllCategoryResponse> response) {
                    Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                    List<Foodinfo> categoriesData = response.body().getData().getFoodinfo();
                    itemRecylerview.setAdapter(new AllCategoriesInfo(getApplicationContext(), categoriesData, MainActivity.this));
                }

                @Override
                public void onFailure(Call<AllCategoryResponse> call, Throwable t) {

                }
            });
        } else {
            getCategoryId = s;
            waiterService.foodListResponse(id, getCategoryId).enqueue(new Callback<FoodlistResponse>() {
                @Override
                public void onResponse(Call<FoodlistResponse> call, Response<FoodlistResponse> response) {
                    Log.d("Response Food List", "" + new Gson().toJson(response.body()));
                    List<FoodinfoFoodList> foodinfoFoodLists = response.body().getData().getFoodinfo();
                    itemRecylerview.setAdapter(new FoodListAdapater(getApplicationContext(), foodinfoFoodLists));
                }

                @Override
                public void onFailure(Call<FoodlistResponse> call, Throwable t) {

                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void AddonsChecking(String productname, String price, String size) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view2 = getLayoutInflater().inflate(R.layout.design_aleartdialog, null);
        close = view2.findViewById(R.id.closebtnaddons);
        iteminformation = view2.findViewById(R.id.iteminformation);
        itemsize = view2.findViewById(R.id.itemsize);
        editextquantity = view2.findViewById(R.id.itemquantity);
        itemprice = view2.findViewById(R.id.itemprice);
        itemprice.setText(price);
        itemsize.setText(size);
        iteminformation.setText(productname);
        builder.setView(view2);
        AlertDialog alert = builder.create();
        close.setOnClickListener(view -> alert.dismiss());
        alert.show();
    }
}