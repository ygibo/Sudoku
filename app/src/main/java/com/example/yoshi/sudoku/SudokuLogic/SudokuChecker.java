package com.example.yoshi.sudoku.SudokuLogic;

import android.util.Log;

import com.example.yoshi.sudoku.SudokuInfo;

public class SudokuChecker {
    // 数独の表は3x3のブロックが3行3列に並んでいる。それにより、9x9のマスが並んでいる。
    private int[] rowCoodinate3x3 = { 0, 3, 6, 9  }; // その3x3のブロックの先頭のマスの行番号を表す
    private int[] colmnCoodinate3x3 = { 0, 3, 6, 9 }; // 3x3のブロックの先頭のマスの列番号を表す
    final private int numOfDigit = 9; // マスに入る数値の数
    final int blockWidth = 3; // 3x3ブロックの横幅、つまり３
    final int blockHeight = 3; // 3x3ブロックの縦幅
    final int tableMaxWidthAndHeight = SudokuInfo.MAX_ROW; // 数独表の横幅と縦幅の最大値

    // そのマスで使用できる数字の数を返す
    public int getRestOfNumDigit(int row, int colmn, SudokuLogic logic){
        boolean[] used = new boolean[numOfDigit+1];
        for(int i=0; i<10; ++i) used[i] = false;
        // 3x3のブロックで使用されている数字をチェックする
        check3x3(row, colmn, used, logic);
        // 縦と横のラインで使用されている数字をチェックする
        checkLine(row, colmn, logic, used);
        return numOfDigit - getUsedDigitNum(used);
    }

    // その colmn を含む3x3のブロックの先頭のマスの列番号を返す
    private int getBaseX(int colmn){
        final int rowBlockNum = 3;
        for(int i=0; i < rowBlockNum; ++i)
            if(colmnCoodinate3x3[i] <= colmn && colmn <colmnCoodinate3x3[i+1])
                return colmnCoodinate3x3[i];
        return 0;
    }

    // その row を含む3x3のブロックの先頭のマスの行番号を返す
    private int getBaseY(int row){
        final int colmnBlockNum = 3;
        for(int i=0; i < colmnBlockNum; ++i)
            if(rowCoodinate3x3[i] <= row && row < rowCoodinate3x3[i+1])
                return rowCoodinate3x3[i];
        return 0;
    }

    // row, colmn を含む3x3ブロックで使用されている数字をチェックする関数
    private void check3x3(int row, int colmn, boolean[] used, SudokuLogic logic){
        // row, colmn を含む3x3ブロックの先頭のマスの座標を得る
        int baseX = getBaseX(colmn);
        int baseY = getBaseY(row);


        for(int i=0; i < blockHeight; ++i)
            for(int j=0; j < blockWidth; ++j)
                used[logic.getNumber(baseY + j, baseX + i)] = true;
    }

    // row, colmn を含む表の縦横のラインで使用されている数字をチェックする
    private void checkLine(int row, int colmn, SudokuLogic logic, boolean[] used){

        for(int i=0; i<tableMaxWidthAndHeight; ++i) {
            used[logic.getNumber(row, i)] = true;
            used[logic.getNumber(i, colmn)] = true;
        }
    }

    // 使用されている数字をカウントし、返す
    private int getUsedDigitNum(boolean[] used){
        int cnt = 0;
        for(int i=1; i < used.length; ++i) {
            if (used[i])
                ++cnt;
        }
        return cnt;
    }

    public boolean isValidDigit(int row, int colmn, SudokuLogic logic) {
        return isValidIn3x3(row, colmn, logic) && isValidLine(row, colmn, logic);
    }

    // row, colmn を含む3x3ブロックの中で、マス（row,colmn）の値を使用しているか？
    private boolean isValidIn3x3(int row, int colmn, SudokuLogic logic){
        int baseX = getBaseX(colmn);
        int baseY = getBaseY(row);
        int curNum = logic.getNumber(row, colmn);
        if(curNum == 0)
            return true;
        for(int i = 0; i < blockHeight; ++i) {
            for (int j = 0; j < blockWidth; ++j) {

                int x = baseX + j;
                int y = baseY + i;

                Log.d("base", "i, j => " + i + ", " + j + " +base => " + x + ", " + y);
                if(x == colmn && y == row)
                    continue;
                if(curNum == logic.getNumber(y, x))
                    return false;
            }
        }
        return true;
    }

    // 数独票の縦と横のラインでマス（row, colmn) の値が使われているか？
    private boolean isValidLine(int row, int colmn, SudokuLogic logic) {
        int curNum = logic.getNumber(row, colmn);
        if(curNum==0)
            return true;
        for (int i = 0; i < tableMaxWidthAndHeight; ++i) {
            if (colmn != i && curNum == logic.getNumber(row, i))
                return false;
            if (row != i && curNum == logic.getNumber(i, colmn))
                return false;
        }
        return true;
    }
}
