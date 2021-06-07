package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.offersmodel.Offers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class offers extends AppCompatActivity {

    private Button backbtn;
    private GridView gridView;
    private RetrofitInterface retrofitInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_offers );

        backbtn = (Button) findViewById(R.id.btnback);
        gridView = (GridView) findViewById(R.id.gridView);


        Retrofit retrofitClient = RetrofitClient.getInstance();

        retrofitInterface = retrofitClient.create( RetrofitInterface.class);

        listingdata();


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(offers.this, UserHome.class);
                startActivity(intent);
            }
        });
    }

    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        Call<Offers> call = retrofitInterface.viewoffer();

        call.enqueue( new Callback<Offers>() {
            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                if (response.isSuccessful()){
                    Toast.makeText( offers.this, "success", Toast.LENGTH_SHORT ).show();
                }else{
                    Toast.makeText( offers.this, ""+response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {

                Toast.makeText( offers.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}