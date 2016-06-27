package com.company;

import dnl.utils.text.table.TextTable;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Ancient> oldLevels;
    private static List<Ancient> newLevels;
    public static SaveDecoder DECODER;

    public static void main(String[] args) {
        DECODER = new SaveDecoder();
        Scanner scanner = new Scanner(System.in);
        String code;
        while(DECODER.getAncients() == null) {
            System.out.println("Paste your save string.");
            code = scanner.nextLine().trim();
            DECODER = new SaveDecoder(code);
        }

        oldLevels = new Ancients().getAncients();
        newLevels = new Calculator(DECODER.getHZE(), DECODER.getTP()).getNewLevels();
        printNewLevels2();
    }

    private static void printNewLevels(){
        for(int i = 0; i < oldLevels.size(); i++){
            Ancient oldL = oldLevels.get(i);
            Ancient newL = newLevels.get(i);
            System.out.println(oldL.getName()+" is level "+ oldL.getLevel()+". Suggested level is: " + newL.getLevel()+". Difference: " + (newL.getLevel()-oldL.getLevel()));
        }
    }

    private static void printNewLevels2(){
        String columnNames[] = {"Ancient", "Current level", "Suggested level", "Difference"};
        Object[][] data = new Object[oldLevels.size()][4];
        for(int i = 0; i < oldLevels.size(); i++){
            Ancient oldL = oldLevels.get(i);
            Ancient newL = newLevels.get(i);
            data[i][0] = oldL.getName();
            data[i][1] = oldL.getLevel();
            data[i][2] = newL.getLevel();
            data[i][3] = newL.getLevel()-oldL.getLevel();
            //System.out.println(oldL.getName()+" "+ oldL.getLevel()+" -> " + newL.getLevel()+". Difference: " + (newL.getLevel()-oldL.getLevel()));
        }
        TextTable tt = new TextTable(columnNames, data);
        tt.printTable();

    }
}
