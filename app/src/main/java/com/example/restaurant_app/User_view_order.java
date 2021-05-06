package com.example.restaurant_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.viewmyordersmodel.Data;
import com.example.restaurant_app.model.viewmyordersmodel.Item;
import com.example.restaurant_app.model.viewmyordersmodel.Order;
import com.example.restaurant_app.model.viewmyordersmodel.ViewMyOrders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class User_view_order extends AppCompatActivity {

    GridView gridView;
    private RetrofitInterface retrofitInterface;

    ViewMyOrders viewMyOrders = new ViewMyOrders();
    Data data = new Data();
    Order orders = new Order();
    List<Order> orderList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_order2);

        Button backbtn = (Button) findViewById(R.id.btnback);
        gridView = (GridView) findViewById(R.id.gridView);

        listingdata();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_view_order.this, UserHome.class);
                startActivity(intent);
            }
        });
    }

    private void listingdata() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        SharedPreferences gettoken = getSharedPreferences("token", MODE_PRIVATE);
        String token = gettoken.getString("TOKEN", "");


        Call<ViewMyOrders> call = retrofitInterface.viewmyorders("Bearer " + token);

        call.enqueue(new Callback<ViewMyOrders>() {
            @Override
            public void onResponse(Call<ViewMyOrders> call, Response<ViewMyOrders> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(User_view_order.this, "Here is your order", Toast.LENGTH_SHORT).show();

                    viewMyOrders = response.body();
                    data = viewMyOrders.getData();
                    orderList = data.getOrders();
                    itemList = orderList.get(i).getItems();

                    CustomAdepter customAdepter = new CustomAdepter(User_view_order.this, itemList, orderList);
                    gridView.setAdapter(customAdepter);

                } else {
                    Toast.makeText(User_view_order.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ViewMyOrders> call, Throwable t) {
                Toast.makeText(User_view_order.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

class CustomAdepter extends BaseAdapter {

    List<Order> data;
    List<Item> item;
    Context context;
    private RetrofitInterface retrofitInterface;


//    public CustomAdepter(User_view_order user_view_order, List<Item> itemList, Data data) {
//        this.context = user_view_order;
//        this.item = itemList;
////        this.dataList = (List<Data>) data;
//    }

//    public CustomAdepter(User_view_order user_view_order, List<Item> itemList) {
//        this.context = user_view_order;
//        this.item = itemList;
//    }

    public CustomAdepter(User_view_order user_view_order, List<Item> itemList, List<Order> orderList) {
        this.context = user_view_order;
        this.data = orderList;
        this.item = itemList;

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

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_view_your_order, parent, false);

        TextView date = view.findViewById(R.id.item_date);
        TextView priority = view.findViewById(R.id.item_priority);
        TextView quantity = view.findViewById(R.id.item_Quantity);
        TextView totalPrice = view.findViewById(R.id.cart_item_price);
        ImageView imageView = view.findViewById(R.id.cart_image);
        Button complaint_btn = view.findViewById(R.id.complaint_btn);

//        date.setText(data.get(position).getCreatedAt());
//        date.setText(dataList.get(position).getCreatedAt());

        complaint_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context.getApplicationContext(),UserComplaint.class);
                context.startActivity( intent );

                Toast.makeText(context, "clicked....", Toast.LENGTH_SHORT).show();
            }
        });

        priority.setText(item.get(position).getPriority() + "");
        quantity.setText(item.get(position).getQty() + "");
        totalPrice.setText(item.get(position).getTotal() + "" + "₹");
//        Picasso.with(context).load(item.get(position).getProductId().getImageUrl()).into(imageView);
//        date.setText(data.get(position).getCreatedAt());

        return view;
    }
}