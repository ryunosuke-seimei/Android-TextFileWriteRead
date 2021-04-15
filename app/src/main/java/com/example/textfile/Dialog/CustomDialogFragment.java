package com.example.textfile.Dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.textfile.DialogActivity;
import com.example.textfile.FileClassDir;
import com.example.textfile.Predict.AsyncTaskPredict;
import com.example.textfile.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class CustomDialogFragment extends DialogFragment {

    private static final int REQUEST_CODE = 12222;
    private static final int REQUEST_CODE_2 = 12223;
    EditText input_text;
    TextView speech_text;

    List<Switch> switchList = new ArrayList<Switch>();

    Boolean first_flag = true;
    String FileName = "";
    String Value = "";


    public CustomDialogFragment(){
        first_flag = true;
        Log.d("start dialog", "start");
    }
    public CustomDialogFragment(String file_name, String value){
        first_flag = false;
        FileName = file_name;
        Value = value;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

//        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_sample, null);
        Button btn = (Button) view.findViewById(R.id.dialoginput);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speech();
            }
        });

        input_text =  view.findViewById(R.id.Text);
        speech_text = view.findViewById(R.id.SpeechText);

        switchList.add((Switch) view.findViewById(R.id.influenza));
        switchList.add((Switch) view.findViewById(R.id.diarrhea));
        switchList.add((Switch) view.findViewById(R.id.hayfever));
        switchList.add((Switch) view.findViewById(R.id.cough));
        switchList.add((Switch) view.findViewById(R.id.headache));
        switchList.add((Switch) view.findViewById(R.id.fever));
        switchList.add((Switch) view.findViewById(R.id.ruunynose));
        switchList.add((Switch) view.findViewById(R.id.cold));

        if (!first_flag){
            Value = Value.replaceAll("[{}]","");
            String[] values = Value.split(",");
            input_text.setText(values[0].split(":")[1]);
            speech_text.setText(values[1].split(":")[1]);

            String switchValue = values[2].split(":")[1];
            String[] switchValues = switchValue.split(";");
//            Log.e("value", switchValue);
//            Log.e("value", String.join(":",switchValues));

            for (int i=0;i<7;i++){
                Log.d("value", switchValues[i]);
                if (switchValues[i].equals("1")){
                    switchList.get(i).setChecked(true);
                }
            }
        }
        builder.setView(view)
                .setTitle("dialog page")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                    }
                });

        return builder.create();
    }

    private void speech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.JAPANESE.toString());
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "please speech");
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            if (result.size() > 0) {
                Log.d("Custom dialog", result.get(0));

                String result_text = result.get(0);
                input_text.setText(result_text);
                speech_text.setText(result_text);

                String baseUrl = "http://35.201.198.255:8080/";
                AsyncTaskPredict sendPredict = new AsyncTaskPredict(baseUrl, switchList);
                sendPredict.execute(result_text);

            } else {
                Log.e("error", "error i don't know");
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // onPause でダイアログを閉じる場合
    }

    @Override
    public void onStop() {
        super.onStop();
        String result = "";

        String input = input_text.getText().toString();
        String speech = speech_text.getText().toString();

        String switch_result = "";
        for (int i=0;i<7;i++){
            if (switchList.get(i).isChecked()){
                switch_result += "1;";
            }else{
                switch_result += "0;";
            }
        }
        result += "{";
        result += "input:" + input + ",";
        result += "speech:" + speech + ",";
        result += "values:" + switch_result + "}";

        Log.d("result", result);


        DialogActivity callingActivity = (DialogActivity) getActivity();
        callingActivity.onReturnValue(result, first_flag, FileName);

        Log.d("destroy", "destroy");
    }


}


