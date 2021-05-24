
package com.example.restaurant_app.model.parcelordermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ParcelOrder {

    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("userDetails")
    @Expose
    private UserDetails userDetails;
    @SerializedName("Order")
    @Expose
    private List<Order> order = null;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

}
