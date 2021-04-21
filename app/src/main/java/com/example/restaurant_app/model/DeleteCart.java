
package com.example.restaurant_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteCart {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("deletedCart")
    @Expose
    private DeletedCart deletedCart;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DeletedCart getDeletedCart() {
        return deletedCart;
    }

    public void setDeletedCart(DeletedCart deletedCart) {
        this.deletedCart = deletedCart;
    }

}
