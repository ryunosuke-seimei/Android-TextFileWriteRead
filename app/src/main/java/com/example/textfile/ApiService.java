package com.example.textfile;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService{
    @GET("test.json")
    Call<Count> getCount();
}