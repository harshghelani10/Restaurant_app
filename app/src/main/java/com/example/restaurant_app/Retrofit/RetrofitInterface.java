package com.example.restaurant_app.Retrofit;

import com.example.restaurant_app.ForgotResult;
import com.example.restaurant_app.LoginResult;
import com.example.restaurant_app.model.Addtocart;
import com.example.restaurant_app.model.CategoryResponse;
import com.example.restaurant_app.model.Subcategory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitInterface {

    //Admin Login
    @POST("/admin/login")
    Call<LoginResult> executeAdminLogin(@Body HashMap<String,String>map);

    //User register & login
    @PUT("/all/register")
    Call<Void> executeRegister(@Body HashMap<String, String> map);

    @POST("/all/login")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> map);

    //Waiter register & login
    @PUT("/waiter/addwaiter")
    Call<Void> executeWaiterRegister(@Body HashMap<String, String> map);

    @POST("/waiter/login")
    Call<LoginResult> executeWaiterLogin(@Body HashMap<String, String> map);

    //Cook register & login
    @PUT("/cook/addcook")
    Call<Void> executeCookRegister(@Body HashMap<String, String> map);

    @POST("/cook/login")
    Call<LoginResult> executeCookLogin(@Body HashMap<String, String> map);

    //Manager register & login
    @PUT("/manage/addmanager")
    Call<Void> executeAddManagerRegister(@Body HashMap<String ,String>map);

    @POST("/manage/login")
    Call<LoginResult> executeManagerLogin(@Body HashMap<String ,String>map);

    //User Forgot password
    @POST("/auth/forgot")
    Call<ForgotResult> executeforgotpass(@Body HashMap<String,String>map);

//    @GET("/feed/getposts")
//    Call<MenuResult> getMenu();

//    @GET("/menu/menues")
//    Call<Subcategory> getSubCategory();
    @GET("/menu/menu/6076527faf90d151f4d513f1")
    Call<Subcategory> getSubCategory();
    //fetch category
    @GET("/categorypost/categories")
    Call<CategoryResponse> getCategory();

    //add to cart
    @POST("/cart/addtocart/60766079dda0dc0dd007089c")
    Call<Addtocart> executecart();

}
