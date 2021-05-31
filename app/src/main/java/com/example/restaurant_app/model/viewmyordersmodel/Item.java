
package com.example.restaurant_app.model.viewmyordersmodel;

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
    private String notes;
    @SerializedName("product_id")
    @Expose
    private ProductId productId;
    @SerializedName("ingredientId")
    @Expose
    private IngredientId ingredientId;
    @SerializedName("ingredientPrice")
    @Expose
    private Integer ingredientPrice;
    @SerializedName("qty")
    @Expose
    private Integer qty;
    @SerializedName("categoryId")
    @Expose
    private CategoryId categoryId;
    @SerializedName("priority")
    @Expose
    private Integer priority;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public IngredientId getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(IngredientId ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(Integer ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public CategoryId getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryId categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
