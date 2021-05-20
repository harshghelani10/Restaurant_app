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
