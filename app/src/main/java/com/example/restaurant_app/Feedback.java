package com.example.restaurant_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Feedback extends AppCompatActivity {

    EditText rating,title,message;
    Button backbtn,submit;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        backbtn = (Button)findViewById(R.id.btnback);
        rating = (EditText) findViewById(R.id.feedback_rating);
        title = (EditText) findViewById(R.id.feedback_title);
        message = (EditText) findViewById(R.id.feedback_message);
        submit = (Button) findViewById(R.id.submit_feedback);


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feedback.this, UserHome.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = retrofitClient.create(RetrofitInterface.class);

                SharedPreferences gettoken = getSharedPreferences("token", MODE_PRIVATE);
                String token = gettoken.getString("TOKEN", "");

                HashMap<String, String> map = new HashMap<>();

                map.put("rating", rating.getText().toString());
                map.put("title", title.getText().toString());
                map.put("message",message.getText().toString());

                Call<com.example.restaurant_app.model.Feedback> call = retrofitInterface.giveFeedback("Bearer " + token,map);

                call.enqueue(new Callback<com.example.restaurant_app.model.Feedback>() {
                    @Override
                    public void onResponse(Call<com.example.restaurant_app.model.Feedback> call, Response<com.example.restaurant_app.model.Feedback> response) {
                        if (response.isSuccessful()){
                            Intent intent = new Intent(Feedback.this,UserHome.class);
                            startActivity(intent);
                            Toast.makeText(Feedback.this, "Thank You.. Your feedback is saved..", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Feedback.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.restaurant_app.model.Feedback> call, Throwable t) {
                        Toast.makeText(Feedback.this, "###"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}