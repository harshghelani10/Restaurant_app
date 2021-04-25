package com.example.restaurant_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class UserHome extends AppCompatActivity {
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private ImageView i1, i2, i3, i4, i5, i6, i7, i8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home);

        drawer = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
        toolbar = findViewById(R.id.toolbar);

        i1 = (ImageView) findViewById(R.id.image1);
        i2 = (ImageView) findViewById(R.id.image2);
        i3 = (ImageView) findViewById(R.id.image3);
        i4 = (ImageView) findViewById(R.id.image4);
        i5 = (ImageView) findViewById(R.id.image5);
        i6 = (ImageView) findViewById(R.id.image6);
        i7 = (ImageView) findViewById(R.id.image7);
        i8 = (ImageView) findViewById(R.id.image8);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image1 = new Intent(UserHome.this, Menu.class);
                startActivity(image1);
            }
        });

        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image2 = new Intent(UserHome.this, Menu.class);
                startActivity(image2);
            }
        });

        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image3 = new Intent(UserHome.this, Menu.class);
                startActivity(image3);
            }
        });

        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image4 = new Intent(UserHome.this, Menu.class);
                startActivity(image4);
            }
        });

        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image5 = new Intent(UserHome.this, Menu.class);
                startActivity(image5);
            }
        });

        i6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image6 = new Intent(UserHome.this, Menu.class);
                startActivity(image6);
            }
        });

        i7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image7 = new Intent(UserHome.this, Menu.class);
                startActivity(image7);
            }
        });

        i8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent image8 = new Intent(UserHome.this, Menu.class);
                startActivity(image8);
            }
        });


        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(UserHome.this, UserHome.class);
                        startActivity(home);
                        break;
                    case R.id.menu:
                        Intent menu = new Intent(UserHome.this, Menu.class);
                        startActivity(menu);
                        break;
                    case R.id.booktable:
                        Intent booktable = new Intent(UserHome.this, BookTable.class);
                        startActivity(booktable);
                        break;
                    case R.id.cart:
                        Intent cart = new Intent(UserHome.this, Cart.class);
                        startActivity(cart);
                        break;
                    case R.id.feedback:
                        Intent feedback = new Intent(UserHome.this, Feedback.class);
                        startActivity(feedback);
                        break;
                    case R.id.logout:
                        SharedPreferences preferences = getSharedPreferences("checked", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember", "false");
                        editor.apply();
                        finish();
                        Intent logout = new Intent(UserHome.this, MainActivity.class);
                        startActivity(logout);
                        break;
                    case R.id.user_view_order:
                        Intent user_view_order = new Intent(UserHome.this, User_view_order.class);
                        startActivity(user_view_order);
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

