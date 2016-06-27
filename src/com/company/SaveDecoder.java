package com.company;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * Created by Philip on 26-06-2016.
 */
public class SaveDecoder {
    private final String ANTI_CHEAT_CODE = "Fe12NAfA3R6z4k0z";
    private final String SALT = "af0ik392jrmt0nsfdghy0";

    private String importedCode;
    private JSONObject decryptedObj;

    public SaveDecoder(String code){
        importedCode = code;
        decryptedObj = decryptSave(importedCode);
    }

    public SaveDecoder(){

    }


    private JSONObject decryptSave(String saveCode){
        if (saveCode.equals(ANTI_CHEAT_CODE)) {
            System.out.println("Invalid save string!");
            return null;
        }
        Boolean b = saveCode.contains(ANTI_CHEAT_CODE);

        if (b) {
            try {
                saveCode = fromAntiCheatFormat(saveCode);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid save string");
                return null;
            }
        }
        String s = new String(Base64.getDecoder().decode(saveCode));
        try {
            JSONObject obj = new JSONObject(s);
            return obj;
        } catch(JSONException e){
            System.out.println("Invalid save string!");
            return null;
        }
    }

    public JSONObject getAncients(){
        if(decryptedObj == null)
            return null;
        return decryptedObj.getJSONObject("ancients").getJSONObject("ancients");
    }

    public int getHZE(){
        return Integer.parseInt(decryptedObj.get("highestFinishedZonePersist").toString());
    }

    public double getTP(){
        double asTotal = decryptedObj.getDouble("ancientSoulsTotal");
        double phanLevel = decryptedObj.getJSONObject("outsiders").getJSONObject("outsiders").getJSONObject("3").getDouble("level");

        return 50-49*Math.pow(Math.E,(-asTotal/10000))+50*(1-Math.pow(Math.E,(-phanLevel/1000)));
    }

    private String fromAntiCheatFormat(String saveCode){
        String[] elements = saveCode.split(ANTI_CHEAT_CODE);
        String data = unSprinkle(elements[0]);
        String hash = elements[1];
        String dataHash = getHash(data);
        if (dataHash.equals(hash))
        return data;
        return data;
    }

    private String unSprinkle(String s){
        String[] arr = s.split("");
        ArrayList<String> res = new ArrayList<>();
        int counter = 0;

        while (counter < arr.length){
            res.add(counter/2, arr[counter]);
            counter += 2;
        }
        String str = "";
        for(String st : res){
            str += st;
        }
        return str;
    }

    private String getHash(String saveCode){
        char[] characters = saveCode.toCharArray();
        Arrays.sort(characters);
        String s = String.valueOf(characters)+SALT;
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            String hex = (new HexBinaryAdapter()).marshal(md.digest(s.getBytes()));
            return hex;
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

}
