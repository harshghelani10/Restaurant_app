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
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.Item;
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

    YourCart yourCart = new YourCart();
    List<Item> items = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        SharedPreferences preferences = getSharedPreferences("token",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember","true");
        editor.apply();

        SharedPreferences token_value = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrivedToken  = preferences.getString("TOKEN",null);

        backbtn = (Button)findViewById(R.id.btnback);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Cart.this, UserHome.class);
                startActivity(intent);
            }
        });

        listingdata();

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

    }


    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        Call<YourCart> call = retrofitInterface.getCart();

        call.enqueue(new Callback<YourCart>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<YourCart> call, Response<YourCart> response) {
                if (response.isSuccessful()) {
                    //   Toast.makeText(SubMenu.this, "Success", Toast.LENGTH_SHORT).show();

                    yourCart = response.body();
                    items = yourCart.getItems();

                    Cart.CustomAdepter customAdepter = new Cart.CustomAdepter(items, Cart.this);
                    gridView.setAdapter(customAdepter);


                } else {
                    Toast.makeText(Cart.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<YourCart> call, Throwable t) {
                System.out.println("############################ " + t.getLocalizedMessage());
            }
        });
    }

    //Adepter
    class CustomAdepter extends BaseAdapter {

        List<Item> item;
        private Context context;
        private LayoutInflater layoutInflater;
        private Button Add_to_cart;
        private EditText quantity,priority;
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


            TextView textview1 = view.findViewById(R.id.item_name);
            TextView textview2 = view.findViewById(R.id.item_price);
            Add_to_cart = view.findViewById(R.id.add_to_cart);
            quantity = view.findViewById(R.id.enter_qty);
            priority  = view.findViewById(R.id.set_priority);

            Retrofit retrofitClient = RetrofitClient.getInstance();
            retrofitInterface = retrofitClient.create(RetrofitInterface.class);

            textview1.setText(items.get(i).getPriority());
            textview2.setText(items.get(i).getPrice());
            return view;
        }
    }
}