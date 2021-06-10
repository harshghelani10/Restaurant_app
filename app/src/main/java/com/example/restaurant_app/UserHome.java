package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.Product;
import com.example.restaurant_app.model.Subcategory;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserHome extends AppCompatActivity {

    public static String id;
    GridView gridView;
    RetrofitInterface retrofitInterface;
    GridView gridView_chinese, gridView_Italian, gridView_southIndian, gridView_coldDrinks, gridView_dessert, gridView_indians;
    ScrollView scrollView;
    Subcategory subcategory = new Subcategory();
    List<Product>  productList = new ArrayList<>();
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user_home );

        drawer = findViewById( R.id.drawer );
        navigationView = findViewById( R.id.navigation );
        toolbar = findViewById( R.id.toolbar );
        gridView = (GridView) findViewById( R.id.gridView );
        TextView offers = (TextView) findViewById( R.id.tv_offer );
        TextView show_menu = (TextView) findViewById( R.id.show_menu );
        TextView show_cart = (TextView) findViewById( R.id.show_cart );
        TextView book_table = (TextView) findViewById( R.id.book_table );
        TextView view_order = (TextView) findViewById( R.id.view_order );

        show_menu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), Menu.class );
                startActivity( intent );
            }
        } );

        show_cart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Cart.class);
                startActivity( intent );
            }
        } );

        book_table.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),BookTable.class);
                startActivity( intent );
            }
        } );

        view_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),User_view_order.class);
                startActivity( intent );
            }
        } );

        offers.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), com.example.restaurant_app.offers.class );
                startActivity( intent );
            }
        } );

        gridView_chinese = (GridView) findViewById( R.id.gridView_chinese );
        gridView_Italian = (GridView) findViewById( R.id.gridView_Italian );
        gridView_southIndian = (GridView) findViewById( R.id.gridView_SouthIndian );
        gridView_coldDrinks = (GridView) findViewById( R.id.gridView_coldDrinks );
        gridView_dessert = (GridView) findViewById( R.id.gridView_Dessert );
//        gridView_indians = (GridView) findViewById( R.id.gridView_Indians );
        scrollView = (ScrollView) findViewById( R.id.scrollView );

        menuChinese();
        menuItalian();
        menuSouthIndian();
        menuColdDrinks();
        menuDessert();
//        menuIndian();
        setSupportActionBar( toolbar );

        toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.open, R.string.close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener( new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer( GravityCompat.START );
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent( UserHome.this, UserHome.class );
                        startActivity( home );
                        break;
                    case R.id.menu:
                        Intent menu = new Intent( UserHome.this, Menu.class );
                        startActivity( menu );
                        break;
                    case R.id.booktable:
                        Intent booktable = new Intent( UserHome.this, BookTable.class );
                        startActivity( booktable );
                        break;
                    case R.id.cart:
                        Intent cart = new Intent( UserHome.this, Cart.class );
                        startActivity( cart );
                        break;
                    case R.id.feedback:
                        Intent feedback = new Intent( UserHome.this, Feedback.class );
                        startActivity( feedback );
                        break;
                    case R.id.complaint:
                        Intent complaint = new Intent( UserHome.this, UserComplaint.class );
                        startActivity( complaint );
                        break;
                    case R.id.logout:
                        SharedPreferences preferences = getSharedPreferences( "checked", MODE_PRIVATE );
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString( "remember", "false" );
                        editor.apply();
                        finish();
                        Intent logout = new Intent( UserHome.this, Loginall.class );
                        startActivity( logout );
                        break;
                    case R.id.user_view_order:
                        Intent user_view_order = new Intent( UserHome.this, User_view_order.class );
                        startActivity( user_view_order );
                        break;
                    case R.id.user_view_order_history:
                        Intent user_view_order_history = new Intent( UserHome.this, User_View_Order_History.class );
                        startActivity( user_view_order_history );

                }
                return true;
            }
        } );
    }


    private void menuChinese() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<Subcategory> call = retrofitInterface.getchinese();

        call.enqueue( new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                if (response.isSuccessful()) {
                    Subcategory subcategory = response.body();
                    productList = subcategory.getProducts();

                    CustomAdepterall customAdepterall = new CustomAdepterall( productList, UserHome.this );
                    gridView_chinese.setAdapter( customAdepterall );
                    // Toast.makeText( UserHome.this, "Success", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                Toast.makeText( UserHome.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void menuItalian() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<Subcategory> call = retrofitInterface.getItalian();

        call.enqueue( new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                if (response.isSuccessful()) {
                    Subcategory subcategory = response.body();
                    productList = subcategory.getProducts();

                    CustomAdepterall customAdepterall = new CustomAdepterall( productList, UserHome.this );
                    gridView_Italian.setAdapter( customAdepterall );
                    //     Toast.makeText( UserHome.this, "Success", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                Toast.makeText( UserHome.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void menuSouthIndian() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<Subcategory> call = retrofitInterface.getSouthIndian();

        call.enqueue( new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                if (response.isSuccessful()) {
                    Subcategory subcategory = response.body();
                    productList = subcategory.getProducts();

                    CustomAdepterall customAdepterall = new CustomAdepterall( productList, UserHome.this );
                    gridView_southIndian.setAdapter( customAdepterall );
                    // Toast.makeText( UserHome.this, "Success", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                Toast.makeText( UserHome.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void menuColdDrinks() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<Subcategory> call = retrofitInterface.getColdDrinks();

        call.enqueue( new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                if (response.isSuccessful()) {
                    Subcategory subcategory = response.body();
                    productList = subcategory.getProducts();

                    CustomAdepterall customAdepterall = new CustomAdepterall( productList, UserHome.this );
                    gridView_coldDrinks.setAdapter( customAdepterall );
                    //  Toast.makeText( UserHome.this, "Success", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                Toast.makeText( UserHome.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    private void menuDessert() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<Subcategory> call = retrofitInterface.getDessert();

        call.enqueue( new Callback<Subcategory>() {
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                if (response.isSuccessful()) {
                    Subcategory subcategory = response.body();
                    productList = subcategory.getProducts();

                    CustomAdepterall customAdepterall = new CustomAdepterall( productList, UserHome.this );
                    gridView_dessert.setAdapter( customAdepterall );
                    // Toast.makeText( UserHome.this, "Success", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                Toast.makeText( UserHome.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

//    private void menuIndian() {
//        Retrofit retrofitClient = RetrofitClient.getInstance();
//        retrofitInterface = retrofitClient.create( RetrofitInterface.class );
//
//        Call<Subcategory> call = retrofitInterface.getIndian();
//
//        call.enqueue( new Callback<Subcategory>() {
//            @Override
//            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
//                if (response.isSuccessful()) {
//                    Subcategory subcategory = response.body();
//                    productList = subcategory.getProducts();
//
//                    CustomAdepterall customAdepterall = new CustomAdepterall( productList, UserHome.this );
//                    gridView_indians.setAdapter( customAdepterall );
//                   // Toast.makeText( UserHome.this, "Success", Toast.LENGTH_SHORT ).show();
//                } else {
//                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Subcategory> call, Throwable t) {
//                Toast.makeText( UserHome.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
//            }
//        } );
//    }




    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }
}

class CustomAdepterall extends BaseAdapter {

    List<Product> productList;
    private Context context;

    public CustomAdepterall(List<Product> productList, UserHome userHome) {
        this.context = userHome;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
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
        if (convertView == null) {
            LayoutInflater lInflater = (LayoutInflater) context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE );
            convertView = lInflater.inflate( R.layout.custom_home, null );
        }

        TextView name = convertView.findViewById( R.id.item_name );
        TextView price = convertView.findViewById( R.id.cart_item_price );
//        TextView catagory_name = convertView.findViewById( R.id.catagory_name );
        ImageView imageView = convertView.findViewById( R.id.cart_image );

        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(context.getApplicationContext(),Menu.class);
               context.startActivity( intent );
            }
        } );


        name.setText( productList.get( position ).getName() );
        price.setText( productList.get( position ).getOriginalPrice() + "" + "₹" );
//        Picasso.with( context ).load( productList.get( position ).getImageUrl() ).into( imageView );
        return convertView;
    }
}