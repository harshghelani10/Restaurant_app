package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.Item;
import com.example.restaurant_app.model.Viewcart;
import com.example.restaurant_app.model.YourCart;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Cart extends AppCompatActivity {
    GridView gridView;
    RetrofitInterface retrofitInterface;
    Button backbtn;

    Viewcart viewcart = new Viewcart();
    YourCart yourCart = new YourCart();
    List<Item> items = new ArrayList<>();
    public static String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        id = getIntent().getStringExtra("_id");

        backbtn = (Button) findViewById(R.id.btnback);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, UserHome.class);
                startActivity(intent);
            }
        });

        listingdata();
    }


    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        SharedPreferences gettoken = getSharedPreferences("token", MODE_PRIVATE);
        String token = gettoken.getString("TOKEN", "");

        Call<Viewcart> call = retrofitInterface.getCart(token);

        call.enqueue(new Callback<Viewcart>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Viewcart> call, Response<Viewcart> response) {
                if (response.isSuccessful()) {

                    viewcart = response.body();
                    yourCart = viewcart.getYourCart();
                    items = yourCart.getItems();

                    Cart.CustomAdepter customAdepter = new Cart.CustomAdepter(items, Cart.this);
                    gridView.setAdapter(customAdepter);


                } else {
                    Toast.makeText(Cart.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Viewcart> call, Throwable t) {
                System.out.println("############################ " + t.getLocalizedMessage());
            }
        });
    }

    //Adepter
    class CustomAdepter extends BaseAdapter {

        List<Item> item;
        private Context context;
        private LayoutInflater layoutInflater;
        RetrofitInterface retrofitInterface;

        public CustomAdepter(List<Item> product, Cart context) {

            this.context = context;
            this.item = item;
        }

        @Override
        public int getCount() {
            return item.size();
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
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                view = lInflater.inflate(R.layout.custom_cart, null);
            }


            TextView quantity = view.findViewById(R.id.item_Quantity);
            TextView priority = view.findViewById(R.id.item_priority);
            TextView totalPrice = view.findViewById(R.id.item_price);

            quantity.setText(items.get(i).getQty());
            priority.setText(items.get(i).getPriority());
            totalPrice.setText(items.get(i).getPrice());
            return view;
        }
    }
}