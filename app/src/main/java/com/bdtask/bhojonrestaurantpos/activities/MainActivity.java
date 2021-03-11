package com.bdtask.bhojonrestaurantpos.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
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
import com.bdtask.bhojonrestaurantpos.adapters.OngoingOrderAdapter;
import com.bdtask.bhojonrestaurantpos.fragments.KitchenStatusFragment;
import com.bdtask.bhojonrestaurantpos.fragments.OngoingOrderFragment;
import com.bdtask.bhojonrestaurantpos.fragments.OnlineOrderFragment;
import com.bdtask.bhojonrestaurantpos.fragments.QROrderFragment;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Addonsinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoriesData;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.AllCategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Allcategory.Foodinfo;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryData;
import com.bdtask.bhojonrestaurantpos.modelClass.Category.CategoryResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerList.CustomerListData;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerList.CustomerListResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerType.CustomerTypeData;
import com.bdtask.bhojonrestaurantpos.modelClass.CustomerType.CustomerTypeResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodinfoFoodList;
import com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.FoodlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.OngoingOrder.OngoingOrderData;
import com.bdtask.bhojonrestaurantpos.modelClass.PlaceOrder.PlaceOrderResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.SignupNewCustomer.SignupNewCustomerResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.WaiterList.WaiterlistData;
import com.bdtask.bhojonrestaurantpos.modelClass.WaiterList.WaiterlistResponse;
import com.bdtask.bhojonrestaurantpos.modelClass.datamodel.ListClassData;
import com.bdtask.bhojonrestaurantpos.modelClass.tablelist.TableListData;
import com.bdtask.bhojonrestaurantpos.modelClass.tablelist.TableResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.retrofit.WaiterService;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.signal.DialogPropertiesSignal;
import org.imaginativeworld.oopsnointernet.dialogs.signal.NoInternetDialogSignal;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ViewInterface {
    private EditText discountET;
    private RecyclerView subcategoryName, itemRecylerview;
    private WaiterService waiterService;
    private String id = "";
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
    private RecyclerView itemshowRecylerview;
    private SearchView searchView;
    private List<CategoryData> categorieslist;
    private String productsId;
    private List<ListClassData> listClassData = new ArrayList<>();
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
    private String service_charge;
    private int addonprice = 0;
    private RelativeLayout view_layout;
    private FrameLayout framelayout_ongoing_order;
    private SearchableSpinner searchableSpinnerCustomerType;
    private List<CustomerTypeData> customerTypeData;
    private List<String> customerTypeNames;
    private Spinner tablelist_spinner;
    private List<TableListData> tableListData;
    private List<String> tableName;
    private Spinner spinnerwaiter;
    private List<WaiterlistData> waiterslist;
    private List<String> waiterlistnames;
    private TextView grandtotalTV, taxTV;
    private String restaurent_Vat;
    private Double subtotal;
    private List<AllCategoriesData> list;
    private Spinner spinnercustomername;
    private List<CustomerListData> customerListData;
    private List<String> customerNames;
    private String customerNameFromSpinner, customerTypeFromSpinner, waiterFromSpinner, tableFromSpinner, discount;
    private double grand_total;
    private List<Foodinfo> categoriesData;
    private List<FoodinfoFoodList> foodinfoFoodLists;
    private Double discountvalue;
    private ImageView addingCustomer;
    private ImageView closeAleart;
    private Button closeAleartbyButton, submitAleartbyButton;
    private EditText addcustomername, addcustomeremail, addcustomermobile, addcustomeraddress, addcustomerfavouriteaddress;
    private String productvat = "0";
    double restaurent_vatt;
    private String orderid;
    private EditText searchviewinmain;
    FragmentManager fm;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPref.init(MainActivity.this);

        SharedPref.write("State", "Neworder");
        // No Internet Dialog: Pendulam
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(
                this,
                getLifecycle()
        );

        DialogPropertiesPendulum properties = builder.getDialogProperties();

        properties.setConnectionCallback(new ConnectionCallback() { // Optional
            @Override
            public void hasActiveConnection(boolean hasActiveConnection) {
                // ...
            }
        });

        properties.setCancelable(false); // Optional
        properties.setNoInternetConnectionTitle("No Internet"); // Optional
        properties.setNoInternetConnectionMessage("Check your Internet connection and try again"); // Optional
        properties.setShowInternetOnButtons(true); // Optional
        properties.setPleaseTurnOnText("Please turn on"); // Optional
        properties.setWifiOnButtonText("Wifi"); // Optional
        properties.setMobileDataOnButtonText("Mobile data"); // Optional

        properties.setOnAirplaneModeTitle("No Internet"); // Optional
        properties.setOnAirplaneModeMessage("You have turned on the airplane mode."); // Optional
        properties.setPleaseTurnOffText("Please turn off"); // Optional
        properties.setAirplaneModeOffButtonText("Airplane mode"); // Optional
        properties.setShowAirplaneModeOffButtons(true); // Optional

        builder.build();
        // No Internet Dialog: signal
//        NoInternetDialogSignal.Builder builder = new NoInternetDialogSignal.Builder(
//                this,
//                getLifecycle()
//        );
//
//        DialogPropertiesSignal properties = builder.getDialogProperties();
//
//        properties.setConnectionCallback(new ConnectionCallback() { // Optional
//            @Override
//            public void hasActiveConnection(boolean hasActiveConnection) {
//                // ...
//            }
//        });
//
//        properties.setCancelable(false); // Optional
//        properties.setNoInternetConnectionTitle("No Internet"); // Optional
//        properties.setNoInternetConnectionMessage("Check your Internet connection and try again"); // Optional
//        properties.setShowInternetOnButtons(true); // Optional
//        properties.setPleaseTurnOnText("Please turn on"); // Optional
//        properties.setWifiOnButtonText("Wifi"); // Optional
//        properties.setMobileDataOnButtonText("Mobile data"); // Optional
//
//        properties.setOnAirplaneModeTitle("No Internet"); // Optional
//        properties.setOnAirplaneModeMessage("You have turned on the airplane mode."); // Optional
//        properties.setPleaseTurnOffText("Please turn off"); // Optional
//        properties.setAirplaneModeOffButtonText("Airplane mode"); // Optional
//        properties.setShowAirplaneModeOffButtons(true); // Optional
//
//        builder.build();
//
//        builder.build();


        if (SharedPref.read("ID", "").isEmpty() || SharedPref.read("ID", "") == null) {
            SharedPref.write("LOGGEDIN", "");
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else {
            id = SharedPref.read("ID", "");
        }
        List<Foodinfo> foodinfos = new ArrayList<>();
        init();
        searchviewinmain.setHint("Search Food by Name");
        foodinfoFoodLists = new ArrayList<>();
        list = new ArrayList<>();
        itemshowRecylerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        itemshowRecylerview.setHasFixedSize(true);
        buttoncancel.setOnClickListener(v -> buttoncancelopearations());
        buttoncalculator.setOnClickListener(v -> Calculator());
        subcategoryName.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.HORIZONTAL, false));
        itemRecylerview.setLayoutManager(new GridLayoutManager(MainActivity.this, 5));
        itemRecylerview.addItemDecoration(new SpacingItemDecoration(3, Tools.dpToPx(this, 2), true));
        waiterService = AppConfig.getRetrofit().create(WaiterService.class);
        //id = SharedPref.read("ID", "");

        getSubCategoryName();


        // When User click for new Order
        newOrder.setOnClickListener(v -> {
            view_layout.setVisibility(View.VISIBLE);
            framelayout_ongoing_order.setVisibility(View.GONE);
            SharedPref.write("State", "Neworder");
            searchviewinmain.setHint("Enter Food by Name");
        });


        // When User click for ongoing Order
        ongoingOrder.setOnClickListener(v -> {
            view_layout.setVisibility(View.GONE);
            SharedPref.write("State", "Ongoing");
            searchviewinmain.setHint("Search Food by OrderID or Table Name");
            framelayout_ongoing_order.setVisibility(View.VISIBLE);
            fm = getSupportFragmentManager();
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.framelayout_ongoing_order, new OngoingOrderFragment(""),"SOMETAG");
//            ft.commit();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.framelayout_ongoing_order, new OngoingOrderFragment(), "SOMETAG").commit();
        });


        // when user click for kitchen status
        kitchenStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPref.write("State", "Kitchen");
                searchviewinmain.setHint("Search Food by OrderID ");
                view_layout.setVisibility(view_layout.GONE);
                framelayout_ongoing_order.setVisibility(View.VISIBLE);
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.framelayout_ongoing_order, new KitchenStatusFragment());
//                ft.commit();
                fm = getSupportFragmentManager();
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.framelayout_ongoing_order, new KitchenStatusFragment(), "KitchenTag").commit();
            }
        });

        // when user click for QR Order status
        qrOrder.setOnClickListener(v -> {
            view_layout.setVisibility(view_layout.GONE);
            SharedPref.write("State", "Qrorder");
            searchviewinmain.setHint("Search Food by OrderID ");
            framelayout_ongoing_order.setVisibility(View.VISIBLE);
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.replace(R.id.framelayout_ongoing_order, new QROrderFragment());
//            ft.commit();
            fm = getSupportFragmentManager();
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.framelayout_ongoing_order, new QROrderFragment(), "QrOderTag").commit();
        });


        // when user click for Online status
        onlineOrder.setOnClickListener(v -> {
            SharedPref.write("State", "Onlineorder");
            view_layout.setVisibility(view_layout.GONE);
            searchviewinmain.setHint("Search Food by OrderID ");
            framelayout_ongoing_order.setVisibility(View.VISIBLE);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.framelayout_ongoing_order, new OnlineOrderFragment());
            ft.commit();
        });

        // When user click on Quick order in new order page
        buttonquickorder.setOnClickListener(v -> {
            placeorderdetails();
        });
        // Setting up All the customer name in the Customer Name Spinner List
        spinnercustomernamelist();
        // Setting up the customer type data in spinner from API
        searchableSpinnerdata();

        // Setting up the table list data in spinner from API
        tablelist_spinnerdata();
        //tableFromSpinner = tablelist_spinner.getSelectedItem().toString();
        // setting up the waiters list data in spinner from API
        waiterslistspinnerdata();
        // waiterFromSpinner = spinnerwaiter.getSelectedItem().toString();
        // Order Button Funtionality setup
        placeorder.setOnClickListener(v -> {
            placeorderdetails();
        });
        // When anyone Setting discount ...
        discountET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                settingDiscoun(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // Adding new Customer
        addingCustomer.setOnClickListener(v -> {
            addingnewCustomer();
        });
        searchviewinmain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (SharedPref.read("State", "").contains("Neworder")) {
                    serchingTableInNewOrder(s.toString());

                } else if (SharedPref.read("State", "").contains("Ongoing")) {
                    OngoingOrderFragment fragmentDemo = (OngoingOrderFragment) getSupportFragmentManager().findFragmentByTag("SOMETAG");
                    fragmentDemo.serchingTable(s.toString());

                } else if (SharedPref.read("State", "").contains("Kitchen")) {
                    KitchenStatusFragment fragmentDemo = (KitchenStatusFragment) getSupportFragmentManager().findFragmentByTag("KitchenTag");
                    fragmentDemo.serchingTableInKitchen(s.toString());

                } else if (SharedPref.read("State", "").contains("Qrorder")) {


                } else if (SharedPref.read("State", "").contains("Onlineorder")) {

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        searchableSpinnerCustomerType = findViewById(R.id.spinnercustomertype);
        tablelist_spinner = findViewById(R.id.spinnertable);
        spinnerwaiter = findViewById(R.id.spinnerwaiter);
        grandtotalTV = findViewById(R.id.grandtotalTV);
        taxTV = findViewById(R.id.taxTV);
        grandtotalTV = findViewById(R.id.grandtotalTV);
        spinnercustomername = findViewById(R.id.spinnercustomername);
        discountET = findViewById(R.id.discountET);
        addingCustomer = findViewById(R.id.addingcustomer);
        searchviewinmain = findViewById(R.id.searchviewinmain);

    }

    // when user click the cancel button
    private void buttoncancelopearations() {
        if (listClassData.size() > 0) {
            taxTV.setText("0");
            grandtotalTV.setText("0");
            listClassData.clear();
            itemshowRecylerview.setAdapter(new ItemDetailsAdapter(MainActivity.this, listClassData));
        } else {
            Toasty.info(MainActivity.this, "No more items to delete", Toast.LENGTH_SHORT, true).show();
        }
    }

    private void spinnercustomernamelist() {
        try {
            waiterService.getallCustomersName(id).enqueue(new Callback<CustomerListResponse>() {
                @Override
                public void onResponse(Call<CustomerListResponse> call, Response<CustomerListResponse> response) {
                    customerNames = new ArrayList<>();
                    customerListData = new ArrayList<>();
                    try {
                        customerListData = response.body().getData();
                        for (int i = 0; i < customerListData.size(); i++) {
                            customerNames.add(customerListData.get(i).getCustomerName());
                        }
                        spinnercustomername.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, customerNames));
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<CustomerListResponse> call, Throwable t) {

                }
            });
            spinnercustomername.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    customerNameFromSpinner = spinnercustomername.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {

        }
    }

    private void searchableSpinnerdata() {
        try {
            waiterService.getallCustomerTypes(id).enqueue(new Callback<CustomerTypeResponse>() {
                @SuppressLint("ResourceType")
                @Override
                public void onResponse(Call<CustomerTypeResponse> call, Response<CustomerTypeResponse> response) {
                    customerTypeData = new ArrayList<>();
                    customerTypeNames = new ArrayList<>();
                    try {
                        customerTypeData = response.body().getData();
                        for (int i = 0; i < customerTypeData.size(); i++) {
                            customerTypeNames.add(customerTypeData.get(i).getTypeName());
                        }

                        searchableSpinnerCustomerType.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, customerTypeNames));
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<CustomerTypeResponse> call, Throwable t) {

                }
            });
            searchableSpinnerCustomerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    customerTypeFromSpinner = searchableSpinnerCustomerType.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {

        }
    }

    private void tablelist_spinnerdata() {
        try {
            waiterService.getTableList(id).enqueue(new Callback<TableResponse>() {
                @Override
                public void onResponse(Call<TableResponse> call, Response<TableResponse> response) {

                    tableListData = new ArrayList<>();
                    tableName = new ArrayList<>();
                    try {
                        tableListData = response.body().getData();
                        for (int i = 0; i < tableListData.size(); i++) {
                            tableName.add(tableListData.get(i).getTableName());
                        }
                        tablelist_spinner.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, tableName));
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<TableResponse> call, Throwable t) {

                }
            });
            tablelist_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    tableFromSpinner = tablelist_spinner.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {

        }
    }

    private void waiterslistspinnerdata() {
        try {
            waiterService.getallWaitersList(id).enqueue(new Callback<WaiterlistResponse>() {
                @Override
                public void onResponse(Call<WaiterlistResponse> call, Response<WaiterlistResponse> response) {
                    waiterslist = new ArrayList<>();
                    waiterlistnames = new ArrayList<>();
                    try {
                        waiterslist = response.body().getData();
                        for (int i = 0; i < waiterslist.size(); i++) {
                            waiterlistnames.add(waiterslist.get(i).getWaitername());
                        }
                        spinnerwaiter.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, waiterlistnames));
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<WaiterlistResponse> call, Throwable t) {

                }
            });
            spinnerwaiter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    waiterFromSpinner = spinnerwaiter.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {

        }
    }


    // All the categories name will be shown here
    public void getSubCategoryName() {
        try {
            waiterService.getAllCategories(id).enqueue(new Callback<CategoryResponse>() {
                @Override
                public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                    Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                    categorieslist = new ArrayList<>();
                    categorieslist.add(new CategoryData("0", "All Categories"));
                    try {
                        categorieslist.addAll(response.body().getData());
                    } catch (Exception e) {

                    }
                    subcategoryName.setAdapter(new CateroiesListNameAdapter(getApplicationContext(), categorieslist, MainActivity.this));
                }

                @Override
                public void onFailure(Call<CategoryResponse> call, Throwable t) {
                }
            });
        } catch (Exception e) {

        }
    }

    // All the categories item will be shown here
    public void getCategoriesItem(String position, String categoryId) {
        getPosition = position;
        // All Categories List
        if (getPosition.contains("0")) {
            itemRecylerview.setVisibility(View.VISIBLE);
            try {
                waiterService.allcategoryItemResponse(id).enqueue(new Callback<AllCategoryResponse>() {
                    @Override
                    public void onResponse(Call<AllCategoryResponse> call, Response<AllCategoryResponse> response) {
                        Log.d("Response", "Onresponse" + new Gson().toJson(response.body()));
                        try {
                            restaurent_Vat = response.body().getData().getRestaurantvat();
                            categoriesData = response.body().getData().getFoodinfo();
                            restaurent_Vat = response.body().getData().getRestaurantvat();
                            itemRecylerview.setAdapter(new AllCategoriesInfo(getApplicationContext(), categoriesData, MainActivity.this));
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<AllCategoryResponse> call, Throwable t) {
                    }
                });
            } catch (Exception e) {

            }
        }
        // The other List
        else {
            getCategoryId = categoryId;
            waiterService.foodListResponse(id, getCategoryId).enqueue(new Callback<FoodlistResponse>() {
                @Override
                public void onResponse(Call<FoodlistResponse> call, Response<FoodlistResponse> response) {
                    Log.d("Response Food List", "" + new Gson().toJson(response.body()));
                    foodinfoFoodLists = response.body().getData().getFoodinfo();
//                    categoriesData = response.body().getData().getFoodinfo();
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
    public void AddonsChecking(String productVats, String baseprice, String addonsStatus, String productname, String price, String size, String productsID, List<Addonsinfo> addonsinfoList1) {
        productvat = productVats;
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
                String priceOfAddons = SharedPref.read("priceaddons", "");
                now = Integer.parseInt(editextquantity.getText().toString());

                ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, priceOfAddons, productsID, now, addonprice, 0, productvat, now, productsID);
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
            String priceOfAddons = SharedPref.read("priceaddons", "");
            now = 1;
            ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, priceOfAddons, productsID, now, addonprice, 0, productvat, now, productsID);
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

            ItemDetailsAdapter itemDetailsAdapter = new ItemDetailsAdapter(MainActivity.this, listClassData);
            itemshowRecylerview.setAdapter(itemDetailsAdapter);
            itemDetailsAdapter.notifyDataSetChanged();
        }
    }


    public void AddonsCheckingForAllCategories(String productVat, String baseprice, String addonsStatus, String productname, String price, String size, String productsID, List<com.bdtask.bhojonrestaurantpos.modelClass.Foodlist.Addonsinfo> addonsinfoList) {
        productvat = productVat;
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
                ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, t, productsID, now, addonprice, 0, productvat, now, productsID);
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
            ListClassData listClassData1 = new ListClassData(baseprice, productname, price, size, t, productsID, now, addonprice, 0, productvat, now, productsID);
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

    private void Calculator() {
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

    public void getalltaxes(String s) {
        subtotal = Double.parseDouble(s);
        double restaurent_vatt = Double.parseDouble(restaurent_Vat);
        restaurent_vatt = (restaurent_vatt * subtotal) / 100;
        taxTV.setText(String.valueOf(restaurent_vatt));
        grand_total = subtotal + restaurent_vatt;
        grandtotalTV.setText(String.valueOf(grand_total));
    }

    private void placeorderdetails() {
        SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        pDialog.setTitleText("Do you want to print Invoice");
        pDialog.setConfirmText("Yes");
        pDialog.setCancelText("No");

        List<ListClassData> orderList = new ArrayList<>();
        List<ListClassData> orderlist2 = new ArrayList<>();
        service_charge = SharedPref.read("SC", "");
        discount = discountET.getText().toString();
//        String chec_data = ("id\t" + id + " vat\t" + taxTV.getText().toString() + " table_name\t" + tableFromSpinner + " customer_name\t" + customerNameFromSpinner +
//                " customer_type\t" + customerTypeFromSpinner + " service_charge\t" + service_charge + " discount\t" + discount + " subtotal\t" + subtotal + " grand_total"
//                + grandtotalTV.getText().toString());

        for (int i = 0; i < categoriesData.size(); i++) {
            for (int j = 0; j < listClassData.size(); j++) {
                if (!categoriesData.isEmpty()) {
                    if (listClassData.get(j).getProductsID().equals(categoriesData.get(i).getProductsID())) {
                        orderList.add(listClassData.get(j));
                    }
                } else {
                    if (listClassData.get(j).getProductsID().equals(foodinfoFoodLists.get(i).getProductsID())) {
                        orderlist2.add(listClassData.get(j));
                    }
                }
            }

        }
        if (orderList.size() == 0) {
            pDialog.dismiss();
            Toasty.error(MainActivity.this, "No Items here", Toast.LENGTH_SHORT, true).show();

        } else {
            String datas;
            if (!orderList.isEmpty()) {
                datas = new Gson().toJson(orderList);
            } else {
                datas = new Gson().toJson(orderlist2);
            }

            Log.d("checkaa", "" + datas);
            Log.d("laraman", id + "\t vatid " + String.valueOf(taxTV.getText().toString()) + "\t tableid " + tableFromSpinner + "\t customerid " +
                    customerNameFromSpinner + "\t typeid " + customerTypeFromSpinner + "\t servicecharge " + service_charge + "\t discount " + discountET.getText().toString()
                    + "\t sumD " + String.valueOf(subtotal) + "\t grandTotal " + String.valueOf(grandtotalTV.getText().toString()) + "\t datas " + datas + "\t Notes " + " ");
//            waiterService.postFoodCart(id, taxTV.getText().toString(), tableFromSpinner, customerNameFromSpinner,
//                    customerTypeFromSpinner, service_charge, discountET.getText().toString(), String.valueOf(subtotal),
//                    String.valueOf(grandtotalTV.getText().toString()), datas, "").enqueue(new Callback<PlaceOrderResponse>() {
//                @Override
//                public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
//                    String ress = response.body().getMessage();
//                    pDialog.dismiss();
//                    Toasty.info(MainActivity.this, ""+ress, Toast.LENGTH_SHORT, true).show();
//                    taxTV.setText("0");
//                    grandtotalTV.setText("0");
//                    listClassData.clear();
//                    itemshowRecylerview.setAdapter(new ItemDetailsAdapter(MainActivity.this, listClassData));
//                }
//
//                @Override
//                public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {
//
//                }
//            });
            waiterService.postFoodCart(id, String.valueOf(taxTV.getText().toString()), String.valueOf(tableFromSpinner),
                    String.valueOf(customerNameFromSpinner), String.valueOf(customerTypeFromSpinner), String.valueOf(service_charge), String.valueOf(discountET.getText().toString())
                    , String.valueOf(subtotal), String.valueOf(grandtotalTV.getText().toString()), datas, "").enqueue(new Callback<PlaceOrderResponse>() {
                @Override
                public void onResponse(Call<PlaceOrderResponse> call, Response<PlaceOrderResponse> response) {
                    String ress = response.body().getMessage();
                    orderid = response.body().getData().getOrderid().toString();
                    pDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            pDialog.dismissWithAnimation();

                        }
                    }).setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            pDialog.dismissWithAnimation();
                            printInvoice(orderid);
                        }
                    }).show();
                    //pDialog.show();

                    Toasty.info(MainActivity.this, "" + ress, Toast.LENGTH_SHORT, true).show();
                    taxTV.setText("0");
                    grandtotalTV.setText("0");
                    listClassData.clear();
                    itemshowRecylerview.setAdapter(new ItemDetailsAdapter(MainActivity.this, listClassData));
                }

                @Override
                public void onFailure(Call<PlaceOrderResponse> call, Throwable t) {

                }
            });
        }
    }

    private void printInvoice(String orderid) {
        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view2 = inflater.inflate(R.layout.aleartduepos, null);
        final ImageView crossicon = view2.findViewById(R.id.crossicon);

        builder2.setView(view2);
        AlertDialog alert = builder2.create();
        //alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WebView dueposWV = view2.findViewById(R.id.dueposWV);
        // dueposWV.setWebViewClient(new WebViewClient());
        //dueposWV.getSettings().setBuiltInZoomControls(true);
        WebSettings webSettings = dueposWV.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url = "https://soft14.bdtask.com/bhojon23_latest/appv1/posorderdueinvoice/" + orderid;
        Log.d("url", "" + url);
        dueposWV.loadUrl(url);
        crossicon.setOnClickListener(v -> {
            alert.dismiss();
        });
        alert.show();
        WindowManager wm = (WindowManager) MainActivity.this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .7;
        Double height = metrics.heightPixels * .7;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());
    }

    private void settingDiscoun(String charSequence) {

        if (charSequence != null && !charSequence.isEmpty()) {
            if (grand_total > 0) {
                double totaldiscount;
                discountvalue = (Double.parseDouble(charSequence) * Double.parseDouble(grandtotalTV.getText().toString())) / 100.00;
                totaldiscount = Double.parseDouble(grandtotalTV.getText().toString()) - discountvalue;
                grandtotalTV.setText(String.valueOf(totaldiscount));

            } else {
                grandtotalTV.setText(String.valueOf(0));
            }
        } else {
            if (grand_total > 0) {
                grandtotalTV.setText(String.valueOf(grand_total));
            } else {
                grandtotalTV.setText(String.valueOf(0));
            }
        }

    }

    private void addingnewCustomer() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view2 = getLayoutInflater().inflate(R.layout.addingnewcustomer, null);
        closeAleart = view2.findViewById(R.id.closeAleart);
        closeAleartbyButton = view2.findViewById(R.id.closeAleartbyButton);
        submitAleartbyButton = view2.findViewById(R.id.submitAleartbyButton);
        addcustomername = view2.findViewById(R.id.addcustomername);
        addcustomeremail = view2.findViewById(R.id.addcustomeremail);
        addcustomermobile = view2.findViewById(R.id.addcustomermobile);
        addcustomeraddress = view2.findViewById(R.id.addcustomeraddress);
        addcustomerfavouriteaddress = view2.findViewById(R.id.addcustomerfavouriteaddress);
        builder.setView(view2);
        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        submitAleartbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addcustomernam = addcustomername.getText().toString();
                String addcustomeremai = addcustomeremail.getText().toString();
                String addcustomermobil = addcustomermobile.getText().toString();
                String addcustomeraddres = addcustomeraddress.getText().toString();
                String addcustomerfavouriteaddres = addcustomerfavouriteaddress.getText().toString();
                String addCustomerPassword = "123456";
                if (TextUtils.isEmpty(addcustomernam)) {
                    addcustomername.setError("Please give a customer name");
                    return;
                } else if (TextUtils.isEmpty(addcustomeremai)) {
                    addcustomeremail.setError("Please give an Email");
                    return;
                } else if (TextUtils.isEmpty(addcustomermobil)) {
                    addcustomermobile.setError("Please give an mobile number");
                    return;
                } else if (TextUtils.isEmpty(addcustomeraddres)) {
                    addcustomeraddress.setError("Please give an address");
                    return;
                } else if (TextUtils.isEmpty(addcustomerfavouriteaddres)) {
                    addcustomerfavouriteaddress.setError("Please give an favouriteaddress");
                    return;
                } else {
                    addnewCustomertoDb(addcustomernam, addCustomerPassword, addcustomeremai, addcustomermobil, addcustomeraddres, addcustomerfavouriteaddres);
                }
            }
        });
        closeAleart.setOnClickListener(v -> {
            alert.dismiss();
        });
        closeAleartbyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        alert.show();
        WindowManager wm = (WindowManager) (MainActivity.this).getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * .7;
        Double height = metrics.heightPixels * .7;
        Window win = alert.getWindow();
        win.setLayout(width.intValue(), height.intValue());
    }

    private void addnewCustomertoDb(String addcustomernam, String addCustomerPassword, String addcustomeremai, String addcustomermobil, String addcustomeraddres, String addcustomerfavouriteaddres) {

        waiterService.signUpNewCustomer(addcustomeremai, addCustomerPassword, id, addcustomermobil, addcustomernam).enqueue(new Callback<SignupNewCustomerResponse>() {
            @Override
            public void onResponse(Call<SignupNewCustomerResponse> call, Response<SignupNewCustomerResponse> response) {
                String response_message = response.body().getMessage();
                Toasty.info(MainActivity.this, "" + response_message, Toasty.LENGTH_SHORT, true).show();
            }

            @Override
            public void onFailure(Call<SignupNewCustomerResponse> call, Throwable t) {
                Toasty.info(MainActivity.this, "Onfailure: " + t, Toasty.LENGTH_SHORT, true).show();
            }
        });
    }

    public void serchingTableInNewOrder(String searchingKey) {
        Log.d("valueeeee", "" + searchingKey);
        List<Foodinfo> searchingList = new ArrayList<>();

        if (searchingKey != null && !searchingKey.isEmpty()) {
            searchingList.clear();
            for (int i = 0; i < categoriesData.size(); i++) {
                if (categoriesData.get(i).getProductName().toLowerCase().startsWith(searchingKey)) {
                    searchingList.add(categoriesData.get(i));
                }

            }

            AllCategoriesInfo ongoingOrderAdapter = new AllCategoriesInfo(getApplicationContext(), searchingList, MainActivity.this);
            itemRecylerview.setAdapter(ongoingOrderAdapter);
        } else {
            AllCategoriesInfo ongoingOrderAdapter = new AllCategoriesInfo(getApplicationContext(), categoriesData, MainActivity.this);
            itemRecylerview.setAdapter(ongoingOrderAdapter);
        }
    }
}