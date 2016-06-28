package com.company;


/**
 * Created by Philip on 28-06-2016.
 */
public class Outsider {
    private String name;
    private int id;
    private double level;

    public Outsider(String n, int i, double l){
        name = n;
        id = i;
        level = l;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Name: " + name + ". ID: " + id + ". Level: " + level + ".";
    }


}
