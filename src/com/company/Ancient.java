package com.company;

import java.util.Comparator;

/**
 * Created by Philip on 26-06-2016.
 */
public class Ancient {
    private String name;
    private int id;
    private double level;

    public Ancient(String n, int i, double l) {
        name = n;
        id = i;
        level = l;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double i) {
        level = Math.round(i);
    }

    @Override
    public String toString() {
        return "Name: " + name + ". ID: " + id + ". Level: " + level + ".";
    }

    public static Comparator<Ancient> getComp() {
        Comparator comp = new Comparator<Ancient>() {
            @Override
            public int compare(Ancient a1, Ancient a2) {
                return a1.getName().compareTo(a2.getName());
            }
        };
        return comp;
    }
}
