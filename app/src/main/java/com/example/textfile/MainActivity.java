package com.example.textfile;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "example.txt";

    EditText mEditText;
    FileClass fileClass;

    private static final int REQUEST_CODE = 12345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //画面の上にあるアクションバーを隠す（隠さなくても良い）
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //start stop は　GUIで設定しました丸
        mEditText = findViewById(R.id.Text);
        fileClass = new FileClass(getApplicationContext());

        Button button = findViewById(R.id.start);
        button.setOnClickListener(view -> speech());

        //retrofitライブラリの仕様？
        //ApiServiceでは行き先を分割して指定する
        //Countでは受け取った情報を加工する処理？

        String baseUrl = "https://www.reina-ryu-f.xyz/dicomo/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();

        ApiService service = retrofit.create(ApiService.class);
        Call<Count> call = service.getCount();
        call.enqueue(new Callback<Count>() {
            @Override
            public void onResponse(Call<Count> call, Response<Count> response) {
                Log.d("debug", response.body().toString());
                Log.d("debug", response.body().getCount());
            }

            @Override
            public void onFailure(Call<Count> call, Throwable t) {
                Log.e("debug", t.getMessage());
            }
        });




    }

    private void speech(){
        //音声認識用のインテントを作成
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //認識する言語を指定（この場合は日本語）
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPANESE.toString());
        //認識する候補数の指定
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        //音声認識時に表示する案内を設定
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "話してください");
        //音声認識を開始
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            //data から音声認識の結果を取り出す（リスト形式で）
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (result.size() > 0) {
                Log.i("tag", result.get(0));
                mEditText.setText(result.get(0));

            } else {
                Log.e("error", "error i don't know");
            }
        }
    }



    public void save(View v) {
        String text = mEditText.getText().toString();
        try {
            fileClass.save(text.getBytes());
            mEditText.getText().clear();

            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileClass.outputStream != null) {
                try {
                    fileClass.outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void load(View v) {
        FileInputStream fis = null;

        try {
            mEditText.setText(fileClass.load());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileClass.inputStream != null) {
                try {
                    fileClass.inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}