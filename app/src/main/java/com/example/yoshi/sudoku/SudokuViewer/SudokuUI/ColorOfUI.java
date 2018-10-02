package com.example.yoshi.sudoku.SudokuViewer.SudokuUI;

import java.util.Vector;

public class ColorOfUI {
    Vector<String> names = new Vector<String>();
    Vector<Integer> colors = new Vector<Integer>();

    public ColorOfUI(){}

    public void add(String name, int color){
        names.add(name);
        colors.add(color);
    }

    public int get(String name){
        for(int i=0; i<names.size(); ++i){
            if(names.elementAt(i).equals(name)){
                return colors.elementAt(i);
            }
        }
        return 0;
    }
}
