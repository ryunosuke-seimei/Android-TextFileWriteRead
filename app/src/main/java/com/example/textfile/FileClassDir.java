package com.example.textfile;


import android.content.Context;
import android.icu.util.Output;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class FileClassDir {

    String parent_path;
    int count=0;

    public FileClassDir(Context context){
        parent_path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).toString();
    }

    public List<String> getAllFiles(){
        File[] files = new File(parent_path).listFiles();
        count = files.length;
        List<String> file_names = new ArrayList<String>();

        if (files != null){
            for(int i = 0; i < files.length; i++){
                Log.i("file", files[i].getName());
                file_names.add(files[i].getName());
            }
        }
        return file_names;
    }
    public void createNewFile(byte[] text) throws IOException{
        String FILE_NAME = "example" + String.valueOf(count) + ".text";
        File file = new File(parent_path, FILE_NAME);
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(text);
        outputStream.flush();
    }
    public void saveFile(String file_name, byte[] text) throws IOException {

        OutputStream outputStream = new FileOutputStream(parent_path + "/" + file_name);
        outputStream.write(text);
        outputStream.flush();
    }
    public String openFile(String file_name) throws IOException{
        InputStream inputStream = new FileInputStream(parent_path + "/" + file_name);
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String text;

        while ((text = br.readLine()) != null) {
            sb.append(text).append("\n");
        }
        return sb.toString();
    }


}
