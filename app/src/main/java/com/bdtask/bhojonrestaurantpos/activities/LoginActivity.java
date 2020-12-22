package com.bdtask.bhojonrestaurantpos.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bdtask.bhojonrestaurantpos.BuildConfig;
import com.bdtask.bhojonrestaurantpos.activities.retrofit.WaiterService;

import com.bdtask.bhojonrestaurantpos.R;
import com.bdtask.bhojonrestaurantpos.modelClass.loginModel.LoginResponse;
import com.bdtask.bhojonrestaurantpos.retrofit.AppConfig;
import com.bdtask.bhojonrestaurantpos.utils.SharedPref;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button signInBtn;
    EditText emailET, passwordET;
    private com.bdtask.bhojonrestaurantpos.activities.retrofit.WaiterService waitersService;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPref.init(this);
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Login Account");
        progressDialog.setMessage("Please Wait, While we are checking the credentials");
        progressDialog.setCanceledOnTouchOutside(false);
        TextView reset = findViewById(R.id.reset);
        reset.setOnClickListener(view -> {
            SharedPref.write("BASEURL", "");
            //startActivity(new Intent(LoginActivity.this, QrCodeActivity.class));
        });
        Log.d("sfadfsd", "onCreate: "+SharedPref.read("BASEURL",""));
        if (SharedPref.read("BASEURL", "").isEmpty()){
            SharedPref.write("BASEURL", BuildConfig.BASE_URL);
        }
        try{
            waitersService = AppConfig.getRetrofit().create(WaiterService.class);
            initAll();
            Log.d("qwewer", "onCreate: "+SharedPref.read("BASEURL",""));
            signInBtn.setOnClickListener(v -> {
                Log.d("sfadfsd", "onCreate: ");
                progressDialog.show();
                signIN();

            });

            SharedPref.write("PP", "pp");}
        catch (Exception e){
            warning();
        }


    }

    private void signIN() {
        Log.d("poi", "signIN: "+SharedPref.read("keytoken",""));
        waitersService.doSignIn(emailET.getText().toString(), passwordET.getText().toString(),SharedPref.read("keytoken",""))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        try {
                            Log.d("ppp", "onResponse: " + response.body().getMessage());
                            LoginResponse loginResponse = response.body();
                            Gson gson = new Gson();
                            String loginResponses = gson.toJson(loginResponse);

                            if (response.body().getStatus().equals("success")) {
                                SharedPref.write("LOGINRESPONSE", loginResponses);
                                SharedPref.write("LOGGEDIN", "YES");
                                SharedPref.write("ID", loginResponse.getData().getId());
                                SharedPref.write("POWERBY", loginResponse.getData().getPowerBy());
                                SharedPref.write("CURRENCY", loginResponse.getData().getCurrencysign());
                                SharedPref.write("SC", loginResponse.getData().getServicecharge());
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong email or Password", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        } catch (Exception e) {
                            Log.d("poi", "onResponse: "+e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
    }

    private void initAll() {
        signInBtn = findViewById(R.id.signInBtnId);
        emailET = findViewById(R.id.emailId);
        passwordET = findViewById(R.id.passwordId);
    }

    private void warning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Base Url is not valid.\nPlease again Scan your valid QR Code");
        builder.setPositiveButton("Rescan QR Code", (dialog, id) -> {
            //Intent intent = new Intent(LoginActivity.this, QrCodeActivity.class);
            //startActivity(intent);

        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }
}
