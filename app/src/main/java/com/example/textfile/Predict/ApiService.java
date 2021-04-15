package com.example.textfile.Predict;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService{

    @POST("predict")
    Call<Predict> sendPost(@Body HashMap<String, String> body);

    @POST("test")
    Call<Void> sendTest(@Body HashMap<String, String> body);

}