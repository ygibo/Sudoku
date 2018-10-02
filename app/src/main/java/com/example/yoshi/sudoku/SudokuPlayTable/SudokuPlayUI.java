package com.example.yoshi.sudoku.SudokuPlayTable;

import android.graphics.Canvas;
import android.util.Log;

import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;
import com.example.yoshi.sudoku.SudokuViewer.SudokuUI.ColorOfUI;
import com.example.yoshi.sudoku.SudokuViewer.SudokuViewer;

import java.util.Vector;

import static com.example.yoshi.sudoku.SudokuInfo.MAX_COLMN;
import static com.example.yoshi.sudoku.SudokuInfo.MAX_ROW;

// 数独プレイ用の UI 入力された数値やコマンドを管理、実行し、また、選択マスの座標を保持する
public class SudokuPlayUI extends SudokuViewer {
    protected int selectRow=0, selectColmn=0; // 現在選択されているマスの行、列

    // 数独プレイ時のコマンドを持つベクトル、コマンドパターンにより操作の取り消しや再実行を行う
    Vector<SudokuPlayCommand> commandVector = new Vector<>(0);
    protected int commandIndex=0;

    public SudokuPlayUI(){
       super();
    }

    public SudokuPlayUI(int tableWidth, int tableHeight, SudokuLogic sudokuLogic) {
        super(tableWidth, tableHeight, sudokuLogic);
    }

    // マスの描画
    @Override
    protected void drawCell(Canvas canvas, ColorOfUI color, SudokuLogic logic){
        for(int i=0; i < MAX_ROW; ++i){
            for(int j=0; j < MAX_COLMN; ++j){
                boolean isSelect;
                if(i == selectRow && j == selectColmn)
                    isSelect = true;
                else
                    isSelect = false;
                // マスの描画、そのマスが選択されているかを関数に渡す
                cellUI[i * MAX_ROW + j].draw(canvas, color, logic, isSelect, checker);
            }
        }
    }

    // 座標(x, y) が押されたときに、そこにあるマスを選択する
    public void push(int x, int y){
        for(int i = 0; i < MAX_ROW; ++i){
            for(int j=0; j < MAX_COLMN; ++j){
                if(cellUI[i * MAX_COLMN + j].isIn(x,y)){
                    selectRow = i;
                    selectColmn = j;
                }
            }
        }
    }

    // コマンドの実行
    protected void executeCommand(int nextNumber){
        if(commandIndex >= commandVector.size())
            commandVector.add(new SudokuPlayCommand(selectRow, selectColmn, logic.getNumber(selectRow,selectColmn), nextNumber));
        else
            commandVector.elementAt(commandIndex).set(selectRow, selectColmn, logic.getNumber(selectRow, selectColmn), nextNumber);

        commandVector.elementAt(commandIndex).execute(logic);
        ++commandIndex;
    }

    // 入力された数値が送られてきたとき、変更可能なマスならコマンドを発行し、実行する
    public void sendNumber(int number){
        if(!logic.isChangeAt(selectRow, selectColmn) || number == logic.getNumber(selectRow, selectColmn))
            return ;
        executeCommand(number);
    }

    // コマンドベクトルに保存されている現在のコマンドを実行する
    public void next(){
        if(commandIndex >= commandVector.size())
            return ;
        commandVector.elementAt(commandIndex).execute(logic);
        ++commandIndex;
    }

    // コマンドベクトルに保存されている直前に実行されたコマンドを取り消す
    public void back(){
        if(commandIndex<=0)
            return ;
        commandIndex--;
        commandVector.elementAt(commandIndex).cansel(logic);
    }
}

