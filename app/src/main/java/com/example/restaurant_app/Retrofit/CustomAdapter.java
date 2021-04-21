package com.example.restaurant_app.Retrofit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.restaurant_app.Cart;
import com.example.restaurant_app.R;
import com.example.restaurant_app.SubMenu;
import com.example.restaurant_app.model.Addtocart;
import com.example.restaurant_app.model.Body.Body;
import com.example.restaurant_app.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends BaseAdapter {
    Activity activity;
    List<Product> product;
    RetrofitInterface retrofitInterface;

    public CustomAdapter(SubMenu subMenu, List<Product> product) {
        activity = subMenu;
        this.product = product;
    }

    @Override
    public int getCount() {
        return product.size();
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

        convertView = LayoutInflater.from(activity).inflate(R.layout.custom_list_layout, parent, false);

        Context context;
        LayoutInflater layoutInflater;
        Button Add_to_cart;


        ImageView imageView = convertView.findViewById(R.id.coverImage);
        TextView textview1 = convertView.findViewById(R.id.item_name);
        TextView textview2 = convertView.findViewById(R.id.item_price);
        Add_to_cart = convertView.findViewById(R.id.add_to_cart);
        EditText quantity = convertView.findViewById(R.id.enter_qty);
        EditText priority = convertView.findViewById(R.id.set_priority);

        Add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences gettoken = activity.getSharedPreferences("token", MODE_PRIVATE);
                String token = gettoken.getString("TOKEN", "");

                retrofitInterface = RetrofitClient.getInstance().create(RetrofitInterface.class);

                String get = product.get(position).getId();

                String pri = priority.getText().toString();
                String qty = quantity.getText().toString();

//                HashMap<String, Integer> map = new HashMap<>();
//
//                map.put("qty", Integer.valueOf(qty));
//                map.put("priority", Integer.valueOf(pri));

                Body body = new Body();
                body.setPriority(Integer.valueOf(pri));
                body.setQty(Integer.valueOf(qty));


                Call<Addtocart> call = retrofitInterface.executecart( get, "Bearer " + token,body);

                call.enqueue(new Callback<Addtocart>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(Call<Addtocart> call, Response<Addtocart> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(activity, "Item added in your cart..", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity, Cart.class);
                            activity.startActivity(intent);
                        } else {
                            Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<Addtocart> call, Throwable t) {
                        System.out.println("############################" + t.getLocalizedMessage());

                    }
                });
            }
        });

        textview1.setText(product.get(position).getName());
        textview2.setText(product.get(position).getOriginalPrice() + " ");
        Picasso.with(activity).load(product.get(position).getImageUrl()).into(imageView);

        return convertView;
    }
}
