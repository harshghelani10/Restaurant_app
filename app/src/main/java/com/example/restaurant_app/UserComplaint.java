package com.example.restaurant_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.vcomplaintmodel.Complaint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserComplaint extends AppCompatActivity {
    GridView gridView;
    RetrofitInterface retrofitInterface;

    com.example.restaurant_app.model.vcomplaintmodel.UserComplaint userComplaint = new com.example.restaurant_app.model.vcomplaintmodel.UserComplaint();
    List<Complaint> complaintList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint);

        gridView = (GridView) findViewById(R.id.gridView);

        listingdata();
    }

    private void listingdata() {
        Retrofit retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = retrofitClient.create(RetrofitInterface.class);

        SharedPreferences gettoken = getSharedPreferences("token", MODE_PRIVATE);
        String token = gettoken.getString("TOKEN", "");

        Call<com.example.restaurant_app.model.vcomplaintmodel.UserComplaint> call = retrofitInterface.viewComplaint("Bearer " + token);

        call.enqueue(new Callback<com.example.restaurant_app.model.vcomplaintmodel.UserComplaint>() {
            @Override
            public void onResponse(Call<com.example.restaurant_app.model.vcomplaintmodel.UserComplaint> call, Response<com.example.restaurant_app.model.vcomplaintmodel.UserComplaint> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UserComplaint.this, "your compalints", Toast.LENGTH_SHORT).show();

                    userComplaint = response.body();
                    complaintList = userComplaint.getComplaints();

                    CustomAdepter1 customAdepter1 = new CustomAdepter1(UserComplaint.this, complaintList);
                    gridView.setAdapter(customAdepter1);

                } else {
                    Toast.makeText(UserComplaint.this, "" + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.restaurant_app.model.vcomplaintmodel.UserComplaint> call, Throwable t) {
                Toast.makeText(UserComplaint.this, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

class CustomAdepter1 extends BaseAdapter {
    List<Complaint> complaints;
    private Context context;

    public CustomAdepter1(UserComplaint userComplaint, List<Complaint> complaintList) {
        this.context = userComplaint;
        this.complaints = complaintList;
    }

    @Override
    public int getCount() {
        return complaints.size();
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
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.custom_complaint, parent, false);

        TextView title = view.findViewById(R.id.c_title);
        TextView message = view.findViewById(R.id.c_message);

        title.setText(complaints.get(position).getTitle());
        message.setText(complaints.get(position).getMessage());

        return view;
    }
}
