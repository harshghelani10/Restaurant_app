package com.example.restaurant_app;

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

import com.example.restaurant_app.Retrofit.Product;
import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Menu extends AppCompatActivity {

    Button backbtn;
    GridView gridView;
    private TextView tv_result;
    RetrofitInterface retrofitInterface;
    //private String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzZXJAZ21haWwuY29tIiwicGhvbmUiOiIxMjM0NTY3ODkwIiwidXNlcklkIjoiNjA2NTJkMjg0NjM1YjcxNTYwMDA5NTE3IiwiaWF0IjoxNjE4Mjg1NzY4LCJleHAiOjE2MTgzNzIxNjh9.CwwpCU1MitGMq--guCdNcBKaUNBmhSC2LT1Ak483Agc";

    //   RetrofitInterface retrofitInterface;

    //List<ImageResult> imageResultList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        backbtn = (Button) findViewById(R.id.btnback);
        gridView = (GridView) findViewById(R.id.gridView);
        tv_result = (TextView) findViewById(R.id.tv_result);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);


        listingdata();
//        HashMap<String, String> map = new HashMap<>();
//
//        map.put("email", email.getText().toString());
//        map.put("password", password.getText().toString());
//
//
//        Call<Product> call = retrofitInterface.getMenu(map);
//
////        Call<MenuResult> call = retrofitInterface.getMenu(map);
//
//        call.enqueue(new Callback<Product>() {
//            @Override
//            public void onResponse(Call<Product> call, Response<Product> response) {
//
//                if (response.code() == 200) {
//
//                    Product result = response.body();
//                    Toast.makeText(Menu.this, "Login Success",
//                            Toast.LENGTH_LONG).show();
//
//                    Intent intent = new Intent(Menu.this, UserHome.class);
//                    startActivity(intent);
//
//                }
//                else if (response.code() == 401) {
//                    Toast.makeText(Menu.this, "Wrong Credentials",
//                            Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Product> call, Throwable t) {
//                Toast.makeText(Menu.this, "Please! Check Network of Your Device",
//                        Toast.LENGTH_LONG).show();
//            }
//        });
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

        Call<Product> listingdata = retrofitInterface.Getdata();

        listingdata.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product item = new Product();
                    item = response.body();
                    Toast.makeText(Menu.this, "Success", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(Menu.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                System.out.println("############################ " + t.getLocalizedMessage());
            }
        });
    }
}


//        OkHttpClient client = new OkHttpClient();
//        String url = "http://192.168.0.26:8080/feed/getposts";
//
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("Authorization","Bearer "+token)
//                .addHeader("cache-control", "no-cache")
//                .build();
//
//
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Toast.makeText(Menu.this, "error", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws
//                    IOException {
//                if (response.isSuccessful()) {
//                    final String myResponse = response.body().string();
//
//                    Menu.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            tv_result.setText(myResponse);
////                          recyclerView.setAdapter(adapter);
//                        }
//                    });
//                }
//
//            }
//        });


//    private  Response requestBuilderWithBearerToken(String userToken) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://192.168.0.26:8080/feed/getposts")
//                .get()
//                .addHeader("cache-control", "no-cache")
//                .addHeader("Authorization", "Bearer " + userToken)
//                .build();
//
//        return client.newCall(request).execute();
//    }


class CustomAdepter extends BaseAdapter {

    private List<ImageResult> imageResultList;
    private Context context;
    private LayoutInflater layoutInflater;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public CustomAdepter(List<ImageResult> imageResultList, Context context) {
        this.imageResultList = imageResultList;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
