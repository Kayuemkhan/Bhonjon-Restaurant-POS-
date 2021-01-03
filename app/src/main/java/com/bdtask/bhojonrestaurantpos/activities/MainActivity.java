package com.bdtask.bhojonrestaurantpos.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.SearchManager;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.SpacingItemDecoration;
import com.bdtask.bhojonrestaurantpos.Tools;
import com.bdtask.bhojonrestaurantpos.adapters.AddonsDetailsAdapter;
import com.bdtask.bhojonrestaurantpos.adapters.AllCategoriesInfo;
import com.bdtask.bhojonrestaurantpos.adapters.CateroiesListNameAdapter;
import com.bdtask.bhojonrestaurantpos.adapters.FoodListAdapater;
import com.bdtask.bhojonrestaurantpos.adapters.ItemDetailsAdapter;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Addonsinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoriesData;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Foodinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryData;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodinfoFoodList;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.datamodel.ListClassData;
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
    private TextView editextquantity;
    private RecyclerView addonsrecylerView;
    private ImageView plusbuttonaddons, minusbuttonaddons;
    private int addonsnumber = 0;
    private Button addcartfromaddons;
    private int now = 1;
    private double n;
    private RecyclerView itemshowRecylerview;
    private SearchView searchView;
    List<CategoryData> categorieslist;
    private String productsId;
    List<ListClassData> listClassData = new ArrayList<>();
    private boolean haveToInsert = false;

    Double z =0.0;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(MainActivity.this);
        itemshowRecylerview = findViewById(R.id.itemshowRecylerview);
        itemshowRecylerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        itemshowRecylerview.setHasFixedSize(true);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        searchView = findViewById(R.id.searchviewId);
//        searchView.setSearchableInfo(searchManager
//                .getSearchableInfo(getComponentName()));
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if(categorieslist.contains(query)){
//
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String query) {
//
//                return false;
//            }
//        });
        // The categories List
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

    // All the categories name will be shown here
    public void getSubCategoryName() {
        waiterService.getAllCategories(id).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                categorieslist = new ArrayList<>();
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
        // All Categories List
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
        }
        // The other List
        else {
            getCategoryId = s;
            waiterService.foodListResponse(id, getCategoryId).enqueue(new Callback<FoodlistResponse>() {
                @Override
                public void onResponse(Call<FoodlistResponse> call, Response<FoodlistResponse> response) {
                    Log.d("Response Food List", "" + new Gson().toJson(response.body()));
                    List<FoodinfoFoodList> foodinfoFoodLists = response.body().getData().getFoodinfo();
                    itemRecylerview.setAdapter(new FoodListAdapater(getApplicationContext(), foodinfoFoodLists, MainActivity.this));
                }

                @Override
                public void onFailure(Call<FoodlistResponse> call, Throwable t) {

                }
            });
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void AddonsChecking(String addonsStatus, String productname, String price, String size, String productsID, List<Addonsinfo> addonsinfoList1) {
        // When Addons are available...
        if (addonsStatus.contains("1")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view2 = getLayoutInflater().inflate(R.layout.design_aleartdialog, null);
            close = view2.findViewById(R.id.closebtnaddons);
            iteminformation = view2.findViewById(R.id.iteminformation);
            itemsize = view2.findViewById(R.id.itemsize);
            editextquantity = view2.findViewById(R.id.itemquantity);
            itemprice = view2.findViewById(R.id.itemprice);
            addonsrecylerView = view2.findViewById(R.id.addonsrecylerView);
            addonsrecylerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            addonsrecylerView.setAdapter(new AddonsDetailsAdapter(MainActivity.this, addonsinfoList1));
            itemprice.setText(price);
            itemsize.setText(size);
            iteminformation.setText(productname);
            plusbuttonaddons = view2.findViewById(R.id.plusbuttonaddons);
            minusbuttonaddons = view2.findViewById(R.id.minusbuttonaddons);
            addcartfromaddons = view2.findViewById(R.id.addcartfromaddons);
            productsId = productsID;
            now = Integer.parseInt(editextquantity.getText().toString());
            Log.d("addonsCheck", "" + now);
            plusbuttonaddons.setOnClickListener(v -> {
                // Updating the addons Number

                addonsnumber += 1;
                now = Integer.parseInt(editextquantity.getText().toString());
                addonsnumber += now;
                editextquantity.setText(String.valueOf(addonsnumber));
                addonsnumber = 0;
            });
            minusbuttonaddons.setOnClickListener(v -> {
                now = Integer.parseInt(editextquantity.getText().toString());
                // If the number is zero then it can't be minimized
                if (now != 0) {
                    now -= 1;
                    editextquantity.setText(String.valueOf(now));
                    addonsnumber = 0;
                }
            });
            builder.setView(view2);
            AlertDialog alert = builder.create();
            close.setOnClickListener(view -> alert.dismiss());


            addcartfromaddons.setOnClickListener(v -> {
                String t = SharedPref.read("priceaddons", "");
                now = Integer.parseInt(editextquantity.getText().toString());
                ListClassData listClassData1 = new ListClassData(productname, price, size, t, productsID, now);
                if (listClassData.size() == 0) {
                    listClassData.add(listClassData1);
                } else {
                    haveToInsert = false;
                    for (int i = 0; i < listClassData.size(); i++) {
                        if (productsID.equals(listClassData.get(i).getProductsID())) {
                            listClassData.get(i).setQuantity(now + listClassData.get(i).getQuantity());
                            haveToInsert = false;
                            break;
                        } else {
                            haveToInsert = true;
                        }
                    }

                    if (haveToInsert) {
                        haveToInsert = false;
                        listClassData.add(listClassData1);
                    }
                }
                Log.wtf("Datacheck", "" + new Gson().toJson(listClassData));
                Log.wtf("Datacheck22", "" + productsID);
                ItemDetailsAdapter itemDetailsAdapter = new ItemDetailsAdapter(MainActivity.this, listClassData);
                itemshowRecylerview.setAdapter(itemDetailsAdapter);
                itemDetailsAdapter.notifyDataSetChanged();
                alert.dismiss();
            });
            alert.show();
        }
        // When Addons are not available
        else {
            String t = SharedPref.read("priceaddons", "");
            now = 1;
            ListClassData listClassData1 = new ListClassData(productname, price, size, t, productsID, now);
            if (listClassData.size() == 0) {
                listClassData.add(listClassData1);
            } else {
                haveToInsert = false;
                for (int i = 0; i < listClassData.size(); i++) {
                    if (productsID.equals(listClassData.get(i).getProductsID())) {
                        n=0;
                        listClassData.get(i).setQuantity(now + listClassData.get(i).getQuantity());
                        haveToInsert = false;
                        break;
                    } else {
                        haveToInsert = true;
                    }
                }

                if (haveToInsert) {
                    haveToInsert = false;
                    listClassData.add(listClassData1);
                }
            }
            Log.wtf("Datacheck", "" + new Gson().toJson(listClassData));
            Log.wtf("Datacheck22", "" + productsID);
            ItemDetailsAdapter itemDetailsAdapter = new ItemDetailsAdapter(MainActivity.this, listClassData);
            itemshowRecylerview.setAdapter(itemDetailsAdapter);
            itemDetailsAdapter.notifyDataSetChanged();
        }
    }


    public void AddonsCheckingForAllCategories(String addonsStatus, String productname, String price, String size, String productsID, List<com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo> addonsinfoList) {

        // When Addons are available
        if (addonsStatus.contains("1")) {
            Log.d("Addons List Check", "" + new Gson().toJson(addonsinfoList));
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view2 = getLayoutInflater().inflate(R.layout.design_aleartdialog, null);

            close = view2.findViewById(R.id.closebtnaddons);
            iteminformation = view2.findViewById(R.id.iteminformation);
            itemsize = view2.findViewById(R.id.itemsize);
            editextquantity = view2.findViewById(R.id.itemquantity);
            itemprice = view2.findViewById(R.id.itemprice);
            addonsrecylerView = view2.findViewById(R.id.addonsrecylerView);
            addonsrecylerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            addonsrecylerView.setAdapter(new AddonsDetailsAdapter(MainActivity.this, addonsinfoList));
            itemprice.setText(price);
            itemsize.setText(size);
            iteminformation.setText(productname);
            plusbuttonaddons = view2.findViewById(R.id.plusbuttonaddons);
            minusbuttonaddons = view2.findViewById(R.id.minusbuttonaddons);
            addcartfromaddons = view2.findViewById(R.id.addcartfromaddons);

            plusbuttonaddons.setOnClickListener(v -> {
                // Updating the addons Number
                addonsnumber += 1;
                now = Integer.parseInt(editextquantity.getText().toString());
                addonsnumber += now;
                editextquantity.setText(String.valueOf(addonsnumber));
                addonsnumber = 0;
            });
            minusbuttonaddons.setOnClickListener(v -> {

                now = Integer.parseInt(editextquantity.getText().toString());
                // If the number is zero then it can't be minimized
                if (now != 0) {
                    now -= 1;
                    editextquantity.setText(String.valueOf(now));
                    addonsnumber = 0;
                }
            });
            builder.setView(view2);
            AlertDialog alert = builder.create();
            close.setOnClickListener(view -> alert.dismiss());
            addcartfromaddons.setOnClickListener(v -> {

                String t = SharedPref.read("priceaddons", "");
                now = Integer.parseInt(editextquantity.getText().toString());
                ListClassData listClassData1 = new ListClassData(productname, price, size, t, productsID, now);
                if (listClassData.size() == 0) {
                    listClassData.add(listClassData1);
                } else {
                    haveToInsert = false;
                    for (int i = 0; i < listClassData.size(); i++) {
                        if (productsID.equals(listClassData.get(i).getProductsID())) {
                            listClassData.get(i).setQuantity(now + listClassData.get(i).getQuantity());
                            haveToInsert = false;
                            break;
                        } else {
                            haveToInsert = true;
                        }
                    }

                    if (haveToInsert) {
                        haveToInsert = false;
                        listClassData.add(listClassData1);
                    }
                }
                Log.wtf("Datacheck", "" + new Gson().toJson(listClassData));
                Log.wtf("Datacheck22", "" + productsID);
                ItemDetailsAdapter itemDetailsAdapter = new ItemDetailsAdapter(MainActivity.this, listClassData);
                itemshowRecylerview.setAdapter(itemDetailsAdapter);
                itemDetailsAdapter.notifyDataSetChanged();

                alert.dismiss();
            });
            alert.show();
        }


        // When Addons are not available
        else {

            String t = SharedPref.read("priceaddons", "");
            ListClassData listClassData1 = new ListClassData(productname, price, size, t, productsID, now);
            if (listClassData.size() == 0) {
                listClassData.add(listClassData1);
            } else {
                haveToInsert = false;
                for (int i = 0; i < listClassData.size(); i++) {
                    if (productsID.equals(listClassData.get(i).getProductsID())) {
                        listClassData.get(i).setPrice(listClassData.get(i).getPrice());
                        listClassData.get(i).setQuantity(1 + listClassData.get(i).getQuantity());
                        haveToInsert = false;
                        break;
                    } else {
                        haveToInsert = true;
                    }
                }

                if (haveToInsert) {
                    haveToInsert = false;
                    listClassData.add(listClassData1);
                }
            }
            Log.wtf("Datacheck", "" + new Gson().toJson(listClassData));
            Log.wtf("Datacheck22", "" + productsID);
            ItemDetailsAdapter itemDetailsAdapter = new ItemDetailsAdapter(MainActivity.this, listClassData);
            itemshowRecylerview.setAdapter(itemDetailsAdapter);
            itemDetailsAdapter.notifyDataSetChanged();
        }
    }

}