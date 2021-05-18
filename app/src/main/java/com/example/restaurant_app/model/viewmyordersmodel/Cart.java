
package com.example.restaurant_app.model.viewmyordersmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Cart implements Serializable
{

    @SerializedName("items")
    @Expose
    private List<Object> items = null;
    private final static long serialVersionUID = 4383088025532872272L;

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

}
