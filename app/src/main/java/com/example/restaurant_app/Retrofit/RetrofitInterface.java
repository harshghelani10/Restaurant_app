package com.example.restaurant_app.Retrofit;

import com.example.restaurant_app.ForgotResult;
import com.example.restaurant_app.LoginResult;
import com.example.restaurant_app.model.Addtocart;
import com.example.restaurant_app.model.CategoryResponse;
import com.example.restaurant_app.model.DeleteCart;
import com.example.restaurant_app.model.Subcategory;
import com.example.restaurant_app.model.ViewCart;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    //Admin Login
    @POST("/admin/login")
    Call<LoginResult> executeAdminLogin(@Body HashMap<String, String> map);

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
    Call<Void> executeAddManagerRegister(@Body HashMap<String, String> map);

    @POST("/manage/login")
    Call<LoginResult> executeManagerLogin(@Body HashMap<String, String> map);

    //User Forgot password
    @POST("/auth/forgot")
    Call<ForgotResult> executeforgotpass(@Body HashMap<String, String> map);

    //fetch category
    @GET("/categorypost/categories")
    Call<CategoryResponse> getCategory();

    //fetch menu
    @GET("/menu/menu/{path}")
    Call<Subcategory> getSubCategory(@Path(value = "path") String path);

    //Add To Cart
    @POST("/cart/addtocart/{path1}")
    Call<Addtocart> executecart(@Path("path1") String path,
                                @Header("Authorization") String auth,
                                @Body com.example.restaurant_app.model.Body.Body body);

    //view cart
    @GET("/cart/getcart")
    Call<ViewCart> getCart(@Header("Authorization") String auth);

    //Empty Cart
    @DELETE("/cart/emptycart")
    Call<DeleteCart> deleteCart(@Header("Authorization") String auth);
}
