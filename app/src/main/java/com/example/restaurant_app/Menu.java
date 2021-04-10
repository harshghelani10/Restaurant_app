package com.example.restaurant_app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class Menu extends AppCompatActivity {
    private static final String SERVER = "http://192.168.0.2:8080/feed/menu";
    Button backbtn;
    GridView gridView;
    private TextView tv_result;



    //   RetrofitInterface retrofitInterface;

    //List<ImageResult> imageResultList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        backbtn = (Button) findViewById(R.id.btnback);
        gridView = (GridView) findViewById(R.id.gridView);
        tv_result = (TextView) findViewById(R.id.tv_result);


        HttpGetRequest request = new HttpGetRequest();
        request.execute();
//        Retrofit retrofitClient = RetrofitClient.getInstance();
//        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

//        getAllImages();
//        Call<List<MenuResult>> imageResults = retrofitInterface.getMenu();
//
//        imageResults.enqueue(new Callback<List<MenuResult>>() {
//
//            @Override
//            public void onResponse(Call<List<MenuResult>> call, Response<List<MenuResult>> response) {
//                if (response.isSuccessful()) {
//                    Log.e("menu", response.body().toString());
//                    Toast.makeText(Menu.this,"Greate....",Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<MenuResult>> call, Throwable t) {
//                Toast.makeText(Menu.this,"error",Toast.LENGTH_LONG).show();
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

    public class HttpGetRequest extends AsyncTask<Void, Void, String> {

        static final String REQUEST_METHOD = "GET";
        static final int READ_TIMEOUT = 15000;
        static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected String doInBackground(Void... params){
            String result;
            String inputLine;

            try {
                // connect to the server
                URL myUrl = new URL(SERVER);
                HttpURLConnection connection =(HttpURLConnection) myUrl.openConnection();
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);
                connection.connect();

                // get the string from the input stream
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                while((inputLine = reader.readLine()) != null){
                    stringBuilder.append(inputLine);
                }
                reader.close();
                streamReader.close();
                result = stringBuilder.toString();

            } catch(IOException e) {
                e.printStackTrace();
                result = "error";
            }

            return result;
        }

        protected void onPostExecute(String result){
            super.onPostExecute(result);
//            gridView.setAdapter();
            tv_result.setText(result);
        }
    }

}
//    public void getAllImages() {
//
//       Call<List<ImageResult>> imageResults = RetrofitClient.getInterface().getAllImage();

//        imageResults.enqueue(new Callback<List<ImageResult>>() {
//            @Override
//            public void onResponse(Call<List<ImageResult>> call, Response<List<ImageResult>> response) {
//
//                if (response.code() == 200) {
//                    Toast.makeText(Menu.this,
//                            "Request Successful", Toast.LENGTH_LONG).show();
//
//                } else if (response.code() == 500) {
//                    Toast.makeText(Menu.this,
//                            "An Error occurred try again later", Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<ImageResult>> call, Throwable t) {
//                Toast.makeText(Menu.this, "Failed..", Toast.LENGTH_LONG).show();
//            }
//        });


        class CustomAdepter extends BaseAdapter {

            private List<ImageResult> imageResultList;
            private Context context;
            private LayoutInflater layoutInflater;

            @RequiresApi(api = Build.VERSION_CODES.M)
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

