package com.example.restaurant_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.restaurant_app.model.offersmodel.Coupon;
import com.example.restaurant_app.model.offersmodel.Offers;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class offers extends AppCompatActivity {

    Offers offers = new Offers();
    List<Coupon> couponList = new ArrayList<>();

    private Button backbtn;
    private GridView gridView;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_offers );

        backbtn = (Button) findViewById( R.id.btnback );
        gridView = (GridView) findViewById( R.id.gridView );


        Retrofit retrofitClient = RetrofitClient.getInstance();

        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        listingdata();


        backbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( offers.this, UserHome.class );
                startActivity( intent );
            }
        } );
    }

    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<Offers> call = retrofitInterface.viewoffer();

        call.enqueue( new Callback<Offers>() {
            @Override
            public void onResponse(Call<Offers> call, Response<Offers> response) {
                if (response.isSuccessful()) {

                    offers = response.body();
                    couponList = offers.getCoupons();

                    CustomAdepter4 customAdepter = new CustomAdepter4( com.example.restaurant_app.offers.this,couponList );
                    gridView.setAdapter( customAdepter );

//                    Toast.makeText( offers.this, "success", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( offers.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<Offers> call, Throwable t) {

                Toast.makeText( offers.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }
}

class CustomAdepter4 extends BaseAdapter{

    Context context;
    List<Coupon> couponList;


    public CustomAdepter4(offers offers, List<Coupon> couponList) {
        this.context = offers;
        this.couponList = couponList;
    }

    @Override
    public int getCount() {
        return couponList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.custom_offer_codes, parent, false);

        TextView coupen_code = (TextView) convertView.findViewById( R.id.coupen_code );
        TextView expire_date = (TextView) convertView.findViewById( R.id.expire_date );
        TextView discount_amount = (TextView) convertView.findViewById( R.id.discount_amount );

        coupen_code.setText( couponList.get( position ).getCcode() );
        expire_date.setText( couponList.get( position ).getExpireDate() );
        discount_amount.setText( couponList.get( position ).getAmount()+ "" + "₹");

        return convertView;
    }
}