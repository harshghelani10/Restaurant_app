
package com.example.restaurant_app.model.booktablemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookTable {

    @SerializedName("message")
    @Expose
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
