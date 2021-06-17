package com.example.restaurant_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.model.menuhomemodel.Product;

import java.util.List;

public class ItemAdp  extends RecyclerView.Adapter<ItemAdp.ViewHolder> {


    List<Product> productList;


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

        }
    }
}

