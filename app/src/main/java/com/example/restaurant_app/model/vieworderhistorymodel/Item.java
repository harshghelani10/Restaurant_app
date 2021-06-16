
package com.example.restaurant_app.model.vieworderhistorymodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("paymentstatus")
    @Expose
    private String paymentstatus;
    @SerializedName("progress")
    @Expose
    private String progress;
    @SerializedName("ToKitchen")
    @Expose
    private Boolean toKitchen;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("notes")
    @Expose
    private Object notes;
    @SerializedName("product_id")
    @Expose
    private ProductId productId;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("categoryId")
    @Expose
    private Object categoryId;
    @SerializedName("productPrice")
    @Expose
    private Double productPrice;
    @SerializedName("total")
    @Expose
    private Double total;

    public String getPaymentstatus() {
        return paymentstatus;
    }

    public void setPaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Boolean getToKitchen() {
        return toKitchen;
    }

    public void setToKitchen(Boolean toKitchen) {
        this.toKitchen = toKitchen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

//    public Integer getQty() {
//        return qty;
//    }
//
//    public void setQty(Integer qty) {
//        this.qty = qty;
//    }
//
//    public Integer getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Integer priority) {
//        this.priority = priority;
//    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

//    public Integer getProductPrice() {
//        return productPrice;
//    }
//
//    public void setProductPrice(Integer productPrice) {
//        this.productPrice = productPrice;
//    }

//    public Integer getTotal() {
//        return total;
//    }
//
//    public void setTotal(Integer total) {
//        this.total = total;
//    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    //    public Double getQty() {
//        return qty;
//    }
//
//    public void setQty(Double qty) {
//        this.qty = qty;
//    }
//
//    public Double getPriority() {
//        return priority;
//    }
//
//    public void setPriority(Double priority) {
//        this.priority = priority;
//    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
