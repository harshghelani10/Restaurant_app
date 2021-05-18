
package com.example.restaurant_app.model.viewcartmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ViewCart {

    @SerializedName("Your_Cart")
    @Expose
    private YourCart yourCart;

    public YourCart getYourCart() {
        return yourCart;
    }

    public void setYourCart(YourCart yourCart) {
        this.yourCart = yourCart;
    }

}
