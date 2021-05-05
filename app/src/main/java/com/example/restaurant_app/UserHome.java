package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.CategoryResponse;
import com.example.restaurant_app.model.Categorypost;
import com.example.restaurant_app.model.allmenuitems.AllMenuItems;
import com.example.restaurant_app.model.allmenuitems.Product;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserHome extends AppCompatActivity {

    public static String id;
    GridView gridView, gridview_tpfu;
    RetrofitInterface retrofitInterface;
    CategoryResponse categorypostList = new CategoryResponse();
    List<Categorypost> categoryposts = new ArrayList<>();
    AllMenuItems allMenuItems = new AllMenuItems();
    List<Product> productList = new ArrayList<>();
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
        gridview_tpfu = (GridView) findViewById( R.id.gridView_tpfu );
        TextView offers = (TextView) findViewById( R.id.tv_offer );

        offers.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), com.example.restaurant_app.offers.class );
                startActivity( intent );
            }
        } );
        listingdata();
        listingtoppicks();
    }

    private void listingtoppicks() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<AllMenuItems> call = retrofitInterface.getallmenu();

        call.enqueue( new Callback<AllMenuItems>() {
            @Override
            public void onResponse(Call<AllMenuItems> call, Response<AllMenuItems> response) {
                if (response.isSuccessful()) {
                    Toast.makeText( UserHome.this, "okk", Toast.LENGTH_SHORT ).show();

                    allMenuItems = response.body();
                    productList = allMenuItems.getProducts();

                    CustomAdepterall customAdepterall = new CustomAdepterall( productList, UserHome.this );
                    gridview_tpfu.setAdapter( customAdepterall );

                } else {
                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<AllMenuItems> call, Throwable t) {
                Toast.makeText( UserHome.this, "###" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }


    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create( RetrofitInterface.class );

        Call<CategoryResponse> call = retrofitInterface.getCategory();

        call.enqueue( new Callback<CategoryResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(Menu.this, "Success", Toast.LENGTH_SHORT).show();

                    categorypostList = response.body();
                    categoryposts = categorypostList.getCategoryposts();

                    CustomAdepterhome customAdepter = new CustomAdepterhome( categoryposts, UserHome.this );
                    gridView.setAdapter( customAdepter );


                } else {
                    Toast.makeText( UserHome.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                System.out.println( "############################ " + t.getLocalizedMessage() );
            }
        } );

//
//        i1 = (ImageView) findViewById(R.id.image1);
//        i2 = (ImageView) findViewById(R.id.image2);
//        i3 = (ImageView) findViewById(R.id.image3);
//        i4 = (ImageView) findViewById(R.id.image4);
//        i5 = (ImageView) findViewById(R.id.image5);
//        i6 = (ImageView) findViewById(R.id.image6);
//        i7 = (ImageView) findViewById(R.id.image7);
//        i8 = (ImageView) findViewById(R.id.image8);
//
//        i1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image1 = new Intent(UserHome.this, Menu.class);
//                startActivity(image1);
//            }
//        });
//
//        i2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image2 = new Intent(UserHome.this, Menu.class);
//                startActivity(image2);
//            }
//        });
//
//        i3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image3 = new Intent(UserHome.this, Menu.class);
//                startActivity(image3);
//            }
//        });
//
//        i4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image4 = new Intent(UserHome.this, Menu.class);
//                startActivity(image4);
//            }
//        });
//
//        i5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image5 = new Intent(UserHome.this, Menu.class);
//                startActivity(image5);
//            }
//        });
//
//        i6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image6 = new Intent(UserHome.this, Menu.class);
//                startActivity(image6);
//            }
//        });
//
//        i7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image7 = new Intent(UserHome.this, Menu.class);
//                startActivity(image7);
//            }
//        });
//
//        i8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent image8 = new Intent(UserHome.this, Menu.class);
//                startActivity(image8);
//            }
//        });
//

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
                        Intent logout = new Intent( UserHome.this, MainActivity.class );
                        startActivity( logout );
                        break;
                    case R.id.user_view_order:
                        Intent user_view_order = new Intent( UserHome.this, User_view_order.class );
                        startActivity( user_view_order );
                        break;

                }
                return true;
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

//Adepter
class CustomAdepterhome extends BaseAdapter {

    List<Categorypost> categorypostList;
    private Context context;
    private LayoutInflater layoutInflater;


    public CustomAdepterhome(List<Categorypost> categoryposts, UserHome userHome) {
        this.context = userHome;
        this.categorypostList = categoryposts;

    }

    @Override
    public int getCount() {
        return categorypostList.size();
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
            LayoutInflater lInflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE );

            view = lInflater.inflate( R.layout.row_grid_iteam, null );
        }

        ImageView imageView = view.findViewById( R.id.imageView );
        TextView textview = view.findViewById( R.id.textView );

        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get = categorypostList.get( i ).getId();

                Intent intent = new Intent( context.getApplicationContext(), SubMenu.class );
                intent.putExtra( "_id", categorypostList.get( i ).getId() );
                context.startActivity( intent );
                // Toast.makeText( context, "click", Toast.LENGTH_SHORT ).show();
                //  Toast.makeText(UserHome.this,"Click",Toast.LENGTH_SHORT).show();
            }
        } );

        textview.setText( categorypostList.get( i ).getCategoryName() );
        Picasso.with( context ).load( categorypostList.get( i ).getImageUrl() ).into( imageView );
        return view;
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
            LayoutInflater lInflater = (LayoutInflater) context.getSystemService(
                    Activity.LAYOUT_INFLATER_SERVICE );

            convertView = lInflater.inflate( R.layout.row_grid_iteam, null );
        }

        ImageView imageView = convertView.findViewById( R.id.imageView );
        TextView textview = convertView.findViewById( R.id.textView );

        imageView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( context, "click", Toast.LENGTH_SHORT ).show();
                //  Toast.makeText(UserHome.this,"Click",Toast.LENGTH_SHORT).show();
            }
        } );

        textview.setText( productList.get( position ).getName() );
        Picasso.with( context ).load( productList.get(position).getImageUrl() ).into( imageView );
        return convertView;
    }
}

