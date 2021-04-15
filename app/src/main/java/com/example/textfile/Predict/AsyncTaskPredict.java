package com.example.textfile.Predict;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Switch;

import com.example.textfile.Predict.ApiService;
import com.example.textfile.Predict.Predict;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AsyncTaskPredict extends AsyncTask<String, Void, List<Boolean>> {

    private String BaseUrl = "";
    Retrofit retrofit;

    List<Switch> SwitchList;

    public AsyncTaskPredict(String Base_url, List<Switch> switchList){
        BaseUrl = Base_url;
        retrofit = new Retrofit.Builder().baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SwitchList = switchList;
    }

    @Override
    protected List<Boolean> doInBackground(String... strings) {
        HashMap<String, String> map = new HashMap<>();
        map.put("text", strings[0]);
        ApiService service = retrofit.create(ApiService.class);
        Call<Predict> call = service.sendPost(map);
        Predict result = null;

        try{
            result = call.execute().body();
        }catch (IOException e){

        }
        return result.getResult();
    }

    @Override
    protected void onPostExecute(List<Boolean> result) {
        super.onPostExecute(result);
        Log.d("Influenza", result.get(0).toString());
        Log.d("Diarrhea", result.get(1).toString());
        Log.d("Hayfever", result.get(2).toString());
        Log.d("Cough", result.get(3).toString());
        Log.d("Headache", result.get(4).toString());
        Log.d("Fever", result.get(5).toString());
        Log.d("Runnynose", result.get(6).toString());
        Log.d("Cold", result.get(7).toString());
        for (int i=0;i<8;i++){
            SwitchList.get(i).setChecked(result.get(i));
        }
    }
}

//http://35.201.198.255:8080/predict