package com.example.restaurant_app;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.booktablemodel.BookTable;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ScannedBarcodeActivity extends AppCompatActivity {


    private static final int REQUEST_CAMERA_PERMISSION = 201;
    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_scanned_barcode );
        initViews();
    }

    private void initViews() {
        txtBarcodeValue = findViewById( R.id.txtBarcodeValue );
        surfaceView = findViewById( R.id.surfaceView );
        btnAction = findViewById( R.id.btnAction );


        btnAction.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = retrofitClient.create( RetrofitInterface.class );

                SharedPreferences gettoken = getSharedPreferences( "token", MODE_PRIVATE );
                String token = gettoken.getString( "TOKEN", "" );

//                String table_value = String.valueOf( intentData.getBytes().toString().matches( "tables" ) );
//
//                Body body = new Body(table_value);


                Call<BookTable> call = retrofitInterface.booktable( "Bearer " + token);

                txtBarcodeValue.setText( intentData );

                call.enqueue( new Callback<BookTable>() {
                    @Override
                    public void onResponse(Call<BookTable> call, Response<BookTable> response) {
                        if (response.isSuccessful()) {

                            txtBarcodeValue.setText( intentData );
                            Toast.makeText( ScannedBarcodeActivity.this, "Your Table Is Booked", Toast.LENGTH_SHORT ).show();
                        } else {
                            Toast.makeText( ScannedBarcodeActivity.this, "" + response.message(), Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BookTable> call, Throwable t) {
                        Toast.makeText( ScannedBarcodeActivity.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();

                    }
                } );

//                Toast.makeText( ScannedBarcodeActivity.this, "get out from here", Toast.LENGTH_SHORT ).show();
//                if (intentData.length() > 0) {
//                    if (isEmail)
//                        startActivity(new Intent(ScannedBarcodeActivity.this, EmailActivity.class).putExtra("email_address", intentData));
//                    else {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intentData)));
//                    }
//                }
            }
        } );
    }

    private void initialiseDetectorsAndSources() {

        Toast.makeText( getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT ).show();
        barcodeDetector = new BarcodeDetector.Builder( this )
                .setBarcodeFormats( Barcode.ALL_FORMATS )
                .build();

        cameraSource = new CameraSource.Builder( this, barcodeDetector )
                .setRequestedPreviewSize( 1920, 1080 )
                .setAutoFocusEnabled( true ) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback( new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission( ScannedBarcodeActivity.this, Manifest.permission.CAMERA ) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start( surfaceView.getHolder() );
                    } else {
                        ActivityCompat.requestPermissions( ScannedBarcodeActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION );
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        } );


        barcodeDetector.setProcessor( new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText( getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    txtBarcodeValue.post( new Runnable() {
                        @Override
                        public void run() {

                            isEmail = false;
                            btnAction.setText( "Book Table" );
                            intentData = barcodes.valueAt( 0 ).displayValue;
                            txtBarcodeValue.setText( intentData );

                        }
                    } );

                }
            }
        } );
    }


    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }
}
