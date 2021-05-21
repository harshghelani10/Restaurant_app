package com.example.restaurant_app;

import android.annotation.SuppressLint;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.deletecartmodel.DeleteCart;
import com.example.restaurant_app.model.deletecartmodel.DeletedCart;
import com.example.restaurant_app.model.deletecartmodel.Item;
import com.example.restaurant_app.model.makeordermodel.MakeOrder;
import com.example.restaurant_app.model.makeordermodel.Order;
import com.example.restaurant_app.model.viewcartmodel.ProductId;
import com.example.restaurant_app.model.viewcartmodel.ViewCart;
import com.example.restaurant_app.model.viewcartmodel.YourCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Cart extends AppCompatActivity {

    public static String id;
    GridView gridView;
    RetrofitInterface retrofitInterface;
    Button backbtn;
    ViewCart viewcart = new ViewCart();
    YourCart yourCart = new YourCart();
    MakeOrder makeOrder = new MakeOrder();
    ProductId productId = new ProductId();
    List<Order> orders = new ArrayList<>();
    List<com.example.restaurant_app.model.viewcartmodel.Item> items = new ArrayList<>();
    private int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart );

        backbtn = (Button) findViewById( R.id.btnback );
        gridView = (GridView) findViewById( R.id.gridView );
        TextView tv_total = (TextView) findViewById( R.id.tv_total );
        Button delete_cart = (Button) findViewById( R.id.delete_cart );
        Button make_order = (Button) findViewById( R.id.make_order );

        // sub_total.setText(viewcart.getYourCart().getSubTotal()+"");

        listingdata();

        backbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Cart.this, UserHome.class );
                startActivity( intent );
            }
        } );

        delete_cart.setOnClickListener( new View.OnClickListener() {

            DeleteCart deleteCart = new DeleteCart();
            DeletedCart deletedCart = new DeletedCart();
            List<Item> items = new ArrayList<>();

            @Override
            public void onClick(View v) {
                Retrofit retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = retrofitClient.create( RetrofitInterface.class );

                SharedPreferences gettoken = getSharedPreferences( "token", MODE_PRIVATE );
                String token = gettoken.getString( "TOKEN", "" );

                Call<DeleteCart> call = retrofitInterface.deleteCart( "Bearer " + token );

                call.enqueue( new Callback<DeleteCart>() {
                    @Override
                    public void onResponse(Call<DeleteCart> call, Response<DeleteCart> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent( Cart.this, Menu.class );
                            startActivity( intent );
                            Toast.makeText( Cart.this, "Your cart is deleted...", Toast.LENGTH_SHORT ).show();
                            deleteCart = response.body();
                            deletedCart = deleteCart.getDeletedCart();
                            items = deletedCart.getItems();
                        } else {
                            Toast.makeText( Cart.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteCart> call, Throwable t) {
                        Toast.makeText( Cart.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                    }
                } );
            }
        } );

        make_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleMakeOrderDialog();
            }
        } );
    }

    private void handleMakeOrderDialog() {

        View view = getLayoutInflater().inflate( R.layout.dialogbox_makeorder, null );

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setView( view ).show();

        final EditText make_order_name = (EditText) view.findViewById( R.id.make_order_name );
        final EditText pay_method = (EditText) view.findViewById( R.id.pay_method );
        Button btn_make_order = (Button) view.findViewById( R.id.btn_make_order );

        btn_make_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(Cart.this, "Make order is clicked....", Toast.LENGTH_SHORT).show();

                Retrofit retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = retrofitClient.create( RetrofitInterface.class );

                SharedPreferences gettoken = getSharedPreferences( "token", MODE_PRIVATE );
                String token = gettoken.getString( "TOKEN", "" );

                HashMap<String, String> map = new HashMap<>();

                map.put( "name", make_order_name.getText().toString() );
                map.put( "paymentMethod", pay_method.getText().toString() );

                Call<MakeOrder> call = retrofitInterface.makeOrder( "Bearer " + token, map );

                call.enqueue( new Callback<MakeOrder>() {
                    @Override
                    public void onResponse(Call<MakeOrder> call, Response<MakeOrder> response) {
                        if (response.isSuccessful()) {

                            makeOrder = response.body();
                            orders = makeOrder.getOrder();

                            String get = orders.get( i ).getId();
                            Intent intent = new Intent( Cart.this, User_view_order.class );
                            intent.putExtra( "order_id",orders.get( i ). getId());
                            startActivity( intent );
                            Toast.makeText( Cart.this, "Your Order Is Placed.....!!", Toast.LENGTH_SHORT ).show();

                        } else {
                            Toast.makeText( Cart.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<MakeOrder> call, Throwable t) {
                        Toast.makeText( Cart.this, "#####" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                    }
                } );

            }
        } );

    }

    private void listingdata() {


        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        SharedPreferences gettoken = getSharedPreferences( "token", MODE_PRIVATE );
        String token = gettoken.getString( "TOKEN", "" );


        Call<ViewCart> call = retrofitInterface.getCart( "Bearer " + token );

        call.enqueue( new Callback<ViewCart>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<ViewCart> call, Response<ViewCart> response) {
                if (response.isSuccessful()) {

                    viewcart = response.body();
                    yourCart = viewcart.getYourCart();
                    items = yourCart.getItems();
//                    productId = items.get( i ).getProductId();


                    CustomAdapter customAdepter = new CustomAdapter( Cart.this, items );
                    gridView.setAdapter( customAdepter );

                    TextView sub_total = (TextView) findViewById( R.id.sub_total );
                    sub_total.setText( viewcart.getYourCart().getSubTotal() + "" + "₹" );

                } else {
                    Toast.makeText( Cart.this, "Your Cart is Empty...!", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ViewCart> call, Throwable t) {
                Toast.makeText( Cart.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}


//Adepter
class CustomAdapter extends BaseAdapter {
    List<com.example.restaurant_app.model.viewcartmodel.Item> item;
    private Context context;
    private int mInteger = 0;

    public CustomAdapter(Cart cart, List<com.example.restaurant_app.model.viewcartmodel.Item> items) {
        this.context = cart;
        this.item = items;
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

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from( context ).inflate( R.layout.custom_cart, parent, false );

        TextView priority = view.findViewById( R.id.item_priority );
        TextView quantity = view.findViewById( R.id.item_Quantity );
        TextView totalPrice = view.findViewById( R.id.cart_item_price );
        ImageView imageView = view.findViewById( R.id.cart_image );
        Button btn_plus_p = view.findViewById( R.id.b_plus_p );
        Button btn_min_p = view.findViewById( R.id.b_min_p );
        Button btn_plus_q = view.findViewById( R.id.b_plus_q );
        Button btn_min_q = view.findViewById( R.id.b_min_q );
        TextView  cart_item_name = view.findViewById( R.id.cart_item_name );


        priority.setText( item.get( position ).getPriority() + "" );
        quantity.setText( item.get( position ).getQty() + "" );
        totalPrice.setText( item.get( position ).getTotal() + "" +  "₹" );
//        cart_item_name.setText( item.get( position ).getProductId().getName());
 //       cart_item_name.setText( item.get( position ).getProductId().getName());
//        Picasso.with(context).load(item.get(position).getProductId().getImageUrl()).into(imageView);
        //      Picasso.with( context ).load( item.get( position ).getProductId().getImageUrl() ).into( imageView );
        // Picasso.with(context).load(item.get(position).getProductId().getImageUrl()).into(imageView);


        btn_min_p.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger - 1;
                priority.setText( mInteger + "" );
            }
        } );

        btn_plus_p.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger + 1;
                priority.setText( mInteger + "" );
            }
        } );

        btn_min_q.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger - 1;
                quantity.setText( mInteger + "" );
            }
        } );

        btn_plus_q.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger + 1;
                quantity.setText( mInteger + "" );
            }
        } );
        return view;
    }
}

