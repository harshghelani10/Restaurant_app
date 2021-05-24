
package com.example.restaurant_app.model.parcelordermodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserDetails {

    @SerializedName("grandTotal")
    @Expose
    private Integer grandTotal;
    @SerializedName("paymentMethod")
    @Expose
    private String paymentMethod;
    @SerializedName("OrderIs")
    @Expose
    private String orderIs;
    @SerializedName("complaints")
    @Expose
    private List<Object> complaints = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public Integer getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Integer grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderIs() {
        return orderIs;
    }

    public void setOrderIs(String orderIs) {
        this.orderIs = orderIs;
    }

    public List<Object> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Object> complaints) {
        this.complaints = complaints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
