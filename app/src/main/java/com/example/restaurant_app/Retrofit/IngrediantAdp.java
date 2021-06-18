package com.example.restaurant_app.Retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurant_app.R;
import com.example.restaurant_app.model.getingrediantmodel.Ingredient;

import java.util.List;

public class IngrediantAdp extends RecyclerView.Adapter<IngrediantAdp.ViewHolder> {

    CustomAdapter activity;
    List<Ingredient> ingredientList;

    public IngrediantAdp(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    @NonNull
    @Override
    public IngrediantAdp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.custom_ingrediant,parent,false );

        return new IngrediantAdp.ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull IngrediantAdp.ViewHolder holder, int position) {

        holder.Ingrediant_name.setText( ingredientList.get( position ).getIngredientName() );
        holder.Ingrediant_price.setText( ingredientList.get( position ).getPrice()+""+"₹" );

    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Ingrediant_name,Ingrediant_price;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );

            Ingrediant_name = itemView.findViewById( R.id.ingredient_name );
            Ingrediant_price = itemView.findViewById( R.id.ingredient_price );
        }
    }
}
