package com.example.restaurant_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.CategoryResponse;
import com.example.restaurant_app.model.Categorypost;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Menu extends AppCompatActivity {

    Button backbtn;
    RecyclerView recyclerView;
    GridView gridView;
    RetrofitInterface retrofitInterface;

    CategoryResponse categorypostList = new CategoryResponse();
    List<Categorypost> categoryposts = new ArrayList<>();
    public static String id;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        backbtn = (Button) findViewById(R.id.btnback);
        gridView = (GridView) findViewById(R.id.gridView);


        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);
        listingdata();
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, UserHome.class);
                startActivity(intent);
            }
        });
    }

    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        Call<CategoryResponse> call = retrofitInterface.getCategory();

        call.enqueue(new Callback<CategoryResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    //Toast.makeText(Menu.this, "Success", Toast.LENGTH_SHORT).show();

                    categorypostList = response.body();
                    categoryposts = categorypostList.getCategoryposts();

                    CustomAdepter customAdepter = new CustomAdepter(categoryposts, Menu.this);
                    gridView.setAdapter(customAdepter);


                } else {
                    Toast.makeText(Menu.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                System.out.println("############################ " + t.getLocalizedMessage());
            }
        });
    }

    //Adepter
    class CustomAdepter extends BaseAdapter {

        List<Categorypost> categorypostList;
        private Context context;
        private LayoutInflater layoutInflater;

        public CustomAdepter(List<Categorypost> categorypostList, Menu context) {

            this.context = context;
            this.categorypostList = categorypostList;

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
                        Activity.LAYOUT_INFLATER_SERVICE);

                view = lInflater.inflate(R.layout.row_grid_iteam, null);
            }

            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textview = view.findViewById(R.id.textView);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String get  = categorypostList.get(i).getId();
                          Intent intent = new Intent(Menu.this, SubMenu.class);
                          intent.putExtra("_id",categorypostList.get(i).getId());
                          startActivity(intent);
                  //  Toast.makeText(Menu.this,"Click",Toast.LENGTH_SHORT).show();
                }
            });

            textview.setText(categorypostList.get(i).getCategoryName());
 //           Picasso.with(Menu.this).load(categorypostList.get(i).getImageUrl()).into(imageView);
            return view;
        }
    }
}