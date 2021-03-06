
package com.example.restaurant_app.model.givecomplaint;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Complaint {

    @SerializedName("replyId")
    @Expose
    private List<Object> replyId = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("userId")
    @Expose
    private String userId;

    public List<Object> getReplyId() {
        return replyId;
    }

    public void setReplyId(List<Object> replyId) {
        this.replyId = replyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
