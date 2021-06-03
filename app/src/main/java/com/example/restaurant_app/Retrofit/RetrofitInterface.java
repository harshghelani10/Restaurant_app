package com.example.restaurant_app.Retrofit;

import com.example.restaurant_app.ForgotResult;
import com.example.restaurant_app.model.CategoryResponse;
import com.example.restaurant_app.model.Feedback;
import com.example.restaurant_app.model.Subcategory;
import com.example.restaurant_app.model.addtocartmodel.AddtoCart;
import com.example.restaurant_app.model.allmenuitems.AllMenuItems;
import com.example.restaurant_app.model.deletecartmodel.DeleteCart;
import com.example.restaurant_app.model.getingrediantmodel.GetIngrdiant;
import com.example.restaurant_app.model.givecomplaint.GiveComplaint;
import com.example.restaurant_app.model.loginallmodel.LoginResult;
import com.example.restaurant_app.model.makeordermodel.MakeOrder;
import com.example.restaurant_app.model.parcelordermodel.ParcelOrder;
import com.example.restaurant_app.model.vcomplaintmodel.UserComplaint;
import com.example.restaurant_app.model.viewcartmodel.ViewCart;
import com.example.restaurant_app.model.viewmyordersmodel.ViewMyOrders;
import com.example.restaurant_app.model.vieworderhistorymodel.ViewOrderHistory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface   RetrofitInterface {

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

    //fetch sub menu all items
    @GET("/menu/menues")
    Call<AllMenuItems> getallmenu();


    //Add To Cart
    @POST("/cart/addtocart/{path1}")
    Call<AddtoCart> executecart(@Path("path1") String path,
                                @Header("Authorization") String auth,
                                @Body com.example.restaurant_app.model.Body.Body body);

    //view cart
    @GET("/cart/getcart")
    Call<ViewCart> getCart(@Header("Authorization") String auth);

    //Empty Cart
    @DELETE("/cart/emptycart")
    Call<DeleteCart> deleteCart(@Header("Authorization") String auth);

    //User Make Order
    @PUT("/order/makeorder")
    Call<MakeOrder> makeOrder(@Header("Authorization") String auth,
                              @Body HashMap<String, String> map);

    //User give feedback
    @POST("/feedback/feedback")
    Call<Feedback> giveFeedback(@Header("Authorization") String auth,
                                @Body HashMap<String, String> map);


    //user view order
    @POST("/order/current")
    Call<ViewMyOrders> viewmyorders(@Header("Authorization") String auth);

    //view user complaint
    @GET("/complaint/complaints")
    Call<UserComplaint> viewComplaint(@Header("Authorization") String auth);


    //Give complaint
    @POST("/complaint/complaint/{path2}")
    Call<GiveComplaint> giveComplaint(@Path(value = "path2") String path,
                                      @Header("Authorization") String auth,
                                      @Body com.example.restaurant_app.model.givecomplaint.Body body);

    //View Order History
    @GET("/order/myorders")
    Call<ViewOrderHistory> viewOrderHistory(@Header("Authorization") String auth);

    //parcel your order
    @PUT("/order/parcel/makeorder")
    Call<ParcelOrder> parcelYourOrder(@Header("Authorization") String auth,
                                      @Body HashMap<String, String> map);

    //get all ingrediant
    @GET("/ingredients/getIngredients")
    Call<GetIngrdiant> getIngredient();

}

