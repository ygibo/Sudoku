package com.example.yoshi.sudoku;

import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.ColorOfUI;
import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayUI;

public class SudokuInfo {
    // 数独用のグローバルな情報
    public static int MAX_ROW = 9, MAX_COLMN = 9;

    private static ColorOfUI colors;
    private static SudokuPlayUI tableUI = null;

    public static void setColorOfUI(ColorOfUI colorOfUI){
        colors = colorOfUI;
    }
    public static ColorOfUI getColor(){
        return colors;
    }
    public static void setSudokuTableUI(SudokuPlayUI sudokuPlayUI){
        tableUI = sudokuPlayUI;
    }
    public static SudokuPlayUI getTableUI(){
        return tableUI;
    }
}
