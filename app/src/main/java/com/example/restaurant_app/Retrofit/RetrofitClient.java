package com.example.restaurant_app.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    private static String BASE_URL = "http://192.168.0.133:8080";
    private static Retrofit retrofit = null;


//    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//    public void setHttpLoggingInterceptor(HttpLoggingInterceptor httpLoggingInterceptor) {
//        this.httpLoggingInterceptor = httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//    }
//
//    OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .build();


    public static Retrofit getInstance() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
