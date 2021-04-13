package com.example.restaurant_app.Retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MenuResult {

    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

}
//public class MenuResult {
//    @SerializedName("id")
//    private String id;
//
//    @SerializedName("name")
//    private String name;
//
//    @SerializedName("description")
//    private String description;
//
//    @SerializedName("price")
//    private String price;
//
//    @SerializedName("imageUrl")
//    private String imageUrl;
//
//    @SerializedName("availability")
//    private String availability;
//
//    public String getAvailability() {
//        return availability;
//    }
//
//    public void setAvailability(String availability) {
//        this.availability = availability;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//}
