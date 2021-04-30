package com.example.restaurant_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BookTable extends AppCompatActivity {

    Button backbtn,scan_barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_table);

        backbtn = (Button)findViewById(R.id.btnback);
        scan_barcode = (Button) findViewById(R.id.scan_barcode);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookTable.this, UserHome.class);
                startActivity(intent);
            }
        });

        scan_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookTable.this,ScannedBarcodeActivity.class);
                startActivity(intent);
            }
        });
    }
}