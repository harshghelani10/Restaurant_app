package com.example.restaurant_app.model.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Demo {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ingredient")
    @Expose
    private Ingredient ingredient;

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

//    public void setIngredient(Ingredient ingredient) {
//        this.ingredient = ingredient;
//    }
//    email = (EditText) findViewById(R.id.email);
//    send_email = (Button) findViewById(R.id.send_email);
//
//        send_email.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            HashMap<String, String> map = new HashMap<>();
//            map.put("email", email.getText().toString());
//
//            Call<ForgotResult> call = retrofitInterface.executeforgotpass(map);
//            call.enqueue(new Callback<ForgotResult>() {
//                @Override
//                public void onResponse(Call<ForgotResult> call, Response<ForgotResult> response) {
//                    if (response.code() == 200) {
//                        Toast.makeText( AdminForgotPassword.this,
//                                "Check Your Email for Reset Password", Toast.LENGTH_LONG).show();
//
//                        startActivity(new Intent(AdminForgotPassword.this, UserLogin.class));
//
//                    } else if (response.code() == 401) {
//                        Toast.makeText(AdminForgotPassword.this,
//                                "Email is not found", Toast.LENGTH_LONG).show();
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<ForgotResult> call, Throwable t) {
//                    Toast.makeText(AdminForgotPassword.this, "Please! Check Network of Your Device",
//                            Toast.LENGTH_LONG).show();
//
//                }
//            });
//        }
//    });
//}
//}

}
//-----------------------------------com.example.Loginall.java-----------------------------------
//
//        package com.example;
//
//        import javax.annotation.Generated;
//        import com.google.gson.annotations.Expose;
//        import com.google.gson.annotations.SerializedName;
//
//@Generated("jsonschema2pojo")
//public class Loginall {
//
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("role")
//    @Expose
//    private String role;
//    @SerializedName("your_accessToken")
//    @Expose
//    private String yourAccessToken;
//    @SerializedName("your_refreshToken")
//    @Expose
//    private String yourRefreshToken;
//    @SerializedName("Id")
//    @Expose
//    private String id;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getYourAccessToken() {
//        return yourAccessToken;
//    }
//
//    public void setYourAccessToken(String yourAccessToken) {
//        this.yourAccessToken = yourAccessToken;
//    }
//
//    public String getYourRefreshToken() {
//        return yourRefreshToken;
//    }
//
//    public void setYourRefreshToken(String yourRefreshToken) {
//        this.yourRefreshToken = yourRefreshToken;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//}
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
