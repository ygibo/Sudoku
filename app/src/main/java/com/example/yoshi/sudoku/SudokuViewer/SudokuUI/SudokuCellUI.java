package com.example.yoshi.sudoku.SudokuViewer.SudokuUI;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.yoshi.sudoku.SudokuLogic.SudokuChecker;
import com.example.yoshi.sudoku.SudokuLogic.SudokuLogic;


public class SudokuCellUI {
    private int row, colmn;
    private Rect rect = new Rect();

    public SudokuCellUI(int rowNum, int colmnNum){
       set(rowNum, colmnNum, rect);
    }

    public void set(int rowNum, int colmnNum, Rect cellRect){
        row = rowNum;
        colmn = colmnNum;
        rect.set(cellRect);
    }

    public void setCellSize(Rect cellRect){
        rect.set(cellRect);
    }

    public boolean isIn(int x, int y){
        return rect.left < x && x < rect.right && rect.top < y && y < rect.bottom;
    }

    public void draw(Canvas canvas, ColorOfUI cellColor, SudokuLogic logic, boolean isSelect, SudokuChecker checker){
        drawState(canvas, cellColor, logic, isSelect, checker);
        drawNumber(canvas, cellColor, logic);
    }

    private boolean isValidCell(SudokuLogic logic, SudokuChecker checker){
        if(logic.getNumber(row, colmn) == 0 && (checker.getRestOfNumDigit(row, colmn, logic) == 0))
            return false;
        if(!checker.isValidDigit(row, colmn, logic))
            return false;
        return true;
    }

    public void drawState(Canvas canvas, ColorOfUI cellColor, SudokuLogic logic, boolean isSelect, SudokuChecker checker){
        Paint state = new Paint();
        if(isSelect){
            if(!isValidCell(logic, checker))
                state.setColor(cellColor.get("hint0"));
            else
                state.setColor(cellColor.get("selected_cell"));
        }else if(!logic.isChangeAt(row, colmn)){
            state.setColor(cellColor.get("static_cell"));
        }else if(!isValidCell(logic, checker)){
            state.setColor(cellColor.get("hint0"));
        }else if(logic.getNumber(row, colmn) == 0 && checker.getRestOfNumDigit(row, colmn, logic) == 1){
            state.setColor(cellColor.get("hint1"));
        }else if(logic.getNumber(row, colmn) == 0 && checker.getRestOfNumDigit(row, colmn, logic) == 2){
            state.setColor(cellColor.get("hint2"));
        }else{
            state.setColor(cellColor.get("normal_cell"));
        }
        canvas.drawRect(rect, state);
    }

    public void drawNumber(Canvas canvas, ColorOfUI cellColor, SudokuLogic logic){
        int cellNumber = logic.getNumber(row, colmn);
        if(cellNumber==0)
            return;
        int cellWidth = rect.right - rect.left;
        int cellHeight = rect.bottom - rect.top;


        Paint foreground = new Paint();
        foreground.setColor(cellColor.get("foreground"));
        foreground.setStyle(Paint.Style.FILL);
        foreground.setTextSize(cellHeight * 0.75f);
        //foreground.setTextScaleX((width / 3) / (height / 3));
        foreground.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fm = foreground.getFontMetrics();

        float x = cellWidth / 2;
        float y = cellHeight / 2 - (fm.ascent + fm.descent) / 2;

        canvas.drawText(
                Integer.toString(cellNumber),
                rect.left + x,
                rect.top + y, foreground);
    }
}
