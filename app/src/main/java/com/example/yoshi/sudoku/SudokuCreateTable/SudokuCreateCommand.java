package com.example.yoshi.sudoku.SudokuCreateTable;

import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;
import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayCommand;

// 数独作成用コマンド
public class SudokuCreateCommand extends SudokuPlayCommand {
    public SudokuCreateCommand(int row, int colmn, int prevNum, int curNum) {
        super(row, colmn, prevNum, curNum);
    }

    public void execute(SudokuLogic logic){
        logic.sendNotChangeNumber(selRow, selCol, currentNumber);
    }

    public void cansel(SudokuLogic logic){
        logic.setChangeableAt(selRow, selCol);
        logic.sendNumber(selRow, selCol, prevNumber);
    }
}
