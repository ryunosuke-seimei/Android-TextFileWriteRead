package com.example.textfile.DataParser;

import android.os.AsyncTask;
import android.util.Log;

import com.example.textfile.DataParser.ApiService;
import com.example.textfile.DataParser.Post;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AsyncTaskRetrofitPost extends AsyncTask<String, Void, String> {

    private String BaseUrl = "";
    Retrofit retrofit;

    public AsyncTaskRetrofitPost(String Base_url){
        BaseUrl = Base_url;
        retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap<String, String> map = new HashMap<>();
        map.put("count", "counter");
        map.put("dog", "dog");

        ApiService service = retrofit.create(ApiService.class);
        Call<Post> call = service.sendPost(map);
        //非同期通信
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.d("debug", response.body().getCount());
                Log.d("debug", response.body().getDog());

            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Log.e("error", t.getMessage());
            }

        });
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
