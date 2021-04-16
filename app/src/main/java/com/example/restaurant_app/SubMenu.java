package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.Addtocart;
import com.example.restaurant_app.model.Product;
import com.example.restaurant_app.model.Subcategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubMenu extends AppCompatActivity {

    GridView gridView;
    RetrofitInterface retrofitInterface;
    String id;

    Subcategory subcategoryList = new Subcategory();
    List<Product> product = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        gridView = (GridView) findViewById(R.id.gridView);
        id = getIntent().getStringExtra("_id");


        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        listingdata();

    }

    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        Call<Subcategory> call = retrofitInterface.getSubCategory(id);

        call.enqueue(new Callback<Subcategory>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SubMenu.this, "Success", Toast.LENGTH_SHORT).show();

                    subcategoryList = response.body();
                    product = subcategoryList.getProducts();

                    SubMenu.CustomAdepter customAdepter = new SubMenu.CustomAdepter(product, SubMenu.this);
                    gridView.setAdapter(customAdepter);


                } else {
                    Toast.makeText(SubMenu.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                System.out.println("############################ " + t.getLocalizedMessage());
            }
        });
    }

    //Adepter
    class CustomAdepter extends BaseAdapter {

        List<Product> product;
        private Context context;
        private LayoutInflater layoutInflater;
        private Button Add_to_cart;
        private EditText quantity,priority;
        RetrofitInterface retrofitInterface;
        private  String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzZXJAZ21haWwuY29tIiwicGhvbmUiOiIxMDI5Mzg0NzU2IiwiSWQiOiI2MDc2N2UwNTcxMWFhODM4MGNlMTE4ZWIiLCJpYXQiOjE2MTg1NjMzNzcsImV4cCI6MTYxODY0OTc3N30.KzvFCBeZPFpez2G5smNJEvahyuACTnuEGlEUr0W_62M";

        public CustomAdepter(List<Product> product, SubMenu context) {

            this.context = context;
            this.product = product;
        }

        @Override
        public int getCount() {
            return product.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (view == null) {
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);

                view = lInflater.inflate(R.layout.custom_list_layout, null);
            }

            ImageView imageView = view.findViewById(R.id.coverImage);
            TextView textview1 = view.findViewById(R.id.item_name);
            TextView textview2 = view.findViewById(R.id.item_price);
            Add_to_cart = view.findViewById(R.id.add_to_cart);
            quantity = view.findViewById(R.id.enter_qty);
            priority  = view.findViewById(R.id.set_priority);

            Retrofit retrofitClient = RetrofitClient.getInstance();
            retrofitInterface = retrofitClient.create(RetrofitInterface.class);
            
            
            Add_to_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<Addtocart> call = retrofitInterface.executecart(token);

                    call.enqueue(new Callback<Addtocart>() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onResponse(Call<Addtocart> call, Response<Addtocart> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(SubMenu.this, "Item add in cart", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(SubMenu.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Addtocart> call, Throwable t) {
                            System.out.println("############################ " + t.getLocalizedMessage());
                        }
                    });

                    //Toast.makeText(SubMenu.this, "clicked", Toast.LENGTH_SHORT).show();
                }
            });


            textview1.setText(product.get(i).getName());
            textview2.setText(product.get(i).getPrice());
            Picasso.with(SubMenu.this).load(product.get(i).getImageUrl()).into(imageView);
            return view;
        }
    }
}
