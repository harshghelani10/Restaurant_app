
package com.example.restaurant_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Subcategory {

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("products")
        @Expose
        private List<Product> products = null;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

    }