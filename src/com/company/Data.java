package com.company;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private List<Outsider> outsiders;
    private SaveDecoder decoder;
    private Calculator calc;

    public void init(String code){
        if(decoder == null)
            decoder = new SaveDecoder(code);
        oldLevels = new Ancients().getAncients();
        calc = new Calculator(decoder.getHZE(), decoder.getTP());
        newLevels = calc.getNewLevels();
        outsiders = setUpOutsiders(decoder.getOutsiders());
    }

    public List<Ancient> getOldLevels(){
        return oldLevels;
    }

    public List<Ancient> getNewLevels(){
        return newLevels;
    }

    public SaveDecoder getDecoder(String code){
        decoder = new SaveDecoder(code);
        return decoder;
    }

    public SaveDecoder getDecoder(){
        return decoder;
    }

    public JSONObject getAncients(){
        return decoder.getAncients();
    }

    public String[] getColumnNames(){
        String[] s =  {"Ancient", "Current level", "Suggested level", "Difference", "Cost"};
        return s;
    }

    public Object[][] getColumnData(){
        Object[][] data = new Object[oldLevels.size()+1][5];
        double totalCost = 0;
        for(int i = 0; i < oldLevels.size(); i++){
            Ancient oldL = oldLevels.get(i);
            Ancient newL = newLevels.get(i);
            data[i][0] = oldL.getName();
            data[i][1] = oldL.getLevel();
            data[i][2] = newL.getLevel();
            data[i][3] = newL.getLevel()-oldL.getLevel();
            double newCost = calc.calcCost(oldL,getChorLevel(),newL.getLevel());
            data[i][4] = newCost;
            totalCost+=newCost;
        }
        data[oldLevels.size()][0] = "Total";
        data[oldLevels.size()][4] = totalCost;
        return data;
    }

    private List<Outsider> setUpOutsiders(JSONObject obj){
        ArrayList<Outsider> list = new ArrayList<>();

        for(Object o : obj.names()){
            String id = o.toString();
            list.add(new Outsider(getOutsiderName(id), Integer.parseInt(id), getOutsiderLevel(obj, id)));
        }



        return list;
    }

    public double getChorLevel(){
        return getDecoder().getOutsiders().getJSONObject("2").getDouble("level");
    }

    private double getOutsiderLevel(JSONObject obj, String id){
        String l = obj.getJSONObject(id).get("level").toString();
        if(l.contains("e")){
            String[] arr = l.split("e");
            Double d1 = Double.parseDouble(arr[0]);
            Double d2 = Double.parseDouble(arr[1]);
            Double res = d1*(Math.pow(10,d2));
            return res;
        }

        if(l.contains(".")) {
            return Double.parseDouble(l.split("\\.")[0]);
        }
        return Double.parseDouble(l);
    }

    private String getOutsiderName(String id){
        String outsiderName = "";
        switch (id){
            case "1" : outsiderName = "Xyliqil";
                break;
            case "2" : outsiderName = "Chor'gorloth";
                break;
            case "3" : outsiderName = "Phandoryss";
                break;
            case "4" : outsiderName = "Borb";
                break;
            case "5" : outsiderName = "Ponyboy";
                break;
        }
        return outsiderName;
    }

}
