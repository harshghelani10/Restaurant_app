
package com.example.restaurant_app.model.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ingredient {


    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("creator")
    @Expose
    private Creator creator;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}

//package com.example.restaurant_app.model.demo;
//
//        import com.google.gson.annotations.Expose;
//        import com.google.gson.annotations.SerializedName;
//
//public class Creator {
//
//    @SerializedName("name")
//    @Expose
//    private String name;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//}


//class MainActivity extends AppCompatActivity {
//    ImageView logo_image;
//    TextView welcome_txt;
//    Button login, sign_up;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView( R.layout.activity_main);
//
//        logo_image = (ImageView) findViewById(R.id.logo_image);
//        welcome_txt = (TextView) findViewById(R.id.welcome_txt);
//        login = (Button) findViewById(R.id.login);
//        sign_up = (Button) findViewById(R.id.sign_up);
//
//        sign_up.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText( MainActivity.this, "Welcome To Register", Toast.LENGTH_SHORT ).show();
//
//            }
//        });
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText( MainActivity.this, "clicked", Toast.LENGTH_SHORT ).show();
//
//            }
//        });
//    }
//}
