package com.example.restaurant_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.loginallmodel.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Loginall extends AppCompatActivity {

    ImageView logo_image;
    EditText email, password;
    TextView forgot_password;
    Button sign_up, login_btn;
    private CheckBox rememberMe;
    RetrofitInterface retrofitInterface;
//    LoginResult loginResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_all );

        //Init Services

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        logo_image = (ImageView) findViewById(R.id.logo_image);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        sign_up = (Button) findViewById(R.id.sign_up);
        login_btn = (Button) findViewById(R.id.login_btn);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);

        SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);

        String checkbox = preferences.getString("remember","");
        if (checkbox.equals("true")) {
            Intent intent = new Intent( Loginall.this, UserHome.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            //Toast.makeText(UserLogin.this, "Please Sign in...", Toast.LENGTH_SHORT).show();
        }

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Loginall.this, UserRegister.class);
                startActivity(i);
            }
        });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = retrofitClient.create(RetrofitInterface.class);
                HashMap<String, String> map = new HashMap<>();

                map.put("email", email.getText().toString());
                map.put("password", password.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText( Loginall.this, "Login Success", Toast.LENGTH_SHORT).show();

                            LoginResult loginResult = new LoginResult();
                            loginResult = response.body();

                            String token = loginResult.getYourAccessToken();
                          //  String refreshtoken = loginResult.getYourRefreshToken();
                            SharedPreferences preferences = getSharedPreferences("token", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("TOKEN",token);
                            editor.commit();

                            if (response.body().getRole().matches( "user" )){
                                Intent user = new Intent(Loginall.this,UserHome.class);
                                startActivity( user );
                            }
                            else if (response.body().getRole().matches( "admin" )){
                                Intent admin = new Intent(Loginall.this,AdminHome.class);
                                startActivity( admin );
                            }
                            else if (response.body().getRole().matches( "manager" )){
                                Intent manager = new Intent(Loginall.this,ManagerHome.class);
                                startActivity( manager );
                            }
                            else if (response.body().getRole().matches( "cook" )){
                                Intent cook = new Intent(Loginall.this,CookHome.class);
                                startActivity( cook );
                            }
//                            else if (response.body().getRole().matches( "waiter" )){
//                                Intent waiter = new Intent(Loginall.this,WaiterHome.class);
//                                startActivity( waiter );
//                            }


                        } else {
                            Toast.makeText( Loginall.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        System.out.println("############################ " + t.getLocalizedMessage());
                    }
                });
            }
        });



        rememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();

                }else if (!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checked",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent( Loginall.this, UserForgetPassword.class);
                startActivity(m);
            }
        });
    }
}