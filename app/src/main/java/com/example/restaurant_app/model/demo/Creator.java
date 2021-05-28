
package com.example.restaurant_app.model.demo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Creator {

    @SerializedName("name")
    @Expose
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


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
