package com.example.restaurant_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.CustomAdapter;
import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.SubCatagoryModel.Product;
import com.example.restaurant_app.model.SubCatagoryModel.SubCategory;
import com.example.restaurant_app.model.getingrediantmodel.GetIngrdiant;
import com.example.restaurant_app.model.getingrediantmodel.Ingredient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubMenu extends AppCompatActivity {

    GridView gridView;
    RetrofitInterface retrofitInterface;
    Button backbtn;

    GetIngrdiant getIngrdiant = new GetIngrdiant();
    List<Ingredient> ingredientList = new ArrayList<>();

    SubCategory subcategoryList = new SubCategory();
    List<Product> product = new ArrayList<>();
    public static String id;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        gridView = (GridView) findViewById(R.id.gridView);
        id = getIntent().getStringExtra("_id");
        backbtn = (Button) findViewById(R.id.btnback);


        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        listingdata();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu.this, Menu.class);
                startActivity(intent);
            }
        });

    }

    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        Call<SubCategory> call = retrofitInterface.getSubCategory(id);

        call.enqueue(new Callback<SubCategory>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                if (response.isSuccessful()) {
                   // Toast.makeText(SubMenu.this, "Success", Toast.LENGTH_SHORT).show();

                    subcategoryList = response.body();
                    product = subcategoryList.getProducts();

                    CustomAdapter customAdepter = new CustomAdapter(SubMenu.this,product);
                    gridView.setAdapter(customAdepter);

                } else {
                    Toast.makeText(SubMenu.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SubCategory> call, Throwable t) {
                System.out.println("############################ " + t.getLocalizedMessage());
            }
        });
    }
}
