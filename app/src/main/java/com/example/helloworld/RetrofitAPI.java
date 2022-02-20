package com.example.helloworld;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {
    @POST("generate")

    Call<DataModal> createPost(@Body DataModal dataModal);
}
