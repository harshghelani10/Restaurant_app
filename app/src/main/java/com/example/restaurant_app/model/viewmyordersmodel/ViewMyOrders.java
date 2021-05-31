
package com.example.restaurant_app.model.viewmyordersmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ViewMyOrders {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orders")
    @Expose
    private List<Order> orders = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
