package com.example.restaurant_app.Retrofit;

import android.app.Activity;
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
import androidx.appcompat.app.AlertDialog;

import com.example.restaurant_app.R;
import com.example.restaurant_app.SubMenu;
import com.example.restaurant_app.model.Body.Body;
import com.example.restaurant_app.model.Product;
import com.example.restaurant_app.model.addtocartmodel.AddtoCart;
import com.example.restaurant_app.model.getingrediantmodel.GetIngrdiant;
import com.example.restaurant_app.model.getingrediantmodel.Ingredient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends BaseAdapter {
    Activity activity;
    List<Product> product;
    GetIngrdiant getIngrdiant =  new GetIngrdiant();
    List<Ingredient> ingredientList  = new ArrayList<>();
    RetrofitInterface retrofitInterface;
    private int mInteger = 0;
    private int i;

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

        Button Add_to_cart, add_ingredient;


        ImageView imageView = convertView.findViewById(R.id.coverImage);
        TextView textview1 = convertView.findViewById(R.id.item_name);
        TextView textview2 = convertView.findViewById(R.id.item_price);
        Add_to_cart = convertView.findViewById(R.id.add_to_cart);
        add_ingredient = convertView.findViewById(R.id.add_ingredient);
        TextView quantity = convertView.findViewById(R.id.enter_qty);
        TextView priority = convertView.findViewById(R.id.set_priority);
        EditText notes = convertView.findViewById( R.id.add_notes );
        TextView ingredient_name = convertView.findViewById(R.id.ingredient_name);
        TextView ingredient_price = convertView.findViewById(R.id.ingredient_price);

        Button btn_min_p = convertView.findViewById(R.id.b_min_p);
        Button btn_plus_p = convertView.findViewById(R.id.b_plus_p);
        Button btn_min_q = convertView.findViewById(R.id.b_min_q);
        Button btn_plus_q = convertView.findViewById(R.id.b_plus_q);

        btn_min_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger - 1;
                priority.setText(mInteger + "");
            }
        });

        btn_plus_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger + 1;
                priority.setText(mInteger + "");
            }
        });

        btn_min_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger - 1;
                quantity.setText(mInteger + "");
            }
        });

        btn_plus_q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInteger = mInteger + 1;
                quantity.setText(mInteger + "");
            }
        });

        Add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                handleIngrediantsDialog();

                SharedPreferences gettoken = activity.getSharedPreferences("token", MODE_PRIVATE);
                String token = gettoken.getString("TOKEN", "");

                retrofitInterface = RetrofitClient.getInstance().create(RetrofitInterface.class);

                String get = product.get(position).getId();
                String pri = priority.getText().toString();
                String qty = quantity.getText().toString();
                String note = notes.getText().toString();

                Body body = new Body();
                body.setPriority(Integer.valueOf(pri));
                body.setQty(Integer.valueOf(qty));
                body.setNotes(note);


                Call<AddtoCart> call = retrofitInterface.executecart(get, "Bearer " + token, body);

                call.enqueue(new Callback<AddtoCart>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(Call<AddtoCart> call, Response<AddtoCart> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(activity, "Item added in your cart..", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(activity, Cart.class);
//                            activity.startActivity(intent);
                        } else {
                            Toast.makeText(activity, "" + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<AddtoCart> call, Throwable t) {
                        System.out.println("############################" + t.getLocalizedMessage());

                    }
                });
            }
        });

        add_ingredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = activity.getLayoutInflater().inflate(R.layout.dialogbox_ingridients, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setView(view).show();

                retrofitInterface = RetrofitClient.getInstance().create(RetrofitInterface.class);

                Call<GetIngrdiant> call = retrofitInterface.getIngredient();

                call.enqueue( new Callback<GetIngrdiant>() {
                    @Override
                    public void onResponse(Call<GetIngrdiant> call, Response<GetIngrdiant> response) {
                       if (response.isSuccessful()){

                           getIngrdiant = response.body();
                           ingredientList = getIngrdiant.getIngredients();

                           Toast.makeText( activity, "sucesss...", Toast.LENGTH_SHORT ).show();


                       }else {
                           Toast.makeText( activity, ""+response.message(), Toast.LENGTH_SHORT ).show();
                       }
                    }

                    @Override
                    public void onFailure(Call<GetIngrdiant> call, Throwable t) {
                        Toast.makeText( activity, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                    }
                } );

            }
        });

        textview1.setText(product.get(position).getName());
        textview2.setText(product.get(position).getOriginalPrice() + " " + "₹");
//        Picasso.with(activity).load(product.get(position).getImageUrl()).into(imageView);
//        ingredient_name.setText(ingredientList.get(position).getIngredientName());
//        ingredient_price.setText(ingredientList.get(position).getPrice() + "" + "₹");

        return convertView;
    }
}
//    private void handleIngrediantsDialog() {
//
//        View view = activity.getLayoutInflater().inflate(R.layout.dialogbox_ingridients, null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        builder.setView(view).show();
//
//        CheckBox checkBox = view.findViewById(R.id.checkbox_cart);
//        TextView ingredient_name = view.findViewById(R.id.ingredient_name);
//        TextView ingredient_price = view.findViewById(R.id.ingredient_price);
//        Button add_item = view.findViewById(R.id.add_item);
//
//        Retrofit retrofitClient = RetrofitClient.getInstance();
//        retrofitInterface = retrofitClient.create(RetrofitInterface.class);
//
//        Call<GetIngredients> call = retrofitInterface.addIngredients();
//
//        call.enqueue(new Callback<GetIngredients>() {
//            @Override
//            public void onResponse(Call<GetIngredients> call, Response<GetIngredients> response) {
//                if (response.isSuccessful()){
//                    getIngredients = response.body();
//                    ingredientList = getIngredients.getIngredients();
//                    Toast.makeText(activity, "okk", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(activity, ""+response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetIngredients> call, Throwable t) {
//                Toast.makeText(activity, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        ingredient_name.setText(ingredientList.get(i).getIngredientName());
//        ingredient_price.setText(ingredientList.get(i).getPrice()+"");
//
//        add_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
//}
