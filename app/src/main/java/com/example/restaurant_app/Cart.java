package com.example.restaurant_app;

import android.annotation.SuppressLint;
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
import com.example.restaurant_app.model.DeleteCart;
import com.example.restaurant_app.model.Item;
import com.example.restaurant_app.model.ViewCart;
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

    ViewCart viewcart = new ViewCart();
    YourCart yourCart = new YourCart();
    List<Item> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        backbtn = (Button) findViewById(R.id.btnback);
        gridView = (GridView) findViewById(R.id.gridView);
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

        Call<ViewCart> call = retrofitInterface.getCart("Bearer "+token);

        call.enqueue(new Callback<ViewCart>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<ViewCart> call, Response<ViewCart> response) {
                if (response.isSuccessful()) {

                    viewcart = response.body();
                    yourCart = viewcart.getYourCart();
                    items = yourCart.getItems();

                    Cart.CustomAdepter customAdepter = new Cart.CustomAdepter(items, Cart.this);
                    gridView.setAdapter(customAdepter);


                } else {
                    Toast.makeText(Cart.this, "Your cart is Empty..!!"+response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewCart> call, Throwable t) {
                System.out.println("Your cart is Empty..!!" + t.getLocalizedMessage());
            }
        });
    }

    //Adepter
    class CustomAdepter extends BaseAdapter {

        List<Item> item;
        private Context context;

        public CustomAdepter(List<Item> product, Cart context) {

            this.context = context;
            this.item = product;
        }

        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint({"ViewHolder", "SetTextI18n"})
        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (view == null) {
                LayoutInflater lInflater = (LayoutInflater) context.getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);

                view = lInflater.inflate(R.layout.custom_cart, null);
            }

//            view = LayoutInflater.from(context).inflate(R.layout.custom_cart, viewGroup, false);

            TextView priority = view.findViewById(R.id.item_priority);
            TextView quantity = view.findViewById(R.id.item_Quantity);
            TextView totalPrice = view.findViewById(R.id.item_price);
            Button delete_cart = view.findViewById(R.id.delete_cart);
            Button make_order = view.findViewById(R.id.make_order);

            delete_cart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Cart.this, "delete cart clicked", Toast.LENGTH_SHORT).show();
                    Retrofit retrofitClient = RetrofitClient.getInstance();
                    retrofitInterface = retrofitClient.create(RetrofitInterface.class);

                    SharedPreferences gettoken = getSharedPreferences("token", MODE_PRIVATE);
                    String token = gettoken.getString("TOKEN", "");

                    Call<DeleteCart> call = retrofitInterface.deleteCart("Bearer "+token);

                    call.enqueue(new Callback<DeleteCart>() {
                        @Override
                        public void onResponse(Call<DeleteCart> call, Response<DeleteCart> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(Cart.this, "Cart is Deleted..", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(Cart.this, ""+response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DeleteCart> call, Throwable t) {
                            Toast.makeText(Cart.this, " ##### "+ t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });
            
            make_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Cart.this, "make order", Toast.LENGTH_SHORT).show();

                }
            });
            
            
            priority.setText(item.get(position).getPriority()+"");
            quantity.setText(item.get(position).getQty()+"");
            totalPrice.setText(item.get(position).getPrice()+"");
            

            return view;
        }
    }
}