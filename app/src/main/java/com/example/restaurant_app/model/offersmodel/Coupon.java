
package com.example.restaurant_app.model.offersmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Coupon {

    @SerializedName("Percent")
    @Expose
    private Object percent;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("expireDate")
    @Expose
    private String expireDate;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("isPercent")
    @Expose
    private Boolean isPercent;
    @SerializedName("ccode")
    @Expose
    private String ccode;
    @SerializedName("created_At")
    @Expose
    private String createdAt;
    @SerializedName("updated_At")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Object getPercent() {
        return percent;
    }

    public void setPercent(Object percent) {
        this.percent = percent;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsPercent() {
        return isPercent;
    }

    public void setIsPercent(Boolean isPercent) {
        this.isPercent = isPercent;
    }

    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
