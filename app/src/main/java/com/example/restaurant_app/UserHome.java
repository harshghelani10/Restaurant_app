package com.example.restaurant_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.menuhomemodel.Categorypost;
import com.example.restaurant_app.model.menuhomemodel.MenuHomePage;
import com.example.restaurant_app.model.menuhomemodel.Product;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserHome extends AppCompatActivity {

    public static String id;
    RetrofitInterface retrofitInterface;
    RecyclerView rvGroup;

    TextView offers, show_menu, show_cart, book_table, view_order;

    MenuHomePage menuHomePage = new MenuHomePage();
    List<Categorypost> categorypostList = new ArrayList<>();
    List<com.example.restaurant_app.model.menuhomemodel.Product> productList = new ArrayList<>();
    LinearLayoutManager layoutManagerGroup;
    GroupAdp adepterGroup;


    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private int i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.user_home );

        drawer = findViewById( R.id.drawer );
        navigationView = findViewById( R.id.navigation );
        toolbar = findViewById( R.id.toolbar );
        offers = (TextView) findViewById( R.id.tv_offer );
        show_menu = (TextView) findViewById( R.id.show_menu );
        show_cart = (TextView) findViewById( R.id.show_cart );
        book_table = (TextView) findViewById( R.id.book_table );
        view_order = (TextView) findViewById( R.id.view_order );
        rvGroup = (RecyclerView) findViewById( R.id.rv_group );


        listingData();


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
                Intent intent = new Intent( getApplicationContext(), Cart.class );
                startActivity( intent );
            }
        } );

        book_table.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), BookTable.class );
                startActivity( intent );
            }
        } );

        view_order.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), User_view_order.class );
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




    private void listingData() {


        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<MenuHomePage> call = retrofitInterface.getMenuHomePage();

        call.enqueue( new Callback<MenuHomePage>() {
            @Override
            public void onResponse(Call<MenuHomePage> call, Response<MenuHomePage> response) {
                if (response.isSuccessful()) {

                    menuHomePage = response.body();
                    categorypostList = menuHomePage.getCategoryposts();

                    for (int i = 0; i < categorypostList.size() ; i++) {
                        for (int i1 = 0; i1 < categorypostList.get( i ).getProducts().size(); i1++) {
                            Product product = new Product();
                            product = categorypostList.get( i ).getProducts().get( i1 );
                            productList.add( product );
                        }
                    }

                    adepterGroup = new GroupAdp( UserHome.this,categorypostList );

                    layoutManagerGroup = new LinearLayoutManager( UserHome.this);
                    rvGroup.setLayoutManager( layoutManagerGroup );
                    rvGroup.setAdapter( adepterGroup );

                    //Toast.makeText( UserHome.this, "Success", Toast.LENGTH_SHORT ).show();
                }else {

                    Toast.makeText( UserHome.this, ""+response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<MenuHomePage> call, Throwable t) {
                Toast.makeText( UserHome.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            super.onBackPressed();
        }
    }

}

