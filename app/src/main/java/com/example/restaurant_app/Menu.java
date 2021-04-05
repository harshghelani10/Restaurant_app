package com.example.restaurant_app;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {

    Button backbtn;
    GridView gridView;

    List<ImageResult> imageResultList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        backbtn = (Button) findViewById(R.id.btnback);
        gridView = (GridView) findViewById(R.id.gridView);

        getAllImages();


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, UserHome.class);
                startActivity(intent);
            }
        });
    }

    public void getAllImages() {
        Call<List<ImageResult>> imageResults = RetrofitClient.getInterface().getAllImages();
        imageResults.enqueue(new Callback<List<ImageResult>>() {
            @Override
            public void onResponse(Call<List<ImageResult>> call, Response<List<ImageResult>> response) {

                if (response.code() == 200) {
                    Toast.makeText(Menu.this,
                            "Request Successful", Toast.LENGTH_LONG).show();

                    //startActivity(new Intent(Menu.this, UserLogin.class));
                } else if (response.code() == 500) {
                    Toast.makeText(Menu.this,
                            "An Error occurred try again later", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<List<ImageResult>> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(Menu.this, message, Toast.LENGTH_LONG).show();
            }
        });


        class CustomAdepter extends BaseAdapter {

            private List<ImageResult> imageResultList;
            private Context context;
            private LayoutInflater layoutInflater;

            public CustomAdepter(List<ImageResult> imageResultList, Context context) {
                this.imageResultList = imageResultList;
                this.context = context;
                this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            }

            @Override
            public int getCount() {
                return imageResultList.size();
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
                    view = layoutInflater.inflate(R.layout.row_grid_iteam, viewGroup, false);
                }

                ImageView imageView = view.findViewById(R.id.iteam_image);
                TextView textview = view.findViewById(R.id.tv1);

                textview.setText(imageResultList.get(i).getName());
                GlideApp.with(context)
                        .load(imageResultList.get(i).getImageUrl())
                        .into(imageView);
                return null;
            }
        }
    }
}
