//package com.example.restaurant_app.model.demo;
//
//package com.example.user_view_order;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.user_view_order.Retrofit.RetrofitClient;
//import com.example.user_view_order.Retrofit.RetrofitInterface;
//
//import java.util.HashMap;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
//public class AllLogin extends AppCompatActivity {
//
//    com.example.user_view_order.allLoginmodel.AllLogin allLogin = new com.example.user_view_order.allLoginmodel.AllLogin();
//    private RetrofitInterface retrofitInterface;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        setContentView( R.layout.activity_all_login );
//
//        EditText email = (EditText) findViewById( R.id.email );
//        EditText password = (EditText) findViewById( R.id.password );
//        Button login = (Button) findViewById( R.id.login_btn );
//
//
//        login.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Retrofit retrofitClient = RetrofitClient.getInstance();
//                retrofitInterface = retrofitClient.create( RetrofitInterface.class);
//                HashMap<String, String> map = new HashMap<>();
//
//                map.put("email", email.getText().toString());
//                map.put("password", password.getText().toString());
//
//                Call<com.example.user_view_order.allLoginmodel.AllLogin> call = retrofitInterface.getaccesslogin(map);
//
//                call.enqueue( new Callback<com.example.user_view_order.allLoginmodel.AllLogin>() {
//                    @Override
//                    public void onResponse(Call<com.example.user_view_order.allLoginmodel.AllLogin> call, Response<com.example.user_view_order.allLoginmodel.AllLogin> response) {
//                        if (response.isSuccessful()){
//                            Toast.makeText( AllLogin.this, "success", Toast.LENGTH_SHORT ).show();
//                            allLogin = response.body();
////                            if (allLogin.getRole() == "user"){
////                                Intent intent = new Intent(getApplicationContext(),UserHome.class);
////                                startActivity( intent );
////                            }if(allLogin.getRole() == "admin"){
////                                Intent i = new Intent(getApplicationContext(),AdminHome.class);
////                                startActivity( i );
////                            }
//
//                        }else{
//                            Toast.makeText( AllLogin.this, ""+response.message(), Toast.LENGTH_SHORT ).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<com.example.user_view_order.allLoginmodel.AllLogin> call, Throwable t) {
//                        Toast.makeText( AllLogin.this, ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
//                    }
//                } );
//
//            }
//        } );
//
//    }
//}
