package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Philip on 26-06-2016.
 */
public class Ancients {
    private Map<String, String> map = new HashMap<>();
    private ArrayList<Ancient> ancients = new ArrayList<>();

    public Ancients(){
        fillMap();
        createAncients();
    }

    public ArrayList<Ancient> getAncients(){
        return ancients;
    }

    private String getAncientName(String id){
        return map.get(id);
    }

    private void createAncients() {
        JSONObject obj = Main.DECODER.getAncients();

        for(Object o : obj.names()){
            ancients.add(new Ancient(getAncientName(o.toString()),Integer.parseInt(o.toString()),getAncientLevel(obj, o.toString())));
        }
        Collections.sort(ancients, Ancient.getComp());
    }

    private double getAncientLevel(JSONObject obj, String id){
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

    private void fillMap(){
        map.put("20","Vaagur, Ancient of Impatience");
        map.put("22","Chawedo, Ancient of Agitation");
        map.put("24","Berserker, Ancient of Rage");
        map.put("27","Energon, Ancient of Battery Life");
        map.put("26","Kleptos, Ancient of Thieves");
        map.put("23","Hecatoncheir, Ancient of Wallops");
        map.put("19","Fragsworth, Ancient of Wrath");
        map.put("9","Mimzee, Ancient of Riches");
        map.put("17","Chronos, Ancient of Time");
        map.put("13","Atman, Ancient of Souls");
        map.put("11","Dogcog, Ancient of Thrift");
        map.put("18","Bubos, Ancient of Diseases");
        map.put("5","Siyalatas, Ancient of Abandon");
        map.put("15","Bhaal, Ancient of Murder");
        map.put("16","Morgulis, Ancient of Death");
        map.put("25","Sniperino, Ancient of Accuracy");
        map.put("14","Dora, Ancient of Discovery");
        map.put("21","Kumawakamaru, Ancient of Shadows");
        map.put("4","Libertas, Ancient of Freedom");
        map.put("8","Mammon, Ancient of Greed");
        map.put("12","Fortuna, Ancient of Chance");
        map.put("28","Argaiv, Ancient of Enhancement");
        map.put("3","Solomon, Ancient of Wisdom");
        map.put("29","Juggernaut, Ancient of Momentum");
        map.put("31","Revolc, Ancient of Luck");
    }
}
