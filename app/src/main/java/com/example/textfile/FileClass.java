package com.example.textfile;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileClass {

    protected String FILE_NAME = "example.txt";
    private File file;

    public FileClass(Context context){
        File path = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        file = new File(path, FILE_NAME);
    }

    public void save(byte[] text) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(text);
        outputStream.flush();
    }
}
