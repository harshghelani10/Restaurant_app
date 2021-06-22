
package com.example.restaurant_app.model.reservationmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Reservation {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("createdTable")
    @Expose
    private CreatedTable createdTable;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CreatedTable getCreatedTable() {
        return createdTable;
    }

    public void setCreatedTable(CreatedTable createdTable) {
        this.createdTable = createdTable;
    }

}
