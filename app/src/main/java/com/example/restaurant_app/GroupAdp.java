package com.example.restaurant_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.model.menuhomemodel.Categorypost;
import com.example.restaurant_app.model.menuhomemodel.Product;

import java.util.ArrayList;
import java.util.List;

public class GroupAdp extends RecyclerView.Adapter<GroupAdp.ViewHolder> {

    private Activity activity;
    List<Product> productList;
    List<Categorypost> catagoryList;

    public GroupAdp(UserHome userHome, List<Categorypost> categorypostList) {
        this.catagoryList = categorypostList;
        this.activity = userHome;

    }

    public void filterList(List<Categorypost> filterllist){
        catagoryList = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GroupAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.section_header,parent,false );
        return new GroupAdp.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdp.ViewHolder holder, int position) {

        holder.tv_name.setText( catagoryList.get( position ).getCategoryName() );

        List<Product> productList = new ArrayList<>();


        for (int i = 0; i < catagoryList.size() ; i++) {
            productList = catagoryList.get( position ).getProducts();
        }
        ItemAdp adapter = new ItemAdp(productList);

        LinearLayoutManager linearLayoutManagerItem = new LinearLayoutManager( activity );
        holder.rv_items.setLayoutManager( linearLayoutManagerItem );
        holder.rv_items.setAdapter( adapter );
    }

    @Override
    public int getItemCount() {
        return  catagoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        RecyclerView rv_items;

        public ViewHolder(@NonNull View itemView) {

            super( itemView );

            tv_name = itemView.findViewById( R.id.text_section_title );

            rv_items = itemView.findViewById( R.id.rv_items );
        }
    }
}

