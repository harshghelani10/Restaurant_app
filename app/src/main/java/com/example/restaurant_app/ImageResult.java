package com.example.restaurant_app;

import java.util.List;

public class ImageResult {
    private String id;
    private String name;
    private String imageUrl;
    private String description;
    private String price;
    List<ImageResult> imageResultList;

    public List<ImageResult> getImageResultList() {
        return imageResultList;
    }

    public void setImageResultList(List<ImageResult> imageResultList) {
        this.imageResultList = imageResultList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
