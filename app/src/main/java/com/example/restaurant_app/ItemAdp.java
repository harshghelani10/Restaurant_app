package com.example.restaurant_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.model.menuhomemodel.Product;

import java.util.List;

public class ItemAdp  extends RecyclerView.Adapter<ItemAdp.ViewHolder> {


    Activity activity;
    List<Product> productList;


    public ItemAdp(Activity activity,List<Product> productList) {
        this.productList = productList;
        this.activity = activity;
    }

    public ItemAdp(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ItemAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.custom_home,parent,false );
        return new ItemAdp.ViewHolder( view );

    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdp.ViewHolder holder, int position) {

        holder.name.setText( productList.get( position ).getName() );
        holder.price.setText( productList.get( position ).getOriginalPrice()+ " " +"₹");
//        holder.image.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity.getApplicationContext().this,Menu.class);
//                activity.startActivity( intent );
//            }
//        } );
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name,price;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            image = itemView.findViewById( R.id.cart_image );
            name  = itemView.findViewById( R.id.item_name );
            price = itemView.findViewById( R.id.cart_item_price );

            image.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText( activity.getApplicationContext(), "clicked", Toast.LENGTH_SHORT ).show();
//                    Intent intent = new Intent(activity.getApplicationContext(),Menu.class);
//                    activity.startActivity( intent );
                }
            } );

        }
    }
}

