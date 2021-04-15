package com.example.textfile;

import android.content.Intent;
import android.os.Bundle;

import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.textfile.DataParser.AsyncTaskRetrofitGet;
import com.example.textfile.DataParser.AsyncTaskRetrofitPost;
import com.example.textfile.Dialog.CustomDialogFragment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        //FileClassの初期セットアップ
        fileClass = new FileClass(getApplicationContext());

        FileClassDir fileClassDir = new FileClassDir(getApplicationContext());
        List<String> files = fileClassDir.getAllFiles();

//        try {
//            fileClassDir.createNewFile("{taxt:1}".getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //音声認識
        Button button = findViewById(R.id.start);
        button.setOnClickListener(view -> speech());

        //api retrofit asynctask test
        String baseUrl = "https://www.reina-ryu-f.xyz/demo/dicomo/";

        AsyncTaskRetrofitGet sendGet = new AsyncTaskRetrofitGet(baseUrl);
        sendGet.execute("");

        AsyncTaskRetrofitPost sendPost = new AsyncTaskRetrofitPost(baseUrl);
        sendPost.execute("");

        Button button1 = findViewById(R.id.dialoginput);
        button1.setOnClickListener(view -> dialog());

    }

    private void dialog(){
//        SampleDialogFragment dialog = new SampleDialogFragment();
//        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
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