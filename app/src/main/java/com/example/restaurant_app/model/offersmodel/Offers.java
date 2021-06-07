
package com.example.restaurant_app.model.offersmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Offers {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("coupons")
    @Expose
    private List<Coupon> coupons = null;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

}
