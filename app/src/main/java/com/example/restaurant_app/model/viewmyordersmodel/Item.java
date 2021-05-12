
package com.example.restaurant_app.model.viewmyordersmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("progress")
    @Expose
    private String progress;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("product_id")
    @Expose
    private ProductId productId;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("priority")
    @Expose
    private Integer priority;
    @SerializedName("productPrice")
    @Expose
    private Integer productPrice;
    @SerializedName("total")
    @Expose
    private Integer total;

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
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

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
