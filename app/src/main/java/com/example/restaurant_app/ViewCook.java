package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ViewCook extends AppCompatActivity {

    private Button backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cook);
        backbtn = (Button)findViewById(R.id.btnback);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCook.this, ManagerHome.class);
                startActivity(intent);
            }
        });
    }
}