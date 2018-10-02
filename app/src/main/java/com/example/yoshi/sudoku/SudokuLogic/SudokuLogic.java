package com.example.yoshi.sudoku.SudokuLogic;

import android.util.Log;

import static com.example.yoshi.sudoku.SudokuInfo.MAX_COLMN;
import static com.example.yoshi.sudoku.SudokuInfo.MAX_ROW;

// 数独の表の変更可能、不可能な値の情報を管理する
public class SudokuLogic {
    protected int table[]; // 数独の表の数値を保存
    protected boolean isChange[]; // 変更可能かを保存

    public SudokuLogic(){ }

    public SudokuLogic(String problem){
            table = new int[MAX_ROW * MAX_COLMN];
            isChange = new boolean[MAX_ROW * MAX_COLMN];
            initTable(problem);
    }

    protected void initTable(String problem) {
        Log.d("problem", problem);
        int problemIndex = 0;
        for (int i = 0; i < MAX_ROW; ++i) {
            for (int j = 0; j < MAX_COLMN; ++j) {
                int digit = Character.getNumericValue(problem.charAt(problemIndex));
                table[i * MAX_COLMN + j] = digit;
                if(digit==0)
                    isChange[i * MAX_COLMN + j] = true;
                else
                    isChange[i * MAX_COLMN +j ] = false;
                ++problemIndex;
            }
        }
    }

    public void reset(){
        for(int row = 0; row < MAX_ROW; ++row){
            for(int colmn = 0; colmn < MAX_COLMN; ++colmn){
                if(isChange[getIndex(row, colmn)])
                    table[getIndex(row, colmn)] = 0;
            }
        }
    }

    protected int getIndex(int row, int colmn){
        return row * MAX_COLMN + colmn;
    }

    // 値の変更
    public void sendNumber(int row, int colmn, int number){
        if(isChange[getIndex(row, colmn)])
            table[getIndex(row, colmn)] = number;
    }

    // 不変な値の設定
    public void sendNotChangeNumber(int row, int colmn, int number) {
        table[getIndex(row, colmn)] = number;
        isChange[getIndex(row,colmn)] = false;
    }

    // そのマスを変更可能にする
    public void setChangeableAt(int row, int colmn){
        isChange[getIndex(row, colmn)] = true;
    }

    // そのマスの値を得る
    public int getNumber(int row, int colmn){
        return table[getIndex(row, colmn)];
    }

    // そのマスが変更可能か？
    public boolean isChangeAt(int row, int colmn){
        return isChange[getIndex(row, colmn)];
    }

    // 現在の状態を文字列として得る
    public String toString(){
        String ret = new String("");
        for(int i=0; i < table.length; ++i){
            ret += Integer.toString(table[i]);
        }
        return ret;
    }
}
