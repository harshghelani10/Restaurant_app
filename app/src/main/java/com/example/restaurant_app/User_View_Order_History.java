package com.example.restaurant_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.vieworderhistorymodel.Data;
import com.example.restaurant_app.model.vieworderhistorymodel.Item;
import com.example.restaurant_app.model.vieworderhistorymodel.Order;
import com.example.restaurant_app.model.vieworderhistorymodel.ViewOrderHistory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.LayoutInflater.from;

public class User_View_Order_History extends AppCompatActivity {
    GridView gridView;
    ViewOrderHistory viewOrderHistory = new ViewOrderHistory();
    Data data = new Data();
    List<Order> orderList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();
    Order order = new Order();
    private RetrofitInterface retrofitInterface;
    private int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user__view__order__history );

        Button backbtn = (Button) findViewById( R.id.btnback );
        gridView = (GridView) findViewById( R.id.gridView );

        listingdata();

        backbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( User_View_Order_History.this, UserHome.class );
                startActivity( intent );
            }
        } );
    }

    private void listingdata() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        SharedPreferences gettoken = getSharedPreferences( "token", MODE_PRIVATE );
        String token = gettoken.getString( "TOKEN", "" );


        Call<ViewOrderHistory> call = retrofitInterface.viewOrderHistory( "Bearer " + token );

        call.enqueue( new Callback<ViewOrderHistory>() {
            @Override
            public void onResponse(Call<ViewOrderHistory> call, Response<ViewOrderHistory> response) {
                if (response.isSuccessful()) {

                    Toast.makeText( User_View_Order_History.this, "Your Order History", Toast.LENGTH_SHORT ).show();

                    viewOrderHistory = response.body();
                    data = viewOrderHistory.getData();
                    orderList = data.getOrders();
                    itemList = orderList.get( i ).getItems();

                    CustomAdepter2 customAdepter = new CustomAdepter2( User_View_Order_History.this, orderList, itemList, order, data );
                    gridView.setAdapter( customAdepter );
                } else {
                    Toast.makeText( User_View_Order_History.this, "No Order History..!!", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ViewOrderHistory> call, Throwable t) {
                Toast.makeText( User_View_Order_History.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}

class CustomAdepter2 extends BaseAdapter {
    Context context;
    List<Order> orderList;
    List<Item> itemList;
    Order order;
    Data data;
    private int i;

    public CustomAdepter2(User_View_Order_History user_view_order_history, List<Order> orderList, List<Item> itemList, Order order, Data data) {
        this.context = user_view_order_history;
        this.orderList = orderList;
        this.itemList = itemList;
        this.order = order;
        this.data = data;
    }


    @Override
    public int getCount() {
        return itemList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = from( context ).inflate( R.layout.custom_order_history, parent, false );

        TextView date = convertView.findViewById( R.id.item_date );
        TextView priority = convertView.findViewById( R.id.item_priority );
        TextView quantity = convertView.findViewById( R.id.item_Quantity );
        TextView totalPrice = convertView.findViewById( R.id.cart_item_price );
//         ImageView imageView = convertView.findViewById( R.id.cart_image );

        date.setText( data.getCreatedAt() );
        priority.setText( itemList.get( position ).getPriority() + "" );
        quantity.setText( itemList.get( position ).getQty() + "" );
        totalPrice.setText( itemList.get( position ).getTotal() + "" + "₹" );
//        Picasso.with( context ).load( itemList.get( position ).getProductId().getImageUrl() ).into( imageView );

        return convertView;
    }
}