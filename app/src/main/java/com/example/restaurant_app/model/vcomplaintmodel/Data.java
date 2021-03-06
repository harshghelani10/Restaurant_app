
package com.example.restaurant_app.model.vcomplaintmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("otps")
    @Expose
    private List<Object> otps = null;
    @SerializedName("feedbacks")
    @Expose
    private List<String> feedbacks = null;
    @SerializedName("activerole")
    @Expose
    private String activerole;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("orders")
    @Expose
    private List<String> orders = null;
    @SerializedName("categoryId")
    @Expose
    private Object categoryId;
    @SerializedName("complaints")
    @Expose
    private List<Complaint> complaints = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("cart")
    @Expose
    private Cart cart;
    @SerializedName("posts")
    @Expose
    private List<Object> posts = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("created_At")
    @Expose
    private String createdAt;
    @SerializedName("updated_At")
    @Expose
    private String updatedAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Object> getOtps() {
        return otps;
    }

    public void setOtps(List<Object> otps) {
        this.otps = otps;
    }

    public List<String> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<String> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public String getActiverole() {
        return activerole;
    }

    public void setActiverole(String activerole) {
        this.activerole = activerole;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Object> getPosts() {
        return posts;
    }

    public void setPosts(List<Object> posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
