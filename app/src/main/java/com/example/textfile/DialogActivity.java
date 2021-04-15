package com.example.textfile;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.textfile.Dialog.CustomDialogFragment;

import java.io.IOException;
import java.util.List;

public class DialogActivity extends AppCompatActivity {

    ListView listView;
    List<String> files;

    FileClassDir fileClassDir;
    ArrayAdapter<String> arrayAdapter;

    private static final int REQUEST_CODE_2 = 12223;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        //画面の上にあるアクションバーを隠す（隠さなくても良い）
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        fileClassDir = new FileClassDir(getApplicationContext());
        files = fileClassDir.getAllFiles();

        Button button1 = findViewById(R.id.dialoginput);
        button1.setOnClickListener(view -> dialog());

        listView = findViewById(R.id.ListView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView =(ListView)parent;
                String item=(String)listView.getItemAtPosition(position);
//                Toast.makeText(getApplicationContext(), "Click: "+item, Toast.LENGTH_SHORT).show();
                dialog_edit(item);
            }
        });
        arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        listView.setAdapter(arrayAdapter);
    }

    private void dialog(){
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }
    private void dialog_edit(String file_name){
        String value = "";
        try {
            value = fileClassDir.openFile(file_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomDialogFragment dialog = new CustomDialogFragment(file_name, value);
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    public void onReturnValue(String value, Boolean flag, String file_name) {

       Log.d("fin", value);
       Log.d("fin",String.valueOf(flag));
       Log.d("fin",file_name);
       if (flag){
           try {
               fileClassDir.createNewFile(value.getBytes());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else{
           try{
               fileClassDir.saveFile(file_name, value.getBytes());
           } catch (IOException e) {
               e.printStackTrace();
           }
       }
        files = fileClassDir.getAllFiles();
        arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, files);
        listView.setAdapter(arrayAdapter);
    }



}