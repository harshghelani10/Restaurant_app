package com.example.restaurant_app.Retrofit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

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
    private int mInteger = 0;

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

        Button Add_to_cart;


        ImageView imageView = convertView.findViewById(R.id.coverImage);
        TextView textview1 = convertView.findViewById(R.id.item_name);
        TextView textview2 = convertView.findViewById(R.id.item_price);
        Add_to_cart = convertView.findViewById(R.id.add_to_cart);
        TextView quantity = convertView.findViewById(R.id.enter_qty);
        TextView priority = convertView.findViewById(R.id.set_priority);

        Button btn_min_p = convertView.findViewById(R.id.b_min_p);
        Button btn_plus_p = convertView.findViewById(R.id.b_plus_p);
        Button btn_min_q = convertView.findViewById(R.id.b_min_q);
        Button btn_plus_q = convertView.findViewById(R.id.b_plus_q);

        btn_min_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger - 1;
                priority.setText(mInteger+"");
            }
        });

        btn_plus_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger + 1;
            priority.setText(mInteger+"");
            }
        });

        btn_min_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger - 1;
                quantity.setText(mInteger+"");
            }
        });

        btn_plus_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger + 1;
                quantity.setText(mInteger+"");
            }
        });

        Add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences gettoken = activity.getSharedPreferences("token", MODE_PRIVATE);
                String token = gettoken.getString("TOKEN", "");

                retrofitInterface = RetrofitClient.getInstance().create(RetrofitInterface.class);

                String get = product.get(position).getId();
                String pri = priority.getText().toString();
                String qty = quantity.getText().toString();

                Body body = new Body();
                body.setPriority(Integer.valueOf(pri));
                body.setQty(Integer.valueOf(qty));


                Call<Addtocart> call = retrofitInterface.executecart(get, "Bearer " + token, body);

                call.enqueue(new Callback<Addtocart>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(Call<Addtocart> call, Response<Addtocart> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(activity, "Item added in your cart..", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(activity, Cart.class);
//                            activity.startActivity(intent);
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
