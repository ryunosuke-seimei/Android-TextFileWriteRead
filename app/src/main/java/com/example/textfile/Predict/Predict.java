package com.example.textfile.Predict;

import java.util.ArrayList;
import java.util.List;

public class Predict {
    public String Influenza;
    public String Diarrhea;
    public String Hayfever;
    public String Cough;
    public String Headache;
    public String Fever;
    public String Runnynose;
    public String Cold;

    public Boolean getInfluenza(){
        Boolean result;
        if (Influenza.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public Boolean getDiarrhea(){
        Boolean result;
        if (Diarrhea.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public Boolean getHayfever(){
        Boolean result;
        if (Hayfever.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public Boolean getCough(){
        Boolean result;
        if (Cough.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public Boolean getHeadache(){
        Boolean result;
        if (Headache.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public Boolean getFever(){
        Boolean result;
        if (Fever.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public Boolean getRunnynose(){
        Boolean result;
        if (Runnynose.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }
    public Boolean getCold(){
        Boolean result;
        if (Cold.equals("yes")){
            result = true;
        }else{
            result = false;
        }
        return result;
    }

    public List<Boolean> getResult(){
        List<Boolean> list = new ArrayList<Boolean>();

        list.add(getInfluenza());
        list.add(getDiarrhea());
        list.add(getHayfever());
        list.add(getCough());
        list.add(getHeadache());
        list.add(getFever());
        list.add(getRunnynose());
        list.add(getCold());
        
        return list;
    }
}

