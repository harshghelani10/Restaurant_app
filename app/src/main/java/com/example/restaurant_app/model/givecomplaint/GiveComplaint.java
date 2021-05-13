
package com.example.restaurant_app.model.givecomplaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GiveComplaint {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("complaint")
    @Expose
    private Complaint complaint;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Complaint getComplaint() {
        return complaint;
    }

    public void setComplaint(Complaint complaint) {
        this.complaint = complaint;
    }

}
