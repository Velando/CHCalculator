package com.company;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Philip on 26-06-2016.
 */
public class Calculator {
    private ArrayList<Ancient> newLevels = new ArrayList<>();
    private double siyaLevel = 0.0;
    private double baseScale = 1.145;
    private int HZE;
    private double TP;
    private ArrayList<Ancient> ancients = new Ancients().getAncients();


    public Calculator(int zone, double trans){
        HZE = zone;
        TP = trans/100.0;
        calc();
    }

    public void calc(){
        for (Ancient a : ancients){
            if(a.getName().startsWith("Siyalatas")) {
                siyaLevel = a.getLevel();
            }
        }
        for(Ancient a : ancients){
            a.setLevel(calcAll(a));
        }
        newLevels = ancients;
    }


    public ArrayList<Ancient> getNewLevels(){
        Collections.sort(newLevels, Ancient.getComp());
        return newLevels;
    }




    private double calcAlpha(){
        double scale = calcScale();
        double a = 1.4067*Math.log(1+TP);
        double b = Math.log(scale);
        return a/b;
        //return 1.4067*Math.log(1+TP)/Math.log(calcScale());
    }

    private double calcScale(){
        double x = HZE/500;
        double scale = baseScale+(x*0.005);
        return scale;
    }

    private double calcArgaiv(double l){
        return siyaLevel;
    }

    private double calcMorgulis(double l){
        return Math.pow(siyaLevel,2.0);
    }

    private double calcBubos(double l){
        double a = 2.8*Math.log(siyaLevel);
        double b = 1.4*Math.log(1+(Math.pow(Math.E,-0.02*l)));
        double c = 5.94;
        double res = a-b-c;
        return res;

        //return (2.8*Math.log(siyaLevel))-(1.4*Math.log(1+Math.pow(Math.E,(-0.02*l))))-5.94;
    }

    private double calcChronos(double l){
        return 2.75*Math.log(siyaLevel)-(1.375*Math.log(2.0-Math.pow(Math.E,(-0.034*l))))-5.1;
    }

    private double calcLibertas(double l){
        return 0.926*siyaLevel;
    }

    private double calcMammon(double l){
        return 0.926*siyaLevel;
    }

    private double calcMimzee(double l){
        return 0.926*siyaLevel;
    }

    private double calcDora(double l){
        double a = 2.877*Math.log(siyaLevel);
        double b = 1.4365*Math.log(100.0/99.0-(Math.pow(Math.E,-0.002*l)));
        double c = 9.63;
        double res = a-b-c;
        return res;
        //return 2.877*Math.log(siyaLevel)-(1.4365*Math.log((100.0/99.0)-Math.pow(Math.E,(-0.002*l))))-9.63;
    }

    private double calcDogcog(double l){
        double a = 2.844*Math.log(siyaLevel);
        double b = 1.422*Math.log(1.0/99.0+(Math.pow(Math.E,-0.01*l)));
        double c = 7.232;
        double res = a-b-c;
        return res;
        //return 2.844*Math.log(siyaLevel)-(1.422*Math.log((1/99)+Math.pow(Math.E,(-0.01*l))))-7.232;
    }

    private double calcFortuna(double l){
        double a = 2.875*Math.log(siyaLevel);
        double b = 1.422*Math.log(10.0/9.0-(Math.pow(Math.E,-0.0025*l)));
        double c = 9.3;
        double res = a-b-c;
        return res;
        //return 2.875*Math.log(siyaLevel)-(1.4375*Math.log((10/9)-Math.pow(Math.E,(-0.0025*l))))-9.3;
    }

    private double calcSolomon(double l){
        return (Math.pow(siyaLevel,0.8))/(Math.pow(calcAlpha(),0.4));
    }

    private double calcAtman(double l){
        double alpha = calcAlpha();
        double a = 2.832*Math.log(siyaLevel);
        double b = 1.416*Math.log(alpha);
        double c = 1.416*Math.log(4.0/3.0-(Math.pow(Math.E,-0.013*l)));
        double d = 6.613;
        double res = a-b-c-d;
        return res;

        //return 2.832*Math.log(siyaLevel)-(1.416*Math.log(calcAlpha()))-(1.416*Math.log((4/3)-Math.pow(Math.E,(-0.013*l))))-6.613;
    }

    private double calcKuma(double l){
        double alpha = calcAlpha();
        double a = 2.844*Math.log(siyaLevel);
        double b = 1.422*Math.log(alpha);
        double c = 1.422*Math.log(0.25+(Math.pow(Math.E,-0.01*l)));
        double d = 7.014;
        double res = a-b-c-d;
        return res;

        //return 2.844*Math.log(siyaLevel)-(1.422*Math.log(calcAlpha()))-(1.422*Math.log(0.25+Math.pow(Math.E,(-0.01*l))))-7.014;
    }



    private double calcAll(Ancient a){
        String ancientName = a.getName().split(",")[0];
        double newLevel = 0.0;
        switch (ancientName){
            case "Siyalatas": newLevel = siyaLevel;
                break;
            case "Argaiv": newLevel = calcArgaiv(a.getLevel());
                break;
            case "Morgulis": newLevel = calcMorgulis(a.getLevel());
                break;
            case "Bubos": newLevel = calcBubos(a.getLevel());
                break;
            case "Chronos": newLevel = calcChronos(a.getLevel());
                break;
            case "Libertas": newLevel = calcLibertas(a.getLevel());
                break;
            case "Mammon": newLevel = calcMammon(a.getLevel());
                break;
            case "Mimzee": newLevel = calcMimzee(a.getLevel());
                break;
            case "Dora": newLevel = calcDora(a.getLevel());
                break;
            case "Dogcog": newLevel = calcDogcog(a.getLevel());
                break;
            case "Fortuna": newLevel = calcFortuna(a.getLevel());
                break;
            case "Solomon": newLevel = calcSolomon(a.getLevel());
                break;
            case "Atman": newLevel = calcAtman(a.getLevel());
                break;
            case "Kumawakamaru": newLevel = calcKuma(a.getLevel());
                break;
        }
        return newLevel;
    }

    private double getChorEff(double level){
        return 1.0-Math.pow(0.95,level);
    }

    private double siyaCost(double currLevel, double newLevel){
        double cost = 0.0;
        for(double n = currLevel+1.0; n <= newLevel; n++){
            cost+=n;
        }
        return cost;
    }

    private double bubosCost(double currLevel, double newLevel){
        double cost = 0.0;
        for(double n = currLevel+1.0; n <= newLevel; n++){
            cost+=4.0*Math.pow(2.0,n-1.0);
        }
        return cost;
    }

    private double solomonCost(double currLevel, double newLevel){
        double cost = 0.0;
        for(double n = currLevel+1.0; n <= newLevel; n++){
            cost+=Math.pow(n,1.5);
        }
        return cost;
    }

    public int calcCost(Ancient a, double chorLevel, double newLevel){
        String ancientName = a.getName().split(",")[0];
        double cost = 0.0;
        switch (ancientName){
            case "Siyalatas": cost = siyaCost(a.getLevel(), newLevel);
                break;
            case "Argaiv": cost = siyaCost(a.getLevel(), newLevel);
                break;
            case "Morgulis": cost = newLevel-a.getLevel();
                break;
            case "Bubos": cost = bubosCost(a.getLevel(), newLevel);
                break;
            case "Chronos": cost = bubosCost(a.getLevel(), newLevel);
                break;
            case "Libertas": cost = siyaCost(a.getLevel(), newLevel);
                break;
            case "Mammon": cost = siyaCost(a.getLevel(), newLevel);
                break;
            case "Mimzee": cost = siyaCost(a.getLevel(), newLevel);
                break;
            case "Dora": cost = bubosCost(a.getLevel(), newLevel);
                break;
            case "Dogcog": cost = bubosCost(a.getLevel(), newLevel);
                break;
            case "Fortuna": cost = bubosCost(a.getLevel(), newLevel);
                break;
            case "Solomon": cost = solomonCost(a.getLevel(), newLevel);
                break;
            case "Atman": cost = bubosCost(a.getLevel(), newLevel);
                break;
            case "Kumawakamaru": cost = bubosCost(a.getLevel(), newLevel);
                break;
        }
        return (int) (cost*(1-getChorEff(chorLevel)));
    }

/**
    private double getDamageFactor(){
        return Math.pow(0.007368,Math.log(1.07)/(15.0*Math.log(10)))*1.07;
    }

    private double getDamageBoost(){
        Ancient morg = null;
        Ancient siya = null;
        Ancient argaiv = null;
        Ancient chronos = null;
        Ancient bubos = null;
        for(Ancient a : ancients){
            if(a.getName().startsWith("Morg"))
                morg = a;
            else if(a.getName().startsWith("Siya"))
                siya = a;
            else if(a.getName().startsWith("Argaiv"))
                argaiv = a;
            else if(a.getName().startsWith("Chronos"))
                chronos = a;
            else if(a.getName().startsWith("Bubos"))
                bubos = a;
        }
        return Math.pow(Math.E,Math.log(morg.getLevel())+Math.log(siya.getLevel())+Math.log(argaiv.getLevel())-Math.log(1.0+Math.pow(Math.E,-0.02*bubos.getLevel()))+Math.log(2.0-Math.pow(Math.E,-0.034*chronos.getLevel())));
    }

    private double getGoldBoost(){
        Ancient lib = null;
        Ancient mam = null;
        Ancient mim = null;
        Ancient dor = null;
        Ancient dog = null;
        Ancient fort = null;
        for(Ancient a : ancients){
            if(a.getName().startsWith("Lib"))
                lib = a;
            else if(a.getName().startsWith("Mam"))
                mam = a;
            else if(a.getName().startsWith("Dor"))
                dor = a;
            else if(a.getName().startsWith("Dog"))
                dog = a;
            else if(a.getName().startsWith("Mim"))
                mim = a;
            else if(a.getName().startsWith("Fort"))
                fort = a;
        }
        return Math.pow(Math.E,Math.log(lib.getLevel())+Math.log(mam.getLevel())+Math.log(mim.getLevel())+Math.log(100.0-99.0*+Math.pow(Math.E,-0.002*dor.getLevel()))-Math.log(1.0+99.0-Math.pow(Math.E,-0.01*dog.getLevel()))+Math.log(10.0-9.0*Math.pow(Math.E,-0.0025*fort.getLevel())));
    }**/
}
