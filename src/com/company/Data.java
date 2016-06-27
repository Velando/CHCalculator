package com.company;

import com.company.Ancient;
import com.company.SaveDecoder;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Philip on 27-06-2016.
 */
public class Data {
    private static Data instance = null;

    protected Data(){

    }

    public static Data getInstance(){
        if(instance == null){
            instance = new Data();
        }
        return instance;
    }

    private List<Ancient> oldLevels;
    private List<Ancient> newLevels;
    private SaveDecoder decoder;
    private Calculator calc;

    public void init(String code){
        decoder = new SaveDecoder(code);
        decoder.getAncients();
        oldLevels = new Ancients().getAncients();
        calc = new Calculator(decoder.getHZE(), decoder.getTP());
        newLevels = calc.getNewLevels();

    }

    public List<Ancient> getOldLevels(){
        return oldLevels;
    }

    public List<Ancient> getNewLevels(){
        return newLevels;
    }

    public SaveDecoder getDecoder(){
        return decoder;
    }

    public JSONObject getAncients(){
        return decoder.getAncients();
    }

    public String[] getColumnNames(){
        String[] s =  {"Ancient", "Current level", "Suggested level", "Difference"};
        return s;
    }

    public Object[][] getColumnData(){
        Object[][] data = new Object[oldLevels.size()][4];
        for(int i = 0; i < oldLevels.size(); i++){
            Ancient oldL = oldLevels.get(i);
            Ancient newL = newLevels.get(i);
            data[i][0] = oldL.getName();
            data[i][1] = oldL.getLevel();
            data[i][2] = newL.getLevel();
            data[i][3] = newL.getLevel()-oldL.getLevel();
        }
        return data;
    }

}
