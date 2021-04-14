package com.example.textfile;

import com.example.textfile.DataParser.Count;
import com.example.textfile.DataParser.Post;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService{
    @GET("test.json")
    Call<Count> getCount();

    @POST("test.php")
    Call<Post> sendPost(@Body HashMap<String, String> body);
}