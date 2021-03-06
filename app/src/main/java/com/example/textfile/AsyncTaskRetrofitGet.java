package com.example.textfile;

import android.os.AsyncTask;
import android.util.Log;

import com.example.textfile.DataParser.Count;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AsyncTaskRetrofitGet extends AsyncTask<String, Void, String> {

    private String BaseUrl = "";
    Retrofit retrofit;

    public AsyncTaskRetrofitGet(String Base_url){
        BaseUrl = Base_url;
        retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected String doInBackground(String... params) {
        ApiService service = retrofit.create(ApiService.class);
        Call<Count> call = service.getCount();
        //非同期通信
        call.enqueue(new Callback<Count>() {
            @Override
            public void onResponse(Call<Count> call, Response<Count> response) {
                Log.d("debug", response.body().getCount());
            }

            @Override
            public void onFailure(Call<Count> call, Throwable t) {
                Log.e("debug", t.getMessage());
            }
        });
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
