package com.example.restaurant_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.viewmyordersmodel.Order;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.view.LayoutInflater.from;

public class Addcook extends AppCompatActivity {

    ImageView logo_image;
    EditText name, phone, email, password;
    Button register;
    private Button backbtn;
    RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cook);

        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);


        backbtn = (Button) findViewById(R.id.btnback);
        logo_image = (ImageView) findViewById(R.id.logo_image);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register_btn);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name.getText().toString().isEmpty()){
                    Toast.makeText(Addcook.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                }
                if (phone.getText().toString().isEmpty()){
                    Toast.makeText(Addcook.this, "Please Enter Phone number", Toast.LENGTH_SHORT).show();
                }
                if (email.getText().toString().isEmpty()){
                    Toast.makeText(Addcook.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if (password.getText().toString().isEmpty()){
                    Toast.makeText(Addcook.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }

                HashMap<String, String> map = new HashMap<>();

                map.put("name", name.getText().toString());
                map.put("email", email.getText().toString());
                map.put("password", password.getText().toString());
                map.put("phone", phone.getText().toString());

                Call<Void> call = retrofitInterface.executeCookRegister(map);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 201) {
                            Toast.makeText(Addcook.this,
                                    "Signed up successfully", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(Addcook.this, UserLogin.class));
                        } else if (response.code() == 422) {
                            Toast.makeText(Addcook.this,
                                    "Already registered", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(Addcook.this, "Please! Check Network of Your Device",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addcook.this, ManagerHome.class);
                startActivity(intent);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Addcook.this, ManagerHome.class);
                startActivity(intent);
            }
        });

        class CustomAdepter extends BaseAdapter {
            List<Order> data;
            Context context;

            @Override
            public int getCount() {

                return data.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            public View getView(int position, View view, ViewGroup parent) {

                view = from( context ).inflate( R.layout.custom_view_your_order, parent, false );

                TextView date = view.findViewById( R.id.item_date );
                TextView order_status = view.findViewById( R.id.item_status );
                TextView priority = view.findViewById( R.id.item_priority );
                TextView quantity = view.findViewById( R.id.item_Quantity );
                TextView totalPrice = view.findViewById( R.id.cart_item_price );
                ImageView imageView = view.findViewById( R.id.cart_image );


                date.setText(data.get(position).getCreatedAt());
                order_status.setText( data.get( position ).getOrderIs() );
                priority.setText( data.get( position).getItems().get( position ).getPriority()+"");
                quantity.setText( data.get( position ).getItems().get( position ).getQty() + "" );
                totalPrice.setText( data.get( position ).getItems().get( position ).getTotal() + "" + "₹" );

                return view;
            }

        }
    }
}