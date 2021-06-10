package com.example.restaurant_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant_app.Retrofit.RetrofitClient;
import com.example.restaurant_app.Retrofit.RetrofitInterface;
import com.example.restaurant_app.model.complaintReply.ComplaintReply;
import com.example.restaurant_app.model.complaintReply.ReplyId;
import com.example.restaurant_app.model.vcomplaintmodel.Complaint;
import com.example.restaurant_app.model.vcomplaintmodel.Data;

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
    Data data = new Data();
    ComplaintReply complaintReply = new ComplaintReply();
    List<ReplyId> replyIdList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_complaint);

        gridView = (GridView) findViewById(R.id.gridView);
        Button backbtn = (Button) findViewById( R.id.btnback );

        listingdata();

        backbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( UserComplaint.this, UserHome.class );
                startActivity( intent );
            }
        } );
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
//                    Toast.makeText(UserComplaint.this, "your compalints", Toast.LENGTH_SHORT).show();

                    userComplaint = response.body();
                    data = userComplaint.getData();
                    complaintList = data.getComplaints();

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

    //complaint reply
    ComplaintReply complaintReply = new ComplaintReply();
    List<ReplyId> replyIdList = new ArrayList<>();
    com.example.restaurant_app.model.complaintReply.Complaint complaint = new com.example.restaurant_app.model.complaintReply.Complaint();

    //give complaint for order
    List<Complaint> complaints;
    private Context activity;
    private RetrofitInterface retrofitInterface;
    private int i;

    public CustomAdepter1(UserComplaint userComplaint, List<Complaint> complaintList) {
        activity = userComplaint;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        Button view_reply;

        convertView = LayoutInflater.from(activity).inflate(R.layout.custom_complaint, parent, false);

        TextView title = convertView.findViewById(R.id.c_title);
        TextView message = convertView.findViewById(R.id.c_message);
        view_reply = convertView.findViewById( R.id.view_reply );

        title.setText(complaints.get(position).getTitle());
        message.setText(complaints.get(position).getMessage());


        view_reply.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from( activity ).inflate( R.layout.dialogbox_viewcomplaint,parent,false );

                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setView(view).show();

                Retrofit retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = retrofitClient.create(RetrofitInterface.class);

                String get = complaints.get( position ).getId();

                Call<ComplaintReply> call = retrofitInterface.getComplaintReply( get );

                call.enqueue( new Callback<ComplaintReply>() {
                    @Override
                    public void onResponse(Call<ComplaintReply> call, Response<ComplaintReply> response) {
                        if (response.isSuccessful()){

                            complaintReply = response.body();
                            complaint = complaintReply.getComplaint();
                            replyIdList = complaint.getReplyId();

                            TextView tv_Complaint_reply = (TextView) view.findViewById( R.id.tv_complaint_reply );
                            tv_Complaint_reply.setText( replyIdList.get( i ).getMessage() );

//                            Toast.makeText( activity.getApplicationContext(), "Success", Toast.LENGTH_SHORT ).show();
                        }else {
                            Toast.makeText( activity.getApplicationContext(), ""+response.message(), Toast.LENGTH_SHORT ).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ComplaintReply> call, Throwable t) {
                        Toast.makeText( activity.getApplicationContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                    }
                } );



                //  Toast.makeText( context, "clicked", Toast.LENGTH_SHORT ).show();

            }
        } );

        return convertView;
    }
}
