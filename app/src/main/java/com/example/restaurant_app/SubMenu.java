package com.example.restaurant_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.CustomAdapter;
import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.Product;
import com.example.restaurant_app.model.Subcategory;
import com.example.restaurant_app.model.getingrideintmodel.GetIngredients;
import com.example.restaurant_app.model.getingrideintmodel.Ingredient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SubMenu extends AppCompatActivity {

    GridView gridView;
    RetrofitInterface retrofitInterface;
    Button backbtn;


    Subcategory subcategoryList = new Subcategory();
    List<Product> product = new ArrayList<>();
    GetIngredients ingredients = new GetIngredients();
    List<Ingredient> ingredientList = new ArrayList<>();
    public static String id;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_menu);

        gridView = (GridView) findViewById(R.id.gridView);
        id = getIntent().getStringExtra("_id");
        backbtn = (Button) findViewById(R.id.btnback);


        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        listingdata();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubMenu.this, Menu.class);
                startActivity(intent);
            }
        });

    }

    private void listingdata() {

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        Call<Subcategory> call = retrofitInterface.getSubCategory(id);

        call.enqueue(new Callback<Subcategory>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(Call<Subcategory> call, Response<Subcategory> response) {
                if (response.isSuccessful()) {
                   // Toast.makeText(SubMenu.this, "Success", Toast.LENGTH_SHORT).show();

                    subcategoryList = response.body();
                    product = subcategoryList.getProducts();

                    CustomAdapter customAdepter = new CustomAdapter(SubMenu.this,product);
                    gridView.setAdapter(customAdepter);

                } else {
                    Toast.makeText(SubMenu.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Subcategory> call, Throwable t) {
                System.out.println("############################ " + t.getLocalizedMessage());
            }
        });
    }
    //Adepter
//    class CustomAdepter extends BaseAdapter {
//
//        List<Product> product;
//        private Context context;
//        private LayoutInflater layoutInflater;
//        private Button Add_to_cart;
//        private EditText quantity, priority;
//        private String _id;
//        RetrofitInterface retrofitInterface;
//
//        public CustomAdepter(List<Product> product, SubMenu context) {
//
//            this.context = context;
//            this.product = product;
//        }
//
//        @Override
//        public int getCount() {
//            return product.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return position;
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            if (view == null) {
//
//                view = LayoutInflater.from(context).inflate(R.layout.custom_list_layout,viewGroup,false);
//
//                ImageView imageView = view.findViewById(R.id.coverImage);
//                TextView textview1 = view.findViewById(R.id.item_name);
//                TextView textview2 = view.findViewById(R.id.item_price);
//                Add_to_cart = view.findViewById(R.id.add_to_cart);
//                quantity = (EditText) view.findViewById(R.id.enter_qty);
//                priority = (EditText) view.findViewById(R.id.set_priority);
//
//                Add_to_cart.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        SharedPreferences gettoken = getSharedPreferences("token", MODE_PRIVATE);
//                        String token = gettoken.getString("TOKEN", "");
//
//                        Retrofit retrofitClient = RetrofitClient.getInstance();
//                        retrofitInterface = retrofitClient.create(RetrofitInterface.class);
//
//                        String get = product.get(i).getId();
//
//                        String pri = priority.getText().toString();
//                        String que = quantity.getText().toString();
//
//                        HashMap<String, Integer> map = new HashMap<>();
//
//                        map.put("priority", Integer.valueOf(pri));
//                        map.put("qty", Integer.valueOf(que));
//
//                        Call<Addtocart> call = retrofitInterface.executecart(map,get,token);
//
//                        call.enqueue(new Callback<Addtocart>() {
//                            @RequiresApi(api = Build.VERSION_CODES.M)
//                            @Override
//                            public void onResponse(Call<Addtocart> call, Response<Addtocart> response) {
//                                if (response.isSuccessful()) {
//                                    Toast.makeText(SubMenu.this, "add in cart", Toast.LENGTH_SHORT).show();
////                                    Intent intent = new Intent(SubMenu.this, Cart.class);
////                                    startActivity(intent);
//                                } else {
//                                    Toast.makeText(SubMenu.this, "" + response.message(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//
//                            @Override
//                            public void onFailure(Call<Addtocart> call, Throwable t) {
//                                System.out.println("############################" + t.getLocalizedMessage());
//
//                            }
//                        });
//                    }
//                });
//
//                textview1.setText(product.get(i).getName());
//                textview2.setText(product.get(i).getOriginalPrice()+"");
//                Picasso.with(SubMenu.this).load(product.get(i).getImageUrl()).into(imageView);
//            }
//
//            return view;
//        }
//    }
}
