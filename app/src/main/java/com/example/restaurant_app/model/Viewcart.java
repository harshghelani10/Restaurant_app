
package com.example.restaurant_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Viewcart {

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
