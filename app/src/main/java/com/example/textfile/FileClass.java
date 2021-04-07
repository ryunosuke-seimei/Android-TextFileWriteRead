package com.example.textfile;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileClass {

    protected String FILE_NAME = "example.txt";
    private File file;

    FileOutputStream outputStream;
    FileInputStream inputStream;

    public FileClass(Context context){
        File path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        file = new File(path, FILE_NAME);
    }

    public void save(byte[] text) throws IOException {
        outputStream = new FileOutputStream(file);
        outputStream.write(text);
        outputStream.flush();
    }
    public String load() throws IOException{
        inputStream = new FileInputStream(file);
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
