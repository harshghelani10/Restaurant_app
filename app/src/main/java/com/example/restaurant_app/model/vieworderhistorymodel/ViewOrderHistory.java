
package com.example.restaurant_app.model.vieworderhistorymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewOrderHistory {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

}
