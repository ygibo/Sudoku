package com.example.yoshi.sudoku.SudokuCreateTable;

import com.example.yoshi.sudoku.SudokuPlayTable.SudokuPlayUI;

import java.util.Vector;

// 数独作成用 UI 、SudokuPlayUI を継承することで途中でプレイ用に切り替えることが可能
public class SudokuCreateUI extends SudokuPlayUI {
    // コマンドパターンにより操作の取り消し、再実行をサポートする
    private Vector<SudokuCreateCommand> createCommands = new Vector<>(0); // 数独作成用のコマンドを保持するベクター
    private int createCommandIndex = 0; // 直前に実行したコマンドの位置 + 1 を現す

    // コマンドの実行
    protected void executeCommand(int nextNumber, boolean createMode) {
        if(!createMode){ // プレイモードのとき、親クラスのSudokuPlayUIでコマンドを実行
            super.executeCommand(nextNumber);
            return;
        }

        // 作成用コマンドの追加と設定
        if (createCommandIndex >= createCommands.size()) {
            createCommands.add(new SudokuCreateCommand(selectRow, selectColmn, logic.getNumber(selectRow, selectColmn), nextNumber));
        }else
            createCommands.elementAt(createCommandIndex).set(selectRow, selectColmn, logic.getNumber(selectRow, selectColmn), nextNumber);

        // 作成用コマンドの実行
        createCommands.elementAt(createCommandIndex).execute(logic);
        ++createCommandIndex;
    }

    // 入力として数値が送られてきたとき、数独の表の値を更新する
    public void sendNumber(int number, boolean createMode) {
        if (!createMode) { // プレイモードのとき
            super.sendNumber(number);
            return;
        }

        // 現在のマスの値と同じならそのままにする
        if (number == logic.getNumber(selectRow, selectColmn))
            return;

        // 値変更のコマンドを設定、実行する
        executeCommand(number, createMode);
    }

    // コマンドの再実行
    public void next(boolean createMode) {
        if (!createMode) {
            super.next();
            return;
        }
        if (createCommandIndex >= createCommands.size())
            return;
        createCommands.elementAt(createCommandIndex).execute(logic);
        ++createCommandIndex;
    }

    // コマンドの取り消し
    public void back(boolean createMode) {
        if (!createMode) {
            super.back();
            return;
        }
        if (createCommandIndex <= 0)
            return;
        --createCommandIndex;
        createCommands.elementAt(createCommandIndex).cansel(logic);
    }

    public void reset(){
        commandIndex = 0;
    }
}
