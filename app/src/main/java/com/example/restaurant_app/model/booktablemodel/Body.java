
package com.example.restaurant_app.model.booktablemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Body {

    @SerializedName("table")
    @Expose
    private String table;

    public Body(String table_value) {
        this.table = table_value;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

}
