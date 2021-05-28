package com.example.restaurant_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.loginallmodel.LoginResult;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WaiterLogin extends AppCompatActivity {

    Button login_btn;
    EditText email, password;
    TextView forgot_password;
    private CheckBox rememberMe;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiter_login);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.login_btn);
        forgot_password = (TextView) findViewById(R.id.forgot_password);
        rememberMe = (CheckBox) findViewById(R.id.rememberMe);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(WaiterLogin.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(WaiterLogin.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                HashMap<String, String> map = new HashMap<>();

                map.put("email", email.getText().toString());
                map.put("password", password.getText().toString());

                Call<LoginResult> call = retrofitInterface.executeWaiterLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.code() == 200) {

                            LoginResult result = response.body();
                            Toast.makeText(WaiterLogin.this, "Login Success",
                                    Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(WaiterLogin.this, waiter_home.class);
                            startActivity(intent);

                        } else if (response.code() == 401) {
                            Toast.makeText(WaiterLogin.this, "Wrong Credentials",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Toast.makeText(WaiterLogin.this, "Please! Check Network of Your Device",
                                Toast.LENGTH_LONG).show();
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
                Intent m  = new Intent(WaiterLogin.this,ManagerForgotPassword.class);
                startActivity(m);
            }
        });
    }
}
