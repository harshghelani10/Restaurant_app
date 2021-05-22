
package com.example.restaurant_app.model.viewmyordersmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ViewMyOrders {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("order")
    @Expose
    private Order order;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
