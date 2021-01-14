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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdtask.bhojonrestaurantpos.Interfaces.ViewInterface;
import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.SpacingItemDecoration;
import com.bdtask.bhojonrestaurantpos.Tools;
import com.bdtask.bhojonrestaurantpos.adapters.AddonsDetailsAdapter;
import com.bdtask.bhojonrestaurantpos.adapters.AllCategoriesInfo;
import com.bdtask.bhojonrestaurantpos.adapters.CateroiesListNameAdapter;
import com.bdtask.bhojonrestaurantpos.adapters.FoodListAdapater;
import com.bdtask.bhojonrestaurantpos.adapters.ItemDetailsAdapter;
import com.bdtask.bhojonrestaurantpos.fragments.KitchenStatusFragment;
import com.bdtask.bhojonrestaurantpos.fragments.OngoingOrderFragment;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Addonsinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoriesData;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Foodinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryData;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerType.CustomerTypeData;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerType.CustomerTypeResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodinfoFoodList;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.WaiterList.WaiterlistData;
import com.bdtask.bhojonrestaurantpos.modelClass.WaiterList.WaiterlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.datamodel.CustomerTypeName;
import com.bdtask.bhojonrestaurantpos.modelClass.datamodel.ListClassData;
import com.bdtask.bhojonrestaurantpos.modelClass.tablelist.TableListData;
import com.bdtask.bhojonrestaurantpos.modelClass.tablelist.TableResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ViewInterface {
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
    private TextView buttoncalculator;
    private Button buttoncancel, buttonquickorder, placeorder;
    private double in1 = 0, i2 = 0;
    private TextView edittext1;
    private boolean Add, Sub, Multiply, Divide, Remainder, deci;
    private Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7,
            button_8, button_9, button_Add, button_Sub,
            button_Mul, button_Div, button_Equ, button_Del, button_Dot, button_Remainder,
            newOrder, ongoingOrder, kitchenStatus, qrOrder, onlineOrder;
    private int addonprice = 0;
    private Double z = 0.0;
    private RelativeLayout view_layout;
    private FrameLayout framelayout_ongoing_order;
    private SearchableSpinner searchableSpinnerCustomerName;
    private List<CustomerTypeData> customerTypeData;
    private List<String> customerTypeNames;
    private Spinner tablelist_spinner;
    private List<TableListData> tableListData;
    private List<String> tableName;
    private Spinner spinnerwaiter;
    private List<WaiterlistData> waiterslist;
    private List<String> waiterlistnames;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(MainActivity.this);
        init();
        itemshowRecylerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        itemshowRecylerview.setHasFixedSize(true);
        buttoncancel.setOnClickListener(v -> buttoncancelopearations());
        buttoncalculator.setOnClickListener(v -> calculate());
        subcategoryName.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));
        itemRecylerview.setLayoutManager(new GridLayoutManager(MainActivity.this, 5));
        itemRecylerview.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(this, 2), true));
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);

        id = SharedPref.read("ID", "");
        Log.wtf("chekID", "ID" + id);
        getSubCategoryName();
        // When User click for new Order
        newOrder.setOnClickListener(v -> {
            view_layout.setVisibility(View.VISIBLE);
            framelayout_ongoing_order.setVisibility(View.GONE);
        });
        // When User click for ongoing Order
        ongoingOrder.setOnClickListener(v -> {
            view_layout.setVisibility(View.GONE);
            framelayout_ongoing_order.setVisibility(View.VISIBLE);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout_ongoing_order, new OngoingOrderFragment());
            ft.commit();
        });
        // when user click for kitchen status
        kitchenStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_layout.setVisibility(view_layout.GONE);
                framelayout_ongoing_order.setVisibility(View.VISIBLE);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.framelayout_ongoing_order, new KitchenStatusFragment());
                ft.commit();
            }
        });
        // Setting up the customer type data in spinner from API
        searchableSpinnerdata();
        // Setting up the table list data in spinner from API
        tablelist_spinnerdata();
        // setting up the waiters list data in spinner from API
        waiterslistspinnerdata();
//        qrOrder.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                view_layout.setVisibility(view_layout.GONE);
////                framelayout_ongoing_order.setVisibility(View.VISIBLE);
////                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
////                ft.replace(R.id.framelayout_ongoing_order, new ());
////                ft.commit();
////            }
////        });
    }

    // binding all the views with xml
    private void init() {
        framelayout_ongoing_order = findViewById(R.id.framelayout_ongoing_order);
        newOrder = findViewById(R.id.newOrder);
        ongoingOrder = findViewById(R.id.ongoingOrder);
        kitchenStatus = findViewById(R.id.kitchenStatus);
        qrOrder = findViewById(R.id.qrOrder);
        onlineOrder = findViewById(R.id.onlineOrder);
        itemRecylerview = findViewById(R.id.itemRecylerview);
        subcategoryName = findViewById(R.id.subcategoryName);
        itemshowRecylerview = findViewById(R.id.itemshowRecylerview);
        buttoncalculator = findViewById(R.id.buttoncalculator);
        buttoncancel = findViewById(R.id.buttoncancel);
        buttonquickorder = findViewById(R.id.buttonquickorder);
        placeorder = findViewById(R.id.placeorder);
        view_layout = findViewById(R.id.view_layout);
        searchableSpinnerCustomerName = findViewById(R.id.spinnercustomertype);
        tablelist_spinner = findViewById(R.id.spinnertable);
        spinnerwaiter = findViewById(R.id.spinnerwaiter);
    }
    // when user click the cancel button
    private void buttoncancelopearations() {
        listClassData.clear();
        itemshowRecylerview.setAdapter(new ItemDetailsAdapter(MainActivity.this, listClassData));
    }
    private void searchableSpinnerdata() {
        waiterService.getallCustomerTypes(id).enqueue(new Callback<CustomerTypeResponse>() {
            @Override
            public void onResponse(Call<CustomerTypeResponse> call, Response<CustomerTypeResponse> response) {
                customerTypeData = new ArrayList<>();
                customerTypeNames = new ArrayList<>();
                customerTypeData = response.body().getData();
                for (int i = 0; i < customerTypeData.size(); i++) {
                    customerTypeNames.add(customerTypeData.get(i).getTypeName());
                }
                searchableSpinnerCustomerName.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, customerTypeNames));
            }

            @Override
            public void onFailure(Call<CustomerTypeResponse> call, Throwable t) {

            }
        });

    }

    private void tablelist_spinnerdata() {
        waiterService.getTableList(id).enqueue(new Callback<TableResponse>() {
            @Override
            public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {

                tableListData = new ArrayList<>();
                tableName = new ArrayList<>();
                tableListData = response.body().getData();
                for (int i = 0; i < tableListData.size(); i++) {
                    tableName.add(tableListData.get(i).getTableName());
                }
                tablelist_spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, tableName));

            }

            @Override
            public void onFailure(Call<TableResponse> call, Throwable t) {

            }
        });
    }

    private void waiterslistspinnerdata() {
        waiterService.getallWaitersList(id).enqueue(new Callback<WaiterlistResponse>() {
            @Override
            public void onResponse(Call<WaiterlistResponse> call, Response<WaiterlistResponse> response) {
                waiterslist= new ArrayList<>();
                waiterlistnames = new ArrayList<>();
                waiterslist = response.body().getData();
                for(int i=0; i<waiterslist.size();i++){
                    waiterlistnames.add(waiterslist.get(i).getWaitername());
                }
                spinnerwaiter.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,waiterlistnames));
            }

            @Override
            public void onFailure(Call<WaiterlistResponse> call, Throwable t) {

            }
        });
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
    // All the categories item will be shown here
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
    // If the list contains any addons
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void AddonsChecking(String baseprice, String addonsStatus, String productname, String price, String size, String productsID, List<Addonsinfo> addonsinfoList1) {
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
            addonsrecylerView.setAdapter(new AddonsDetailsAdapter(MainActivity.this, addonsinfoList1, MainActivity.this::view));
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
                ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, t, productsID, now, addonprice);
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
            SharedPref.write("booleanstat", "false");
            String t = SharedPref.read("priceaddons", "");
            now = 1;
            ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, t, productsID, now, addonprice);
            if (listClassData.size() == 0) {
                listClassData.add(listClassData1);
            } else {
                haveToInsert = false;
                for (int i = 0; i < listClassData.size(); i++) {
                    if (productsID.equals(listClassData.get(i).getProductsID())) {
                        n = 0;
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


    public void AddonsCheckingForAllCategories(String baseprice, String addonsStatus, String productname, String price, String size, String productsID, List<com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo> addonsinfoList) {
        String bprice = baseprice;
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
            addonsrecylerView.setAdapter(new AddonsDetailsAdapter(MainActivity.this, addonsinfoList, MainActivity.this::view));
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
                ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, t, productsID, now, addonprice);
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
            ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, t, productsID, now, addonprice);
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

    private void calculate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view2 = getLayoutInflater().inflate(R.layout.aleartdialog_calculator, null);
        button_0 = view2.findViewById(R.id.b0);
        button_1 = view2.findViewById(R.id.b1);
        button_2 = view2.findViewById(R.id.b2);
        button_3 = view2.findViewById(R.id.b3);
        button_4 = view2.findViewById(R.id.b4);
        button_5 = view2.findViewById(R.id.b5);
        button_6 = view2.findViewById(R.id.b6);
        button_7 = view2.findViewById(R.id.b7);
        button_8 = view2.findViewById(R.id.b8);
        button_9 = view2.findViewById(R.id.b9);
        button_Dot = view2.findViewById(R.id.bDot);
        button_Add = view2.findViewById(R.id.badd);
        button_Sub = view2.findViewById(R.id.bsub);
        button_Mul = view2.findViewById(R.id.bmul);
        button_Div = view2.findViewById(R.id.biv);
        button_Remainder = view2.findViewById(R.id.BRemain);
        button_Del = view2.findViewById(R.id.buttonDel);
        button_Equ = view2.findViewById(R.id.buttoneql);
        edittext1 = view2.findViewById(R.id.display);
        button_1.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "1"));

        button_2.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "2"));

        button_3.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "3"));

        button_4.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "4"));

        button_5.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "5"));

        button_6.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "6"));

        button_7.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "7"));

        button_8.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "8"));

        button_9.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "9"));

        button_0.setOnClickListener(v -> edittext1.setText(edittext1.getText() + "0"));

        button_Add.setOnClickListener(v -> {
            if (edittext1.getText().length() != 0) {
                in1 = Float.parseFloat(edittext1.getText() + "");
                Add = true;
                deci = false;
                edittext1.setText(null);
            }
        });

        button_Sub.setOnClickListener(v -> {
            if (edittext1.getText().length() != 0) {
                in1 = Float.parseFloat(edittext1.getText() + "");
                Sub = true;
                deci = false;
                edittext1.setText(null);
            }
        });

        button_Mul.setOnClickListener(v -> {
            if (edittext1.getText().length() != 0) {
                in1 = Float.parseFloat(edittext1.getText() + "");
                Multiply = true;
                deci = false;
                edittext1.setText(null);
            }
        });

        button_Div.setOnClickListener(v -> {
            if (edittext1.getText().length() != 0) {
                in1 = Float.parseFloat(edittext1.getText() + "");
                Divide = true;
                deci = false;
                edittext1.setText(null);
            }
        });

        button_Remainder.setOnClickListener(v -> {
            if (edittext1.getText().length() != 0) {
                in1 = Float.parseFloat(edittext1.getText() + "");
                Remainder = true;
                deci = false;
                edittext1.setText(null);
            }
        });

        button_Equ.setOnClickListener(v -> {
            if (Add || Sub || Multiply || Divide || Remainder) {
                i2 = Float.parseFloat(edittext1.getText() + "");
            }

            if (Add) {

                edittext1.setText(in1 + i2 + "");
                Add = false;
            }

            if (Sub) {

                edittext1.setText(in1 - i2 + "");
                Sub = false;
            }

            if (Multiply) {
                edittext1.setText(in1 * i2 + "");
                Multiply = false;
            }

            if (Divide) {
                edittext1.setText(in1 / i2 + "");
                Divide = false;
            }
            if (Remainder) {
                edittext1.setText(in1 % i2 + "");
                Remainder = false;
            }
        });

        button_Del.setOnClickListener(v -> {
            edittext1.setText("");
            in1 = 0.0;
            i2 = 0.0;
        });

        button_Dot.setOnClickListener(v -> {
            if (deci) {

            } else {
                edittext1.setText(edittext1.getText() + ".");
                deci = true;
            }

        });
        builder.setView(view2);
        AlertDialog alert = builder.create();
        alert.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        alert.show();

    }

    @Override
    public void view(String addonsprice) {
        addonprice += Integer.parseInt(addonsprice);

    }
}