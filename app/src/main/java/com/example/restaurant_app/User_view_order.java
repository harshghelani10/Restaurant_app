package com.example.restaurant_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.givecomplaint.Complaint;
import com.example.restaurant_app.model.givecomplaint.GiveComplaint;
import com.example.restaurant_app.model.viewmyordersmodel.Data;
import com.example.restaurant_app.model.viewmyordersmodel.Item;
import com.example.restaurant_app.model.viewmyordersmodel.Order;
import com.example.restaurant_app.model.viewmyordersmodel.ViewMyOrders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.LayoutInflater.from;

public class User_view_order extends AppCompatActivity {

    GridView gridView;
    ViewMyOrders viewMyOrders = new ViewMyOrders();
    Data data = new Data();
    List<Order> orderList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();
    GiveComplaint giveComplaints = new GiveComplaint();
    Complaint complaint = new Complaint();
    private RetrofitInterface retrofitInterface;
    private int i;
    public static String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_user_view_order2 );

        Button backbtn = (Button) findViewById( R.id.btnback );
        gridView = (GridView) findViewById( R.id.gridView );
        Button btn_complaint = (Button) findViewById( R.id.btn_comlaint );

        listingdata();

        backbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( User_view_order.this, UserHome.class );
                startActivity( intent );
            }
        } );
        btn_complaint.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleComplaintDialog();
                // Toast.makeText( User_view_order.this, "click", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void handleComplaintDialog() {
        View view = getLayoutInflater().inflate( R.layout.dialogbox_complaint, null );

        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setView( view ).show();


        final EditText complaint_title = (EditText) view.findViewById( R.id.complaint_title );
        final EditText complaint_message = (EditText) view.findViewById( R.id.complaint_message );
        Button make_complaint = (Button) view.findViewById( R.id.btn_make_order );
        id = getIntent().getStringExtra( "order_id" );

 //       id = "609f8daadc449509e0b578cc";
        make_complaint.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = retrofitClient.create( RetrofitInterface.class );

               SharedPreferences gettoken = getSharedPreferences("token", MODE_PRIVATE);
               String token = gettoken.getString("TOKEN", "");

                HashMap<String, String> map = new HashMap<>();

                map.put( "complaintTitle", complaint_title.getText().toString() );
                map.put( "complaintMessage", complaint_message.getText().toString() );

                Call<GiveComplaint> call = retrofitInterface.giveComplaint("Bearer " + token, map);

                call.enqueue( new Callback<GiveComplaint>() {
                    @Override
                    public void onResponse(Call<GiveComplaint> call, Response<GiveComplaint> response) {
                        if (response.isSuccessful()) {

                            giveComplaints = response.body();
                            complaint = giveComplaints.getComplaint();

                            Toast.makeText( User_view_order.this, "Your complaint is save..We will get you soon..", Toast.LENGTH_SHORT ).show();
                            Intent intent = new Intent(getApplicationContext(),UserHome.class);
                            startActivity( intent );

                        }else{
                            Toast.makeText( User_view_order.this, ""+response.message(), Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GiveComplaint> call, Throwable t) {
                        Toast.makeText( User_view_order.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();

                    }
                });
            }
        } );
    }

    private void listingdata() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        SharedPreferences gettoken = getSharedPreferences( "token", MODE_PRIVATE );
        String token = gettoken.getString( "TOKEN", "" );


        Call<ViewMyOrders> call = retrofitInterface.viewmyorders( "Bearer " + token );

        call.enqueue( new Callback<ViewMyOrders>() {
            @Override
            public void onResponse(Call<ViewMyOrders> call, Response<ViewMyOrders> response) {
                if (response.isSuccessful()) {
                    Toast.makeText( User_view_order.this, "Here is your order", Toast.LENGTH_SHORT ).show();

                    viewMyOrders = response.body();
                    data = viewMyOrders.getData();
                    orderList = data.getOrders();
                    itemList = orderList.get( i ).getItems();

                    CustomAdepter customAdepter = new CustomAdepter( User_view_order.this, orderList,itemList );
                    gridView.setAdapter( customAdepter );

                } else {
                    Toast.makeText( User_view_order.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ViewMyOrders> call, Throwable t) {
                Toast.makeText( User_view_order.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();

            }
        } );
    }
}

class CustomAdepter extends BaseAdapter {
    List<Order> data;
    List<Item> item;
    private Context context;
    private int position;




    public CustomAdepter(User_view_order user_view_order, List<Order> orderList, List<Item> itemList) {
        this.context = user_view_order;
        this.item = itemList;
        this.data = orderList;
    }


    @Override
    public int getCount() {
////        item = data.get( position ).getItems();
//       for (int i = 0; i<data.size(); i++ ){
//        item = data.get( position ).getItems();
//       }
        return data.size();
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
    public View getView(int position, View view, ViewGroup parent) {

        view = from( context ).inflate( R.layout.custom_view_your_order, parent, false );

        TextView date = view.findViewById( R.id.item_date );
        TextView order_status = view.findViewById( R.id.item_status );
        TextView priority = view.findViewById( R.id.item_priority );
        TextView quantity = view.findViewById( R.id.item_Quantity );
        TextView totalPrice = view.findViewById( R.id.cart_item_price );
        ImageView imageView = view.findViewById( R.id.cart_image );


        date.setText(data.get(position).getCreatedAt());
        order_status.setText( data.get( position ).getOrderIs() );
        priority.setText( data.get( position).getItems().get( position ).getPriority()+"");
        quantity.setText( data.get( position ).getItems().get( position ).getQty() + "" );
        totalPrice.setText( data.get( position ).getItems().get( position ).getTotal() + "" + "₹" );
        //Picasso.with( context ).load( data.get( position ).getItems().get( position ).getProductId().getImageUrl() ).into( imageView );


        return view;

    }

}