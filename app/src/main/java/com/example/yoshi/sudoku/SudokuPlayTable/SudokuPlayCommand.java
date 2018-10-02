package com.example.yoshi.sudoku.SudokuPlayTable;

import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;

// 数独プレイ時のコマンド
public class SudokuPlayCommand {
    protected int selRow, selCol; // 実行時の選択マス
    protected int prevNumber; // 直前の値
    protected int currentNumber; // 実行後の値

    public SudokuPlayCommand(int row, int colmn, int prevNum, int curNum) {
        set(row, colmn, prevNum, curNum);
    }

    public void set(int row, int colmn, int prevNum, int curNum) {
        selRow = row;
        selCol = colmn;
        prevNumber = prevNum;
        currentNumber = curNum;
    }

    // コマンドの実行
    public void execute(SudokuLogic logic) {
        logic.sendNumber(selRow, selCol, currentNumber);
    }

    // コマンドの取り消し
    public void cansel(SudokuLogic logic) {
        logic.sendNumber(selRow, selCol, prevNumber);
    }

}
